package com.citrix.gotowebinar.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private Properties properties;

	public PropertyReader(String relFilePath) throws IOException {
		InputStream data = getClass().getClassLoader().getResourceAsStream(
				relFilePath);
		properties = new Properties();
		properties.load(data);
	}
	
	

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
}
