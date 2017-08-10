package com.nareshcompany.objectrepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetDate {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Date today = new Date();
		SimpleDateFormat currentDate = new SimpleDateFormat("M/d/yyyy");
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		String expectedDay = (currentDate.format(today).toString().split("/"))[1];
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='search']/div/div[3]/span")).click();
		Thread.sleep(5000);
		WebElement table = driver.findElement(By.xpath(".//div[@id='rb-calendar_onward_cal']/table"));
		WebElement tbody = table.findElement(By.tagName("tbody"));
		List<WebElement> rows = tbody.findElements(By.tagName("tr"));
		
		for(int i=2;i<rows.size();i++)
		{
			boolean breakOuterLoop = false;
			List<WebElement> cols =rows.get(i).findElements(By.tagName("td"));
			
			for(int j=0;j<cols.size();j++)
			{
				if((cols.get(j).getText()).equals(expectedDay))
				{
					System.out.println(cols.get(j).getText());
					cols.get(j).click();
					breakOuterLoop = true;
					break;
				}
			}
			
			if(breakOuterLoop){break;}
		}
	}

}
