package org.epam.commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverUtils {
	
	public static WebDriver getIEDriver() {		
		return new InternetExplorerDriver();
	}

	public static WebDriver getChromeDriver() {
		return new ChromeDriver();
	}
	
	public static WebDriver getFirefoxDriver() {
		return new ChromeDriver();
	}
	
	public static WebDriver getEdgeDriver() {
		return new ChromeDriver();
	}

}
