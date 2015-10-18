package com.citrix.gotowebinar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.citrix.gotowebinar.base.keywords;

public class ManageWebinarPage  extends keywords{
	
	protected By trainingName_label=By.id("trainingName");
	protected By cancelWebinar_link=By.linkText("Cancel Webinar");
	protected By cancelWebinar_modal=By.id("cancelWebinarContainer");
	protected By confirmDelete_link=By.xpath("//*[@id='confirmDelete']/div/span");

	
	public ManageWebinarPage(WebDriver driver,String env) {
		super(env);
		this.driver = driver;
	}
	
	public String getWebinarTitle()
	{
		return getText(trainingName_label);
	}
	
	public WebinarHomePage CancelWebinar() throws InterruptedException
	{
		click(cancelWebinar_link);
		
		WebDriverWait wait =new WebDriverWait(driver,20);
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(confirmDelete_link)));
	   
	  
	    driver.findElement(confirmDelete_link).click();
	   waitForPageLoad(driver);
		return new WebinarHomePage(driver, env);
		
	}
}
