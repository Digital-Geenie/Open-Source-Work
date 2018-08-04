package com.theft.obd.detector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
	String result = "";
	InputStream inputStream;
	public static String URL = "";
	public static int PORT;
	public static String GROUP = "";
	public static String NETWORK_NAME = "";
	public static Boolean ON;
	public static String START_SCRIPT_HOSTAPD ="";
	public static String STOP_SCRIPT_HOSTAPD ="";
	public static String START_SCRIPT_UDHCPD ="";
	public static String STOP_SCRIPT_UDHCPD ="";
	public static PropertyFileReader propreader= new PropertyFileReader();
	
	public void getPropValues() throws IOException {
		 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			
			// get the property value and print it out
			URL = prop.getProperty("URL");
			PORT = Integer.parseInt(prop.getProperty("PORT"));
			GROUP = prop.getProperty("GROUP");
			NETWORK_NAME = prop.getProperty("NETWORK_NAME");
			ON = Boolean.parseBoolean(prop.getProperty("ON"));
			START_SCRIPT_HOSTAPD = prop.getProperty("START_SCRIPT_HOSTAPD");
			STOP_SCRIPT_HOSTAPD = prop.getProperty("STOP_SCRIPT_HOSTAPD");
			START_SCRIPT_UDHCPD = prop.getProperty("START_SCRIPT_UDHCPD");
			STOP_SCRIPT_UDHCPD = prop.getProperty("STOP_SCRIPT_UDHCPD");
			} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
}
