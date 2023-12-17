package com.infy.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.infy.base.Base;
import com.infy.pageObjects.SouceHomePage;
import com.infy.pageObjects.SouceloginPage;
import com.infy.utilities.Utility;
@Listeners(com.infy.utilities.Listener.class)
public class LoginLogoutFunctionality extends Base {
	
	SouceloginPage souceloginPage;
	SouceHomePage souceHomePage;
	
	
//	@Test(testName="hello123",dataProvider = "loginData1")
	public void verifyErrorMessageWhileLoginWithInvalidCredentials(String username, String password) throws InterruptedException {
		souceloginPage = new SouceloginPage(driver);
		souceloginPage.loginIntoBank(username, password);
		String errorMessage = souceloginPage.getErrorMessage();
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this servic", errorMessage,"Verified");
		
	}

//	@DataProvider(name = "loginData1")
	public Object[][] loginData1() {
		Object[][] data = new Object[2][2];
		data[0][0] = "admin2";
		data[0][1] = "password";

		data[1][0] = "password";
		data[1][1] = "admin2";

		return data;
	}

//	@Test(dataProvider = "loginData2")
	public void verifyLoginIsSuccessfulWithGivenCredentials(String username, String password) throws InterruptedException, IOException {
		souceloginPage = new SouceloginPage(driver);
		souceloginPage.loginIntoBank(username, password);
		souceHomePage = new SouceHomePage(driver);
		if(getCurrentURL().equals(readPropertiesFile("HomePageURL"))) {
			String logo = souceHomePage.getAppLogo();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++"+logo);
			Assert.assertEquals("Swag Labs", logo);
		}
		else if(getCurrentURL().equals(readPropertiesFile("DefaultURL"))) {
			String error=souceloginPage.getErrorMessage();
			System.out.println("+===================="+error);
		}
		
	}

//	@DataProvider(name = "loginData2")
	public Object[][] loginData2() {
		Object[][] data = new Object[3][2];
		data[0][0] = "standard_user";
		data[0][1] = "secret_sauce";

		data[1][0] = "locked_out_user";
		data[1][1] = "secret_sauce";
		
		data[2][0] = "problem_user";
		data[2][1] = "secret_sauce";

		return data;
	}

	@Test (testName="hello456")
	public void verifyLoginLogoutFunctionality() throws InterruptedException, IOException {
		souceloginPage = new SouceloginPage(driver);
		souceloginPage.loginIntoBank(readPropertiesFile("LoginUsername"),readPropertiesFile("LoginPassword"));
		logger.info("entered username and passsword");
		souceHomePage = new SouceHomePage(driver);
		logger.info("entered the homepage");
		souceHomePage.logout();
		logger.info("logging out from soucelab");
		System.out.println("First test case is executed");
	}
	
	@Test
	public void TestCase1() {
		System.out.println("This is my first test case as named");
	}
	
	@Test
	public void TestCase2() {
		System.out.println("This is my second test case as named");
	}
}
