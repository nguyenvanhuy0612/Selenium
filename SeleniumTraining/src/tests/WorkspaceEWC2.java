package tests;

import java.util.ArrayList;
import java.util.List;
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

public class WorkspaceEWC2 {
	// Variable
	WebDriver driver;
	ArrayList<String> windows;
	List<String> winLists;
	String wsURL = "http://100.30.5.92:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";// "http://100.30.6.137:31380/Login/?returnpage=../services/UnifiedAgentController/workspaces/";
	String webchatURL = "http://10.30.1.210:81/ewcsite%20-%20mcha576%20link/";// "http://10.30.1.236:8080/ewcsite/";
	String checkLink = "https://mcha576.aoc.com:8445/CustomerControllerWeb/currentqueue";// "https://autosrv98:8445/CustomerControllerWeb/callback";
	String username = "aoc\\nvhuy0002"; // "ACC_Huy@automation";
	String password = "1_Abc_123";
	String skillset = "WC_Webchat2";// "WC_HUY_1";
	String customerEmail = "huy@gmail.com";

	@Test
	public void EWC() throws InterruptedException {
		// Thong so test
		WebDriverWait waits = new WebDriverWait(driver, 60);
		windows = new ArrayList<String>(driver.getWindowHandles());
		winLists = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("windows: " + windows);
		System.out.println("winLists: " + winLists);

	}

	@BeforeMethod
	public void Setup() {
		driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(webchatURL);
	}

	@AfterMethod
	public void TearDown() {
		// driver.close();
	}

	public static void Hover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void openAndSwitchNewTabs(String URL, String posTab) {
		((JavascriptExecutor) driver).executeScript("window.open()");
		
		driver.get(URL);
	}

	public void EWCpage(String URL, String linkCheck, String skillset) {
		
	}

}
