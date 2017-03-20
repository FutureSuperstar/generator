package com.xyan.generator;

public class Column {
	
	private String javaType;
	private String shortJavaType;
	private String dbType;	
	private String columnName;
	private String fieldName;
	private String fieldNameF;//首字母大写的属性
	private String comment;//注释
	private boolean isPK;
	
	public Column() {
	}
	
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
		this.shortJavaType=GenerateUtil.shortName(javaType);
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		this.fieldNameF=GenerateUtil.firstUpper(fieldName);
	}
	public boolean isPK() {
		return isPK;
	}
	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}
	
	public String getShortJavaType() {
		return shortJavaType;
	}

	public void setShortJavaType(String shortJavaType) {
		this.shortJavaType = shortJavaType;
	}

	public String getFieldNameF() {
		return fieldNameF;
	}

	public void setFieldNameF(String fieldNameF) {
		this.fieldNameF = fieldNameF;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Column [javaType=" + javaType + ", dbType=" + dbType
				+ ", columnName=" + columnName + ", fieldName=" + fieldName + ",comment="+comment+", isPK=" + isPK + "]";
	}

	
}
