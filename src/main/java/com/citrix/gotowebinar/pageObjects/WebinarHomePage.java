package com.citrix.gotowebinar.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.citrix.gotowebinar.base.keywords;

public class WebinarHomePage extends keywords{

	private By MyAccount_link=By.linkText("My Account");
	private By UpcomingWebinar_tablink=By.linkText("Upcoming Webinars");
	private By webinarSearchDateRangePicker_jquerySelect=By.id("upcomingWebinar-webinarSearchDateRangePicker_trig");
	private By pageStatus_lbl=By.xpath("//span[@id='upcomingWebinar-PageStatus']/b[1]");

	private By webinarloading_statusbar=By.xpath("//li[contains(@class,'UpcomingLoadingDiv'][1]");
	
	
	
	private By webinarDetail_container=By.className("myWebinarDetail");
	private By w1ebinarEdit_link=By.xpath("//*[@class='actionBtn']/div[3]/form/a");
	private By webinarEdit_link=By.linkText("Edit");
	
	
	public WebinarHomePage(WebDriver driver,String env) {
		super(env);
		this.driver = driver;
	}
	
	
	public boolean isMyAccountLinkDisplayed()
	{
		if (findIfElementDisplayed(MyAccount_link))
			return true;	
		return false;
		
	}	
	
	public void navigateToUpcomingWebinars()
	{
			click(UpcomingWebinar_tablink);	
			waitForElementNotDisplyed(webinarloading_statusbar,ilarge);
			wait(pageStatus_lbl, ilarge);
	}
	
	public void selectwebinarSearchDateRange(int numOfDays) throws InterruptedException
	{
		
		
		String byXpath;
		if (numOfDays<=0) 
			byXpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li[@title='Today']";	
		else if (numOfDays==1)
			byXpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li[@title='Tomorrow']";	
		else if (numOfDays<=7)
			byXpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li[@title='Next 7 Days']";	
		else if (numOfDays<=30)
			byXpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li[@title='Next 30 Days']";	
		else if (numOfDays<=90)
			byXpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li[@title='Next 90 Days']";	
		else if (numOfDays<=180)
			byXpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li[@title='Next 180 Days']";	
		else
			byXpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li[@title='Next 365 Days']";
		jQueryOnClick(webinarSearchDateRangePicker_jquerySelect);

		smallSleep();
		jQueryOnClick(By.xpath(byXpath));
		waitForElementNotDisplyed(webinarloading_statusbar,ilarge);
		wait(pageStatus_lbl, ilarge);

	}
	
	public int getTotalPages()
	{
		return Integer.parseInt(getText(pageStatus_lbl));
	}
	
	public boolean getIfScheduledWebinarListedInUpcomingWebinarsByTitle(String Title)
	{
		List<WebElement> scheduledWebinars=driver.findElements(By.xpath("//ul[@class='myWebinarMain']"));
		
		for (int i = 1; i <= scheduledWebinars.size(); i++) {
			String webinarTitle=scheduledWebinars.get(i).findElement(By.xpath("//ul[1]/li[contains(@class, 'column-11')]/a/span")).getText();
			if (webinarTitle.equalsIgnoreCase(Title)) {
				return true;
			}
		}		
		return false;
		
	}
	
	public ManageWebinarPage EditWebinarByTitle(String Title)
	{
		List<WebElement> scheduledWebinars=driver.findElements(By.xpath("//*[@class='table-data-row openWebinar']"));
		
		for (int i = 1; i <= scheduledWebinars.size(); i++) {
			String webinarTitle=scheduledWebinars.get(i).findElement(By.xpath("//ul[1]/li[contains(@class, 'column-11')]/a/span")).getText();
			if (webinarTitle.equalsIgnoreCase(Title)) {
				WebElement EditClick = scheduledWebinars.get(i).findElement(By.xpath("//ul[2]/div[2]/li[@class='actionBtn']/div[3]/form/a"));
				return navigateToWebinarEdit(EditClick);
			}
		}	
		return null;
	}
	
	public ManageWebinarPage navigateToWebinarEdit(WebElement EditWebinar_link)
	{
		EditWebinar_link.click();
		waitForPageLoad(driver);
		return  new ManageWebinarPage(driver,env);
		
	}
	
}
