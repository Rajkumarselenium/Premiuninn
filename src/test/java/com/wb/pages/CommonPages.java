package com.wb.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wb.framework.DriverAndBrowserFactory;

public class CommonPages extends DriverAndBrowserFactory{
	
	
	public CommonPages()
	{
		PageFactory.initElements(getWebDriver(), this);
	}
	
	private String setDynamicXPath=null;
	public void elementClick(WebElement we)
	{
		try
		{
			//waitUntilVisible(we);
			waitUntilVisible2(we);
			Thread.sleep(3000);
			if(we.isDisplayed() && we.isEnabled())
			{
				we.click();
			}
			else
				System.out.println("Else - Element "+we.toString()+" not visible");
			}
		catch(Exception e)
		{
			System.out.println("Catch - Element "+we.toString()+" not available");
		}
	}
	
	public void elementDoubleClick(WebElement we)
	{
		try
		{
			//waitUntilVisible(we);
			waitUntilVisible2(we);
			Thread.sleep(10000);
			if(we.isDisplayed() && we.isEnabled())
			{
				Action a =(Action)getWebDriver();
				
			}
			else
				System.out.println("Else - Element "+we.toString()+" not visible");
			}
		catch(Exception e)
		{
			System.out.println("Catch - Element "+we.toString()+" not available");
		}
	}
	
	public void elementSendKeys(WebElement we,String inputData)
	{
		try
		{
			if(we.isDisplayed() && we.isEnabled())
			{
				we.sendKeys(inputData);
			}
			else
				System.out.println("Element "+we.toString()+" not visible");
		}
		catch(Exception e)
		{
			System.out.println("Element "+we.toString()+" not available");
			executeScriptScrollToView(we);
			we.sendKeys(inputData);
		}
	}
	
	public void executeScriptScrollToView(WebElement we)
	{
		JavascriptExecutor jse= (JavascriptExecutor)getWebDriver();
		jse.executeScript("arguments[0].scrollIntoView()", we);
	}
	
	public void executeScript(String command,WebElement ... elementArray )
	{
		JavascriptExecutor jse= (JavascriptExecutor)getWebDriver();
		if(elementArray.length==1)
			jse.executeScript(command, elementArray[0]);
	}

	public void scrollUntillExpectedMonth(String expectedMonth,WebElement calendarMonth,WebElement next)
	{
		do
		{
			try
			{
				Thread.sleep(2000);
				System.out.println("rest... ");
				if(calendarMonth.isDisplayed())
				{
					System.out.println("caledar month - "+calendarMonth.toString());
					break;
				}
				else
					next.click();
			}
			catch(Exception e)
			{
				System.out.println("Matching month not found, Click next.");
				next.click();
			}
		}while(true);
	}
	
	public void scrollUntillExpectedMonth(String expectedMonth,String xpath,WebElement next)
	{
		WebElement calendarMonth=null;
		do
		{
			try
			{
				
				System.out.println("rest... ");
				calendarMonth=getWebDriver().findElement(By.xpath(xpath));
				Thread.sleep(2000);
				if(calendarMonth.isDisplayed())
				{
					System.out.println("caledar month - "+calendarMonth.toString());
					break;
				}
				else
					next.click();
			}
			catch(Exception e)
			{
				System.out.println("Matching month not found, Click next.");
				next.click();
			}
		}while(true);
	}
	
	public void scrollAndClickElement(WebElement elemenToClick)
	{
		
			try
			{
				waitUntilVisible(elemenToClick);
				if(elemenToClick.isEnabled() && elemenToClick.isDisplayed())
				{
					elemenToClick.click();
				}
				else
				{
					executeScriptScrollToView(elemenToClick);
					elemenToClick.click();
					
				}
			}
			catch(Exception e)
			{
				System.out.println("Element not found, attempt to scroll and click.");
				executeScriptScrollToView(elemenToClick);
				elemenToClick.click();
			}
	}
	
	public String scrollAndReadElement(String readConfig,String attributeType,WebElement readElement)
	{
		
			try
			{
				if(readElement.isEnabled() && readElement.isDisplayed())
				{
					return readFromElement(readConfig,attributeType,readElement);

				}
				else
				{
					executeScriptScrollToView(readElement);
					return readFromElement(readConfig,attributeType,readElement);
				}
			}
			catch(Exception e)
			{
				System.out.println("Element not found, attempt to scroll and click.");
				executeScriptScrollToView(readElement);
				return readFromElement(readConfig,attributeType,readElement);
			}
	}
	
	
	
	public String scrollAndReadElement2(String readConfig,String attributeType,WebElement readElement)
	{
		
			try
			{
				if(readElement.isEnabled() && readElement.isDisplayed())
				{
					return readFromElement(readConfig,attributeType,readElement);

				}
				else
				{
					executeScriptScrollToView(readElement);
					return readFromElement(readConfig,attributeType,readElement);
				}
			}
			catch(Exception e)
			{
				System.out.println("Element not found, attempt to scroll and click.");
				executeScriptScrollToView(readElement);
				return readFromElement(readConfig,attributeType,readElement);
			}
	}
	
	
	public String readFromElement(String attributeType,String readConfig,WebElement readElement)
	{
		switch(attributeType){
		case "text()": return readElement.getText();
		case "attribute": return readElement.getAttribute(readConfig);
		}
		
		return "";
	}
	
	public void csvWriter(String ... newData)
	{
		try {

			Writer writer = null;
			CSVPrinter csvPrint=null;
			List<String> oldData=new ArrayList<String>();
            //We have to create the CSVPrinter class object 
			File fl=new File(System.getProperty("user.dir")+"/captureData/"+"roomsAvailability.csv");
			oldData.addAll(csvReader(System.getProperty("user.dir")+"/captureData/"+"roomsAvailability.csv"));
			
			writer=Files.newBufferedWriter(Paths.get(System.getProperty("user.dir")+"/captureData/"+"roomsAvailability.csv"));
			csvPrint = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Rooms Available"));

            //Writing in CSV file 
			//1st new data then old data
            for(String singleCell:newData)
            {
            	csvPrint.printRecord(singleCell);
            }
            
            for(String singleCell:oldData)
            {
            	csvPrint.printRecord(singleCell);
            }

            csvPrint.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public List<String> csvReader(String path)
	{
		List<String> list=new ArrayList<String>();
	
		try {
				
	            Reader reader = Files.newBufferedReader(Paths.get(path));
	            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
	                    .withHeader("Rooms Available")
	                    .withIgnoreHeaderCase()
	                    .withTrim());
	         
	            for (CSVRecord csvRecord : csvParser) {
	                // Accessing values by the names assigned to each column
	                String roomsAvailable = csvRecord.get("Rooms Available");
	                list.add(roomsAvailable);
	            }
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public void validateElementContains(String expected,String actual)
	{
		Assert.assertTrue("Validate Expected: "+expected+" - has - "+actual+" - (actual)?",expected.contains(actual));
	}
	
	public void waitUntilVisible(WebElement we)
	{
		do
		{
			try
			{
				if(we.isDisplayed())
					break;
				
			}
			catch(Exception e)
			{
				getWebDriver().manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
			}
		}while(true);
	}
	
	public void waitUntilVisible2(WebElement we)
	{
		WebDriverWait wait1 = new WebDriverWait(getWebDriver(), 10);
		WebElement element1 = null;
		try
		{
			element1=wait1.until(ExpectedConditions.elementToBeClickable(we));
		}
		catch(Exception e)
		{
			System.out.println("waitUntilVisible2 - catch - "+e);
		}
	}
	
	public void loadPage(String url2)
	{
		try
		{
			URL url=new URL("http://"+url2);
			//getWebDriver().get;
			getWebDriver().navigate().to(url);
		}
		catch(Exception e)
		{
			System.out.println("output: "+e);
		}
	}
}