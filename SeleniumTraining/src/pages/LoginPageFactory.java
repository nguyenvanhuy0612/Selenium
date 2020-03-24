package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
	WebDriver driver;

	@FindBy(xpath = "//*[@id='MainContent_txtUserName']")
	public WebElement userNameBox;

	@FindBy(xpath = "//*[@id='MainContent_txtPassword']")
	public WebElement passwordBox;

	@FindBy(xpath = "//*[@id='MainContent_btnLogin']")
	public WebElement loginButton;

	// Steps
	public void setUserName(String username) {
		userNameBox.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordBox.sendKeys(password);
	}

	public void clickSubmit() {
		loginButton.click();
	}

	// Actions
	public void login(String username, String password) {
		setUserName(username);
		setPassword(password);
		clickSubmit();
	}

	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;

		// WebElement
		PageFactory.initElements(driver, this);
	}
}
