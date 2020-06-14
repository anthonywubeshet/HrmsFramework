package com.hrms.testcases;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

public class LoginTest extends CommonMethods {

	//@BeforeMethod
	//public void openBrowser() {
	//	setUp();
	//	initialize();
	//}
	
	//@AfterMethod
	//public void closeBroswer() {
	//	tearDown();
	//}
	
	@Test(groups = "smoke")
	public void validAdminLogin() {
		//test.info("Entering valid admin credentilas");
		//LoginPageElements login =new LoginPageElements();
		sendText(login.username, ConfigsReader.getProperty("username"));
		sendText(login.password, ConfigsReader.getProperty("password"));
		click(login.loginBtn);
		wait(2);
		
		//DashBoardPageElements dash=new DashBoardPageElements();
		String expectedUser="Welcome Admin";
		String actualUser=dashboard.welcome.getText();
		Assert.assertEquals(actualUser, expectedUser, "Admin is NOT logged in");
		//Assert.assertTrue(actualUser.contains(ConfigsReader.getProperty("username")));
	}
	
	@Test(groups = "regression")
	public void invalidPasswordLogin() {
		//LoginPageElements login =new LoginPageElements();
		sendText(login.username, ConfigsReader.getProperty("username"));
		sendText(login.password, "adsada");
		click(login.loginBtn);
		String expected="Invalid credentials";
		
		Assert.assertEquals(login.errorMsg.getText(), expected, "Error message text is not matched");
	}
	
	@Test(groups = "regression")
	public void emptyUsernameLogin() {
		//LoginPageElements login =new LoginPageElements();
		sendText(login.password, ConfigsReader.getProperty("password"));
		click(login.loginBtn);
		String expected="Username cannot be empty";
		
		Assert.assertEquals(login.errorMsg.getText(), expected);
	}
	
	@Test
	public void timeStamp() {
		Date d=new Date();
		System.out.println(d.getTime());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		System.out.println(sdf.format(d.getTime()));
	}
}
