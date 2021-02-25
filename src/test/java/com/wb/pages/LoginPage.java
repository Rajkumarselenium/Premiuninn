package com.wb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wb.framework.DriverAndBrowserFactory;

public class LoginPage extends DriverAndBrowserFactory{
	
	
	public LoginPage()
	{
		PageFactory.initElements(getWebDriver(), this);
	}
	
	@FindBy(xpath = "//*[@id='pi-menu-button']/span[1]")
	public WebElement hamburgerMenu;
	
	@FindBy(xpath = "//button[text()='Manage booking']")
	public WebElement manageBooking;
	
	@FindBy(xpath = "//*[@id='booking-reference-input']")
	public WebElement bookingReference;
	
	@FindBy(xpath = "//*[@id='booking-surname-input']")
	public WebElement bookingSurname;
	
	@FindBy(xpath = "//*[@id='booking-surname-input']//following::input[@required='required' and @type='text']")
	public WebElement arrivalDate;
	
	@FindBy(xpath = "//*[@id='find-booking-form-button']")
	public WebElement search;
	
	public WebElement calendarTitle(String month)
	{
		WebElement we;
		we=getWebDriver().findElement(By.xpath("//div[@class='calendar-heading' and contains(text(),'"+month+"')]"));
		return we;
	}
	

	
}