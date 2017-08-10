package com.nareshcompany.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations
{    
	public String getHubUrl()
	{
		return "http://"+HUB_URL+":4444/wd/hub";
	}
	private String HUB_URL =  getConfigData("hubUrl");
	public String EXECUTION_MODE =  getConfigData("executionMode");
	public String EMAIL_FLAG =  getConfigData("emailFlag");
	public String EMAIL_RECEPIENTS =  getConfigData("email_to");
	public String EMAIL_RECEPIENTS_CC =  getConfigData("email_cc");
	
	public String Browser = getConfigData("Browser");	

	public String URL = getConfigData("URL");

	public String pageLoadTimeOut = getConfigData("pageLoadTimeOut");
	public String implicitWaitForElement = getConfigData("implicitWaitForElement");

	public String UserName = getConfigData("UserName");
	public String Password = getConfigData("Password");

	public final String sTakeScreenshot = getConfigData("TakeScreenshot");
	public final String sScriptPassResult = getConfigData("ScriptPassResult");
	public final String sResultFolder = getConfigData("ResultFolder");

	public String getConfigData(String sKey) {

		Properties prop = new Properties();
		InputStream input = null;
		String data = null;
		try {
			String userDir = System.getProperty("user.dir");
			input = new FileInputStream(userDir+"\\src\\main\\java\\com\\nareshcompany\\config\\config.properties");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			data = prop.getProperty(sKey);
			if(data!=null)
				data = data.trim();
			return data;
		} catch (Exception ex) {
			ex.printStackTrace();
			return data;
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}