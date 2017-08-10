package com.nareshcompany.testscripts;

import org.testng.annotations.Test;

import com.nareshcompany.base.Base;
import com.nareshcompany.businessmethods.FacebookHomePage;
import com.nareshcompany.utilities.TestFailedException;

public class FacebookTest extends Base {

	@Test
	public void facebookTest() throws TestFailedException, Exception
	{
		facebookHomePage = new FacebookHomePage(driver);
		facebookHomePage.invokeFacebookApplication();
		facebookHomePage.createNewAccount();
	}
}
