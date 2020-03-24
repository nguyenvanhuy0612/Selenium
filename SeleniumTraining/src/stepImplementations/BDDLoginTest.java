package stepImplementations;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BDDLoginTest {

	// WebDriver driver;

	@Given("^user is on the login page$")
	public void user_is_on_the_login_page() {
		System.out.println("user_is_on_the_login_page");
	}

	@When("^user enters correct username and correct password$")
	public void user_enters_correct_username_and_correct_password() {
		System.out.println("user_enters_correct_username_and_correct_password");
	}

	@Then("^user gets confirmation$")
	public void user_gets_confirmation() {
		System.out.println("user_gets_confirmation");
	}
}
