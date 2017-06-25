package org.epam.pages;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.epam.commonutils.HelperClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingResultsPage {
	private static final Logger LOGGER = Logger.getLogger(BookingResultsPage.class);
	@FindBy(xpath = "//li[@id='step1'][@class='selected']")
	private WebElement lblChooseYourflight;

	@FindBy(xpath = "//div[@id='research']/div/div/p/strong")
	private WebElement txtCityDetails;

	@FindBy(xpath = "//span[@id='inboundDate']/parent::p")
	private WebElement txtPassengers;	
	private HelperClass helper;

	public BookingResultsPage(WebDriver driver) {
		helper=new HelperClass(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isDisplayed() {
		helper.waitForElementVisible(lblChooseYourflight, 20);
		return lblChooseYourflight.isDisplayed();
	}

	public String getChooseYourflightTabStatus() {
		return lblChooseYourflight.getAttribute("class");
	}

	public String getFromAndDestination() {
		return txtCityDetails.getText();
	}

	public Map<String, String> getPassangersDetails() {
		Map<String, String> validationData = new TreeMap<String, String>();
		helper.waitForElementVisible(txtPassengers, 20);
		String journeyDetails = txtPassengers.getText();
		LOGGER.info("journeyDetails "+  journeyDetails);
		String[] splittedData = journeyDetails.split("\\|");
		validationData.put("JourneyDates", splittedData[0]);
		validationData.put("PassengersData", splittedData[1]);

		return validationData;
	}

}
