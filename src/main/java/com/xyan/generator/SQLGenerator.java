package com.xyan.generator;

public interface SQLGenerator {
	
	String getTable(String schema,String...tableNames);
	
	String getColumn(String schema,String tableName);
}
