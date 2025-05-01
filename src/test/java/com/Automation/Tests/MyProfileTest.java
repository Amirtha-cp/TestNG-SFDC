package com.Automation.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.Automation.Base.UserMenuDropDown;


public class MyProfileTest extends UserMenuDropDown { 
	
	
	@Test
	public void profilePageTest() throws InterruptedException {
	myProfile();
	assertTrue(driver.getCurrentUrl().contains("UserProfilePage"), "Profile page not loaded");

	}
	
	@Test
	public void editIframeTest() throws InterruptedException {

	assertTrue(editContactIframe());
	
	}
    @Test
    
    public void aboutTabTest() throws InterruptedException {

    	myProfile();
    	if (editContactIframe()) 
    	{
    	WebElement firstName = driver.findElement(By.id("firstName"));
    	String expectedFirstName = firstName.getAttribute("value");
    	logger.info(expectedFirstName);
		WebElement lastName = driver.findElement(By.id("lastName"));
		enterText(lastName,"Panneer", "Lastname");
		String expectedLastName = "Panneer";
		String expectedProfileName = expectedFirstName +" "+expectedLastName;
		logger.info (expectedProfileName);
		WebElement saveAll = driver.findElement(By.xpath("//input[contains(@value,\"Save All\")]"));
		clickElement(saveAll,"Save All");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		WebElement userProfileName = driver.findElement(By.id("userNavLabel"));
		assertEquals(userProfileName.getText(), expectedProfileName);
		
    	}
    	else {
    		logger.error("Something went worng with IFRAME Window to perfoem Edit contact information");
    	}
    }
    
    @Test
    
    public void postLinkTest() throws InterruptedException {
    	myProfile();
    	WebElement postLinkBtn = driver.findElement(By.id("publisherAttachTextPost"));
		clickElement(postLinkBtn,"Post link");
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		Thread.sleep(3000);
		String postComment = "Hybrid Framework using Testng, Java design pattern singleton and Modularity implemtation Testing after page refresh";
		WebElement postTextbox = driver.findElement(By.xpath("//body[@role=\"textbox\"]"));
		clickElement(postTextbox, "Post Comment in Multiline Textbox");
		enterText(postTextbox,postComment, "in comment box after clicking post link ");
		driver.switchTo().defaultContent();
		WebElement shareBtn = driver.findElement(By.id("publishersharebutton")); 
		clickElement(shareBtn,"Share button");
		driver.navigate().refresh();
		Thread.sleep(5000);
		List <WebElement> postedComments = driver.findElements(By.xpath("//p"));
		
		logger.info(postedComments.get(1).getText());
		logger.info(postComment);
		assertEquals(postedComments.get(1).getText(), postComment);
   	
    }
   
    @Test
    public void uploadFileTest() throws InterruptedException {
    
    	myProfile();
    	WebElement fileLinkBtn = driver.findElement(By.id("publisherAttachContentPost"));
		clickElement(fileLinkBtn,"File Link");
		
		WebElement uploadFile = driver.findElement(By.id("chatterUploadFileAction"));
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(uploadFile));
		clickElement(uploadFile,"Upload file from computer");
		WebElement chooseFile = driver.findElement(By.id("chatterFile"));
		enterText(chooseFile,"/Users/mura/Downloads/LoginHistory1742293555793.csv","Uploaded file ");
		Thread.sleep(1000);
		WebElement shareFileBtn = driver.findElement(By.xpath("//input[@value=\"Share\"]"));
		clickElement(shareFileBtn,"Share button");
		List <WebElement> posted = driver.findElements(By.xpath("//div[@class = \"preamblecontainer displayblock\"]/span[text()=\" posted a file.\"]"));
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(posted));
		assert(posted.get(1).getText().contains(" posted a file."));
    }
    
   
}