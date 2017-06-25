package org.epam.commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeBrowser extends WebDriverCreater {

	private static final String EDGE_BINARY_PATH = "src\\test\\resources\\drivers\\MicrosoftWebDriver.exe";

	@Override
	public  WebDriver getWebDriverInstance() {
		System.setProperty("webdriver.edge.driver", EDGE_BINARY_PATH);
		return new EdgeDriver();
	}

}
