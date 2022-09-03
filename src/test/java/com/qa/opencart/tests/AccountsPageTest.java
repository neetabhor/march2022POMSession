package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.constants;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accSetup() {
		accPage = LoginPage.dologin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountPageTitle();
		System.out.println("Acc page title : " + actTitle);
		Assert.assertEquals(actTitle, constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccountPageURL();
		System.out.println("Acc page url : " + actURL);
		Assert.assertTrue(actURL.contains(constants.ACCOUNT_PAGE_TITLE_FRACTION));
	}
	
	@Test
	public void accPageSectionsTest() {
		List<String> actAccSelList = accPage.getAccountPageSelectionList();
		System.out.println("Actual account selection list: " +actAccSelList);
		Assert.assertEquals(actAccSelList, constants.EXPECTED_ACCOUNT_SELECTION_LIST); 
		
	}
	
	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void SearchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
//	@Test
//	public void logoutTest() {
//		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccessMsg(),constants.LOGOUT_SUCCESS_MSG);
//		
//	}
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	
	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchkey) {
		searchResPage = accPage.doSearch(searchkey);
		Assert.assertTrue(searchResPage.getSearchResultsCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductName(){
		return ExcelUtil.getTestData(constants.PRODUCT_SHEET_NAME);
		
	}
		
	@Test(dataProvider = "getProductName")
	public void selectProductTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		ProductInfoPage = searchResPage.selectProduct(productName);
		String productHeader =ProductInfoPage.getProductHeaderName();
		System.out.println("Product Header: " +productHeader);
		Assert.assertEquals(productHeader, productName);
	}
	
	
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}

	
	@Test(dataProvider = "getProductData")
	public void productImageTest(String searchKey, String productName, int productimageCount) {
		searchResPage = accPage.doSearch(searchKey);
		ProductInfoPage = searchResPage.selectProduct(productName);
		Assert.assertEquals(ProductInfoPage.getProductImageCount(), productimageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("Macbook");
		ProductInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = ProductInfoPage.getProductInformation();
		SoftAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		SoftAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		SoftAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		SoftAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		SoftAssert.assertAll();
	}
	
	@Test
	public void productInfoDescriptionTest() {
		searchResPage = accPage.doSearch("Macbook");
		ProductInfoPage = searchResPage.selectProduct("MacBook Pro");
		SoftAssert.assertTrue(ProductInfoPage.getProductInfoPageInnerText().contains("Latest Intel mobile architecture"));
		SoftAssert.assertTrue(ProductInfoPage.getProductInfoPageInnerText().contains("new Core 2 Duo MacBook Pro is over 50%"));
		SoftAssert.assertTrue(ProductInfoPage.getProductInfoPageInnerText().contains("Connect. Create. Communicate."));
		SoftAssert.assertAll();
	}
	
	@Test
	public void addToCartTest() {
		searchResPage = accPage.doSearch("Macbook");
		ProductInfoPage = searchResPage.selectProduct("MacBook Pro");
		String actSuccessMsg =ProductInfoPage.
				enterQty("1")
					.ClickonAddToCart()
						.getCartSuceessMsg();
		System.out.println("cart msg: "+actSuccessMsg);
		SoftAssert.assertTrue(actSuccessMsg.contains("MacBook Pro"));
		String actCartItemText =ProductInfoPage.getCartItemText();
		SoftAssert.assertTrue(actCartItemText.contains("1" +" items(s)"));
	}
	
	
	
	
	
}
