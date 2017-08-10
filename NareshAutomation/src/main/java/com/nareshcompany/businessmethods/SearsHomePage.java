package com.nareshcompany.businessmethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nareshcompany.base.GenericLibrary;
import com.nareshcompany.config.Configurations;
import com.nareshcompany.objectrepository.SearsRepo;
import com.nareshcompany.utilities.HTMLReports;
import com.nareshcompany.utilities.TestFailedException;

public class SearsHomePage extends GenericLibrary {

	// Object Creation - Generic Objects
	public WebDriver driver;
	HTMLReports reports;
	Configurations config;

	public SearsHomePage(WebDriver driver) {
		this.driver = driver;
		reports = new HTMLReports();
		config = new Configurations();
	}

	/**
	 * @Description login to application with valid credentials
	 * @throws TestFailedException
	 * @throws Exception
	 */
	public void invokeSearsApplication() throws TestFailedException, Exception{
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

	public void searchProduct(String product,String filter) throws TestFailedException, Exception {
		boolean bSuccessFlag = false;
		try {  
			//type search text
			type(SearsRepo.txt_search,product);
			
			//click go button
			click(SearsRepo.btn_go);
			
			//click on given filter
			click(By.partialLinkText(filter));
			
			Thread.sleep(1000);
			
			//verify breadcrumb
			verifyinnerTextContains(SearsRepo.gnf_breadcrumb,filter);
			
			bSuccessFlag = true;
			
		} catch (Exception ex) {
			bSuccessFlag = false;
			throw new TestFailedException("Unable to search product");
		} 	
		finally{
			if(bSuccessFlag)
				reports.successReport("Search for the product ", "Product Search successfully working for \""+product+"\"");
			else
				reports.failureReport("Search for the product ", "Failed to search product "+product);
		}

	}

	public void checkChatFunction() throws TestFailedException, Exception {
		boolean bSuccessFlag = false;
		try {  
			
			//Click sears logo
			click(SearsRepo.logo_sears);

			//Click chat link
			click(SearsRepo.link_Chat);

			//Selected second window
			selectWindow(2);

			//Child.chat window steps
			type(SearsRepo.txt_visitorName,"Daffy");

			//Child.chat window steps
			type(SearsRepo.txt_email,"Daffy@gmail.com");

			//just for demo added
			Thread.sleep(2200);
			//close child window
			closeWindow();
			
			//Selected first/main window
			selectWindow(1);

			bSuccessFlag = true;
		} catch (Exception ex) {
			bSuccessFlag = false;
			throw new TestFailedException("Unable to check Chat function");
		} 	
		finally{
			if(bSuccessFlag)
				reports.successReport("Chat function ", "Chat window working fine !");
			else
				reports.failureReport("Chat function ", "Failed to create account");
		}

	}


}
