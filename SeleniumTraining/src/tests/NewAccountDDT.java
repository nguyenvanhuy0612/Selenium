package tests;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@RunWith(value = Parameterized.class)
public class NewAccountDDT {
	String name, email, phone, gender, password, country;
	boolean weeklyEmail, monthlyEmail, occasionalEmail;
	WebElement nameElement, emailElement, phoneNumberElement, passwordElement, verifyPasswordElement, countryElement,
			maleRadio, femaleRadio, weeklyCheckbox, monthlyCheckbox, occasionalUpdate, submitButton;
	WebDriver driver;

	@Test
	public void NewAccountTest() {
		System.out.println("NEW RECORD: " + name + " " + email + " " + phone + " " + gender + " " + password + " "
				+ country + " " + weeklyEmail + " " + monthlyEmail + " " + occasionalEmail);
		// defineWebElements()
		defineWebElements();
		// Fill out the form
		nameElement.sendKeys(name);
		emailElement.sendKeys(email);
		phoneNumberElement.sendKeys(phone);
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
		// +weeklyEmail
		if (weeklyEmail) {
			if (!weeklyCheckbox.isSelected()) {
				weeklyCheckbox.click();
			}
		} else {
			if (weeklyCheckbox.isSelected()) {
				weeklyCheckbox.click();
			}
		}
		// +monthlyEmail
		if (monthlyEmail) {
			if (!monthlyCheckbox.isSelected()) {
				monthlyCheckbox.click();
			}
		} else {
			if (monthlyCheckbox.isSelected()) {
				monthlyCheckbox.click();
			}
		}
		// +occasionalEmail
		if (occasionalEmail) {
			if (!occasionalUpdate.isSelected()) {
				occasionalUpdate.click();
			}
		} else {
			if (occasionalUpdate.isSelected()) {
				occasionalUpdate.click();
			}
		}
		// Submit
		submitButton.click();
		// Confirm
		String confirm = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		if (confirm.equalsIgnoreCase("Customer information added successfully")) {
			System.out.println("confirm: " + confirm);
		} else {
			System.out.println("Test Failed");
		}
		System.out.println("Title: " + driver.getTitle());
	}

	@Before
	public void setUp() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		String baseUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		driver.get(baseUrl);
		driver.findElement(By.xpath("//*[@id=\"ctl01\"]/div[3]/div[2]/div/div[2]/a")).click();
	}

	@After
	public void tearDown() {
		driver.close();
	}

	public void defineWebElements() {
		// Define Web Elements
		nameElement = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
		emailElement = driver.findElement(By.id("MainContent_txtEmail"));
		phoneNumberElement = driver.findElement(By.xpath("//*[@id=\"MainContent_txtHomePhone\"]"));
		passwordElement = driver.findElement(
				By.cssSelector("input[id='MainContent_txtPassword'][name='ctl00$MainContent$txtPassword']"));
		verifyPasswordElement = driver
				.findElement(By.cssSelector("input[type='password'][name='ctl00$MainContent$txtVerifyPassword']"));
		countryElement = driver.findElement(By.id("MainContent_menuCountry"));
		maleRadio = driver.findElement(By.name("ctl00$MainContent$Gender"));
		femaleRadio = driver.findElement(By.id("MainContent_Female"));
		weeklyCheckbox = driver.findElement(By.id("MainContent_checkWeeklyEmail"));
		monthlyCheckbox = driver.findElement(By.id("MainContent_checkMonthlyEmail"));
		occasionalUpdate = driver.findElement(By.id("MainContent_checkUpdates"));
		submitButton = driver.findElement(By.id("MainContent_btnSubmit"));
	}

	@Parameters
	public static List<String[]> getData() {
		String filename = "D:\\selenium\\nvhuy\\Training\\Week3\\6. Build a Complete Data-Driven Framework\\43. UserAccounts.csv";
		return utilities.CSV.get(filename);
	}

	// Constructor that passes parameters to the test method
	// Thu tu cua bien truyen vao trong ham truyen quyet dinh thu tu cua gia tri lay
	// ra
	public NewAccountDDT(String name, String email, String phone, String gender, String password, String country,
			String weeklyEmail, String monthlyEmail, String occasionalEmail) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
		this.country = country;
		if (weeklyEmail.equalsIgnoreCase("true")) {
			this.weeklyEmail = true;
		} else {
			this.weeklyEmail = false;
		}
		if (monthlyEmail.equalsIgnoreCase("true")) {
			this.monthlyEmail = true;
		} else {
			this.monthlyEmail = false;
		}
		if (occasionalEmail.equalsIgnoreCase("true")) {
			this.occasionalEmail = true;
		} else {
			this.occasionalEmail = false;
		}
	}
}
