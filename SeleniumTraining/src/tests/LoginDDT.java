package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDDT {
	WebDriver driver;
	WebElement submitElement, emailElement, passwordElement;

	@Test(dataProvider = "getData")
	public void loginTest(String name, String email, String password) {
		System.out.println("RECORD: " + name + " " + email + " " + password + "\n");
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);
		submitElement.click();

		String message = driver.findElement(By.id("conf_message")).getText();
		System.out.println(message);
	}

	@BeforeMethod
	public void setUp() {
		String url = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.get(url);
		emailElement = driver.findElement(By.id("MainContent_txtUserName"));
		passwordElement = driver.findElement(By.id("MainContent_txtPassword"));
		submitElement = driver.findElement(By.id("MainContent_btnLogin"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public String[][] getData() {
		String filename = "D:\\selenium\\nvhuy\\Training\\Week3\\6. Build a Complete Data-Driven Framework\\45. UserLogin.xls";
		return utilities.Excel.get(filename);
	}
}
