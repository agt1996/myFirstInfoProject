package com.infy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.infy.base.Base;

public class ParabankLoginPageObject extends Base {
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='loginPanel']/form/div[1]/input")
	private WebElement usernameEditBox;
	
	@FindBy(xpath="//*[@id='loginPanel']/form/div[2]/input")
	private WebElement passwordEditBox;
	
	@FindBy(xpath="//*[@id='loginPanel']/form/div[3]/input")
	private WebElement loginButton;
	
	@FindBy(xpath="//*[@id='rightPanel']/p")
	private WebElement errorMessage;

	
	public ParabankLoginPageObject(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String loginIntoBank(String username,String password) throws InterruptedException {
		usernameEditBox.sendKeys(username);
		passwordEditBox.sendKeys(password);
		loginButton.click();
		Thread.sleep(2000);
		String errorMessag=errorMessage.getText();
		return errorMessag;
		
	}
}
