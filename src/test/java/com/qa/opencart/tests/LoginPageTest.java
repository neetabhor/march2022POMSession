package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.constants;

public class LoginPageTest extends BaseTest {
	
	@Test(priority =1)
	public void loginPageTitleTest() {
		String actTitle = LoginPage.getLoginPageTitle();
		System.out.println("login Page title :" + actTitle);
		Assert.assertEquals(actTitle, constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority =2)
	public void loginPageURLTest() {
		String actURL = LoginPage.getLoginPageUrl();
		System.out.println("login Page url :" + actURL);
		Assert.assertTrue(actURL.contains(constants.LOGIN_PAGE_URL_FRACTION));
	}
	@Test(priority =3)
	public void forgotpwdLinkExistTest() {
		Assert.assertTrue(LoginPage.isforgotLinkExists());
	}
	@Test(priority =4)
	public void registerExistTest() {
		Assert.assertTrue(LoginPage.isRegisterLinkExist());
	}
	
	@Test(priority =5)
	public void loginTest() {
		Assert.assertTrue(LoginPage
			.dologin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.isLogoutLinkExist());
	}
	
}
