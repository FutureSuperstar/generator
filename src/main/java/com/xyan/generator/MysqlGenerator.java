package com.xyan.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class MysqlGenerator implements Generator{
	
	private List<Table> tables;
	
	public MysqlGenerator() {
	}

	public boolean generate() {
		System.out.println("====================生成文件===============start========================");
		SQLGenerator sqlGenerator=new MysqlSQLGenerator();
		try {
			tables=DBUtil.getInstance().getTables(sqlGenerator);
			System.err.println("开始写入文件");
			writeFile(tables);
		} catch (SQLException e) {
			return false;
		}
		System.out.println("====================生成文件=================end======================");
		return true;
	}

	private void writeFile(List<Table> tables) {
		
		 VelocityEngine ve = new VelocityEngine();
		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		 
		 ve.init();
		 VelocityContext ctx = new VelocityContext();
		 ctx.put("user", ProjectConfig.user);
		 ctx.put("date", ProjectConfig.date);
		 ctx.put("email", ProjectConfig.email);
		 for (Table table : tables) {
			System.out.println("元信息："+table.toString());
			ctx.put("table", table);
			
			List<com.xyan.generator.Template> outFiles=ProjectConfig.templates;
			for (com.xyan.generator.Template outTemp : outFiles) {
				System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
				Template template = ve.getTemplate(outTemp.getSrcPath(),"utf-8");
				//要生成的文件：输出路径+模块+文件名
				File file=new File(ProjectConfig.outPath+"\\"+outTemp.getFileName().replaceAll("xxx", table.getModelName()));
				try {
					if(!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
						file.createNewFile();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("生成文件："+file.getAbsolutePath());
				Writer writer;
				try {
					writer = new FileWriter(file);
					merge(template, ctx,writer);
				} catch (Exception e) {
					System.err.println("文件"+file.getAbsolutePath()+"生成失败！");
					e.printStackTrace();
				}
				System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
			}
		}
	}
	
	private void merge(Template template,VelocityContext ctx,Writer writer){
		 template.merge(ctx, writer);
		 try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
