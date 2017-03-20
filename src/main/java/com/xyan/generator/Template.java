package com.xyan.generator;

/**
 * 模板
 * @author wangming
 */
public class Template {
	
	private String srcPath;
	private String destPath;
	private String fileName;
	
	public Template() {
	}
	
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getDestPath() {
		return destPath;
	}
	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
