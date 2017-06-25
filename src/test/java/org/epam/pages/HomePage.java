package org.epam.pages;

import java.util.Map;

import org.epam.commonutils.DatePickerClass;
import org.epam.commonutils.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	@FindBy(xpath = "//span[text()='Return']")
	private WebElement returnLabel;

	@FindBy(xpath = "//input[contains(@id,'_TextBoxMarketOrigin1')]")
	private WebElement txtFrom;

	@FindBy(xpath = "//input[contains(@id,'_TextBoxMarketDestination1')]")
	private WebElement txtDestination;

	private String cityFirst = "//div[@id='stationsList']/ul/li/a/strong[contains(text(),'";
	private String cityNext = "')]";

	@FindBy(id = "DropDownListPassengerType_ADT_PLUS")
	private WebElement adultPassengers;

	@FindBy(id = "adtSelectorDropdown")
	private WebElement slctAdults;

	@FindBy(xpath= "//select[@id='AvailabilitySearchInputXmlSearchView_DropDownListPassengerType_CHD']")
	private WebElement slctChildren;

	@FindBy(xpath = "//select[@id='AvailabilitySearchInputXmlSearchView_DropDownListPassengerType_INFANT']")
	private WebElement slctBabies;

	@FindBy(xpath = "//div[@class='popupBottomSingleButton']/a")
	private WebElement babyPopup;

	@FindBy(xpath = "//label[@for='isResident']")
	private WebElement chkResidents;

	@FindBy(xpath = "//select[contains(@id,'_residentFamNumSelector')]")
	private WebElement slctFamilyType;

	@FindBy(xpath = "//a[contains(@id,'_btnClickToSearchNormal')]")
	private WebElement lnkSearchFlights;

	private HelperClass helper;
	private DatePickerClass datePicker;
	private int timeOut = 20;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
		helper = new HelperClass(getDriver());
		datePicker = new DatePickerClass(getDriver());
	}

	private void bookingType() {
		returnLabel.click();
	}

	private void setFromCity(String fromCity) {
		txtFrom.clear();
		txtFrom.sendKeys(fromCity);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		By lnkCityFrom = By.xpath(cityFirst + fromCity + cityNext);
		getDriver().findElement(lnkCityFrom).click();
	}

	private void setDestinationCity(String destinationCity) {
		helper.waitForElementVisible(txtDestination, timeOut);
		txtDestination.clear();
		txtDestination.sendKeys(destinationCity);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		By destination = By.xpath(cityFirst + destinationCity + cityNext);
		getDriver().findElement(destination).click();
	}

	private void setFromDate(String outBoundDate) {
		datePicker.selectDate(outBoundDate);
	}

	private void setToDate(String inBoundDate) {
		datePicker.selectDate(inBoundDate);
	}

	private void setPassengers(String adultsCount, String childrenCount, String babiesCount) {
		helper.waitForElementVisible(adultPassengers, timeOut);
		adultPassengers.click();
		helper.waitForElementVisible(slctAdults, timeOut);
		helper.selectOptionByText(slctAdults, adultsCount);
		helper.waitForElementVisible(slctChildren, timeOut);
		helper.selectOptionByText(slctChildren, childrenCount);
		helper.waitForElementVisible(slctBabies, timeOut);
		helper.selectOptionByText(slctBabies, babiesCount);
		helper.jsClick(babyPopup);
	}

	private void setFamilyType(String familyType) {
		helper.waitForElementVisible(chkResidents, timeOut);
		chkResidents.click();
		helper.selectOptionByText(slctFamilyType, familyType);

	}

	public BookingResultsPage flightSearch(Map<String,String> data) {
		bookingType();
		setFromCity(data.get("fromCity"));
		setDestinationCity(data.get("destinationCity"));
		setFromDate(data.get("outBoundDate"));
		setToDate(data.get("inBoundDate"));
		setPassengers(data.get("adultsCount"), data.get("childrenCount"), data.get("babiesCount"));
		setFamilyType(data.get("familyType"));
		lnkSearchFlights.click();
		return new BookingResultsPage(getDriver());

	}

}
