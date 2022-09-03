package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImage = By.cssSelector("ul.thumbnails img");	
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By productQty = By.name("quantity");
	private By AddtoCart = By.id("button-cart");
	private By cartSuccessMsg = By.cssSelector("div.alert.alert-success");
	private By cart = By.cssSelector("div#cart button.dropdown-toggle");
	
	
	Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName() {
		return eleUtil.waitForElementVisible(productHeader, constants.DEFAULT_ELEMENT_TIME_OUT).getText();
			
	}
	
	public int getProductImageCount() {
		return eleUtil.waitForElementsVisible(productImage, constants.DEFAULT_ELEMENT_TIME_OUT).size();
		
	}
	
	public Map<String, String> getProductInformation() {
		productInfoMap = new HashMap<String,String>();
		productInfoMap.put("name", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		productInfoMap.forEach((k,v)-> System.out.println(k+":"+v));
		return productInfoMap;
		
	}
	
	private void getProductMetaData() {
		//meta data:
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		System.out.println("Total product meta data: " +metaDataList.size());
		
		for(WebElement e: metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		
		}
		
	}
	
	private void getProductPriceData() {
		List<WebElement>priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim();
		String ExTaxPrice = priceList.get(1).getText().trim();
		
		productInfoMap.put("price", price);
		productInfoMap.put("extraprice", ExTaxPrice);
		
	}
	
	public String getProductInfoPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String pageInnerText = js.executeScript("return document.documentElement.innerText").toString();
		System.out.println("============\n" +pageInnerText+ "\n===========");
		return pageInnerText;
		
	}
	
	public ProductInfoPage enterQty(String qty) {
		eleUtil.doSendkeys(productQty, qty);
		return this;
	}
	
	public ProductInfoPage ClickonAddToCart() {
		eleUtil.doClick(AddtoCart);
		return this;
	}
	
	
	public String getCartSuceessMsg() {
		return eleUtil.waitForElementVisible(cartSuccessMsg, constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	
	public String getCartItemText() {
		return eleUtil.doGetText(cart);
	}
	
	
	
	
}
