package com.Automation.Base;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.Automation.Utility.ConfigProperties;
import com.Automation.Utility.Constants;
import com.Automation.Utility.ExtentReportUtils;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
//	To specify the class that the logger is associated with. 
//	This is commonly done to allow the logger to record log messages that are related to the class in which the logger is defined, 
//	helping to easily trace where logs are coming from in a large application.
//	the below logger belongs to the this project of Base class and thus it can be extended to sub classes.
	protected static Logger logger = LogManager.getLogger(BaseTest.class);
	protected static ExtentReportUtils extentReportUtilsObj = ExtentReportUtils.getInstance();
	protected static WebDriver driver = null; //driver instance
	protected static final String TEST_USERNAME = "amirtha.c.panneer126@agentforce.com";
	protected static final String TEST_PASSWORD = "amirthajava123"; 

	protected WebDriverWait wait ; 
	
	public void launchBrowser(String browserName)
	{
		switch(browserName.toLowerCase())
		{
		case("chrome"):
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case("firefox"):
	
		    System.setProperty("webdriver.gecko.driver", "/Users/mura/Downloads/geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
			driver = new FirefoxDriver(options);
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			break;
			
		case("safari"):
			WebDriverManager.safaridriver().setup();
		    driver = new SafariDriver();
		    break;
		    
		case("edge"):
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		
		default:
			break;
			
		}
	}
	
	public void goToUrl(String url){
		driver.get(url);
		driver.manage().window().maximize();

	}
	
	public void closeDriver(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();

	}

	public static String getContent(WebElement ele, String objName)
	{
		String text = ele.getText();
			
		return text;
		
	}
	//To enter text in IputBox
	public void enterText(WebElement ele,String data,String objName) throws InterruptedException {
		if(ele.isDisplayed())
		{
			ele.clear();
			ele.sendKeys(data);
			Thread.sleep(2000);
			System.out.println("data is entered to "+objName);
		}
		else
		{
			System.out.println(objName+" textbox is not displayed");
		}
	}

	//To get text from element
	public String getTextFromElement(WebElement ele, String objName) {
		String data=null;
		if(ele.isDisplayed()) {
			data = ele.getText();
		}
		else {
			System.out.println(objName+" not dispalyed");
		}
		return data;
	}
	
	//TO click WebElement
	public void clickElement(WebElement ele,String objName) throws InterruptedException {
		if(ele.isEnabled())
		{
			ele.click();
			Thread.sleep(2000);
			System.out.println(objName+" button is clicked");
		}
		else {
			System.out.println(objName+" button is not displayed");
		}
	}

	
	//To select checkbox
	public void selectCheckBox(WebElement ele,String objectName) {
		if(!ele.isSelected()) {
			ele.click();

		}
		else{
			System.out.println(objectName+" button is already selected");
		}
	}
	public void waitUntilAlertPresent(int sec) {

	}
	
	//Select from dropdown by Value
	public void selectByValueData(WebElement ele, String value) {

		Select select = new Select(ele);
		select.selectByValue(value);
	}

	//Select from dropdown by Visible Text
	public void selectByTextData(WebElement ele, String value, String objName) {

		Select select = new Select(ele);
		select.selectByVisibleText(value);;
	}
	
	//Select from dropdown by IndexVlue
	public void selectByIndexData(WebElement ele, int value) {

		Select select = new Select(ele);
		select.selectByIndex(value);
	}

	
	public Alert switchToAlert() 
	{
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }
	
	public String getAlertText(Alert alert, String objectName) {
        String text = alert.getText();
        System.out.println(objectName + " Alert text: " + text);
        return text;
    }
	
	public void alertAccept(Alert alert)
	{
		alert.accept();
	}
	public void alertCancel(Alert alert)
	{
		alert.dismiss();
	}
	
	
	public void hoverOverElement(WebElement element) {
		Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        System.out.println("Hovered over: " + element);
    }

    // Click using Actions
    public void clickElement(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        System.out.println("Clicked using Actions: " + element);
    }

    // Right-click (context click)
    public void rightClickElement(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        System.out.println("Right-clicked on: " + element);
    }

    // Double-click
    public void doubleClickElement(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        System.out.println("Double-clicked on: " + element);
    }

    // Drag and drop
    public void dragAndDrop(WebElement source, WebElement target) {
    	Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        System.out.println("Dragged and dropped from source to target");
    }

    // Click and hold
    public void clickAndHold(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.clickAndHold(element).perform();
        System.out.println("Clicked and held on: " + element);
    }
    
    //Wiat for visibilty using  Explicit wait time
    public void waitForVisibility(WebElement ele,long timeInSec,String ObjectName) {
		System.out.println(ObjectName+ "waiting for visibility for maximum of "+timeInSec+ " sec");
		wait=new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
    
    public void waitForIframe(WebElement ele, long timeInSec, String ObjectName)  {
    	wait = new WebDriverWait(driver, timeInSec);
    	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
    }
    //Take screen shot
    public void takeScreenshot(String filepath) {
    
		TakesScreenshot screenCapture=(TakesScreenshot)driver;
		File srcFile= screenCapture.getScreenshotAs(OutputType.FILE);
		File destFile=new File(filepath);
		try {
			
			Files.copy(srcFile, destFile);
			logger.info("Screen captured");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Screencapture failed");
		}
	}

}


