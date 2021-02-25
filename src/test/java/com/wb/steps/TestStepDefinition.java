package com.wb.steps;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wb.framework.*;
import com.wb.pages.AmendPage;
import com.wb.pages.CommonPages;
import com.wb.pages.Dashboard;
import com.wb.pages.LoginPage;

import io.cucumber.java8.En;

public class TestStepDefinition extends DriverAndBrowserFactory implements En{
	
	public LoginPage lp=null;
	public Dashboard db=null;
	public AmendPage ap=null;
	public CommonPages cp=null;
	private static String roomsAvailableMsg=null;
	
	public TestStepDefinition()
	{
	
		Given("Load the page {}",(String url2) -> {
			initWebDriver("web","chrome");
			cp=new CommonPages();
			cp.loadPage(url2);
		});		
		
		When("In the home page, click on manage booking", () -> {
			lp=new LoginPage();
			ap=new AmendPage();
			cp=new CommonPages();
			cp.elementClick(lp.manageBooking);
	});
		
		
		Then("^In the booking information page, click on Amend booking$",() -> {
			lp=new LoginPage();
			ap=new AmendPage();
			cp=new CommonPages();
			cp.scrollAndClickElement(ap.amendBooking);
		});
		
		
		Then("^Write the information capture to a csv file$",() ->{
			ap=new AmendPage();
			cp=new CommonPages();
			cp.csvWriter(roomsAvailableMsg.toString().replace("<p>", "").replace("<b>", "").replace("</p>","").replace("</b>",""));
		});
	
		Then("Load the booking details booking reference {}, lastname {}, arrivaldate {string}",(String bookRef,String lastName,String arrivalDate) -> {
			
			String day=arrivalDate.toString().split(Pattern.quote("-"))[0];
			String month=arrivalDate.toString().split(Pattern.quote("-"))[1];
			String year=arrivalDate.toString().split(Pattern.quote("-"))[2];
			cp.elementSendKeys(lp.bookingReference, bookRef);
			cp.elementSendKeys(lp.bookingSurname, lastName);
			cp.elementClick(lp.arrivalDate);
			ap.setDynamicXPath(month+" "+year);
			cp.scrollUntillExpectedMonth(month,ap.getDynamicXPath(),ap.monthRightArrow);
			cp.scrollAndClickElement(ap.calendarDayPicker(month, day));
			cp.elementClick(ap.search);
		});

		Then("In the Amend booking page, change the arrival date to a {string}",(String arrivalDate) -> {
			String day=arrivalDate.toString().split(Pattern.quote("-"))[0];
			String month=arrivalDate.toString().split(Pattern.quote("-"))[1];
			String year=arrivalDate.toString().split(Pattern.quote("-"))[2];

			cp.elementClick(ap.arrivalDate2);
			ap.setDynamicXPath2(month);
			cp.scrollUntillExpectedMonth(month,ap.getDynamicXPath2(),ap.monthRightArrow2);
			cp.scrollAndClickElement(ap.calendarDayPicker2(month, day));
		});

		Then("Capture the information in the Rooms available {string}",(String date) -> {
			ap=new AmendPage();
			cp=new CommonPages();
			String day=date.toString().split(Pattern.quote("-"))[0];
			String month=date.toString().split(Pattern.quote("-"))[1];
			String year=date.toString().split(Pattern.quote("-"))[2];
			//roomsAvailableMsg=ap.allBoldMessages();
			roomsAvailableMsg=cp.readFromElement("attribute","outerHTML",  ap.roomsAvailableCompleteMessage);
			//String splitString=roomsAvailableMsg.split("<p>",2)[1];
			//System.out.println("hhhhhhhhhhhhhhhhhhdddddddd"+splitString.replace("<div>","").replace("<p>","").replace("<b>",""));
			roomsAvailableMsg=cp.readFromElement("attribute","outerHTML",  ap.roomsAvailableMessage);
//			System.out.println("roomsAvailableMsg: "+allMsg);
			System.out.println("roomsAvailableMsg: "+roomsAvailableMsg);
			cp.validateElementContains(roomsAvailableMsg, day+" "+month+" "+year);
		});
	}

}
