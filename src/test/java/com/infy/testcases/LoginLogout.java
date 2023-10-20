package com.infy.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.infy.base.Base;
import com.infy.pageObjects.SouceHomePage;
import com.infy.pageObjects.SouceloginPage;
import com.infy.utilities.Utility;

public class LoginLogout extends Base {

	SouceloginPage souceloginPage;
	SouceHomePage souceHomePage;

	@Test(dataProvider = "loginData1")
	public void verifyLoginError(String username, String password) throws InterruptedException {
		souceloginPage = new SouceloginPage(driver);
		souceloginPage.loginIntoBank(username, password);
		String errorMessage = souceloginPage.getErrorMessage();
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage);
	}

	@DataProvider(name = "loginData1")
	public Object[][] loginData1() {
		Object[][] data = new Object[2][2];
		data[0][0] = "admin2";
		data[0][1] = "password";

		data[1][0] = "password";
		data[1][1] = "admin2";

		return data;
	}

	@Test(dataProvider = "loginData2")
	public void verifyLoginWithGivenCredentials(String username, String password) throws InterruptedException {
		souceloginPage = new SouceloginPage(driver);
		souceloginPage.loginIntoBank(username, password);
		souceHomePage = new SouceHomePage(driver);
		if(souceHomePage.isProductDisplayed()) {
			String logo = souceHomePage.getAppLogo();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++"+logo);
			Assert.assertEquals("Swag Labs", logo);
		}
		else {
			String error=souceloginPage.getErrorMessage();
			System.out.println(error);
		}
		
	}

	@DataProvider(name = "loginData2")
	public Object[][] loginData2() {
		Object[][] data = new Object[2][2];
		data[0][0] = "standard_user";
		data[0][1] = "secret_sauce";

		data[1][0] = "locked_out_user";
		data[1][1] = "secret_sauce";

		return data;
	}

	@Test
	public void verifyLoginLogout() throws InterruptedException, IOException {
		Utility utility=new Utility();
		souceloginPage = new SouceloginPage(driver);
		souceloginPage.loginIntoBank(utility.readPropertiesFile("LoginUsername"), utility.readPropertiesFile("LoginPassword"));
		souceHomePage = new SouceHomePage(driver);
		souceHomePage.logout();
	}
}
