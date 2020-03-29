package stepEWC;

import java.util.ArrayList;
import java.util.List;
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

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EWCChat {
	WebDriver driver;
	ArrayList<String> tabs;
	String[][] tabInfo = new String[10][2];
	int posTab = 0;
	String wsURL = "http://100.30.5.92:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";// "http://100.30.6.137:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";
	String webchatURL = "http://10.30.1.210:81/ewcsite%20-%20mcha5.76%20ip/";// "http://10.30.1.236:8080/ewcsite/";
	String checkLink = "https://100.30.5.76:8445/CustomerControllerWeb/currentqueue";// "https://autosrv98:8445/CustomerControllerWeb/callback";
	String agentID = "aoc\\nvhuy0002"; // "ACC_Huy@automation";
	String agentPass = "1_Abc_123";
	// String skillset = "WC_Webchat3";// "WC_HUY_1";
	// String cusEmail = "huy@gmail.com";
	// String cusName = "huy";
	WebDriverWait explicitWait;

	@Given("^agent login into Workspace$")
	public void login() throws InterruptedException {
		System.out.println("Agent login into Workspace");
// setUp	
		setUp();
// Login and wait for page load
		driver.findElement(By.id("username")).sendKeys(agentID);
		driver.findElement(By.id("password")).sendKeys(agentPass);
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);
// Active
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']"))).click();
		Thread.sleep(1000);
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
			goReady();
		}
	}

	@When("^customer send ewc with (.*), (.*) and (.*)$")
	public void sendEWC(String cusName, String cusEmail, String skillset) throws InterruptedException {
		System.out.println("cus send ewc: " + cusName + " " + cusEmail + " " + skillset);
		// Mo tab moi
		((JavascriptExecutor) driver).executeScript("window.open('" + webchatURL + "', '_blank')");
		tabs = new ArrayList<String>(driver.getWindowHandles());
		posTab++;
		tabInfo[posTab][0] = String.valueOf(posTab);
		tabInfo[posTab][1] = cusEmail;
		System.out.println("tabs.size(): " + tabs.size());
		driver.switchTo().window(tabs.get(tabs.size() - 1)); // chuyen qua tab moi
		Thread.sleep(500);
		if (!(driver.findElement(By.xpath("//*[@id='chatPanel']/a")).isDisplayed())) { // check link chat
			((JavascriptExecutor) driver).executeScript("window.open('" + checkLink + "', '_blank')");
			tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size() - 1));
			try {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='details-button']")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@id='proceed-link']")).click();
				Thread.sleep(500);
			} catch (Exception e) {
			}
			driver.close();
			tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size() - 1));
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			if (!(driver.findElement(By.xpath("//*[@id='chatPanel']/a")).isDisplayed())) {
				System.out.println("Khong tim thay skillset nao");
			}
		}
		driver.findElement(By.xpath("//*[@id='chatPanel']/a")).click();
		Thread.sleep(500);
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
		Thread.sleep(1000);
	}

	@And("^agent accepts$")
	public void accept() throws InterruptedException {
		System.out.println("accept");
		driver.findElement(By.xpath("//*[@id='ow_card_accept_btn']")).click();
		Thread.sleep(1000);
	}

	@And("^agent accepts (.+)$")
	public void agentAccepts(String email) throws InterruptedException {
		System.out.println("agentAccept cus with email " + email);
		agentAccept(email);
	}

	@And("^agent chat (.+) with (.+)$")
	public void agentChat(String chat1, String cusEmail) throws InterruptedException {
		System.out.println("agent " + chat1 + " with " + cusEmail);
		currAgentChat(chat1, cusEmail);
	}

	@And("^customer with (.+) chat (.*)$")
	public void cusChat(String cusEmail, String chat) throws NumberFormatException, InterruptedException {
		System.out.println("cus with email " + cusEmail + " chat: " + chat);
		for (int i = 1; i <= 9; i++) {
			if (tabInfo[i][1].contains(cusEmail)) {
				currCusChat(Integer.parseInt(tabInfo[i][0]), chat);
				break;
			}
		}
	}

	@And("^agent switch to ewc (.*)$")
	public void swWorkcard(String cusEmail) throws InterruptedException {
		System.out.println("Switch to work card " + cusEmail);
		swToWorkcard(cusEmail);
	}

	@And("^agent unhold (.+)$")
	public void unhold(String cusEmail) throws InterruptedException {
		System.out.println("unhold");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@ng-click='unhold()']")).click();
		Thread.sleep(1000);
	}

	@And("^agent close ewc1 and ewc2$")
	public void closeEWC() throws InterruptedException {
		System.out.println("Close EWC");
		try {
			driver.findElement(By.xpath("//button[@aria-label='End']")).click();
			Thread.sleep(3000);
			unhold("all");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[@aria-label='End']")).click();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			System.out.println("Close failed");
		}
	}

	@And("^set ACW (.+)$")
	public void setACW(String iptACW) throws InterruptedException {
		System.out.println("Set ACW");
		driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
		Thread.sleep(500);
		Hover(driver, driver.findElement(By.xpath("//*[@id='ow_go_acw']")));
		Thread.sleep(1000);
		List<WebElement> ACWcodes = driver
				.findElements(By.xpath("//*[contains(@id,'menu_container')]//button[@ng-click='goACW(reason.code)']"));
		System.out.println("ACWcodes.size(): " + ACWcodes.size());
		for (WebElement ACWcode : ACWcodes) {
			String acwcode = ACWcode.findElement(By.xpath("./div/div[2]")).getText();
			Thread.sleep(500);
			System.out.println("current acwcode: " + acwcode);
			if (acwcode.contains(iptACW)) {
				Thread.sleep(500);
				ACWcode.click();
				break;
			}
		}
	}

	@Then("^check ACW code displayed on Workspaces$")
	public void checkACW() throws InterruptedException {
		System.out.println("Check ACW");
		Thread.sleep(2000);
		String acw = driver.findElement(By.xpath("//div[@id='ow_Icon_State1']")).getText();
		System.out.println("======ACW Code =======: " + acw);
		Thread.sleep(800);
	}

	@And("^print to console$")
	public void print() {
		System.out.println("======ACW Code =======");
	}

	@After
	public void tearDown() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
		Thread.sleep(700);
		driver.findElement(By.xpath("//*[@id='ow_finish_work']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
		Thread.sleep(700);
		driver.findElement(By.xpath("//*[@id='ow_exit']")).click();
		Thread.sleep(3000);
		System.out.println("Complete");
		driver.quit();
	}

	// DATA
	public void setUp() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.get(wsURL);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}

	public void agentAccept(String cusEmail) throws InterruptedException {
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(1000);
		WebElement currEmailEl = null;
		try {
			explicitWait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//bdi[@aria-label='" + cusEmail + "']")));
			currEmailEl = driver.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']"));
			currEmailEl.findElement(By.xpath("//*[@id='ow_card_accept_btn']")).click();
		} catch (NoSuchElementException e) {
			System.out.println("cannot find or accept " + cusEmail + " in wordcard");
		}

	}

	public void currCusChat(int posTab, String message) throws InterruptedException {
		driver.switchTo().window(tabs.get(posTab));
		Thread.sleep(1000);
		WebDriverWait waits = new WebDriverWait(driver, 60);
		waits.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='outmessage']")));
		WebElement action = driver.findElement(By.xpath("//*[@id='outmessage']"));
		action.sendKeys(message);
		action.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}

	public void currAgentChat(String chat, String cusEmail) throws InterruptedException {
		driver.switchTo().window(tabs.get(0));
		System.out.println("click to " + chat + " - " + cusEmail + ":: "
				+ driver.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']")).getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']")).click();
		driver.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']")).click();
		System.out.println("click to wordcard " + cusEmail);
		Thread.sleep(1000);
		List<WebElement> actions = driver.findElements(By.xpath("//div[@class='limited-input__container']/textarea"));
		System.out.println("actions.size(): " + actions.size());
		for (WebElement action : actions) {
			if (action.isDisplayed()) {
				action.sendKeys(chat);
				System.out.println("send chat: " + chat);
				action.sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				break;
			}
		}
	}

	public void swToWorkcard(String cusEmail) throws InterruptedException {
		driver.switchTo().window(tabs.get(0));
		System.out.println("click to " + cusEmail + " :: "
				+ driver.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']")).getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//bdi[@aria-label='" + cusEmail + "']")).click();
		System.out.println("click to wordcard " + cusEmail);
		Thread.sleep(1000);
	}

	public static void Hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public void goReady() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[@id='ow_agent_dropdown_menu']/md-menu-button/button")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@id='ow_go_ready']")).click();
			Thread.sleep(500);
		} catch (NoSuchElementException e) {
			System.out.println("Cannot go ready");
		}
	}
}

//Given agent login into Workspace
//#Chat
//When customer send ewc
//And agent accepts
//And agent chat <chat1>
//And customer chat <cus1>
//#New chat
//And customer send ewc
//And agent switch to ewc 2
//And agent accepts
//And agent chat <chat1>
//And customer chat <cus1>
//#Set acw
//And agent switch to ewc 1
//And agent unhold
//And agent close ewc1 va ewc2
//And Set ACW
//Then check ACW code displayed on Workspaces
//And print to console