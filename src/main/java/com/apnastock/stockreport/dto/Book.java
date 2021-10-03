package com.apnastock.stockreport.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {

	private Integer bookId;
	private String bookName;
	
}
