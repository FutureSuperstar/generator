package com.xyan.generator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectConfig {
	
	public static String outPath;
	public static List<Template> templates;
	public static String email;
	public static String user;
	public static String date;
	
	static{
		ResourceBundle bundle=ResourceBundle.getBundle("application");
		outPath=bundle.getString("outPath");
		String[] templateArr=bundle.getString("templates").split(",");
		templates=new ArrayList<Template>();
		for (String template : templateArr) {
			Template temp=new Template();
			String str=template.split(":")[0];
			temp.setDestPath(str);//生成的路径
			temp.setFileName(template.split(":")[1]);//生成的文件名
			temp.setSrcPath(template.split(":")[2]);//源文件
			templates.add(temp);
		}
		
		email=bundle.getString("java.email");
		user=bundle.getString("java.user");
		date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}	
