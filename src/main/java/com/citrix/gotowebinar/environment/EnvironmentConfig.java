package com.citrix.gotowebinar.environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentConfig {
	
	public static Properties properties;

	public Properties loadEnvironmentConfig(String env) {
		
		try {
			properties = new Properties();			
			properties.load(EnvironmentConfig.class.getClassLoader().getResourceAsStream("Configurations/" + env
						+ "/config.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return properties;
	}
	
	public Properties loadTestData(String env) {
		
		try {
			System.out.println("Loading Test data setup :" + env);
			
			InputStream data = EnvironmentConfig.class.getClassLoader().getResourceAsStream("Data/" + env
					+ "/config.properties");
			properties = new Properties();
			properties.load(data);
			
		} catch (IOException e) {
			throw new RuntimeException("Unable intialize environment.", e);
		}
		return properties;
	}
	
}