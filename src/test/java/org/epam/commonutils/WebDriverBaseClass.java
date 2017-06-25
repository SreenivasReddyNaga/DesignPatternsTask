package org.epam.commonutils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class WebDriverBaseClass {

	private static final Logger LOGGER = Logger.getLogger(WebDriverBaseClass.class);
	protected WebDriver driver;
	private WebDriverCreater driverCreator;
	protected EventFiringWebDriver eDriver;
	

	@BeforeClass
	@Parameters({ "browserName", "url" })
	public void setUp(String browserName, String url) {
		switch (browserName.toUpperCase()) {
		case "FIREFOX":
			driverCreator = new FirefoxBrowser();
			driver = driverCreator.getWebDriverInstance();
			break;
		case "IEXPLORE":
			driverCreator = new IEBrowser();
			driver = driverCreator.getWebDriverInstance();
			break;
		case "CHROME":
			driverCreator = new ChromeBrowser();
			driver = driverCreator.getWebDriverInstance();
			break;
		case "EDGE":
			driverCreator = new EdgeBrowser();
			driver = driverCreator.getWebDriverInstance();
			break;
		default:
			LOGGER.info("Invalid browser " + browserName);
			System.exit(0);
		}
		LOGGER.info("Script execting on " + browserName + "browser");
		eDriver=initEDriver();
		openUrl(url);
	}
	
	

	private EventFiringWebDriver initEDriver() {
		EventFiring eventFiring = new EventFiring(driver);
		return eventFiring.getEdriver();
	}

	public void openUrl(String url) {
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
