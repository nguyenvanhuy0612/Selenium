package tests;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	boolean acceptEWC = true;

	@Test
	public void EWC() throws InterruptedException {
		WebDriverWait explicitWait = new WebDriverWait(driver, 60);
		// Login
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();
		// Wait for load
		Thread.sleep(5000);
		// Active
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='submit']"))).click();
		Thread.sleep(2000);
		// Start work
		try {
			driver.findElement(By.xpath("//*[@type='button'][@aria-label='Start Work']")).click();
		} catch (NoSuchElementException e) {
		}
		// Check agent status
		Thread.sleep(1000);
		String agentStatus = driver.findElement(By.xpath("//div[@id='ow_Icon_State2']")).getText();
		System.out.println("agentStatus: " + agentStatus);
		// Go ready
		if (!agentStatus.startsWith("READY")) {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='ow_go_ready']")).click();
		}
		// current WS tab
		wsID = driver.getWindowHandle();
		// Open EWC tab
		Thread.sleep(7000);
		ewcID = chatSetUp();
		Thread.sleep(1000);
		driver.switchTo().window(wsID);
		Thread.sleep(1000);
		// Chon card group
		WebElement selectCard = null;
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//card-group")));
		Thread.sleep(3000);
		List<WebElement> cardgroup = driver.findElements(By.xpath("//card-group"));
		
		//explicitWait.until(ExpectedConditions.visibilityOfAllElements(cardgroup));
		System.out.println("cardgroup.size(): " + cardgroup.size());
		
		for (int i = 0; i <= cardgroup.size(); i++) {
			WebElement curCard = cardgroup.get(i);
			if (selectCard == null) {
				selectCard = cardgroup.get(0);
			}
			if (curCard.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']")).getText().contains(cusEmail)) {
				selectCard = curCard;
				break;
			}
		}
		// Accept or reject
		Thread.sleep(500);
		if (acceptEWC) {
			selectCard.findElement(By.xpath("//*[@id='ow_card_accept_btn']")).click();
		} else {
			selectCard.findElement(By.xpath("//span/button[@aria-label='End']")).click();
		}
		// Hoi thoai
		// ===========================================================================================================
		Thread.sleep(2000);
		cusChat("Hello");
		Thread.sleep(2000);
		agentChat("Hello Cust");
		Thread.sleep(2000);

		// Close
		selectCard.findElement(By.xpath("//*[contains(@id,'ow_Card_End_btn')]")).click();
		WebElement drpAgentStatus = driver
				.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button"));
		Thread.sleep(2000);
		drpAgentStatus.click();
		Thread.sleep(2000);
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='ow_finish_work']"))))
				.click();
		Thread.sleep(2000);
		drpAgentStatus.click();
		Thread.sleep(2000);
		explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='ow_exit']"))))
				.click();
		Thread.sleep(7000);

	}

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
		driver.quit();
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

	public void cusChat(String message) throws InterruptedException {
		driver.switchTo().window(ewcID);
		Thread.sleep(1000);
		WebDriverWait waits = new WebDriverWait(driver, 60);
		waits.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='outmessage']")));
		WebElement action = driver.findElement(By.xpath("//*[@id='outmessage']"));
		action.sendKeys(message);
		action.sendKeys(Keys.ENTER);
	}

	public void agentChat(String message) throws InterruptedException {
		driver.switchTo().window(wsID);
		Thread.sleep(1000);
		WebElement action = driver.findElement(By.xpath("//div[@class='limited-input__container']/textarea"));
		action.sendKeys(message);
		action.sendKeys(Keys.ENTER);
	}

	public static void Hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public void openTab(String URL) {
		String currWindow = driver.getWindowHandle();
		((JavascriptExecutor) driver).executeScript("window.open()");
		windows = driver.getWindowHandles();
		System.out.println("windows" + windows.toString());
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
}
