package com.apnastock.stockreport.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apnastock.stockreport.service.Processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StockBooksRepo {
	
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<String> queryListOfBooks(Processor<String> processor) {
		processor.preProcess(getHeaders());
		log.info("Repo executing -------");
		namedParameterJdbcTemplate.queryForStream("select bookId as bookId, bookName as bookName from books", Collections.emptyMap(), new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				StringBuffer sb = new StringBuffer();
				String dataString = sb.append(rs.getString(1)).append("|").append(rs.getString(2)).toString();
				return dataString;
			}
		}).forEach(row -> processor.process(row));
		
		return Collections.emptyList();
	}
	
	public List<String> getHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.addAll(Arrays.asList("BookId", "BookName"));
		return headers;
	}
	
	public void initData() {
		for(int i = 1001; i <= 2000; i++) {
			jdbcTemplate.update("insert into books (bookId, bookName) values (?, ?)", i, "book"+i);
		}
	}

}
