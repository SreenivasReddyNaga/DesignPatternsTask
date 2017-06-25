package org.epam.tests;

public class SampleTest {

	public static void main(String[] args) {

		String journeyDetails = "26/06/2017 - 20/07/2017 | 2 Adults, 2 Children, 2 Babies";

		String[] splittedData = splitData(journeyDetails, "\\|");
		System.out.println("splittedData " + splittedData);
		System.out.println("JourneyDates" + splittedData[0]);
		System.out.println("PassengersData" + splittedData[1]);
	}

	public static String[] splitData(String dataToSplit, String delimiter) {
		return dataToSplit.split(delimiter);
	}

}
