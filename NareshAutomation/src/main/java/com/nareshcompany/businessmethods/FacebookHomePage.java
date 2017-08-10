package com.nareshcompany.businessmethods;
import org.openqa.selenium.WebDriver;

import com.nareshcompany.base.GenericLibrary;
import com.nareshcompany.config.Configurations;
import com.nareshcompany.objectrepository.FacebookRepo;
import com.nareshcompany.utilities.HTMLReports;
import com.nareshcompany.utilities.TestFailedException;

public class FacebookHomePage extends GenericLibrary {

	// Object Creation - Generic Objects
	public WebDriver driver;
	HTMLReports reports;
	Configurations config;

	public FacebookHomePage(WebDriver driver) {
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

	public void createNewAccount() throws TestFailedException, Exception {
		boolean bSuccessFlag = false;
		try {  
			type(FacebookRepo.txt_firstname,"first");
			type(FacebookRepo.txt_lastname,"last");
			Thread.sleep(1000);
			click(FacebookRepo.btn_submit);
			bSuccessFlag = true;
		} catch (Exception ex) {
			bSuccessFlag = false;
			throw new TestFailedException("Unable to search for the buses");
		} 	
		finally{
			if(bSuccessFlag)
				reports.successReport("Search for the buses", "Successfully created account !");
			else
				reports.failureReport("Search for the buses ", "Failed to create account");
		}

	}


}
