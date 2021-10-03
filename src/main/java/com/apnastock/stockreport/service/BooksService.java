package com.apnastock.stockreport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apnastock.stockreport.repo.StockBooksRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BooksService {

	private final StockBooksRepo repository;

	public void initData() {
		log.info("-----------------Init data-------------");
		repository.initData();
	}

	public List<String> getBooks() {

		return repository.queryListOfBooks(new ExcelProcessorImpl() {
			private List<String> books = new ArrayList<String>();
			
			@Override
			public void process(String book) {
				books.add(book);
				if (books.size() > 500)
					postProcess(books);
			}
		});
	}

}