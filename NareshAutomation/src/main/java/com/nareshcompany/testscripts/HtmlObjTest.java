package com.nareshcompany.testscripts;

import org.testng.annotations.Test;

import com.nareshcompany.base.Base;
import com.nareshcompany.businessmethods.GenericWebPage;
import com.nareshcompany.utilities.TestFailedException;

public class HtmlObjTest extends Base{

	@Test
	public void genericHtmlTest() throws TestFailedException, Exception
	{
		genericHtmlTestPage = new GenericWebPage(driver);
		genericHtmlTestPage.invokeFacebookApplication();
		genericHtmlTestPage.test1();
	}
}
