package com.Automation.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.Automation.Base.LoginPage;
import com.Automation.Base.SFDCBase;
import com.Automation.Utility.Constants;
import com.Automation.Utility.ExcelUtils;

public class LoginTest extends SFDCBase {
	
	
	@DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData() {
        return ExcelUtils.getFilteredData(Constants.LOGINCREDENTIALS_EXCEL, "Sheet1", "Y");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return ExcelUtils.getFilteredData(Constants.LOGINCREDENTIALS_EXCEL, "Sheet1", "N");
    }
    
    @Test(dataProvider = "validLoginData")
    public void validLoginTest(String username, String password, String isValid) throws Exception {
        login(username, password);
        Thread.sleep(1000);

        assertTrue(driver.getTitle().contains(expectedPageTitleValidLogin), "Valid login test passed!");
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(String username, String password, String isValid) throws Exception {
        login(username, password);
        Thread.sleep(1000);
        WebElement loginError = driver.findElement(By.xpath("//div[@class=\"loginError\" and @id=\"error\"]"));
//		Thread.sleep(3000);
		getContent(loginError," LOGIN ERROR MESSAGE DISPLAYED");
        assertTrue(driver.getTitle().contains(expectedPageTitleInValidLogin), "Invalid login test doesnot allow user to login");
    }
    
    @Test
    
    public void rememberMeTest() throws Exception {
//    	WebElement username1 = driver.findElement(By.id("username")); //		using XPATH //*[@id="username"]
//		waitForVisibility(username1,60, "Email");
//		enterText(username1, TEST_USERNAME, "Username");
//		
//		WebElement password1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password")))); //		using xpath //*[@id="password"]
//		enterText(password1, TEST_PASSWORD, "Password");
//    	
//		WebElement rememberMe = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));
//		clickElement(rememberMe, "Clicked remeber me");
    	login(TEST_USERNAME,TEST_PASSWORD);
		
//		WebElement loginButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("Login"))));
//		clickElement(loginButton);
//		Thread.sleep(1000);
		assertTrue(driver.getTitle().contains(expectedPageTitleValidLogin), "Valid login test passed!");
    }
    

    @Test
    public void forgotPasswordTest() throws Exception {

    	WebElement forgotPwdBTN = driver.findElement(By.linkText("Forgot Your Password?"));
    	clickElement(forgotPwdBTN, "Forgot Password Button");
    	Thread.sleep(1000);
    	WebElement uname = driver.findElement(By.id("un"));
    	enterText(uname, TEST_USERNAME, "Username");
    	
    	WebElement continueBtn = driver.findElement(By.id("continue"));
    	Thread.sleep(1000);
    	
    	clickElement(continueBtn, "Clicked Continue Button");
    	List <WebElement> resendEmailText = driver.findElements(By.tagName("p"));
    	assertEquals(resendEmailText.get(0).getText(),"We’ve sent you an email with a link to finish resetting your password.");   

    }

    @Test
    public void logoutTest() throws Exception {
    	
    	logout();
    	
    	assertTrue(driver.getTitle().contains("Login | Salesforce"));
    }
}
