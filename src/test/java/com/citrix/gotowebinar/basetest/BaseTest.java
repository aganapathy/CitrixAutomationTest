package com.citrix.gotowebinar.basetest;

import java.net.MalformedURLException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.citrix.gotowebinar.base.Base;
import com.citrix.gotowebinar.entities.webinar;
import com.citrix.gotowebinar.environment.EnvironmentConfig;
import com.citrix.gotowebinar.pageObjects.SignInPage;

public class BaseTest extends Base {
	
	public Properties testProp;
	private EnvironmentConfig envconf=new EnvironmentConfig();
	protected String testEnv=null;
	protected webinar webinarEntity=null;
	
	
	
	public SignInPage openWebinarUrl()
	{
		driver.get(testProp.getProperty("gotoWebinarSignInPage"));
		try {
			Base.ignoreCertificateError(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SignInPage(driver,testEnv);
	}
			
	@BeforeMethod
	@Parameters({ "test.env"})
	public void beforeMethod(@Optional("prod")String testEnv) throws MalformedURLException,InterruptedException
	{
		this.testEnv=testEnv;		
		testProp=envconf.loadTestData(testEnv);
		
		driver = getDriver();
		driver.manage().deleteAllCookies();
	}

	@AfterMethod
	public void afterMethod() {
		try {
			if (driver != null)
				driver.quit();
		} catch (Exception e) {
			System.out.println("Error is quiting driver" + e.getMessage());
		}
		driver = null;
	}
	
	
}
