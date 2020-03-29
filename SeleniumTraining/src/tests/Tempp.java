package tests;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Tempp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String webchatURL = "http://10.30.1.210:81/ewcsite%20-%20mcha576%20link/";
		WebDriver driver = utilities.DriverFactory.CreateDriver("chrome");
		driver.get(webchatURL);
		WebElement panel = driver.findElement(By.xpath("//*[@id='chatPanel']"));
		// Su dung dau cham de di tiep trong WebElement.findElement
//		//*[@id='chatPanel']/a  <=> //*[@id='chatPanel'] va ./a
		System.out.println(panel.findElement(By.xpath("./a")).getAttribute("style"));
		System.out.println(panel.findElement(By.xpath("./a")).getText());

//		String currEWC = driver.getWindowHandle();
//		System.out.println("currEWC: " + currEWC);
//		driver.findElement(By.xpath("//body[1]")).sendKeys(Keys.CONTROL + "t");
//		System.out.println("Send ctrl T");
//		((JavascriptExecutor) driver).executeScript("window.open('" + webchatURL + "', '_blank')");
//		String currEWC1 = driver.getWindowHandle();
//		System.out.println("currEWC1: " + currEWC1);
	}

//	public static void findFluentWait(WebDriver driver, By locator, String text) {
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
//				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
//		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver driver) {
//				WebElement currElement = driver.findElement(locator);
//				String elText = currElement.getText();
//				String elAtt = currElement.getAttribute("type");
//				if (elAtt.contains(text)) {
//					return currElement;
//				} else {
//					System.out.println("elText: " + elText);
//					System.out.println("elAtt: " + elAtt);
//					return null;
//				}
//			}
//		});
//	}

}
