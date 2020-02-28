package pk.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SMGR {

	public static void main(String[] args) {
		// Define data
		String name = "Huy Nguyen";
		String email = "nguyenhuy11@gmail.com";
		String phoneNumber = "0982785214";
		String password = "testpass";
		String browserType = "chrome";
		String country = "China";
		String gender = "Female";
		Boolean weeklyEmail = true; 
		Boolean monthlyEmail = true; 
		Boolean occassionalEmail = false;
				
		// Define driver
		WebDriver driver;
		driver = utilities.DriverFactory.CreateDriver(browserType);
		driver.get("https://smgr49.aoc.com/securityserver/UI/Login?org=dc=nortel,dc=com&goto=https://smgr49.aoc.com:443");
	    driver.findElement(By.id("IDToken1")).clear();
	    driver.findElement(By.id("IDToken1")).sendKeys("nvhuy");
	    driver.findElement(By.id("IDToken2")).clear();
	    driver.findElement(By.id("IDToken2")).sendKeys("1_Abc_123");
	    driver.findElement(By.id("SubmitButton")).click();
	    driver.findElement(By.linkText("Manage Users")).click();
	    driver.findElement(By.xpath("(//input[@value='on'])[2]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    driver.findElement(By.id("surname")).click();
	    driver.findElement(By.id("surname")).click();
	    driver.findElement(By.id("surname")).clear();
	    driver.findElement(By.id("surname")).sendKeys("6500022");
	    driver.findElement(By.id("givenName")).click();
	    driver.findElement(By.id("givenName")).clear();
	    driver.findElement(By.id("givenName")).sendKeys("6500022");
	    driver.findElement(By.id("loginName")).click();
	    driver.findElement(By.id("loginName")).clear();
	    driver.findElement(By.id("loginName")).sendKeys("6500022@aoc.com");
	    driver.findElement(By.linkText("Communication Profile")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
	    driver.findElement(By.xpath("//form/div/div/div/div[2]/div/div/div/div")).click();
	    driver.findElement(By.xpath("//div[4]/div/div/div/ul/li")).click();
	    driver.findElement(By.id("handle")).click();
	    driver.findElement(By.id("handle")).clear();
	    driver.findElement(By.id("handle")).sendKeys("6500022");
	    driver.findElement(By.xpath("//div/div[3]/div/div/div/div/div/div")).click();
	    driver.findElement(By.xpath("//div[5]/div/div/div/ul/li")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[2]/div")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
	    driver.findElement(By.xpath("//form/div/div/div/div[2]/div/div/div/div")).click();
	    driver.findElement(By.xpath("//div[4]/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.id("handle")).click();
	    driver.findElement(By.id("handle")).clear();
	    driver.findElement(By.id("handle")).sendKeys("+6500022");
	    driver.findElement(By.xpath("//div/div[3]/div/div/div/div/div/div")).click();
	    driver.findElement(By.xpath("//div[5]/div/div/div/ul/li")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
	    driver.findElement(By.linkText("CM Endpoint Profile")).click();
	    driver.findElement(By.xpath("(//input[@id='extension_input'])[2]")).click();
	    driver.findElement(By.xpath("(//input[@id='extension_input'])[2]")).click();
	    driver.findElement(By.xpath("(//input[@id='extension_input'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@id='extension_input'])[2]")).sendKeys("6500022");
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    driver.findElement(By.xpath("//div[3]/div/span/div/div/div")).click();
	    driver.findElement(By.xpath("//div[3]/div/span/div/div/div/div")).click();
      
	}
}