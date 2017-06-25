package org.epam.commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEBrowser extends WebDriverCreater{

	private static final String IE_BINARY_PATH = "src\\test\\resources\\drivers\\IEDriverServer32Bit.exe";

	@Override
	public WebDriver getWebDriverInstance() {
		System.setProperty("webdriver.ie.driver", IE_BINARY_PATH);
		return new InternetExplorerDriver();
	}

}
