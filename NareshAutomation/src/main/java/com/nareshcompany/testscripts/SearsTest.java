package com.nareshcompany.testscripts;

import org.testng.annotations.Test;

import com.nareshcompany.base.Base;
import com.nareshcompany.businessmethods.SearsHomePage;
import com.nareshcompany.utilities.TestFailedException;

public class SearsTest extends Base {

	@Test
	public void searsTest() throws TestFailedException, Exception
	{
		searsHomePage = new SearsHomePage(driver);
		
		//launch url
		searsHomePage.invokeSearsApplication();
		/*
		//search tv-->LED TVs
		searsHomePage.searchProduct("tv","LED TVs");
		
		//check child window functionality
		searsHomePage.checkChatFunction();
		
		//come back to main window and search washer-->Top Load Washers
		searsHomePage.searchProduct("washer","Top Load Washers");*/
	}
}
