package pk.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\nvhuy\\chromedriver_win32\\chromedriver.exe");

		WebDriver driverChrome = new ChromeDriver();

		driverChrome.get("https://smgr49.aoc.com");
		
		driverChrome.findElement(By.name("IDToken1")).sendKeys("nvhuy");
		driverChrome.findElement(By.name("IDToken2")).sendKeys("1_Abc_123");
		driverChrome.findElement(By.name("Login.Submit")).click();

//		String message = driverChrome.findElement(By.id("conf_message")).getText();
//		System.out.println(message);

		String title = driverChrome.getTitle();
		System.out.println(title);

		driverChrome.close();

	}
}
