package org.epam.commonutils;

import java.util.Arrays;
import java.util.List;

import org.epam.pages.DateSelectionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DatePickerClass {
	private WebDriver driver;
	private List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December");
	private DateSelectionPage datePage;
	private HelperClass helper;

	public DatePickerClass(WebDriver driver) {
		this.driver = driver;
		this.datePage = new DateSelectionPage(this.driver);
		helper = new HelperClass(this.driver);
	}
	// Date format should be dd-mm-yyyy
	public void selectDate(String expDate) {
		boolean dateNotFound = true;
		String[] date = splitData(expDate, "/");
		int expMonth = Integer.parseInt(date[1]);
		int expYear = Integer.parseInt(date[2]);
		String expDay = date[0];
		while (dateNotFound) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			helper.waitForElementVisible(datePage.getLblMonth(), 20);
			String calMonth = datePage.getLblMonth().getText();
			String calYear = datePage.getLblYear().getText();
			if (monthList.indexOf(calMonth) + 1 == expMonth && (expYear == Integer.parseInt(calYear))) {
				pickDate(expDay);
				dateNotFound = false;
			} else if (monthList.indexOf(calMonth) + 1 < expMonth && (expYear == Integer.parseInt(calYear))
					|| expYear > Integer.parseInt(calYear)) {
				datePage.getLnkNext().click();
			} else if (monthList.indexOf(calMonth) + 1 > expMonth && (expYear == Integer.parseInt(calYear))
					|| expYear < Integer.parseInt(calYear)) {
				datePage.getLnkPrevious().click();
			}
		}

	}
	private void pickDate(String date) {
		List<WebElement> noOfColumns = datePage.getDate().findElements(By.tagName("td"));		
		for (WebElement cell : noOfColumns) {		
			if (cell.getText().equals(date)) {
				WebElement dateElement = cell.findElement(By.linkText(date));
				Assert.assertTrue(dateElement.isEnabled(),
						"Date is not enabled for selection so please choose another date");
				dateElement.click();
				break;
			}
		}
	}

	public String[] splitData(String dataToSplit, String delimiter) {
			return dataToSplit.split(delimiter);
	}
}
