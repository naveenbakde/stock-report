package com.apnastock.stockreport.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DatasourceConfig {
	
	private final DataSource dataSource;
	
	@Bean
	public JdbcTemplate jdbcTemplate() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		log.info("Datasource ------------> {} ", dataSource.getConnection().getMetaData().getUserName());
		jdbcTemplate.setFetchSize(10);
		return jdbcTemplate;
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}

}
