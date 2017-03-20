package com.xyan.generator;

public class MysqlSQLGenerator implements SQLGenerator{

	public String getTable(String schema,String... tableNames) {
		if(tableNames.length==1){
			return "select table_name,table_comment from information_schema.tables  where table_schema = '"+schema+"' and table_name in ('"+tableNames[0]+"')";
		}else{
			StringBuilder sql=new StringBuilder();
			sql.append("select table_name,table_comment from information_schema.tables  where table_schema = '"+schema+"' and table_name in (");
			sql.append(tableNames[0]);
			for (int i=1;i<tableNames.length;i++) {
				sql.append(",'"+tableNames[i]+"'");
			}
			sql.append(")");
			return sql.toString();
		}
	}

	public String getColumn(String schema,String tableName) {
		return "select * from information_schema.columns where table_schema = '"+schema+"'  and table_name = '"+tableName+"'";
	}

}
