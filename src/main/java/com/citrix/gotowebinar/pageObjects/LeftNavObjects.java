package com.citrix.gotowebinar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.citrix.gotowebinar.base.keywords;

public class LeftNavObjects extends keywords{

		private By scheduleAWebinar_link=By.linkText("Schedule a webinar");
		private By MyWebinar_link=By.linkText("My Webinars");
		
		
		public LeftNavObjects(WebDriver driver,String env) {
			super(env);
			this.driver = driver;
		}
		
		
		public ScheduleWebinarPage NavigatetoScheduleAWebinarPage()
		{
			click(scheduleAWebinar_link);
			waitForPageLoad(driver);
			return  new ScheduleWebinarPage(driver,env);
		}	
		
		public WebinarHomePage NavigateToMyWebinarPage()
		{
			click(MyWebinar_link);
			waitForPageLoad(driver);
			return  new WebinarHomePage(driver,env);
		}
}
