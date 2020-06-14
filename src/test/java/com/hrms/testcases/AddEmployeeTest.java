package com.hrms.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.scanner.Constant;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelUtility;

public class AddEmployeeTest extends CommonMethods {
	
	
	@Test(dataProvider = "userDataFromExcel", groups = {"homework", "addEmp", "regression"}, enabled = true)
	public void addEmp(String firstName,String lastName,String username, String password) {
		//login into hrms
		login.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		
		//navigate to Add Employee
		dashboard.navigateToAddEmployee();
		wait(1);
		
		//add employee information 
		sendText(addEmp.firstName, firstName);
		sendText(addEmp.lastName, lastName);
		//get EmployeeID
		String expectedEmpId= addEmp.employeeId.getAttribute("value");
		
		//click on Create login details
		click(addEmp.checkboxLoginDetails);
		wait(1);
		
		sendText(addEmp.username, username);
		sendText(addEmp.userpassword, password);
		sendText(addEmp.repassword, password);
		jsClick(addEmp.saveBtn);
		wait(1);
		
		waitForVisibility(pdetails.personalDetails);
		String actualEmpId=pdetails.employeeId.getAttribute("value");
		Assert.assertEquals(actualEmpId, expectedEmpId, "Employee ID did not match");
		
		//take screenshot
		takeScreenshot(firstName+"_"+lastName);
	}
	
	@DataProvider
	public Object[][]getData() {
		Object [][] data= {
				{"Raj", "Capoor","raj123", "AmirKhan_@123"},
				{"John", "Smith","@123", "AmirKhan_@123"},
				{"Mary", "Ann","mary123", "AmirKhan_@123"},
			//	{"Rohani", "Sakhi", "rohani123","AmirKhan_@123"}
		};
		return data;
	}

	@DataProvider(name = "userDataFromExcel")
	public Object[][] getData2() {
		return ExcelUtility.excelIntoArray(Constants.TESTDATA_FILEPATH, "Employee");
	}
}
