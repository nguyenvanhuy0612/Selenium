package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkspaceEWC2 {
	// Variable
	WebDriver driver;
	String wsURL = "https://google.com";
	String username = "ACC_Huy@automation";
	String password = "1_Abc_123";

	@Test
	public void TEST() throws InterruptedException {
		String mainTab = driver.getWindowHandle();
		System.out.println("mainTab: " + mainTab);
		// driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "t");

		// new tabs
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("windows" + windows);
		driver.switchTo().window(windows.get(1));
		System.out.println("Tab1: " + driver.getWindowHandle());
		driver.get(wsURL);
		Thread.sleep(5000);
		
		((JavascriptExecutor) driver).executeScript("window.open()");
		windows = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("windows" + windows);
		driver.switchTo().window(windows.get(2));
		System.out.println("Tab1: " + driver.getWindowHandle());
		driver.get(wsURL);

//		((JavascriptExecutor) driver).executeScript("window.open()");
//		Set<String> tabs = driver.getWindowHandles();
//		System.out.println("List tabs: " + tabs);
//		Iterator<String> tabsList = tabs.iterator();
	}

	@BeforeMethod
	public void Setup() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		driver.get(wsURL);
	}

	@AfterMethod
	public void TearDown() {

	}

	public static void Hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

}
