package com.citrix.gotowebinar.testcases;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.citrix.gotowebinar.basetest.BaseTest;
import com.citrix.gotowebinar.pageObjects.HeaderFooter;
import com.citrix.gotowebinar.pageObjects.LeftNavObjects;
import com.citrix.gotowebinar.pageObjects.ManageWebinarPage;
import com.citrix.gotowebinar.pageObjects.ScheduleWebinarPage;
import com.citrix.gotowebinar.pageObjects.SignInPage;
import com.citrix.gotowebinar.pageObjects.WebinarHomePage;
import com.citrix.gotowebinar.utility.Utilities;

public class ScheduleWebinar extends BaseTest {
	
	protected SignInPage mySignInPage=null;
	protected WebinarHomePage myWebinarHomePage=null;	
	protected LeftNavObjects myLeftNavObjects=null;
	protected ScheduleWebinarPage myScheduleWebinarPage=null;
	protected ManageWebinarPage myManageWebinarPage=null;
	
	@AfterMethod
	public void afterMethod() {
		try {
			if (driver != null)
				driver.quit();
		} catch (Exception e) {
			System.out.println("Coulf not quit");
		}
		driver = null;
	}
	
	@Test(enabled = true)
	public void testScheduleWebinarXDaysFromToday() throws InterruptedException
	{
		String TitleStartsWith=testProp.getProperty("TitleStartsWith");
		String Description=testProp.getProperty("Description");
		String Occurance=testProp.getProperty("Occurance");
		int XdaysFromToday=Integer.parseInt(testProp.getProperty("XdaysFromToday"));
		String StartTime=testProp.getProperty("StartTime");
		String EndTime=testProp.getProperty("EndTime");
		String TimeZone=testProp.getProperty("TimeZone");
		String Language=testProp.getProperty("Language");
		Date date=new Date();
		Utilities utility=new Utilities();
		Date dayslaterDate = utility.addDays(date, Math.abs(XdaysFromToday));
		
		
		mySignInPage=openWebinarUrl();
		myWebinarHomePage = mySignInPage.SignIn(testProp.getProperty("email"), testProp.getProperty("password"));
		Assert.assertTrue(myWebinarHomePage.isMyAccountLinkDisplayed(), "My Account link not displayed, signin not successful");
		myLeftNavObjects=new LeftNavObjects(driver, testEnv);
		
		myScheduleWebinarPage=myLeftNavObjects.NavigatetoScheduleAWebinarPage();
		
		myScheduleWebinarPage.enterDetailsForNewWebinarSchedule(TitleStartsWith, Description, Occurance, dayslaterDate, StartTime, EndTime,dayslaterDate, TimeZone, Language);
		myManageWebinarPage=myScheduleWebinarPage.scheduleWebinar();
		
		String finalWebinarTitle=myManageWebinarPage.getWebinarTitle();
				
		Assert.assertTrue(myManageWebinarPage.getWebinarTitle().trim().contains(TitleStartsWith.trim()), "Webinar Creation failed");
		
		myLeftNavObjects.NavigateToMyWebinarPage().navigateToUpcomingWebinars();
		myWebinarHomePage.selectwebinarSearchDateRange(XdaysFromToday);
		
		Assert.assertTrue(myWebinarHomePage.getIfScheduledWebinarListedInUpcomingWebinarsByTitle(finalWebinarTitle),"Upcoming Webinar is not listing the newly scheduled webinar");
		
		
		myManageWebinarPage=myWebinarHomePage.EditWebinarByTitle(finalWebinarTitle);
		myManageWebinarPage.CancelWebinar();
		

	}
	
}


