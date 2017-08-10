package com.nareshcompany.businessmethods;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nareshcompany.base.GenericLibrary;
import com.nareshcompany.config.Configurations;
import com.nareshcompany.utilities.HTMLReports;
import com.nareshcompany.utilities.TestFailedException;

public class GenericWebPage extends GenericLibrary {

	// Object Creation - Generic Objects
	public WebDriver driver;
	HTMLReports reports;
	Configurations config;

	public GenericWebPage(WebDriver driver) {
		this.driver = driver;
		reports = new HTMLReports();
		config = new Configurations();
	}

	/**
	 * @Description login to application with valid credentials
	 * @throws TestFailedException
	 * @throws Exception
	 */
	public void invokeFacebookApplication() throws TestFailedException, Exception{
		boolean bFlag = false;
		try{
			invokeWebApplication(config.URL);
			bFlag=true;
		}
		catch(Exception e)
		{
			bFlag = false;
			throw new TestFailedException("Navigation to the URL is failed: "+config.URL);
		}

		finally
		{
			if(bFlag)
			{
				reports.successReport("Navigate to URL", "Successfully navigated to the URL:"+config.URL );
			}
			else
			{
				reports.failureReport("Navigate to URL", "Failed to navigated to the URL:"+config.URL );

			}
		}

	}

	public void test1()
	{

		try {
			verifyTableColumnPresent(driver.findElement(By.id("customers")), "Contact");

			reports.successReport("Test ", "Test Passed !" );

		} catch (TestFailedException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				reports.successReport("Test ", "Test failed due to exception "+e.getMessage() );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
	}

	public void test2()
	{

	}
}
