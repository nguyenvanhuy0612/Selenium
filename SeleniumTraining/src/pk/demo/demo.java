package pk.demo;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class demo {
	@Test
	public void testMultipleWindows() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:chromedriver.exe");
// To open browser
		WebDriver driver = new ChromeDriver();
// To maximize browser
		driver.manage().window().maximize();
// To open Naukri website with multiple windows
		driver.get("http://www.naukri.com/");
// It will return the parent window name as a String
		String mainWindow = driver.getWindowHandle();
// It returns no. of windows opened by WebDriver and will return Set of Strings
		Set<String> set = driver.getWindowHandles();
// Using Iterator to iterate with in windows
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
// Compare whether the main windows is not equal to child window. If not equal, we will close.
			if (!mainWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());
				driver.close();
			}
		}
// This is to switch to the main window
		driver.switchTo().window(mainWindow);
	}
}
