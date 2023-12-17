package com.infy.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	protected WebDriver driver;
	Properties pro;
	protected Logger logger;
//fetching browsers from properties file data
	@BeforeMethod
	public void setup() throws IOException {
		if (readPropertiesFile("DesiredBrowser").equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (readPropertiesFile("DesiredBrowser").equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		logger=LogManager.getLogger(Base.class);
//		PropertyConfigurator.configure("Log4j.properties");
		driver.get(readPropertiesFile("DefaultURL"));
		logger.info("landed on login page");
		driver.manage().window().maximize();
		logger.info("Maximize the window");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
// fetching browsers through TestNG.xml parameters

//	@Parameters("browser")
//	@BeforeMethod
//	public void setup(String br) throws IOException {
//		if(br.equals("Chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		}
//		else if(br.equals("Edge")) {
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//		}
//		driver.get(readPropertiesFile("DefaultURL"));
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		
//	}

	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public String getCurrentURL() {
		String url = driver.getCurrentUrl();
		return url;
	}

	@AfterMethod
	public void quit() {
		driver.quit();
	}

	public String readPropertiesFile(String key) throws IOException {
		String value = null;
		try {
			String pathOfPropertiesFile = System.getProperty("user.dir") + "\\src\\test\\resources\\ConfigurationFiles\\Config.properties";
			FileInputStream fis = new FileInputStream(pathOfPropertiesFile);
			pro = new Properties();
			pro.load(fis);
			value = pro.getProperty(key);

		} catch (Exception e) {

			System.out.println("The Error is :  " + e.getMessage());
		}
		return value;
	}

	public void captureScreenshot(String fileName) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/" + "/" + fileName + ".jpg");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			System.out.println("Screenshot saved successfullyyyyyy");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
	}


}
