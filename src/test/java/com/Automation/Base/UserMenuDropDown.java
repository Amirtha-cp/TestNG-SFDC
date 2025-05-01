package com.Automation.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserMenuDropDown extends SFDCBase{
	
//	public void userProfileNavigation() throws InterruptedException {
//
//		login(TEST_USERNAME,TEST_PASSWORD);
//		Thread.sleep(1000);
//		WebElement navButton = driver.findElement(By.id("userNavButton"));
//		clickElement(navButton, "My Profile Navigation button");
////		
//	}
	
	
	public void myProfile() throws InterruptedException {
		userProfileNavigation();
		WebElement myProfileBtn = driver.findElement(By.xpath("//*[@class=\"menuButtonMenuLink firstMenuItem\"]"));
		clickElement(myProfileBtn,"My Profile");
	}
	
	public void mySetting() throws InterruptedException {
		Thread.sleep(1000);
		userProfileNavigation();
		//Testcase to click on My Setting option from dropdown options
		WebElement mySettingBtn = driver.findElement(By.xpath("//*[@title=\"My Settings\"]"));
		clickElement(mySettingBtn,"My Settings");
	}
 
	public  boolean editContactIframe() throws InterruptedException {
//		
//		myProfile();
		WebElement editProfileBtn = driver.findElement(By.xpath("//img[@title=\"Edit Profile\"]"));
		clickElement(editProfileBtn,"Edit Profile");
		Thread.sleep(2000);
		
		//Testcase to click on ABout Tab after clikcing edit icon.
		//Iframe is opened with two tabs About and Contactcs
		driver.switchTo().frame("contactInfoContentId");
		WebElement aboutTab = driver.findElement(By.xpath("//li/a[contains(text(),\"About\")]"));
		clickElement(aboutTab, "About Tab");
		
		waitForVisibility(aboutTab, 60, "About Tab from Iframe window after switch");
		boolean aboutTabvisible = aboutTab.isDisplayed();
		return aboutTabvisible;
	}
	

	
	public void addReports() throws InterruptedException {

//		mySetting();	
		WebElement displayAndLayout = driver.findElement(By.id("DisplayAndLayout_font"));
		clickElement(displayAndLayout,"Display & Layout");
		WebElement cutomMyTabs = driver.findElement(By.xpath("//span[@id=\"CustomizeTabs_font\"]"));
		clickElement(cutomMyTabs,"Customize My tabs");
		Thread.sleep(1000);
		WebElement cutomAppDropdown = driver.findElement(By.xpath("//select[@id=\"p4\"]"));
		clickElement(cutomAppDropdown,"Custom App Dropdown");
		selectByTextData(cutomAppDropdown, "Salesforce Chatter", "Salesforce Chatter selected");
		WebElement availableTabs = driver.findElement(By.id("duel_select_0"));
		clickElement(availableTabs,"Available tabs");
		selectByTextData(availableTabs, "Reports", "Reports tab");
		WebElement addArrowbtn = driver.findElement(By.id("duel_select_0_right"));
		clickElement(addArrowbtn,"Added Reports Tab");
		WebElement saveBtn = driver.findElement(By.xpath("//input[@name=\"save\"]"));
		clickElement(saveBtn,"Added Reports Tab");
		Thread.sleep(1000);
		
	}
	

	public void removeReports() throws InterruptedException {

		mySetting();	
		WebElement displayAndLayout = driver.findElement(By.id("DisplayAndLayout_font"));
		clickElement(displayAndLayout,"Display & Layout");
		WebElement cutomMyTabs = driver.findElement(By.xpath("//span[@id=\"CustomizeTabs_font\"]"));
		clickElement(cutomMyTabs,"Customize My tabs");
		Thread.sleep(1000);
		WebElement cutomAppDropdown = driver.findElement(By.xpath("//select[@id=\"p4\"]"));
		clickElement(cutomAppDropdown,"Custom App Dropdown");
		selectByTextData(cutomAppDropdown, "Salesforce Chatter", "Salesforce Chatter selected");
		WebElement selectedTabs = driver.findElement(By.id("duel_select_1")); 
		clickElement(selectedTabs,"Selected tabs");
		selectByTextData(selectedTabs, "Reports", "Reports tab");
		
		WebElement removeArrowbtn = driver.findElement(By.id("duel_select_0_left"));
		clickElement(removeArrowbtn,"Removed Reports Tab");
		
		WebElement saveBtn = driver.findElement(By.xpath("//input[@name=\"save\"]"));
		clickElement(saveBtn,"Removed Reports Tab");
	}
}



