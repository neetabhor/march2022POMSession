package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productNameLink;
	private By searchResults = By.cssSelector("div.product-layout.product-grid");
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public int getSearchResultsCount() {
		return eleUtil.waitForElementsVisible(searchResults, constants.DEFAULT_ELEMENT_TIME_OUT).size();
		
	}
	
	public ProductInfoPage selectProduct(String productName){
		productNameLink =By.linkText(productName);
		eleUtil.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}
	
}
