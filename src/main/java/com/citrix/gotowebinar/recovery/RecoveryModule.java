package com.citrix.gotowebinar.recovery;

import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

/**
 * This class contains all the recovery scenario condition for UI Automation
 */
public class RecoveryModule {
	private static final Logger s_logger = (Logger) LoggerFactory
			.getLogger(RecoveryModule.class);

	private String currentWindowHandle = null;
	private WebDriver driver = null;
	public RecoveryModule(WebDriver driver) {
		this.driver = driver;
	}

	public void invokeRecovery() {
		currentWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		allWindowHandles.remove(currentWindowHandle);
		if (allWindowHandles.size() > 1) {
			driver.switchTo().window(allWindowHandles.iterator().next());
			driver.close();
			driver.switchTo().window(currentWindowHandle);
			s_logger.info("Closed all child windows");
		} 
	}
}
