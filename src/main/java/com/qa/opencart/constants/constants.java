package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class constants {
	
	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public static final String  LOGIN_PAGE_URL_FRACTION = "route=account/login"; 
	
	public static final String ACCOUNT_PAGE_TITLE = "My Account"; 
	public static final String ACCOUNT_PAGE_TITLE_FRACTION = "route=account/account";

	public static final List<String> EXPECTED_ACCOUNT_SELECTION_LIST = 
			Arrays.asList("My Account" , "My Orders" , "My Affiliate Account" ,"Newsletter");
	
	public static final int  DEFAULT_ELEMENT_TIME_OUT = 10; 
	public static final int  DEFAULT_TIME_OUT = 5;
	public static final String LOGOUT_SUCCESS_MSG = "Account Logout";
	public static final CharSequence ACCOUNT_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created";
	
	//**************sheeta names *******************//
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_SHEET_NAME = "product";


	

}
