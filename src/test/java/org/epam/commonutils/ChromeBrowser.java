package org.epam.commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser extends WebDriverCreater {

	private static final String CHROME_BINARY_PATH = "src\\test\\resources\\drivers\\chromedriver.exe";

	@Override
	public WebDriver getWebDriverInstance() {
		System.setProperty("webdriver.chrome.driver", CHROME_BINARY_PATH);
		return new ChromeDriver();
	}
}
