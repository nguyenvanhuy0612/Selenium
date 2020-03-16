package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	public static WebDriver CreateDriver(String browserType) {
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--disable-features=VizDisplayCompositor");
		// options.addArguments("--ignore-certificate-errors");
		// options.addArguments("enable-automation");
		// options.addArguments("--headless");
		// options.addArguments("--dns-prefetch-disable");
		// options.addArguments("--disable-gpu");
		// options.addArguments("--no-sandbox");
		// options.addArguments("enable-features=NetworkServiceInProcess");
		// options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		//==== System.setProperty ("webdriver.chrome.driver",".\\chromedriver.exe" );
		
		
		
		String chromeLocation = "D:\\selenium\\nvhuy\\drivers\\chromedriver_v80_new.exe";
		String ieLocation = "D:\\selenium\\nvhuy\\drivers\\IEDriverServer.exe";
		String ffLocation = "D:\\selenium\\nvhuy\\drivers\\geckodriver.exe";
		
		if (browserType.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", ieLocation);
			return new InternetExplorerDriver();
		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ffLocation);
			return new FirefoxDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", chromeLocation);
			return new ChromeDriver(options);
		}

	}
}
