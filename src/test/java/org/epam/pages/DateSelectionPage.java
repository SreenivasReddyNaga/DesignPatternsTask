package org.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DateSelectionPage {
	
	@FindBy(className = "ui-datepicker-month")
	private WebElement lblMonth;

	@FindBy(className = "ui-datepicker-year")
	private WebElement lblYear;

	@FindBy(xpath = "//div[contains(@class='ui-corner-right')]/a")
	private WebElement lnkNext;

	@FindBy(xpath = "//div[contains(@class,'ui-corner-left')]/a")
	private WebElement lnkPrevious;
	
	@FindBy(className = "ui-datepicker-calendar")
	private WebElement date;
	
	@FindBy(tagName = "td")
	private WebElement columns;

	public WebElement getLblMonth() {
		return lblMonth;
	}

	public WebElement getLblYear() {
		return lblYear;
	}

	public WebElement getLnkNext() {
		return lnkNext;
	}

	public WebElement getLnkPrevious() {
		return lnkPrevious;
	}

	public WebElement getDate() {
		return date;
	}
	
	public WebElement getColumns() {
		return columns;
	}

	public DateSelectionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
		
	

}
