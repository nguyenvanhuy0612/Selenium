package tests;

import java.util.Iterator;
import java.util.Set;
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
	Set<String> windows;
	Iterator<String> itr;
	String wsURL = "http://100.30.5.92:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";// "http://100.30.6.137:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";
	String webchatURL = "http://10.30.1.210:81/ewcsite%20-%20mcha576%20link/";// "http://10.30.1.236:8080/ewcsite/";
	String checkLink = "https://mcha576.aoc.com:8445/CustomerControllerWeb/currentqueue";// "https://autosrv98:8445/CustomerControllerWeb/callback";
	String username = "aoc\\nvhuy0002"; // "ACC_Huy@automation";
	String password = "1_Abc_123";
	String skillset = "WC_Webchat2";// "WC_HUY_1";
	String customerEmail = "huy@gmail.com";
	
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
	//@Test
	public void EWC() throws InterruptedException {
		// Thong so test
		WebDriverWait waits = new WebDriverWait(driver, 60);
		windows = driver.getWindowHandles();

		// Login
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();

		// Wait for load
		Thread.sleep(5000);

		// Active
		waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='submit']"))).click();

		Thread.sleep(2000);
		// Start work
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

		// Check agent status
		Thread.sleep(2000);
		String agentStatus = driver.findElement(By.xpath("//div[@id='ow_Icon_State2']")).getText();
		System.out.println("agentStatus: " + agentStatus);
		if (!agentStatus.startsWith("READY")) {
			driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='ow_go_ready']")).click();
		}

		// current WS tab

		// Open EWC tab
		Thread.sleep(1000);

		// Check link EWC
		Thread.sleep(1000);

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

		waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ow_card_accept_btn']")));

		driver.findElement(By.xpath("//*[@id='ow_card_accept_btn']")).click();

	}
	@Test
	public void chat(String message) throws InterruptedException{
		openTab(webchatURL);
	}
	



	public static void Hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public void openTab(String URL) {
		((JavascriptExecutor) driver).executeScript("window.open()");
		windows = driver.getWindowHandles();
		System.out.println("windows" + windows.toString());
		String currWindow = driver.getWindowHandle();
		itr = windows.iterator();
		while (itr.hasNext()) {
			String itrWindow = itr.next();
			if (itrWindow.equalsIgnoreCase(currWindow)) {
				itrWindow = itr.next();
				driver.switchTo().window(itrWindow);
				driver.get(URL);
				break;
			}
		}

	}

	public void EWCpage(String URL, String linkCheck, String skillset) {

	}

}

//}
