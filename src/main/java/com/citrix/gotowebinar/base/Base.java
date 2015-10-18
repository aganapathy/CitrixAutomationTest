package com.citrix.gotowebinar.base;


import java.io.File;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.citrix.gotowebinar.environment.Environment;
import com.citrix.gotowebinar.environment.EnvironmentConfig;


public class Base {

	//Create an instance of logger(log4j) to dump all the detailing for debug option
	
	public static String testEnv;
	public static String browser;
	public static String OS;
	public static WebDriver driver;

	public static EnvironmentConfig envprop=new EnvironmentConfig();
	public static Properties prop;
	
	@BeforeClass
	@Parameters({ "test.env", "test.browser", "test.os"})
	public void initSuite(@Optional("prod") String env,@Optional("chrome") String browser,@Optional("mac") String OS) throws Exception {		
		Base.browser = browser;
		Base.OS=OS;
		
		//pick up config, test data etc depending on the input Env
		if (env == null || env.equals(""))
			env = "prod";
		testEnv =Environment.valueOf(env.toUpperCase()).toString();	
		prop=envprop.loadEnvironmentConfig(testEnv);		
		setDriver(Base.browser);
		

	}
	
	public void setDriver(String browserType) {
		switch (browserType.toLowerCase()) {
		case "chrome":
			Base.driver = initChromeDriver(Base.OS);
			break;
		case "firefox":
			Base.driver = initFirefoxDriver(Base.OS);
			break;
		case "ie":
			Base.driver = initIEDriver(Base.OS);
			break;
		default:
//			s_logger.info("browser : " + browserType
//					+ " is invalid, Launching Headless browser as choice..");
			Base.driver = initHeadlessDriver();
		}
	}
	
	public WebDriver getDriver()
	{
		return Base.driver;
		
	}
	
	private static WebDriver initHeadlessDriver()
	{
		
		return new HtmlUnitDriver();

	}
	
	private static WebDriver initChromeDriver(String os) {
//		s_logger.info("Launching Chrome driver");
		
		   
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setJavascriptEnabled(Boolean.valueOf(prop.getProperty("setJavascriptEnabled")));
		capabilities.setCapability("ignoreProtectedModeSettings", Boolean.valueOf(prop.getProperty("ignoreProtectedModeSettings")));
	
		if(os.contains("win")){
//				s_logger.info("Starting execution on window OS");
			String filepath = System.getProperty("user.dir");
			
			capabilities.setPlatform(Platform.WINDOWS);
			File file = new File(filepath+ "\\Software\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
			}
			else{

				capabilities.setPlatform(Platform.MAC);
				System.setProperty("webdriver.chrome.driver", "Drivers/mac/chromedriver");
				
			}
		driver = new ChromeDriver(capabilities);
		return driver;
	}
	
	private static WebDriver initFirefoxDriver(String os) {
//		s_logger.info("Launching FireFox driver");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setJavascriptEnabled(Boolean.valueOf(prop.getProperty("setJavascriptEnabled")));
		capabilities.setCapability("ignoreProtectedModeSettings", Boolean.valueOf(prop.getProperty("ignoreProtectedModeSettings")));
		
		if(os.contains("win")){
			capabilities.setPlatform(Platform.WINDOWS);
			}
			else
			{
				capabilities.setPlatform(Platform.MAC);
			}
		driver = new FirefoxDriver(capabilities);			
		return driver;
	}
	
	private static WebDriver initIEDriver(String os) {
//		s_logger.info("Launching FireFox driver");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability("browserName", "internet explorer");
		capabilities.setJavascriptEnabled(Boolean.valueOf(prop.getProperty("setJavascriptEnabled")));
		capabilities.setCapability("ignoreProtectedModeSettings", Boolean.valueOf(prop.getProperty("ignoreProtectedModeSettings")));
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		
		if(os.contains("win")){
			capabilities.setPlatform(Platform.WINDOWS);
			String filepath = System.getProperty("user.dir");
			File file = new File(filepath
					+ "\\Software\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver",
					file.getAbsolutePath());
			driver = new InternetExplorerDriver(capabilities);
			driver = new InternetExplorerDriver(capabilities);	
			}
			else
			{
//				s_logger.info("Unsupported IE browser for OS :"+ os + " use different browser");
			}
		return driver;
	}
	
	protected static void ignoreCertificateError(WebDriver driver)
			throws InterruptedException {
				if(driver.getTitle().contains("Certificate"))
				{
					driver.navigate().to(
							"javascript:document.getElementById('overridelink').click()");
				}
					Thread.sleep(5000);
							
	}
}
