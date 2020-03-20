package tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

		// Wait for load
		Thread.sleep(5000);

		// Active
		driver.findElement(By.xpath("//*[@type='submit']")).click();

		Thread.sleep(4000);
		try {
			driver.findElement(By.xpath("//*[@type='button'][@aria-label='Start Work']")).click();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}

		/*
		 * // Start work if (!driver.findElements(By.
		 * xpath("//*[@type='button'][@aria-label='Start Work']")).isEmpty()) { // THEN
		 * CLICK ON THE START WORK BUTTON
		 * driver.findElement(By.xpath("//*[@type='button'][@aria-label='Start Work']"))
		 * .click(); } else { // DO SOMETHING ELSE AS STARTWORK BUTTON IS NOT THERE
		 * System.out.println("Message: No need start work"); }
		 */

		// Change state
		driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
		Thread.sleep(2000);

		try {
			driver.findElement(By.xpath("//*[@id='ow_go_ready']")).click();
		} catch (NoSuchElementException e) {
			System.out.println("ow_go_ready: " + e.toString());
		}

		String agentStatus = driver.findElement(By.xpath("//div[@id='ow_Icon_State2']")).getText();
		System.out.println("agentStatus: " + agentStatus);

		// current tab
		String curTab0 = driver.getWindowHandle();
		System.out.println("curTab0: " + curTab0);

		// Open new tab
		driver.findElement(By.xpath("/html/body")).click();
		driver.findElement(By.xpath("/html/body")).sendKeys(Keys.chord(Keys.CONTROL, "t"));
		Thread.sleep(2000);
		String curTab1 = driver.getWindowHandle();
		System.out.println("curTab1: " + curTab1);

	}

	@BeforeMethod
	public void Setup() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(wsURL);
	}

	@AfterMethod
	public void TearDown() {
		// driver.close();
	}

	public static void Hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();

	}

}

//String agentStatus = driver.findElement(By.xpath("//div[@id='ow_Icon_State2']")).getText();
//System.out.println("agentStatus: " + agentStatus);
//Hover(driver, driver.findElement(By.xpath("//*[@id='ow_go_not_ready']")));
//List<WebElement> agentStates = driver
//		.findElements(By.xpath("//*[starts-with(@id,'menu_container')][2]/md-menu-content/md-menu-item")); // *[starts-with(@id,"menu_container")][2]/md-menu-content/md-menu-item
//for (WebElement agentState : agentStates) {
//	System.out.println("agentState: " + agentState.getText());
//}
//if (!agentStatus.contains("NOT")) {
//	agentStates.get(3).click();
//}
