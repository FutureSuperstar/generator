package com.xyan;

import java.io.IOException;

import com.xyan.generator.Generator;
import com.xyan.generator.MysqlGenerator;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main(String[] args ) throws IOException{
       Generator generator=new MysqlGenerator();
       generator.generate();
    }
    /**
     * Description	Resource	Path	Location	Type
Archive for required library: 'C:/Users/mh/.m2/repository/mysql/mysql-connector-java/6.0.5/mysql-connector-java-6.0.5.jar' in project 'generator' cannot be read or is not a valid ZIP file	generator		Build path	Build Path Problem

     */
}
