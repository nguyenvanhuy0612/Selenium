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
	WebDriver driver;
	String city = "New York, New York";
	String checkIn = "03/20/2020";
	String checkOut = "03/25/2020";
	int adults = 1;
	String starInput = "star-3";
	String searchInput = "2";

	@Test
	public void hotelReservation() throws Exception {
		// 1 Search
		Thread.sleep(2000);
		By hotelbutonBy = By.xpath("//button[@data-lob='hotel']/span[1]");
		System.out.println("hotelbutonBy: "+hotelbutonBy.toString());
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(hotelbutonBy)).click();
		hotelbutonBy = null;
		// driver.findElement(hotelbutonBy).click();

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
		adultsElementValue = decreaseAdultsElement = increaseAdultsElement = null;

		closeAdsWindows();
		driver.findElement(By.cssSelector("#gcw-hotel-form-hp-hotel > div.cols-nested.ab25184-submit > label > button"))
				.click();
		closeAdsWindows();

		// 2. Modify the search results page, give criteria
		By byStar = By.cssSelector("label[class='uitk-button-toggle-label'][for='" + starInput + "']");
		driver.findElement(byStar).click();
		byStar = null;
		closeAdsWindows();

		// 3. Analyze the results and make our selection
		// Thread.sleep(5000);
		By byResult = By.xpath("//*[@class = 'results']/ol/li[" + searchInput + "]/div/div/a");
		driver.findElement(byResult).click();
		byResult = null;
		// closeAdsWindows();

		// switch tabs
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(windows.get(1));
		System.out.println("URL cua cua so la: " + driver.getCurrentUrl());
		Thread.sleep(5000);
		if (!driver.getCurrentUrl().contains("https://www.expedia.com")) {
			// driver.close();
			driver.switchTo().window(windows.get(0));
		}

		// Print data
		By hotelBy = By.xpath("//*[@id='app']//h1[@data-stid='content-hotel-title']");
		String hotelName = driver.findElement(hotelBy).getText();
		System.out.println("hotelName: " + hotelName);

		By rateBy = By.xpath("//*[@id='app']//span[@class='reviews-summary__rating-value']");
		String ratePoint = driver.findElement(rateBy).getText();
		System.out.println("ratePoint: " + ratePoint);
		Thread.sleep(3000);
		hotelBy  = rateBy = null;
		hotelName = ratePoint = null;

		// 4. Book reservation
		By reserveBy = By.xpath("//button[@class='uitk-button uitk-button-small uitk-button-primary']");
		System.out.println("reserveBy: " + reserveBy);
		driver.findElement(reserveBy).click();

		By firstPickBy = By.xpath("(//button[@type='submit'])[3]");
		System.out.println("firstPickBy: " + firstPickBy.toString());
		driver.findElement(firstPickBy).click();
		reserveBy = firstPickBy = null;
		//del Null
		System.gc();
		
		// 5. Fill out contact / bill

		// 6. Get Confirmation

	}

	@BeforeMethod
	public void setUp() {
		String url = "https://www.expedia.com";
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
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> listWindows = windows.iterator();
		while (listWindows.hasNext()) {
			String ChildWindow = listWindows.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				System.out.println("closeAdsWindows: " + ChildWindow + driver.getTitle() + driver.getCurrentUrl());
				// Closing the Child Window.
				if (!driver.getCurrentUrl().contains("expedia.com")) {
					driver.close();
				}
			}
		}
		driver.switchTo().window(MainWindow);
	}
}
