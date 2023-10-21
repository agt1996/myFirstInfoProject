package com.infy.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
	
	public String getTitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public String getCurrentURL() {
		String url=driver.getCurrentUrl();
		return url;
	}

	
	
	@AfterMethod
	public void quit() {
		driver.close();
		driver=null;
	}
	
	public String readPropertiesFile(String key) throws IOException {
		String pathOfPropertiesFile=System.getProperty("user.dir")+"\\Config.properties";
		FileInputStream fis=new FileInputStream(pathOfPropertiesFile);
		Properties pr=new Properties();
		pr.load(fis);
		String value=pr.getProperty(key);
		return value;
	}

}
