package stepImplementations;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BDDLoginTest {

	WebDriver driver;

	@Given("^user is on the login page$")
	public void user_is_on_the_login_page() {
		System.out.println("user_is_on_the_login_page");
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");
	}

	@When("^user enters correct username and correct password and click$")
	public void user_enters_correct_username_and_correct_password() {
		System.out.println("user_enters_correct_username_and_correct_password");
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys("huy@gmail.com");
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys("1234");
		driver.findElement(By.id("MainContent_btnLogin")).click();
	}

	@When("^user enters email (.*)$")
	public void user_enters_username(String username) {
		System.out.println("TESTING: " + username);
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys(username);
	}

	@And("^user enters password (.*)$")
	public void user_enters_password(String password) {
		System.out.println("password: " + password);
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
	}

	@And("^user click login button$")
	public void user_click_login_button() {
		driver.findElement(By.id("MainContent_btnLogin")).click();
	}

	@Then("^user gets confirmation$")
	public void user_gets_confirmation() {
		System.out.println("user_gets_confirmation");
		String conf = driver.findElement(By.id("conf_message")).getText();
		System.out.println("conf: " + conf);
		Assert.assertTrue(conf.contains("successfully"));
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
