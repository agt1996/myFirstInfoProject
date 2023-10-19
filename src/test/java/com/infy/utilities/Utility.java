package com.infy.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility {
	
	public String readPropertiesFile(String key) throws IOException {
		String pathOfPropertiesFile=System.getProperty("user.dir")+"\\Config.properties";
		FileInputStream fis=new FileInputStream(pathOfPropertiesFile);
		Properties pr=new Properties();
		pr.load(fis);
		String value=pr.getProperty(key);
		return value;
	}

}
