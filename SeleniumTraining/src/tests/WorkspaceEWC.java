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
	WebDriverWait waits = new WebDriverWait(driver, 60);
	String wsID;
	String ewcID;
	String wsURL = "http://100.30.5.92:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";// "http://100.30.6.137:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";
	String webchatURL = "http://10.30.1.210:81/ewcsite%20-%20mcha576%20link/";// "http://10.30.1.236:8080/ewcsite/";
	String checkLink = "https://100.30.5.76:8445/CustomerControllerWeb/currentqueue";// "https://autosrv98:8445/CustomerControllerWeb/callback";
	String username = "aoc\\nvhuy0002"; // "ACC_Huy@automation";
	String password = "1_Abc_123";
	String skillset = "WC_Webchat3";// "WC_HUY_1";
	String cusEmail = "huy@gmail.com";
	String cusName = "huy";

	@BeforeMethod
	public void Setup() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(wsURL);
		wsID = driver.getWindowHandle();
	}

	@AfterMethod
	public void TearDown() {
		// driver.close();
	}

	@Test
	public void EWC() throws InterruptedException {
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
		}
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
		wsID = driver.getWindowHandle();
		// Open EWC tab
		ewcID = chatSetUp();
		Thread.sleep(1000);


	}

	public String chatSetUp() throws InterruptedException {
		openTab(webchatURL);
		ewcID = driver.getWindowHandle();
		Thread.sleep(1000);
		if (!(driver.findElement(By.xpath("//*[@id='chatPanel']/a")).isDisplayed())) {
			openTab(checkLink);
			try {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='details-button']")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@id='proceed-link']")).click();
				Thread.sleep(500);
			} catch (Exception e) {
			}
			driver.close();
			driver.switchTo().window(ewcID);
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			if (!(driver.findElement(By.xpath("//*[@id='chatPanel']/a")).isDisplayed())) {
				System.out.println("Khong tim thay skillset nao");
				return ewcID;
			}
		}
		driver.findElement(By.xpath("//*[@id='chatPanel']/a")).click();
		Thread.sleep(1000);
		// Form chat
		WebElement chatForm = driver.findElement(By.xpath("//*[@id='chatForm']"));
		// cusName
		chatForm.findElement(By.xpath("//*[@id='user-chat']")).sendKeys(cusName);
		// cusEmail
		chatForm.findElement(By.xpath("//*[@id='email-chat']")).sendKeys(cusEmail);
		// Skillset
		Select drpSkill = new Select(chatForm.findElement(By.xpath("//*[@id='skillset-chat']")));
		drpSkill.selectByVisibleText(skillset);
		Thread.sleep(500);
		chatForm.findElement(By.xpath("//*[@id='openbutton-chat']")).click();
		Thread.sleep(500);
		return ewcID;
	}

	public void cusChat(String message) {
		driver.switchTo().window(ewcID);
		waits.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='outmessage']")));
		driver.findElement(By.xpath("//*[@id='outmessage']")).sendKeys(message);
	}

	public void agentChat(String message) {
		driver.switchTo().window(wsID);
		driver.findElement(By.xpath("//div[@class='limited-input__container']/textarea")).sendKeys(message);
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
				System.out.println("NEW TAB WITH ID: " + driver.getWindowHandle());
				driver.get(URL);
				break;
			}
		}

	}

	public void EWCpage(String URL, String linkCheck, String skillset) {

	}

}

//}
