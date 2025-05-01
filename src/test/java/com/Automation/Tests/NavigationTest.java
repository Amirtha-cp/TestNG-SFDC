package com.Automation.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.Automation.Base.SFDCBase;

public class NavigationTest extends SFDCBase {
	@Test
	public void userDropdown() throws InterruptedException {
		userProfileNavigation();
		List<String> expectedDropDownList = Arrays.asList("My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout");
		List<WebElement> navButtonDropdown = driver.findElements(By.xpath("//div[@id = 'userNav-menuItems']/a"));
		List<String> actualDropdownList = new ArrayList<String>();
	
	for (WebElement menuItem: navButtonDropdown) 
	{
		actualDropdownList.add(menuItem.getText());
		System.out.println(menuItem.getText());
		logger.info(menuItem.getText());
		
	}
	
	assertEquals(actualDropdownList, expectedDropDownList, "Navigation List mathes with expected List");
	}

}
