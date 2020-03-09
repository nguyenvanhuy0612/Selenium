package pk.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewAccount {

	public static void main(String[] args) {
		// Define data
		String name = "Huy Nguyen";
		String email = "nguyenhuy11@gmail.com";
		String phoneNumber = "0982785214";
		String password = "testpass";
		String browserType = "chrome";
		String country = "China";
		String gender = "Female";
		Boolean weeklyEmail = true;
		Boolean monthlyEmail = true;
		Boolean occasionalEmail = false;

		// Define driver
		WebDriver driver;
		driver = utilities.DriverFactory.CreateDriver(browserType);
		String baseUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		driver.get(baseUrl);
		driver.findElement(By.xpath("//*[@id=\"ctl01\"]/div[3]/div[2]/div/div[2]/a")).click();

		// Define Web Elements
		WebElement nameElement = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
		WebElement emailElement = driver.findElement(By.id("MainContent_txtEmail"));
		WebElement phoneNumberElement = driver.findElement(By.xpath("//*[@id=\"MainContent_txtHomePhone\"]"));
		WebElement passwordElement = driver.findElement(
				By.cssSelector("input[id='MainContent_txtPassword'][name='ctl00$MainContent$txtPassword']"));
		WebElement verifyPasswordElement = driver
				.findElement(By.cssSelector("input[type='password'][name='ctl00$MainContent$txtVerifyPassword']"));
		WebElement countryElement = driver.findElement(By.id("MainContent_menuCountry"));
		WebElement maleRadio = driver.findElement(By.name("ctl00$MainContent$Gender"));
		WebElement femaleRadio = driver.findElement(By.id("MainContent_Female"));
		WebElement weeklyCheckbox = driver.findElement(By.id("MainContent_checkWeeklyEmail"));
		WebElement monthlyCheckbox = driver.findElement(By.id("MainContent_checkMonthlyEmail"));
		WebElement occasionalUpdate = driver.findElement(By.id("MainContent_checkUpdates"));
		WebElement submitButton = driver.findElement(By.id("MainContent_btnSubmit"));

		// Fill out the form
		nameElement.sendKeys(name);
		emailElement.sendKeys(email);
		phoneNumberElement.sendKeys(phoneNumber);
		passwordElement.sendKeys(password);
		verifyPasswordElement.sendKeys(password);
		new Select(countryElement).selectByVisibleText(country);

		// The Ratio Button Algorithm
		if (gender.equalsIgnoreCase("male")) {
			maleRadio.click();
		} else {
			femaleRadio.click();
		}

		// Check box Algorithm
		// weeklyEmail
		if (weeklyEmail) {
			if (!weeklyCheckbox.isSelected()) {
				weeklyCheckbox.click();
			}
		} else {
			if (weeklyCheckbox.isSelected()) {
				weeklyCheckbox.click();
			}
		}
		// monthlyEmail
		if (monthlyEmail) {
			if (!monthlyCheckbox.isSelected()) {
				monthlyCheckbox.click();
			}
		} else {
			if (monthlyCheckbox.isSelected()) {
				monthlyCheckbox.click();
			}
		}
		// occassionalEmail
		if (occasionalEmail) {
			if (!occasionalUpdate.isSelected()) {
				occasionalUpdate.click();
			}
		} else {
			if (occasionalUpdate.isSelected()) {
				occasionalUpdate.click();
			}
		}

		submitButton.click();

		// Confirm
		String confirm = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		if (confirm.equalsIgnoreCase("Customer information added successfully")) {
			System.out.println("confirm: " + confirm);
		} else {
			System.out.println("Test Failed");

		}
		System.out.println("Title: " + driver.getTitle());

		// Close
		driver.close();
		// driver.quit();
	}

}

/*
 * driver.findElement(By.linkText("Create Account")).click();
 * driver.findElement(By.linkText("CREATE ACCOUNT")).click();
 * driver.findElement(By.xpath("//*[@id=\"ctl01\"]/div[3]/div[2]/div/div[2]/a"))
 * .click();
 * 
 * new Select(driver.findElement(By.id("MainContent_menuCountry"))).
 * selectByVisibleText("China");
 * driver.findElement(By.xpath("//*[@id=\"MainContent_menuCountry\"]/option[8]")
 * ).click();
 * 
 * 
 */

/*
 * String gender; String weeklyEmail; String monthlyEmail; String
 * occassionalEmail;
 * 
 */
