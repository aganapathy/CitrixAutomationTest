package com.citrix.gotowebinar.pageObjects;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.citrix.gotowebinar.base.keywords;
import com.citrix.gotowebinar.utility.Utilities;

public class ScheduleWebinarPage extends keywords {
	
	protected By webinarName_input=By.id("name");
	protected By webinarDesc_input=By.id("description");
	protected By occurs_jquerySelect=By.id("recurrenceForm_recurs_trig");
	protected By date_jquerydatepicker=By.className("ui-datepicker-trigger");
	protected By date_jqueryinput=By.id("webinarTimesForm.dateTimes_0.baseDate");
	protected By enddate_jqueryinput=By.id("recurrenceForm.endDate");
	
	protected WebElement datePickerDiv;

	protected By StartTime_input=By.id("webinarTimesForm.dateTimes_0.startTime");
	protected By StartTimeAMPM_jquerySelect=By.id("webinarTimesForm_dateTimes_0_startAmPm_trig");
	protected By EndTime_input=By.id("webinarTimesForm.dateTimes_0.endTime");
	protected By EndTimeAMPM_jquerySelect=By.id("webinarTimesForm_dateTimes_0_endAmPm_trig");
	
	protected By timezone_jquerySelect=By.id("webinarTimesForm_timeZone_trig");
	protected By lang_jquerySelect=By.id("language_trig");
	
	protected By schedule_btn=By.id("schedule.submit.button");
	
	
	Utilities utilitiy=new Utilities();


	public ScheduleWebinarPage(WebDriver driver, String env) {
		// TODO Auto-generated constructor stub
		super(env);
		this.driver = driver;
	}
	
	
	public void enterDetailsForNewWebinarSchedule(String Title,String Description,
			String Occurance,Date StartDate,String StartTime,String EndTime,Date EndDate,
			String TimeZone,String Language) throws InterruptedException
	{
		
		String[] Start_Time=StartTime.split(" ");
		String[] End_Time=EndTime.split(" ");
		
		
		type(webinarName_input, Title.concat(" ").concat(Utilities.generateRandomString()));		
		type(webinarDesc_input, Description);
		
		//Enter Occurance details
		jQueryOnClick(occurs_jquerySelect);
		String byXpath="//div[@id='recurrenceForm_recurs__menu']/ul/li[@title='"+Occurance+"']";
		jQueryOnClick(By.xpath(byXpath));
		smallSleep();
		
		
		//Select the Date from DatePicker
		setStartDate(StartDate);
		smallSleep();


		//Enter Start time details
		
		jQuerytype(StartTime_input, Start_Time[0]);	
		jQueryOnClick(StartTimeAMPM_jquerySelect);
		byXpath="//div[@id='webinarTimesForm_dateTimes_0_startAmPm__menu']/ul/li[@title='"+Start_Time[1].toUpperCase()+"']";
		jQueryOnClick(By.xpath(byXpath));
		
		//Enter End time details
		jQuerytype(EndTime_input, End_Time[0]);		
		jQueryOnClick(EndTimeAMPM_jquerySelect);
		byXpath="//div[@id='webinarTimesForm_dateTimes_0_endAmPm__menu']/ul/li[@title='"+End_Time[1].toUpperCase()+"']";
		jQueryOnClick(By.xpath(byXpath));
		
		//Enter Time Zone details
		jQueryOnClick(timezone_jquerySelect);
		byXpath="//div[@id='webinarTimesForm_timeZone__menu']/ul/li[@title='"+TimeZone+"']";
		jQueryOnClick(By.xpath(byXpath));
		
		//Enter Language details
		jQueryOnClick(lang_jquerySelect);
		byXpath="//div[@id='language__menu']/ul/li[@title='"+Language+"']";
		jQueryOnClick(By.xpath(byXpath));
		
		
	}
	
	public void setStartDate(Date date) throws InterruptedException
	{
		smallSleep();
		jQueryOnClick(date_jqueryinput);
	
		datePickerDiv= driver.findElement(By.id("ui-datepicker-div"));
		
		selectDateFromJqueryDatepicker(datePickerDiv,date);

	}
	
	public ManageWebinarPage scheduleWebinar()
	{
		click(schedule_btn);
		waitForPageLoad(driver);
		return new ManageWebinarPage(driver, env);
		
	}

}
