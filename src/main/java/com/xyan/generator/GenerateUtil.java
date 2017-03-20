package com.xyan.generator;

public class GenerateUtil {
	
	/**
	 * 转成驼峰命名
	 * @param oldName 原来的名字
	 * @return
	 */
	public static String hump(String oldName){
		if(isBlank(oldName)){
			return null;
		}else{
			String[] nameArr=oldName.split("_");
			StringBuilder builder=new StringBuilder(32);
			for (int i=0;i<nameArr.length;i++) {
				builder.append(firstUpper(nameArr[i]));
			}
			return builder.toString();
		}
	}
	
	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if(str==null||str.trim().equals("")){
			return true;
		}
		return false;
	}
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String firstUpper(String str){
		if(isBlank(str)){
			return null;
		}else{
			return Character.toString(str.charAt(0)).toUpperCase()+str.substring(1);
		}
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String lowerUpper(String str){
		if(isBlank(str)){
			return null;
		}else{
			return Character.toString(str.charAt(0)).toLowerCase()+str.substring(1);
		}
	}

	/**
	 * 数据库类型转换为java类型
	 * @param dataType
	 * @param columnType
	 * @return
	 */
	public static String type(String dataType, String columnType) {
		if("char".equalsIgnoreCase(dataType)||"varchar".equalsIgnoreCase(dataType)||"varchar1".equalsIgnoreCase(dataType)||"varchar2".equalsIgnoreCase(dataType)){
			return "java.lang.String";
		}else if("tinyint".equalsIgnoreCase(dataType)||"int".equalsIgnoreCase(dataType)){
			return "java.lang.Integer";
		}else if("decimal".equalsIgnoreCase(dataType)){
			return "java.math.BigDecimal";
		}else if("date".equalsIgnoreCase(dataType)||"timestamp".equalsIgnoreCase(dataType)){
			return "java.util.Date";
		}else if("bigint".equalsIgnoreCase(dataType)){
			return "java.lang.Long";
		}
		return "java.lang.String";
	}

	/**
	 * 简短的名称
	 * @param fieldName
	 * @return
	 */
	public static String shortName(String fieldName) {
		if(fieldName==null){
			return null;
		}else{
			return fieldName.substring(fieldName.lastIndexOf(".")+1);
		}
	}
}
