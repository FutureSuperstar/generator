package com.xyan.generator;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private String tableName;//表名
	private String tableComment;//注释
	private List<Column> columns;//列
	private String modelName;//model名
	private String packageName;//包路径
	
	public Table() {
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
		this.modelName=GenerateUtil.hump(tableName);
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void addColumn(Column column) {
		if(columns==null){
			columns=new ArrayList<Column>();
		}
		columns.add(column);
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "Table [tableName=" + tableName + ", tableComment=" + tableComment + ", columns=" + columns
				+ ", modelName=" + modelName + ", packageName=" + packageName + "]";
	}
	
}
