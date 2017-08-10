package com.nareshcompany.testscripts;

import org.testng.annotations.Test;

import com.nareshcompany.base.Base;
import com.nareshcompany.businessmethods.RedBusHomePage;
import com.nareshcompany.datadriven.TestData;
import com.nareshcompany.utilities.TestFailedException;

public class RedBusSearchTest_001 extends Base{
	
	
	@Test
	public void redBusSearchTest_001() throws TestFailedException, Exception
	{
		redBusHomePage = new RedBusHomePage(driver);
		redBusHomePage.invokeRedBusApplication();
		redBusHomePage.seacrhForBuses(TestData.getCellData("fromPlace", "REDBUS_TESTCASE_001.xls"), TestData.getCellData("ToPlace", "REDBUS_TESTCASE_001.xls"));
	}

}
