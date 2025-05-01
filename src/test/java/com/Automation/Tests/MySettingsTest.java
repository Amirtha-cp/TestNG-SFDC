package com.Automation.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import com.Automation.Base.UserMenuDropDown;

public class MySettingsTest extends UserMenuDropDown {
	@Test

	public void mySettingPageTest() throws InterruptedException {
	
	mySetting();
	WebElement settingPanel = driver.findElement(By.xpath("//*[@id=\"PersonalSetup_font\"]/span[@class=\"folderText\"]"));
	
	assertEquals(settingPanel.getText(), "My Settings");
	

	}
	@Test
	public void downloadLoginHistoryTest() throws InterruptedException {
	
	mySetting();
	Thread.sleep(1000);
	WebElement personalTab = driver.findElement(By.id("PersonalInfo_font"));
	clickElement(personalTab,"Personal Tab");
	WebElement loginHistory = driver.findElement(By.id("LoginHistory_font"));
	clickElement(loginHistory,"Login History");
	Thread.sleep(1000);
	WebElement downloadLoginLinkBtn = driver.findElement(By.xpath("//a[contains(text(),\"(Excel .csv file)\")]"));
	clickElement(downloadLoginLinkBtn, "Download Login History link");
	

	}
	
	@Test
	
	public void displayLayoutTest() throws InterruptedException {
		mySetting();
		addReports();

	}
	
	@Test
	
	public void calendarTest() throws InterruptedException {
		mySetting();
		WebElement calendarReaminders = driver.findElement(By.xpath("//span[text()=\"Calendar & Reminders\"]"));
		clickElement(calendarReaminders,"Calendar & Reaminders");
		WebElement activityRemainders = driver.findElement(By.xpath("//span[text()=\"Activity Reminders\"]"));
		clickElement(activityRemainders,"Activity Remainders");
		Thread.sleep(5000);
		String parentWindowhandle = driver.getWindowHandle();
		WebElement testReminderBtn = driver.findElement(By.id("testbtn"));
		clickElement(testReminderBtn,"Open a Test Reminder");
		Thread.sleep(5000);
		Set<String> allWindowHandles = driver.getWindowHandles();
		System.out.println("after: Total no. of Windows= "+allWindowHandles.size());
		
		
		for (String windowHandle : allWindowHandles) {
		    if (!windowHandle.equals(parentWindowhandle)) { // Switch only if it's a new window
		        driver.switchTo().window(windowHandle);
		        Thread.sleep(1000);
		        System.out.println("Switched to window: " + driver.getTitle());
		        break;
		    }
		}
		Thread.sleep(500);
		WebElement dismissAll = driver.findElement(By.xpath("//input[@name=\"dismiss_all\"]"));
		clickElement(dismissAll,"Pop up window Dismiss all button");
		Thread.sleep(1000);
		driver.switchTo().window(parentWindowhandle);
		Thread.sleep(1000);
	}
	
	@Test
	
	public void developerConsolseTest() throws InterruptedException
	{	userProfileNavigation();
		Thread.sleep(1000);
		String parentWindowHandle = driver.getWindowHandle();
		String expectedTitle = "Developer Console";
		
		WebElement developerConsole = driver.findElement(By.xpath("//*[@title=\"Developer Console (New Window)\"]"));
		clickElement(developerConsole,"Developer Console");
		
		Set <String> windowHandles = driver.getWindowHandles();
		for(String windowhandle: windowHandles) 
		{
		if (!windowhandle.endsWith(parentWindowHandle))
			{
			driver.switchTo().window(windowhandle);
			System.out.println(driver.getTitle());// child window title
			}
		}
		
		assertEquals(driver.getTitle(), expectedTitle);
		driver.switchTo().window(parentWindowHandle);
		System.out.println(driver.getTitle());//. parent window title 
		
	}
}
