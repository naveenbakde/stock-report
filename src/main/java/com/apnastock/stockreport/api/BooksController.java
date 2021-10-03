package com.apnastock.stockreport.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnastock.stockreport.service.BooksService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BooksController {

	private final BooksService booksService;
	
	@GetMapping("/init")
	public ResponseEntity<String> initData() {
		log.info("---------------Init Books service called------------");
		booksService.initData();
		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("findBooks")
	public ResponseEntity<List<String>> findBooks() {
		log.info("---------------Find Books service called------------");
		return ResponseEntity.ok(booksService.getBooks());
	}
}
