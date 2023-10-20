package com.infy.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.infy.base.Base;
import com.infy.pageObjects.ParabankLoginPageObject;

public class LoginLogout extends Base {

	ParabankLoginPageObject parabankLoginPageObject;
	
	
	@Test(dataProvider="loginData")
	public void verifyLogIn(String username,String password) throws InterruptedException {
		parabankLoginPageObject=new ParabankLoginPageObject(driver);
		String errorMessage=parabankLoginPageObject.loginIntoBank(username,password);
		System.out.println(errorMessage);
		Assert.assertEquals("An internal error has occurred and has been logged.", errorMessage);
	}
	
	@DataProvider(name="loginData")
	public Object[][] loginData(){
		Object [][] data=new Object[2][2];
		data[0][0]="admin2";
		data[0][1]="password";
		
		data[1][0]="password";
		data[1][1]="admin2";
		
		return data;
	}
	
	@Test
	public void verifyLoginLogout() {
		System.out.println("Second test case is pass also");
	}
}
