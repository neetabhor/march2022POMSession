package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	
	public WebDriver driver;
	
	protected LoginPage LoginPage;
	protected AccountsPage accPage;
	protected SearchResultPage searchResPage;
	protected ProductInfoPage ProductInfoPage;
	protected RegisterPage registerPage;
	
	public SoftAssert SoftAssert;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		LoginPage = new LoginPage(driver);
		SoftAssert = new SoftAssert();
		
		
		
	}
	
//	@AfterTest
//	public void tearDown () {
//		driver.quit();
//	}
	

}
