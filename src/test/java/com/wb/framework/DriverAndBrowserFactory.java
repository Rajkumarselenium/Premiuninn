package com.wb.framework;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


public abstract class DriverAndBrowserFactory{

	private static WebDriver wd;
	
	public WebDriver initWebDriver(String dr,String br)
	{
		RemoteWebDriver driver=null;
		ChromeOptions options=null;
		switch(br) {
		case "CHROME" :
		case "chrome" :
		// Optional. If not specified, WebDriver searches the PATH for chromedriver.
			
			String path=System.getProperty("user.dir");
			path=path.replace("\\", "/")+"/drivers";
			File file = new File(path+"/chromedriver88.exe");
			System.out.println("hello : "+file.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			
	    options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	    
	    break;
		}
		
	    return wd=(WebDriver)driver;
	    
	}
	
	public WebDriver getWebDriver()
	{
	    return (WebDriver)wd;
	}
}
