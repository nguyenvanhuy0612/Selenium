package smoketests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class PageTitleJUnit {
	WebDriver driver;
	String webURL  = "http://sdettraining.com/trguitransactions/NewAccount.aspx";
	
	@Test
	public void pageTitleTest() {
		System.out.println("Running the test");
		driver.get(webURL);
		String actualTitle = driver.getTitle();
		String expectedTitle = "SDET Training | Register New Account";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Before
	public void setUp() {
		System.out.println("Initializing the driver");
		driver = utilities.DriverFactory.CreateDriver("chrome");
	}
	@After
	public void tearDown() {
		System.out.println("Closing the driver");
		driver.close();
	}
}
