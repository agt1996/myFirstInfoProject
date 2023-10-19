package com.infy.base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.infy.utilities.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base extends Utility {
	protected WebDriver driver;
	public static void main(String[]args) {
		String pat=System.getProperty("user.dir")+"\\Config.properties";
		System.out.println(pat);
	}
	@BeforeMethod
	public void setup() throws IOException {
		if(readPropertiesFile("DesiredBrowser").equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(readPropertiesFile("DesiredBrowser").equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(readPropertiesFile("DefaultURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@AfterMethod
	public void quit() {
		driver.close();
		driver=null;
	}

}
