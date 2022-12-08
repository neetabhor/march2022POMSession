package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

import bsh.org.objectweb.asm.Constants;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.private By locator :OR
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton =By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logoutSuccessMsg = By.cssSelector("div#common-success h1");

	//2. page constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//3.page actions:
	
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(constants.LOGIN_PAGE_TITLE, constants.DEFAULT_TIME_OUT);
	}
	
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(constants.LOGIN_PAGE_URL_FRACTION, constants.DEFAULT_TIME_OUT);
	}
	
	public AccountsPage dologin(String un, String pwd) {
			System.out.println("login creds are: " + un + " : " + pwd);
			eleUtil.waitForElementVisible(emailId, constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(un);
			eleUtil.doSendkeys(password, pwd);
			eleUtil.doClick(loginButton);
			return new AccountsPage(driver);
	
	}
	public boolean isforgotLinkExists() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}

	public String getLogoutSuccessMsg() {
		return eleUtil.waitForElementVisible(logoutSuccessMsg, constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		
	}
	
	
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
