package HuyData;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.DashboardPage;
import pages.LoginPageFactory;

public class LoginFactory {
	@Test
	public void loginFactoryTest() {
		String username = "huy@gmail.com";
		String password = "1234";
		// 1. driver
		WebDriver driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");

		// 2 login
		LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
		loginPageFactory.login(username, password);

		// 3 Get info
		DashboardPage dashboardPage = new DashboardPage(driver);
		String conf = dashboardPage.confirmationMessage();

		// 4 Assertion
		Assert.assertTrue(conf.contains("successfully"));

		// 5 Close
		driver.close();
		driver.quit();
	}
}
