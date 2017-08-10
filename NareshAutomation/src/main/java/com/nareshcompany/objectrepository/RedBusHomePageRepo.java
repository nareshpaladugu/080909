package com.nareshcompany.objectrepository;

import org.openqa.selenium.By;

public class RedBusHomePageRepo 
{	
	public static final By txt_from_place      = By.id("src");
	public static final By txt_to_place        = By.id("dest");
	public static final By btn_date_of_journey = By.id("onward_cal");
	public static final By btn_date_of_return  = By.id("return_cal");
	public static final By btn_bus_search      = By.id("search_btn");
	public static final By txt_from_date       = By.xpath("//*[@id='search']/div/div[3]/span");
	public static final By from_date_web_table  = By.xpath(".//*[@id='rb-calendar_onward_cal']/table");
		
}