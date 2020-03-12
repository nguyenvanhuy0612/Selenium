package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Expedia {
	WebDriverWait wait;
	WebDriver driver;
	String city = "New York, New York";
	String checkIn = "03/12/2020";
	String checkOut = "03/17/2020";
	int adults = 3;
	String star = "star-3";
	String posResult = "2";

	@Test
	public void hotelReservation() throws Exception {
		// 1 Search

		WebElement hotelElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-lob='hotel']")));
		hotelElement.click();
		driver.findElement(By.id("hotel-destination-hp-hotel")).sendKeys(city);
		driver.findElement(By.id("hotel-checkin-hp-hotel")).sendKeys(checkIn);
		driver.findElement(By.id("hotel-checkout-hp-hotel")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.findElement(By.id("hotel-checkout-hp-hotel")).sendKeys(checkOut);
		driver.findElement(By.cssSelector("#traveler-selector-hp-hotel > div > ul > li > button")).click();
		WebElement adultsElementValue = driver.findElement(By.cssSelector(
				"#traveler-selector-hp-hotel > div > ul > li > div > div > div.traveler-selector-room-data > div.uitk-grid.step-input-outside.gcw-component.gcw-component-step-input.gcw-step-input.gcw-component-initialized > div.uitk-col.all-col-shrink.uitk-step-input-value-wrapper.traveler-selector-traveler-field > span"));
		WebElement decreaseAdultsElement = driver.findElement(By.cssSelector(
				"#traveler-selector-hp-hotel > div > ul > li > div > div > div.traveler-selector-room-data > div.uitk-grid.step-input-outside.gcw-component.gcw-component-step-input.gcw-step-input.gcw-component-initialized > div:nth-child(2) > button"));
		WebElement increaseAdultsElement = driver.findElement(By.cssSelector(
				"#traveler-selector-hp-hotel > div > ul > li > div > div > div.traveler-selector-room-data > div.uitk-grid.step-input-outside.gcw-component.gcw-component-step-input.gcw-step-input.gcw-component-initialized > div:nth-child(4) > button"));
		while (true) {
			if (Integer.parseInt(adultsElementValue.getText()) > adults) {
				decreaseAdultsElement.click();
			} else if (Integer.parseInt(adultsElementValue.getText()) < adults) {
				increaseAdultsElement.click();
			} else {
				break;
			}
		}
		driver.findElement(By.cssSelector("#gcw-hotel-form-hp-hotel > div.cols-nested.ab25184-submit > label > button"))
				.click();
		closeAdsWindows();
		// 2. Modify the search results page, give criteria

		WebElement starElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='" + star + "']")));
		starElement.click();
		closeAdsWindows();
		
		// 3. Analyze the results and make our selection
		WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//section[@class = 'results']/ol/li[" + posResult + "]/div/div/a")));
		resultElement.click();

		// switch tabs
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		// Print data
		String hotelName = driver.findElement(By.xpath("//h1[@data-stid='content-hotel-title']")).getText();
		String ratePoint = driver.findElement(By.xpath("//span[@class='reviews-summary__rating-value']")).getText();
		System.out.println(hotelName);
		System.out.println(ratePoint);
		// 4. Book reservation

		// 5. Fill out contact / bill

		// 6. Get Confirmation

	}

	@BeforeMethod
	public void setUp() {
		String url = "https://www.expedia.com/";
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
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
