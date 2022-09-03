package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * this method is used to initialize the webdriver on the basis of given browser name
	 * @param browserName
	 * @return it return driver 
	 * */
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		optionsManager = new OptionsManager(prop);
		
		
		System.out.println("browser name is : " +browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver(optionsManager.getfirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getfirefoxOptions()));

			
		}
		else if (browserName.equalsIgnoreCase("safari")) {
		//	driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		}
		else {
			System.out.println("Please pass the right browser : " +browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
		
		}
		
	/**
	 * get the thread local copy of driver
	 * @return
	 */
	
	
		public static WebDriver getDriver() {
			return tlDriver.get();
		}
	
		/**
		 *this method is to intialized 	the properties  
		 **/
	public Properties init_prop() {
		
		prop = new Properties();
		FileInputStream ip = null;
		
		//mvn clean install -Denv="qa"
		String envName =System.getProperty("env");
		System.out.println("Running tests on environment :" +envName);
		
		if(envName== null) {
			System.out.println("No env is given.. hence running it on QA environment");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		else {
			try {
			switch (envName.toLowerCase()) {
			case "qa":
					System.out.println("running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "stage":
				System.out.println("running it on STAGE env");
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				System.out.println("running it on UAT env");
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			case "dev":
				System.out.println("running it on DEV env");
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "prod":
				System.out.println("running it on Prod env");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;

			default:
				System.out.println("please pass the right environment..." +envName);
				throw new FrameworkException("no env is found exception...");
				
			//	break;
			}
			}
			catch(Exception e) {
				
			}
		} 
		
		try{
			prop.load(ip);
		}catch (IOException e) {
			e.printStackTrace();
		}
	
			return prop;
		}
	
	/**
	 * take screenshot
	 */
	
	public String getScreenShot() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path ="./"+ "screenshot/" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
