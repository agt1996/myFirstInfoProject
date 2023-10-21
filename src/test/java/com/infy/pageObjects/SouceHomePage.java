package com.infy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SouceHomePage {

WebDriver driver;
	
	@FindBy(xpath="//div[@class='app_logo']")
	private WebElement appLogo;
	
	@FindBy(xpath="//div[@class='header_secondary_container']")
	private WebElement products;
	
	@FindBy(xpath="//*[@id='react-burger-menu-btn']")
	private WebElement menubar;
	
	@FindBy(xpath="//*[@id='logout_sidebar_link']")
	private WebElement logoutBtn;
	
	
	public SouceHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getAppLogo() {
		String logo=appLogo.getText();
		return logo;
	}
	
	public Boolean isProductDisplayed() {
		products.isDisplayed();
		return true;
	}
	
	public void logout() throws InterruptedException {
		menubar.click();
		Thread.sleep(2000);
		logoutBtn.click();
	}
	
	
}
