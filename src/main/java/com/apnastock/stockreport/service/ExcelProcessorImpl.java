package com.apnastock.stockreport.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ExcelProcessorImpl implements Processor<String> {

	private static final String FILE_NAME = "E:/data/test.xls";
	private List<String> headers;
	private Workbook workbook;
	private int count = 0;

	@Override
	public void preProcess(List<String> headers) {
		this.headers = headers;
		try (Workbook workbook = WorkbookFactory.create(false)) {
			this.workbook = workbook;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean postProcess(List<String> books) {
		try (FileOutputStream fileout = new FileOutputStream(FILE_NAME)) {
			Sheet sheet = workbook.createSheet();
			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < headers.size(); i++) {
				headerRow.createCell(i).setCellValue(headers.get(i));
			}
			for (int i = 0; i < books.size(); i++) {
				Row row = sheet.createRow(i+1);
				String[] rowData = books.get(i).split(Pattern.quote("|"));
				for (int j = 0; j < rowData.length; j++) {
					row.createCell(j).setCellValue(rowData[j]);
				}
			}
			workbook.write(fileout);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("processing book------> {} ", count++);
		books.clear();
		return true;
	}
}
