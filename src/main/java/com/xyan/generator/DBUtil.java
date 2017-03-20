package com.xyan.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtil {
	
	private DBUtil(){}
	
	private static DBUtil dbUtil;
	private static Properties properties;
	public static DBUtil getInstance(){
		if(dbUtil==null){
			System.out.println("读取数据库属性-------------------start---------------------->");
			dbUtil=new DBUtil();
			properties=new Properties();
			ResourceBundle bundle=ResourceBundle.getBundle("application");
			properties.setProperty("user",bundle.getString("jdbc.user"));    //设置Properties对象属性
			properties.setProperty("password",bundle.getString("jdbc.password"));
			properties.setProperty("url",bundle.getString("jdbc.url"));
			properties.setProperty("schema", bundle.getString("jdbc.schema"));
			properties.setProperty("tables", bundle.getString("tables"));
			System.out.println("读取数据库属性-------------------end---------------------->");
		}
		return dbUtil;
	}
	
	private Connection initConnection(){
		System.out.println("获取数据库连接-------------------start---------------------->");
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));
		} catch (SQLException e) {
			System.err.println("获取数据库连接-------------------fail---------------------->");
		}
		System.out.println("获取数据库连接-------------------end---------------------->");
		return connection;
	}
	
	public List<Table> getTables(SQLGenerator sqlGenerator) throws SQLException{
		Connection connection=this.initConnection();
		String[] tableNames=properties.getProperty("tables").split(",");
		List<Table> results=new ArrayList<Table>();
		for (String tableName : tableNames) {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlGenerator.getTable(properties.getProperty("schema"),tableName));
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()){//表
				Table table=new Table();
				table.setTableName(tableName);
				table.setTableComment(rs.getString("TABLE_COMMENT"));
				PreparedStatement columnSt=connection.prepareStatement(sqlGenerator.getColumn(properties.getProperty("schema"),tableName));
				ResultSet columnRs=columnSt.executeQuery();
				while(columnRs.next()){//列
					Column column=new Column();
					column.setColumnName("xxx");
					String columnName=columnRs.getString("COLUMN_NAME");
					column.setColumnName(columnName);
					String columnComment=columnRs.getString("COLUMN_COMMENT");
					column.setComment(columnComment);
					String dataType=columnRs.getString("DATA_TYPE");
					String columnType=columnRs.getString("COLUMN_TYPE");
					column.setJavaType(GenerateUtil.type(dataType,columnType));
					column.setFieldName(GenerateUtil.lowerUpper(GenerateUtil.hump(columnName)));
					String columnKey=columnRs.getString("COLUMN_KEY");
					column.setPK("PRI".equalsIgnoreCase(columnKey));
					table.addColumn(column);
				}
				results.add(table);
			}else{
				System.err.println("表"+tableName+"不存在");
			}
		}
		return results;
	}
	
}
