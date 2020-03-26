package stepEWC;

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

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EWCChat {
	WebDriver driver;
	Set<String> windows;
	Iterator<String> itr;
	String wsID;
	//String ewcID;
	String ewcID1;
	String ewcID2;
	String wsURL = "http://100.30.5.92:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";// "http://100.30.6.137:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";
	String webchatURL = "http://10.30.1.210:81/ewcsite%20-%20mcha576%20link/";// "http://10.30.1.236:8080/ewcsite/";
	String checkLink = "https://100.30.5.76:8445/CustomerControllerWeb/currentqueue";// "https://autosrv98:8445/CustomerControllerWeb/callback";
	String username = "aoc\\nvhuy0002"; // "ACC_Huy@automation";
	String password = "1_Abc_123";
	//String skillset = "WC_Webchat3";// "WC_HUY_1";
	//String cusEmail = "huy@gmail.com";
	//String cusName = "huy";
	WebDriverWait explicitWait;

	@Given("^agent login into Workspace$")
	public void login() throws InterruptedException {
		System.out.println("Agent login into Workspace");
// setUp	
		setUp();
// Login and wait for page load
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);
// Active
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='submit']"))).click();
		Thread.sleep(3000);
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
	}

	@When("^customer send ewc with (.*), (.*) and (.*)$")
	public void sendEWC(String name, String email, String skillset) throws InterruptedException {
		System.out.println("cus send ewc: " + name + " " + email + " " + skillset);
		ewcID1 = chatSetUp(name, email, skillset);
	}

	@And("^agent accepts$")
	public void accept() {
		System.out.println("accept");
		
	}

	@And("^agent chat (.*)$")
	public void agentChat(String chat1) {
		System.out.println("agent chat: " + chat1);
	}

	@And("^customer chat (.*)$")
	public void cusChat(String cus1) {
		System.out.println("cus chat: " + cus1);
	}

	@And("^agent switch to ewc (.*)$")
	public void swWorkcard(String cusEmail) {
		System.out.println("Switch to work card " + cusEmail);
	}

	@And("^agent unhold$")
	public void unhold() {
		System.out.println("unhold");
	}

	@And("^agent close ewc1 va ewc2$")
	public void closeEWC() {
		System.out.println("Close EWC");
	}

	@And("^set ACW$")
	public void setACW() {
		System.out.println("Set ACW");
	}

	@Then("^check ACW code displayed on Workspaces$")
	public void checkACW() {
		System.out.println("Check ACW");
	}

	@And("^print to console$")
	public void print() {
		System.out.println("print");
	}

	@After
	public void TearDown() {
		// driver.quit();
	}

	// DATA
	public void setUp() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.get(wsURL);
		wsID = driver.getWindowHandle();
	}

	public String chatSetUp(String cusName, String cusEmail, String skillset) throws InterruptedException {
		openTab(webchatURL);
		String currEWC = driver.getWindowHandle();
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
			driver.switchTo().window(currEWC);
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			if (!(driver.findElement(By.xpath("//*[@id='chatPanel']/a")).isDisplayed())) {
				System.out.println("Khong tim thay skillset nao");
				return currEWC;
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
		return currEWC;
	}

	public void agentAccept(String email) throws InterruptedException{
		WebElement selectCard = null;
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//card-group")));
		Thread.sleep(3000);
		List<WebElement> cardgroup = driver.findElements(By.xpath("//card-group"));
		System.out.println("cardgroup.size(): " + cardgroup.size());
		for (int i = 0; i <= cardgroup.size(); i++) {
			WebElement curCard = cardgroup.get(i);
			if (selectCard == null) {
				selectCard = cardgroup.get(0);
			}
			if (curCard.findElement(By.xpath("//bdi[@aria-label='" + email + "']")).getText().contains(email)) {
				selectCard = curCard;
				break;
			}
		}
	}
	
	public void currCusChat(String ewcid, String message) throws InterruptedException {
		driver.switchTo().window(ewcid);
		Thread.sleep(1000);
		WebDriverWait waits = new WebDriverWait(driver, 60);
		waits.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='outmessage']")));
		WebElement action = driver.findElement(By.xpath("//*[@id='outmessage']"));
		action.sendKeys(message);
		action.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}

	public void currAgentChat(String wcID, String message) throws InterruptedException {
		driver.switchTo().window(wsID);
		Thread.sleep(1000);
		WebElement action = driver.findElement(By.xpath("//div[@class='limited-input__container']/textarea"));
		action.sendKeys(message);
		action.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
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