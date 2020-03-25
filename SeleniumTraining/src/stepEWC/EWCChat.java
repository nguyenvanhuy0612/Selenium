package stepEWC;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EWCChat {
	// WebDriver driver;

	@Given("^Agent login Workspaces and accepts two incoming EWC contacts$")
	public void open() {
		System.out.println("Agent login Workspaces and accepts two incoming EWC contacts");
	}

	@When("^Switch between two contacts to chat with customers$")
	public void processChat() {
		System.out.println("Switch between two contacts to chat with customers");
	}

	@And("^Set ACW then closes contacts$")
	public void close() {
		System.out.println("set ACW");
	}

	@Then("^Check ACW code displayed on Workspaces$")
	public void checkACW() {
		System.out.println("Check ACW");
	}

	@And("^print to console$")
	public void print() {
		System.out.println("print");
	}
}
