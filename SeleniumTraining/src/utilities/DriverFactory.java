package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	public static WebDriver CreateDriver(String browserType) {
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--disable-features=VizDisplayCompositor");
		//options.addArguments("--ignore-certificate-errors");
		//options.addArguments("enable-automation");
		//options.addArguments("--headless");
		//options.addArguments("--dns-prefetch-disable");
		//options.addArguments("--disable-gpu");

		if (browserType.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					"D:\\selenium\\nvhuy\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"D:\\\\selenium\\\\nvhuy\\\\geckodriver-v0.26.0-win32\\\\geckodriver.exe");
			return new FirefoxDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", "D:\\selenium\\nvhuy\\chromedriver_win32\\chromedriver.exe");
			return new ChromeDriver(options);
		}

	}
}
