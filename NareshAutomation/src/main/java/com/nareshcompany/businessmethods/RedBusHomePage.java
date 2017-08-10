package com.nareshcompany.businessmethods;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nareshcompany.base.GenericLibrary;
import com.nareshcompany.config.Configurations;
import com.nareshcompany.objectrepository.RedBusHomePageRepo;
import com.nareshcompany.utilities.HTMLReports;
import com.nareshcompany.utilities.TestFailedException;

public class RedBusHomePage extends GenericLibrary {

	// Object Creation - Generic Objects
	public WebDriver driver;
	HTMLReports reports;
	Configurations config;

	public RedBusHomePage(WebDriver driver) {
		this.driver = driver;
		reports = new HTMLReports();
		config = new Configurations();
	}

	/**
	 * @Description login to application with valid credentials
	 * @throws TestFailedException
	 * @throws Exception
	 */
	public void invokeRedBusApplication() throws TestFailedException, Exception{
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
	
	/**
	 * @Description Search for the buses
	 * @param fromplace
	 * @param toplace
	 * @throws TestFailedException
	 * @throws Exception
	 */
	public void seacrhForBuses(String fromplace, String toplace) throws TestFailedException, Exception {
		boolean bFlag = false;
		try {  
			type(RedBusHomePageRepo.txt_from_place,fromplace);
			click(RedBusHomePageRepo.txt_from_place);
			type(RedBusHomePageRepo.txt_to_place,toplace);
			click(RedBusHomePageRepo.txt_from_date);
			click(RedBusHomePageRepo.txt_from_date);	
			Thread.sleep(5000);
			click(RedBusHomePageRepo.txt_from_date);
			Thread.sleep(2000);
			selectDate(RedBusHomePageRepo.from_date_web_table);
			click(RedBusHomePageRepo.btn_bus_search);
			bFlag = true;
		} catch (Exception ex) {
			bFlag = false;
			throw new TestFailedException("Unable to search for the buses");
		} 	
		finally{
			if(bFlag)
				reports.successReport("Search for the buses", "Successfully got the results between "+fromplace+" and "+toplace);
			else
				reports.failureReport("Search for the buses ", "Failed to get the results between "+fromplace+" and "+toplace);
		}

	}
	
	
	public void selectDate(By webTable)
	{
		Date today = new Date();
		SimpleDateFormat currentDate = new SimpleDateFormat("M/d/yyyy");
		String expectedDay = (currentDate.format(today).toString().split("/"))[1];
		
		WebElement table =driver.findElement(webTable);
		WebElement tbody = table.findElement(By.tagName("tbody"));
		List<WebElement>rows =tbody.findElements(By.tagName("tr"));
		
		for(int i=2;i<rows.size();i++)
		{
			boolean stop_row_iteration = false;
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			
			for(int j=0;j<cols.size();j++)
			{
				if(cols.get(j).getText().equals(expectedDay))
				{
					cols.get(j).click();
					stop_row_iteration = true;
					break;
				}
			}
			if(stop_row_iteration){break;}
	}
		
	}
	
}
