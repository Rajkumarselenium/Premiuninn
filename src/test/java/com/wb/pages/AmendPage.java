package com.wb.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wb.framework.DriverAndBrowserFactory;

public class AmendPage extends DriverAndBrowserFactory{
	
	
	public AmendPage()
	{
		PageFactory.initElements(getWebDriver(), this);
	}
	
	private String dynamicXPath=null;
	private String dynamicXPath2=null;
	
	@FindBy(xpath = "//*[text()='Amend booking']")
	public WebElement amendBooking;
	
	@FindBy(xpath = "//*[text()='Amend your booking']")
	public WebElement amendYourBooking;
	
	@FindBy(xpath = "//*[@id='arrivalDate']")
	public WebElement arrivalDate;
		
	@FindBy(xpath = "//*[text()='Rooms available']")
	public WebElement roomsAvailable;
	
	@FindBy(xpath = "//*[@data-test='rooms_available_message']")
	public WebElement roomsAvailableCompleteMessage;
	
	@FindBy(xpath = "//*[@data-test='rooms_available_message']/p")
	public WebElement roomsAvailableMessage;

	@FindBy(xpath = "//*[@data-test='rooms_available_message']/p/b")
	public List<WebElement> roomsAvailableMessageBold;
	
	@FindBy(xpath = "//*[@data-test='rooms_available_message']//*[contains(text(),'21')]")
	public WebElement storeMessage;
	
	@FindBy(xpath ="//button[@class='calendar-chevron date-picker-right-arrow' and contains(@style,'block')]")
	public WebElement monthRightArrow;
	
	@FindBy(xpath ="//*[@class='pika-next']")
	public WebElement monthRightArrow2;
	
	@FindBy(xpath ="//button[@id='find-booking-form-button']")
	public WebElement search;
	
	@FindBy(xpath ="//*[@class='wb-icon_calendar']")
	public WebElement amendIcon;
	
	@FindBy(xpath ="//*[@name='arrivalDate']")
	public WebElement arrivalDate2;
	
	
	
	
	
	
	public WebElement calendarTitle(String Month)
	{
		WebElement we;
		try
		{
			System.out.println("calendarTitle - "+Month);
			we=getWebDriver().findElement(By.xpath("//div[@class='calendar-heading' and contains(text(),'"+Month+"')]"));
			System.out.println("calendarTitle - "+we.toString()+" - "+we.isDisplayed());
			return we;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public WebElement calendarTitle2(String Month)
	{
		return getWebDriver().findElement(By.xpath("//div[@class='calendar-heading' and contains(text(),'"+Month+"')]"));
	}
	
	public void setDynamicXPath(String dynamicValue)
	{
		dynamicXPath="//div[@class='calendar-heading' and contains(text(),'"+dynamicValue+"')]";
	}
	
	public String getDynamicXPath()
	{
		return dynamicXPath;
	}
	
	public void setDynamicXPath2(String dynamicValue)
	{
		dynamicXPath="//div[@class='pika-title']//div[@class='pika-label' and contains(text(),'"+dynamicValue+"')]";
	}
	
	public String getDynamicXPath2()
	{
		return dynamicXPath;
	}
	
	public WebElement calendarDayPicker(String month,String day)
	{
		try
		{
			WebElement we=getWebDriver().findElement(By.xpath("//*[starts-with(@id,'"+month+"')]//*[starts-with(@id,'date-picker-day') and @class='date-value' and text()='"+day+"']"));
			return we;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public WebElement calendarDayPicker2(String month,String day)
	{
		try
		{
			WebElement we=getWebDriver().findElement(By.xpath("//td[@data-day='"+day+"']"));
			return we;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	

	public String allBoldMessages()
	{
		
		String msg="";
		
		for(WebElement we:roomsAvailableMessageBold)
		{
			msg=msg+" "+we.getText();
		}
		return msg;
	}
}
