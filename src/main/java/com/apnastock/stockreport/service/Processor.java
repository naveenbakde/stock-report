package com.apnastock.stockreport.service;

import java.util.List;

@FunctionalInterface
public interface Processor<T> {

	default void preProcess(T t) {
	}

	default void preProcess(List<T> t) {
	}

	Boolean process(T t);

	default Boolean process(List<T> t) {
		return Boolean.TRUE;
	}

	default Boolean postProcess(List<T> t) {
		return Boolean.TRUE;
	}

	default Boolean postProcess(T t) {
		return Boolean.TRUE;
	}

}
