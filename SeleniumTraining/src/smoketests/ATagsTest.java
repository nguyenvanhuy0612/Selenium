package smoketests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ATagsTest {
	WebDriver driver;

	@Test
	public void loginElementsPresentTest() {
		System.out.println("Running test");

		boolean createAccountpresent = false;
		List<WebElement> aElements = driver.findElements(By.tagName("a"));

		int numberOfAElements = aElements.size();
		System.out.println("There are " + numberOfAElements + " a tags on the page");

		for (WebElement aElement : aElements) {
			System.out.println(aElement.getText());
			if (aElement.getText().equals("CREATE ACCOUNT")) {
				createAccountpresent = true;
				break;
			}
		}

		// Assertion
		Assert.assertTrue(createAccountpresent);
	}

	@BeforeMethod
	public void setUp() {
		System.out.println("Starting test");
		driver = utilities.DriverFactory.CreateDriver("chrome");

		String webURL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		driver.get(webURL);
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("Closing test");
		driver.close();
	}
}
