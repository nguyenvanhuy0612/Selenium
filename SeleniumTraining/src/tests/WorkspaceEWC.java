package tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkspaceEWC {
	// Variable
	WebDriver driver;
	String wsURL = "http://100.30.5.92:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";// "http://100.30.6.137:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";
	String webchatURL = "http://10.30.1.210:81/ewcsite%20-%20mcha576%20link/";// "http://10.30.1.236:8080/ewcsite/";
	String checkLink = "https://mcha576.aoc.com:8445/CustomerControllerWeb/currentqueue";// "https://autosrv98:8445/CustomerControllerWeb/callback";
	String username = "aoc\\nvhuy0002"; // "ACC_Huy@automation";
	String password = "1_Abc_123";
	String skillset = "WC_Webchat2";//"WC_HUY_1";

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

		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//*[@type='button'][@aria-label='Start Work']")).click();
		} catch (NoSuchElementException e) {
			// System.out.println(e.toString());
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
		Thread.sleep(2000);
		String agentStatus = driver.findElement(By.xpath("//div[@id='ow_Icon_State2']")).getText();
		System.out.println("agentStatus: " + agentStatus);

		if (!agentStatus.startsWith("READY")) {
			driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='ow_go_ready']")).click();
		}

		// current WS tab
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		String mainTab0 = windows.get(0);
		System.out.println("mainTab0: " + mainTab0);

		// Open EWC tab
		Thread.sleep(1000);
		windows = openAndSwitchNewTabs(webchatURL, 1);

		// Check link EWC
		Thread.sleep(1000);
		windows = openAndSwitchNewTabs(checkLink, 2);

		try {
			driver.findElement(By.xpath("//*[@id='details-button']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='proceed-link']")).click();
			Thread.sleep(1000);
		} catch (NoSuchElementException e) {
		}
		// Close check link EWC
		driver.close();

		// return to EWC tab
		driver.switchTo().window(windows.get(1));
		driver.navigate().refresh();
		Thread.sleep(1000);

		// Initial chat
		driver.findElement(By.xpath("//*[@id='chatPanel']/a")).click();

		Select drpSkillset = new Select(driver.findElement(By.cssSelector("#skillset-chat")));
		drpSkillset.selectByVisibleText(skillset);

		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='openbutton-chat']/span")).click();

		driver.switchTo().alert().accept();

		// sw to ws tab
		Thread.sleep(1000);
		driver.switchTo().window(windows.get(0));

		WebDriverWait waits = new WebDriverWait(driver, 60);
		waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ow_card_accept_btn']")));

		driver.findElement(By.xpath("//*[@id='ow_card_accept_btn']")).click();

	}

	@BeforeMethod
	public void Setup() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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

	public ArrayList<String> openAndSwitchNewTabs(String URL, int posTab) {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("windows" + windows);
		driver.switchTo().window(windows.get(posTab));
		System.out.println("CurrTabID: " + driver.getWindowHandle());
		driver.get(URL);
		return windows;
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
