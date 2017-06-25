package org.epam.tests;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.epam.commonutils.HelperClass;
import org.epam.commonutils.WebDriverBaseClass;
import org.epam.pages.BookingResultsPage;
import org.epam.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FlightBookingTest extends WebDriverBaseClass {
	private static final Logger LOGGER = Logger.getLogger(FlightBookingTest.class);
	private static final String expTitle = "Cheap flights, hotel deals, rental car | vueling.com";
	private Map<String, String> data;
	private HelperClass helper;

	@DataProvider(name = "flightBookingData")
	public Object[][] createData() {
		LOGGER.info("trying to create row based data");
		helper = new HelperClass(driver);
		Object[][] testData = helper.tesData(testData());
		return testData;
	}

	@Test(dataProvider="flightBookingData")
	public void flightBookingTest(Map<String, String> bookingData) {
		Assert.assertEquals(eDriver.getTitle(), expTitle);

		boolean isFlightSearchComplete = new HomePage(driver).flightSearch(bookingData).isDisplayed();
		Assert.assertTrue(isFlightSearchComplete, "User not able to search for flights");

	}

	@Test(dataProvider="flightBookingData",dependsOnMethods = { "flightBookingTest" })
	public void validateFlightSearchTest(Map<String, String> bookingData) {
		SoftAssert softAssert = new SoftAssert();
		BookingResultsPage bookingPage = new BookingResultsPage(driver);
		softAssert.assertEquals(bookingPage.getChooseYourflightTabStatus(), bookingData.get("chooseYourflightTabStatusStatus"));
		softAssert.assertEquals(bookingPage.getFromAndDestination().trim(),
				bookingData.get("selectedFromCity") + " - " + bookingData.get("selectedDestinationCity"));
		softAssert.assertEquals(bookingPage.getPassangersDetails().get("JourneyDates").trim(),
				bookingData.get("outBoundDate") + " - " + bookingData.get("inBoundDate"));
		softAssert.assertEquals(bookingPage.getPassangersDetails().get("PassengersData").trim(),
				bookingData.get("adultsCount") + " Adults, " + bookingData.get("childrenCount") + " Children, "
						+ bookingData.get("babiesCount") + " Babies");
		softAssert.assertAll();

	}

	private Map<String, String> testData() {
		data = new TreeMap<String, String>();
		data.put("fromCity", "Almeria");
		data.put("destinationCity", "Barcelona");
		data.put("outBoundDate", "26/6/2017");
		data.put("inBoundDate", "20/7/2017");
		data.put("adultsCount", "2");
		data.put("childrenCount", "2");
		data.put("babiesCount", "2");		
		data.put("familyType", "General Large Family");
		data.put("selectedFromCity", "Almeria (LEI)");
		data.put("selectedDestinationCity", "Barcelona (BCN)");
		data.put("chooseYourflightTabStatusStatus", "selected");		

		return data;
	}

}
