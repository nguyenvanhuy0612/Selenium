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

public class WorkspaceEWC {
	// Variable
	WebDriver driver;
	String wsURL = "http://100.30.6.137:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";
	String username = "ACC_Huy@automation";
	String password = "1_Abc_123";

	@Test
	public void EWC() throws InterruptedException {
		// Login

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(5000);

		// Active

		driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Thread.sleep(2000);
//		try {
//			driver.findElement(By.xpath("//*[@type='button'][@aria-label='Start Work']")).click();
//		} catch (NoSuchElementException e) {
//			System.out.println(e.toString());
//		}
		if (!driver.findElements(By.xpath("//*[@type='button'][@aria-label='Start Work']")).isEmpty()) {
			// THEN CLICK ON THE SUBMIT BUTTON
			driver.findElement(By.xpath("//*[@type='button'][@aria-label='Start Work']")).click();
		} else {
			// DO SOMETHING ELSE AS SUBMIT BUTTON IS NOT THERE
			System.out.println("Message: No need start work");
		}
		driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
		Thread.sleep(2000);
		String agentStatus = driver.findElement(By.xpath("//div[@id='ow_Icon_State2']")).getText();
		System.out.println("agentStatus: " + agentStatus);
		Hover(driver, driver.findElement(By.xpath("//*[@id='ow_go_not_ready']")));
		List<WebElement> agentState = driver
				.findElements(By.xpath("//*[@id='menu_container_22']/md-menu-content/md-menu-item"));
		if (!agentStatus.contains("NOT")) {
			agentState.get(2).click();
		}

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
