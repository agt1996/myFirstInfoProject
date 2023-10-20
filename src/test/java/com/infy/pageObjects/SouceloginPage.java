package com.infy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.infy.base.Base;

public class SouceloginPage extends Base {
	WebDriver driver;
	
	@FindBy(id="user-name")
	private WebElement usernameEditBox;
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement passwordEditBox;
	
	@FindBy(id="login-button")
	private WebElement loginButton;
	
	@FindBy(xpath="//h3[@data-test='error']")
	private WebElement errorMessage;

	@FindBy(xpath="//div[@id='login_credentials']//br")
	private List<WebElement> loginCredentials;
	
	@FindBy(xpath="//div[@class='login_password']")
	private WebElement loginPassword;
	
	public SouceloginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginIntoBank(String username,String password) throws InterruptedException {
		usernameEditBox.sendKeys(username);
		passwordEditBox.sendKeys(password);
		loginButton.click();
		Thread.sleep(2000);
		
	}
	
	public String getErrorMessage() {
		String errorMessag=errorMessage.getText();
		return errorMessag;
	}
	
	public String getListOfLoginCredentials(int i) {
		String loginCre=loginCredentials.get(i).getText();
		return loginCre;
		
	}
	public String getPassword() {
		String loginPass=loginPassword.getText();
		return loginPass;
		
	}
}
