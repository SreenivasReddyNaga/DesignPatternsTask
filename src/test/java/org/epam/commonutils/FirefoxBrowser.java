package org.epam.commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser extends WebDriverCreater {

	private static final String FIREFOX_BINARY_PATH = "src\\test\\resources\\drivers\\geckodriver.exe";

	@Override
	public WebDriver getWebDriverInstance() {
		System.setProperty("webdriver.gecko.driver", FIREFOX_BINARY_PATH);
		return new FirefoxDriver();
	}

}
