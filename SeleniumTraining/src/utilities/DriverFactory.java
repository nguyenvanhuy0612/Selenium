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
		
		String chromeLocation = "D:\\BBBB_JAVA_Selenium\\eclipse-jee-2019-12-R-win32-x86_64\\software\\chromedriver.exe";
		String ieLocation = "D:\\BBBB_JAVA_Selenium\\eclipse-jee-2019-12-R-win32-x86_64\\software\\IEDriverServerWin32_3.150.1.exe";
		String ffLocation = "D:\\BBBB_JAVA_Selenium\\eclipse-jee-2019-12-R-win32-x86_64\\software\\geckodriver_v0.26.0-win32.exe";
		
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
