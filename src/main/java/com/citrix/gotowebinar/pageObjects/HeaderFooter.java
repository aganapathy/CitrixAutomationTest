package com.citrix.gotowebinar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.citrix.gotowebinar.base.keywords;

public class HeaderFooter extends keywords{

		private By logout_link=By.linkText("Log Out");
		
		public HeaderFooter(WebDriver driver,String env) {
			super(env);
			this.driver = driver;
		}
		
		
		public SignInPage Logout()
		{
			click(logout_link);
			waitForPageLoad(driver);
			return  new SignInPage(driver,env);
		}	
		
}
