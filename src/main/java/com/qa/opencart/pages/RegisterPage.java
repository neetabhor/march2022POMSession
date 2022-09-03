package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage{
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By address1 = By.id("input-address-1");
	private By city = By.id("input-city");
	private By postcode = By.id("input-postcode");
	private By country = By.id("input-country");
	private By regional = By.id("input-zone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type='radio']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0' and @type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		
	}
	
	public boolean registerUser(String firstName, String lastName,
			String email, String telephone, String password, String subsribe) {

		WebElement firstName_ele = eleUtil.waitForElementVisible(this.firstName, constants.DEFAULT_ELEMENT_TIME_OUT);
		firstName_ele.clear();
		firstName_ele.sendKeys(firstName);
		
		eleUtil.doSendkeys(this.lastName, lastName);
		eleUtil.doSendkeys(this.email, email);
		eleUtil.doSendkeys(this.telephone, telephone);
		
		eleUtil.doSendkeys(this.password, password);
		eleUtil.doSendkeys(this.confirmpassword, password);


		if (subsribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);

		
		String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, constants.DEFAULT_TIME_OUT).getText();

		if (successMesg.contains(constants.ACCOUNT_REGISTER_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
		}
		return false;
	}
	
}
