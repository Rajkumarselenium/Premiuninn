package com.wb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wb.framework.DriverAndBrowserFactory;

public class Dashboard extends DriverAndBrowserFactory{
	
	
	public Dashboard()
	{
		PageFactory.initElements(getWebDriver(), this);
	}
	
	@FindBy(xpath = "//h5[text()='Total Cost']")
	WebElement totalCost;
	
	
}
