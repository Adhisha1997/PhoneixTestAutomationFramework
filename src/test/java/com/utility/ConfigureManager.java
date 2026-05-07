package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigureManager {

	public static String getProperty(String env) {

		Properties prop = new Properties();
		
		String path;
	    env = env.trim().toUpperCase();
		
		//File configFile_path = new File(System.getProperty("user.dir") +File.separator+ "src"+File.separator+"test"+File.separator+"resources"+File.separator+"Config"+File.separator+"config.properties");
				
	   // String env= System.getProperty("env");
	    switch (env) {
		case "QA" ->path="/src/test/resources/Config/config.qa.properties";
		case "Dev"->path="/src/test/resources/Config/config.dev.properties";
		case "UAT"->path="/src/test/resources/Config/config.uat.properties";
		default->path= "/src/test/resources/Config/config.properties";	
		}
		
	    File configFile = new File(System.getProperty("user.dir") + path);
	    FileReader fileReader = null;
	    
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("BASEURI");
	}

	
	
	
	
	
	
	// public static String getProperty(String key) throws IOException {

	// Properties prop = new Properties();
	// File configFile = new
	// File(System.getProperty("user.dir")+"/src/test/resources/Config/config.properties");
	// FileReader fileReader = new FileReader(configFile);

	// prop.load(fileReader);
	// return prop.getProperty(key);}

}
