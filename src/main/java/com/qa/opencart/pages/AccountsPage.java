package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By sectionsHeader = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getAccountPageTitle() {
		return eleUtil.waitForTitleIs(constants.ACCOUNT_PAGE_TITLE, constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountPageURL () {
		return eleUtil.waitForUrlContains(constants.ACCOUNT_PAGE_TITLE_FRACTION, constants.DEFAULT_TIME_OUT);
	}
	
	public List<String> getAccountPageSelectionList(){
		List<WebElement> secList = eleUtil.getElements(sectionsHeader);
		List<String> secValList = new ArrayList<String>();
		for (WebElement e: secList) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	
	public LoginPage clickOnLogout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
		return new LoginPage(driver);
	}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	
	public SearchResultPage doSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendkeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultPage(driver);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}
