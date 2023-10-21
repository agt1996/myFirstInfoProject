package com.infy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//div[@class='inventory_item_price']")
	private List<WebElement> items;
	
	@FindBy(xpath="//*[@id='shopping_cart_container']/a")
	private WebElement cartButton;
	
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
	
	public void getItemsBelow10$() throws InterruptedException {
		for(WebElement item:items) {
			String priceString=item.getText();
			double price=Double.parseDouble(priceString.replace("$", ""));
			if(price<=10.00){
				System.out.println(price);
				item.findElement(By.xpath("..//button")).click();
			}
		}
		Thread.sleep(3000);
		cartButton.click();
		
	}
	public void logout() throws InterruptedException {
		menubar.click();
		Thread.sleep(2000);
		logoutBtn.click();
	}
	
	
}
