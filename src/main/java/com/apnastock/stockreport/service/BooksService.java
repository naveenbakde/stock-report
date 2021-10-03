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

	public Boolean getBooks() {
		return repository.queryListOfBooks(new ExcelProcessorImpl() {
			private List<String> books = new ArrayList<String>();

			@Override
			public Boolean process(String book) {
				if (book != null && !book.equals("")) {
					books.add(book);
					if (books.size() > 500)
						return postProcess(books);
					else 
						return true;
				} 
				return postProcess(books);
			}

		});
	}

}