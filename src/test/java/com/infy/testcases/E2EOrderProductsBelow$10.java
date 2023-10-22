package com.infy.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.infy.base.Base;
import com.infy.pageObjects.SouceHomePage;
import com.infy.pageObjects.SouceloginPage;

public class E2EOrderProductsBelow$10 extends Base {
	SouceloginPage souceloginPage;
	SouceHomePage souceHomePage;
	
	@Test
	public void orderProductsbelow10$() throws InterruptedException, IOException {
		souceloginPage = new SouceloginPage(driver);
		souceloginPage.loginIntoBank(readPropertiesFile("LoginUsername"),readPropertiesFile("LoginPassword"));
		souceHomePage = new SouceHomePage(driver);
		if(getCurrentURL().equals(readPropertiesFile("HomePageURL"))) {
			souceHomePage.addItemsToCartBelow10$();
			souceHomePage.getListOfItemsInCart();
			souceHomePage.verifyAmountIncart();
		}
		
		
		
	}
	
	

}
