package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkspaceEWC2 {
	// Variable
	WebDriver driver;
	String wsURL = "https://www.mercuryholidays.co.uk/";
	String username = "ACC_Huy@automation";
	String password = "1_Abc_123";

	@Test
	public void EWC() throws InterruptedException {
		Thread.sleep(5000);
		
		WebElement ele1 = driver.findElement(By.xpath("//body//nav//li[9]"));
		List<WebElement> ele2 = driver.findElements(By.xpath("//li[9]//div[1]//a"));
		Hover(driver,ele1);
		Hover(driver,ele2.get(0));
		ele2.get(0).click();
	}

	@BeforeMethod
	public void Setup() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
