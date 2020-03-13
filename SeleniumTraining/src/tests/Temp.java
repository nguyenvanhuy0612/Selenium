package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Temp {
	WebDriver driver;
	String city = "New York, New York";
	String checkIn = "03/10/2020";
	String checkOut = "03/17/2020";
	int adults = 3;
	String starInput = "star-3";
	String searchInput = "2";

	@Test
	public void hotelReservation() throws Exception {
		// 1 Search

		closeAdsWindows();
		// 2. Modify the search results page, give criteria

		By byStar = By.cssSelector("label[class='uitk-button-toggle-label'][for='" + starInput + "']");

		driver.findElement(byStar).click();
		closeAdsWindows();

		// 3. Analyze the results and make our selection
		Thread.sleep(6000);
		By byResult = By.xpath("//*[@class = 'results']/ol/li[" + searchInput + "]/div/div/a");

		driver.findElement(byResult).click();
		// System.out.println(
		// driver.findElement(By.xpath("//*[@id='app']//a[@data-stid =
		// 'open-hotel-information']")).getText());

		// switch tabs
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		// String currWindow = driver.getWindowHandle();
//		for (String window : windows) {
//			driver.switchTo().window(window);
//			String currentUrl = driver.getCurrentUrl();
//			System.out.println("URL cua cua so " + window + " la: " + currentUrl);
//		}
		driver.switchTo().window(windows.get(1));
		Thread.sleep(5000);
		if (!driver.getCurrentUrl().contains("https://www.expedia.com")) {
			driver.close();
			driver.switchTo().window(windows.get(0));
			driver.findElement(byResult).click();
		}
		// Print data
		By hotelBy = By.xpath("//*[@id='app']//h1[@data-stid='content-hotel-title']");
		String hotelName = driver.findElement(hotelBy).getText();
		By rateBy = By.xpath("//*[@id='app']//span[@class='reviews-summary__rating-value']");
		String ratePoint = driver.findElement(rateBy).getText();
		System.out.println(hotelName);
		System.out.println(ratePoint);

		// 4. Book reservation
		By reserveBy = By.xpath("//button[@class='uitk-button uitk-button-small uitk-button-primary']");
		System.out.println("reserveBy: " + reserveBy);
		driver.findElement(reserveBy).click();
		// 5. Fill out contact / bill

		// 6. Get Confirmation

	}

	@BeforeMethod
	public void setUp() {
		String url = "https://www.expedia.com/Hotel-Search?adults=1&destination=New%20York%2C%20New%20York&endDate=2020-03-20&latLong=40.75668%2C-73.98647&regionId=178293&rooms=1&semdtl=&sort=RECOMMENDED&startDate=2020-03-10&theme=&useRewards=true&userIntent";
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		// driver.quit();
	}

	public void closeAdsWindows() {
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				System.out.println(driver.getTitle());
				// Closing the Child Window.
				driver.close();
			}
		}
		driver.switchTo().window(MainWindow);
	}
}
