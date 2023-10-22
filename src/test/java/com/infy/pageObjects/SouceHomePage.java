package com.infy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SouceHomePage {

WebDriver driver;
double totalAmount=0;
double totalAmountOfItemsInCart=0;
	
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
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	private List<WebElement> itemsListInCart;
	
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
	
	public void addItemsToCartBelow10$() throws InterruptedException {
		for(WebElement item:items) {
			String priceString=item.getText();
			double price=Double.parseDouble(priceString.replace("$", ""));
			if(price<=10.00){
				totalAmount=totalAmount+price;
//				System.out.println(price);
				Thread.sleep(1000);
				item.findElement(By.xpath("..//button")).click();
			}
		}
		Thread.sleep(3000);
		cartButton.click();
		
	}
	
	public void getListOfItemsInCart() {
		System.out.println("Below are the items added to cart");
		for(WebElement CartItem:itemsListInCart) {
			String itemName=CartItem.getText();
			String cartItemPriceString=CartItem.findElement(By.xpath("../..//div[@class='inventory_item_price']")).getText();
			double cartItemPrice=Double.parseDouble(cartItemPriceString.replace("$", ""));
			totalAmountOfItemsInCart=totalAmountOfItemsInCart+cartItemPrice;
			System.out.println(itemName);
		}
	}
	
	public void verifyAmountIncart() {
		Assert.assertEquals(totalAmount, totalAmountOfItemsInCart);
		System.out.println("Total amount of selected items is "+totalAmount+"$"+" which is absolutly equal to "+totalAmountOfItemsInCart+ "$"+" as total amount of items in cart ");
	}
	public void logout() throws InterruptedException {
		menubar.click();
		Thread.sleep(2000);
		logoutBtn.click();
	}
	
	
}
