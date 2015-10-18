package com.citrix.gotowebinar.base;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.citrix.gotowebinar.environment.EnvironmentConfig;
import com.citrix.gotowebinar.recovery.RecoveryModule;
import com.citrix.gotowebinar.utility.Utilities;

public class keywords {

		private Select select = null;
		public WebDriver driver;
		protected int ilarge;
		protected int imedium;
		public static Properties prop;
		protected String env;
		
		EnvironmentConfig envconf=new EnvironmentConfig();
		protected int ismall;

		
		public keywords(String env)
		{
			this.env=env;
			prop=envconf.loadEnvironmentConfig(env);
			ilarge=Integer.parseInt(prop.getProperty("maxWaitTimeinSec"));
			imedium=Integer.parseInt(prop.getProperty("defaultWaitTimeinSec"));
			ismall=Integer.parseInt(prop.getProperty("minWaitTimeinSec"));
						
		}

		public void jQueryOnClick(By by) throws InterruptedException {
			if (findIfElementPresent(by)) {
				WebElement element = driver.findElement(by);
				Actions actions = new Actions(driver);
				actions.click(element).perform();
			} else {
				new RecoveryModule(driver).invokeRecovery();
				WebElement element = driver.findElement(by);
				Actions actions = new Actions(driver);
				actions.click(element).perform();
			}
			smallSleep();
		}
		
		public void smallSleep() throws InterruptedException
		{
			TimeUnit.SECONDS.sleep(ismall);
		}
		
		public void selectDateFromJqueryDatepicker(WebElement datePickerDiv, Date setdate) throws InterruptedException {
		  
		
		    	    	    
		    Utilities utility =new Utilities();
		    Date today=new Date();
		    int datediff = utility.getDateDiffInMonths(today,setdate);
		    if (datediff>0) 
		    {
		    	for (int i = 0; i < Math.abs(datediff); i++) {
		    		WebElement nextbtn=datePickerDiv.findElement(By.xpath("//a[@title='Next']"));
		    		nextbtn.click();
				}	
		    }else 
		    {
		    	for (int i = 0; i < Math.abs(datediff); i++) {
		    		WebElement prevbtn=datePickerDiv.findElement(By.xpath("//a[@title='Prev']"));
		    		prevbtn.click();
		    }
		    }
		    
		    Thread.sleep(1000);
		   

		    WebElement calTable= datePickerDiv.findElement(By.className("ui-datepicker-calendar"));		    
		    calTable.findElement(By.linkText(String.valueOf(utility.getDate(setdate)))).click();
		}
		
		public void waitAndClickOnJQuery(By by, int maxWaitTimeInSec) {
			wait(by, maxWaitTimeInSec);
			
			if (findIfElementPresent(by)) {
				WebElement element = driver.findElement(by);
				Actions actions = new Actions(driver);
				actions.click(element).perform();
			} else {
				new RecoveryModule(driver).invokeRecovery();
				WebElement element = driver.findElement(by);
				Actions actions = new Actions(driver);
				actions.click(element).perform();
			}
		}
		/**
		 * This method can be used to click WebElement locator in the UI.
		 * 
		 * @param element
		 *            locator
		 */
		public void click(By by) {
			if (findIfElementPresent(by)) {
				driver.findElement(by).click();
			} else {
				new RecoveryModule(driver).invokeRecovery();
				driver.findElement(by).click();
			}
		}


		/** USED
		 * This method can be used to type desired value in the Web element locator
		 * 
		 * @param element
		 * @param value
		 *            which needs to be typed
		 */
		public void type(By by, String value) {
			if (findIfElementPresent(by)) {
				//driver.findElement(by).clear();
				driver.findElement(by).sendKeys(value);
			} else {
				new RecoveryModule(driver).invokeRecovery();
				driver.findElement(by).clear();
				driver.findElement(by).sendKeys(value);
			}
		}
		
		public void jQuerytype(By by,String value)
		{
			WebElement inputField = driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value +"')", inputField);
		}
		/**
		 * This method can be used to select dropdown as per the string value
		 * 
		 * @param element
		 *            locator of the dropdown.
		 * @param value
		 *            that needs to be selected.
		 */
		public void select(By by, String value) {
			if (findIfElementPresent(by)) {
				select = new Select(driver.findElement(by));
				select.selectByVisibleText(value);
			} else {
				new RecoveryModule(driver).invokeRecovery();
				select = new Select(driver.findElement(by));
				select.selectByVisibleText(value);
			}
		}

		/** USED
		 * This method can be used to find if element displayed in the UI.
		 * 
		 * @param By
		 *            value can be Xpath, css etc..
		 * @return true if the element displayed.
		 */
		public boolean findIfElementDisplayed(By by) {
			try {
				 wait(by,ilarge);
				if (driver.findElement(by).isDisplayed()) {
					return true;
				} else {
					return false;
				}
			} catch (NoSuchElementException e) {
				return false;
			} catch (Exception e) {
				return false;
			} 
		}


		/**USED
		 * This method can be used to find if element is present in the UI.
		 * 
		 * @param By
		 *            value can be Xpath, CSS etc..
		 * @return true if the element displayed.
		 */
		public boolean findIfElementPresent(By by) {
			try {
				//explicit wait for field
				 WebDriverWait wait = new WebDriverWait(driver, ilarge);
				    wait.until(ExpectedConditions.presenceOfElementLocated(by));
				
				driver.findElement(by);
				return true;
			} catch (NoSuchElementException e) {
				return false;
			} catch (Exception e) {
				return false;
			} finally {
				driver.manage().timeouts()
						.implicitlyWait(imedium, TimeUnit.SECONDS);
			}
		}

		/**
		 * This method can be used to wait for specified time until the element
		 * present
		 * 
		 * @param element
		 * @param maxWaitTimeInSec
		 * @return true if the element is present with in the specified time
		 * @throws Exception
		 */
		public boolean waitForElementPresent(By by, int maxWaitTimeInSec) {
			int waittime = maxWaitTimeInSec * 1000;
			long starttime = System.currentTimeMillis();
			while (System.currentTimeMillis() < starttime + waittime) {
				if (findIfElementPresent(by)) {
					return true;
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// throw new Exception(e);
				}
			}
			return false;
		}


		/** Used
		 * This method cane be used to wait for page to load in 120 second.
		 * 
		 * @param driver
		 */
		public void waitForPageLoad(WebDriver driver) {
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript(
							"return document.readyState").equals("complete");
				}
			};
			Wait<WebDriver> wait = new WebDriverWait(driver, ilarge);
			try {
				wait.until(expectation);
			} catch (Throwable error) {
			}
		}

		/**
		 * This method can be used validate string message displayed in the locator
		 * value in the UI.
		 * 
		 * @param By
		 *            locator value
		 * @param message
		 *            that needs to be validated
		 * @return true if the message is displayed in the locator value of the UI.
		 */
		public boolean findIfCorrectTextDisplayed(By by, String message) {
			String actual=getText(by);
			return actual.contains(message);
		}

		/**
		 * This method can be used to open mentioned url.
		 * 
		 * @param url
		 *            which needs to be opened.
		 */
		public void openURL(String url, WebDriver driver) {
			driver.get(url);
		}
		
		public String getPageTitle()
		{
			return driver.getTitle();
		}

		/**
		 * This method can be used to get the value of any attribute for any locator
		 * Usage:
		 * 
		 * @param Element
		 *            the locator of the field which has attributes
		 * @param Attributes
		 *            the attribute that needs to be picked for its value Returns
		 *            the value of the attribute for any element.
		 * @return value of the attribute
		 */
		public String getValueOfAnAttribute(By by, String attribute) {
			
			return driver.findElement(by).getAttribute(attribute);
		}

		/**
		 * This method can be used to validate text present in the current page.
		 * 
		 * @param textToSearch
		 *            in the page
		 * @return true if the text to search string is present in the page.
		 */
		public boolean checkTextPresenceInThePage(String textToSearch) {
			return driver.getPageSource().contains(textToSearch);
		}


		/**
		 * This method can be used to get text of the element.
		 * 
		 * @param element
		 * @return text displayed in the element.
		 */
		public String getText(By by) {
			if (findIfElementPresent(by)) {
				return driver.findElement(by).getText();
			} else {
				new RecoveryModule(driver).invokeRecovery();
				return driver.findElement(by).getText();
			}
		}

		/**
		 * This function can be used for finding no of xpath count for an element
		 * 
		 * @param by
		 * @return no of element found for a given xpath
		 */
		public Integer getXpathCount(By by) {
			Integer i = driver.findElements(by).size();
			return i;
		}

		/**
		 * Function to run java script
		 * 
		 * @param script
		 * @throws Exception
		 */
		public void runJavaScript(String script) {
			JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
			javaScriptExecutor.executeScript(script);
		}

		/**
		 * Function to wait for element for specific time.
		 * 
		 * @param by
		 * @param time
		 */
		public void wait(By by, int time) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}

		/**
		 * Function to Wait for element not present.
		 * 
		 * @param by
		 * @param time
		 */
		public void waitForElementNotDisplyed(By by, int time) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}

		

		
		/**
		 * Function to get Attribute value of the element.
		 * 
		 * @param Xpath
		 * @param attributeName
		 * @return attribute value as a string
		 */
		public String getAttributeValuebyXPath(String xpathStr, String attributeName) {
			return driver.findElement(By.xpath(xpathStr)).getAttribute(attributeName);
			
		}

		/**
		 * Function to verify alert present.
		 * 
		 * @return true if alert oresent.
		 */
		public boolean isAlertPresent() {
			try {
				Alert alert = driver.switchTo().alert();
				alert.getText();
				return true;
			} catch (NoAlertPresentException ex) {
				return false;
			}
		}

		/**
		 * Function to clickOk button once alert is present.
		 */
		public void clickOkIfAlertPresent() {
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();
			}
		}

		/**
		 * Function to click Yes when Window Security alert present.
		 */
		public void clickYesIfWindowSecurityAlertPresent() {
			if (isAlertPresent()) {
				driver.switchTo().activeElement().sendKeys(Keys.TAB);

			}
		}

		/**
		 * Function to enable check if it is not selected.
		 * 
		 * @param by
		 */
		public void enableCheck(By by) {
			if (this.findIfElementDisplayed(by)) {
				if (!(driver.findElement(by).isSelected())) {
					driver.findElement(by).click();
				}
			}
		}

		/**
		 * Function to verify check is enabled or not.
		 * 
		 * @return true if it is enabled
		 */
		public boolean isCheckBoxEnabled(By by) {
			if (this.findIfElementDisplayed(by)) {
				return driver.findElement(by).isSelected();
			}
			return false;
		}
		
		public void clickNOIfAlertPresent() {
			if (isAlertPresent()) {
				driver.switchTo().alert().dismiss();
				;
			}

		}


		/**
		 * This method can be used to find if element displayed in the UI.
		 * 
		 * @param By
		 *            value can be Xpath, css etc..
		 * @return true if the element displayed.
		 */
		public boolean findIfElementEnabled(By by) {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				if (driver.findElement(by).isEnabled()) {
					return true;
				} else {
					return false;
				}
			} catch (NoSuchElementException e) {
				return false;
			} catch (Exception e) {
				return false;
			} finally {
				driver.manage().timeouts()
						.implicitlyWait(imedium, TimeUnit.SECONDS);
			}

		}

		public void pageRefresh() {
			this.driver.navigate().refresh();
		}

		/**
		 * This method can be used to click WebElement locator in the UI.
		 * 
		 * @param element
		 *            locator
		 */
		public void navigateUrl(String url) {
			driver.get(url);
		}


		public String getCurrentURL() {
			return driver.getCurrentUrl().toString();
		}
	}

