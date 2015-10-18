package com.citrix.gotowebinar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.citrix.gotowebinar.base.keywords;

public class SignInPage extends keywords {

	
	private By byEmail_input=By.id("emailAddress");
	private By byPwd_input=By.id("password");
	private By bySignIn_btn=By.id("submit");	
	
	public SignInPage(WebDriver driver,String testenv) {
		super(testenv);
//		s_logger.info("Loading SignIn Page");
		this.driver = driver;
//		s_logger.info("Loaded SignIn Page");
	}

	public WebinarHomePage SignIn(String email, String password)
	{
		waitForPageLoad(driver);
		
		if(findIfElementDisplayed(byEmail_input))
		{
			type(byEmail_input, email);
			type(byPwd_input, password);
			click(bySignIn_btn);
			waitForPageLoad(driver);
			return new WebinarHomePage(driver,env);
		}
		
//		s_logger.info("SignIn Page : Unable to find email address");
		return null;
	}
	


}
