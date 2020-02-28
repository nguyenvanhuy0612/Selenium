package pk.demo;

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;		


/*
 * WebDriver driverff = new FirefoxDriver();
 * import org.openqa.selenium.firefox.FirefoxDriver;
 * import org.openqa.selenium.remote.server.handler.SendKeys;
 * System.setProperty("webdriver.gecko.driver", "D:\\selenium\\nvhuy\\geckodriver-v0.26.0-win32\\geckodriver.exe");
*/
public class MyClass {				
    		
    public static void main(String[] args) {	
//link
        String baseUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";  //"http://demo.guru99.com/test/link.html";
//Create Web driver
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\nvhuy\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
//Fill out the form       	
        driver.get(baseUrl);
        driver.findElement(By.linkText("CREATE ACCOUNT")).click();
        //driver.findElement(By.xpath("//*[@id=\"ctl01\"]/div[3]/div[2]/div/div[2]/a")).click();
        driver.findElement(By.name("ctl00$MainContent$txtFirstName")).sendKeys("Huy Nguyen");
        driver.findElement(By.id("MainContent_txtEmail")).sendKeys("nguyenhuy11@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"MainContent_txtHomePhone\"]")).sendKeys("0982785214");
        driver.findElement(By.xpath("/html/body/form/div[3]/div[2]/div/div[2]/div[4]/div[2]/input[2]")).click();
        driver.findElement(By.cssSelector("input[id='MainContent_txtPassword'][name='ctl00$MainContent$txtPassword']")).sendKeys("testpass");
        driver.findElement(By.cssSelector("input[type='password'][name='ctl00$MainContent$txtVerifyPassword']")).sendKeys("testpass");
        new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText("China");
        driver.findElement(By.xpath("//*[@id=\"ctl01\"]/div[3]/div[2]/div/div[2]/div[8]/div[2]/label[1]")).click();
        driver.findElement(By.id("MainContent_btnSubmit")).click();
        
//Confirm
        String confirm = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
        System.out.println(confirm);
        System.out.println("title of page is: " + driver.getTitle());	
        
//Close
//        driver.close();
//        driver.quit();
    }		

}