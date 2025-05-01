package com.Automation.Base;


import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

//import com.Automation.Utility.ConfigProperties;
import com.Automation.Utility.Constants;

public class SFDCBase extends BaseTest{
	
	String url = Constants.TEST_URL;
//	String url = ConfigProperties.readDatafromConfigPropertiesFile(Constants.APPLICATION_PROPERTIES, "url");
	protected String expectedPageTitleValidLogin = "Salesforce - Developer Edition";
	protected String expectedPageTitleInValidLogin = "Login | Salesforce";
	long waitTime = 60;
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String browserName) throws InterruptedException
	{
		launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);		// dynamic wait
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		goToUrl(url);
		Thread.sleep(1000);
		
	}

	@AfterMethod
	public void tearDownAfterMethod() throws Exception
	{
		
		closeDriver();
	}
	
	 public void login(String username, String password) throws InterruptedException {
		 
		 
//			WebElement username1 = driver.findElement(By.id("username")); //		using XPATH //*[@id="username"]
//			waitForVisibility(username1,60, "Email");
//			enterText(username1, username, "Username");
//			WebElement password1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password")))); //		using xpath //*[@id="password"]
//			enterText(password1, password, "Password");
		 	setUsername(username);
		 	setPassword(password);
			WebElement loginButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("Login"))));
			clickElement(loginButton);

	    }
	 
	 
	 private void setUsername(String username) throws InterruptedException {
		 
			WebElement username1 = driver.findElement(By.id("username")); //		using XPATH //*[@id="username"]
			waitForVisibility(username1,60, "Email");
			enterText(username1, username, "Username");
	 }
	 
	 private void setPassword(String password) throws InterruptedException {
		 
			WebElement password1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password")))); //		using xpath //*[@id="password"]
			
			enterText(password1, password, "Password");
	 }
	
	 public boolean isValidLogin() throws InterruptedException {
		 	Thread.sleep(1000);
	        return driver.getTitle().contains(expectedPageTitleValidLogin);
	    }
	 public boolean isnvalidLogin() throws InterruptedException {
		 	Thread.sleep(1000);
	        return driver.getTitle().contains(expectedPageTitleInValidLogin);
	  }
	 
	 public void rememberMe(String username, String password) throws InterruptedException {
		 	setUsername(username);
		 	setPassword(password);
			WebElement rememberMe = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));
			clickElement(rememberMe, "Clicked remeber me");
			
	 }
		public void userProfileNavigation() throws InterruptedException {

			login(TEST_USERNAME,TEST_PASSWORD);
			Thread.sleep(1000);
			WebElement navButton = driver.findElement(By.id("userNavButton"));
			clickElement(navButton, "My Profile Navigation button");
//			
		}
	public void logout() throws Exception {
			userProfileNavigation();
	 		
			WebElement logout = driver.findElement(By.xpath("//*[@title=\"Logout\"]"));
			clickElement(logout,"Logout");
			Thread.sleep(1000);
	    	
	    }
	
}

