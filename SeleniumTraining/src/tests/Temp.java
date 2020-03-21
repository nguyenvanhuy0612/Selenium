package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Temp {
	WebDriver driver;
	ArrayList<String> windows;
	String city = "New York, New York";
	String checkIn = "03/10/2020";
	String checkOut = "03/17/2020";
	int adults = 3;
	String starInput = "star-3";
	String searchInput = "2";

	@Test
	public void hotelReservation() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"chatPanel\"]/a")).click();
		Thread.sleep(5000);

		WebElement khungchat = driver.findElement(By.xpath("//*[@id='chatPanel']"));
		int aTagName = driver.findElements(By.tagName("a")).size();
		WebElement khungskill = khungchat.findElement(By.id("skillset-chat"));
		int aTag = khungchat.findElements(By.tagName("a")).size();
		System.out.println("aTagName: " + aTagName);
		System.out.println("aTag: " + aTag);
		System.out.println("So luong tag Select: "+khungchat.findElements(By.tagName("select")).size());
		Thread.sleep(5000);
	}

	@BeforeMethod
	public void setUp() {
		String url = "http://10.30.1.210:81/ewcsite%20-%20mcha576%20link/";
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		// driver.manage().deleteAllCookies();
		// driver.manage().window().maximize();
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
