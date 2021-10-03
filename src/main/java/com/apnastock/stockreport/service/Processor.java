package com.apnastock.stockreport.service;

import java.util.List;

@FunctionalInterface
public interface Processor<T> {

	default void preProcess(T t) {}
	default void preProcess(List<T> t) {}

	void process(T t);
	default void process(List<T> t) {}

	default void postProcess(List<T> t) {}
	default void postProcess(T t) {}

}
