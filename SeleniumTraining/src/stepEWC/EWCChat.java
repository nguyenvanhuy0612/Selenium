package stepEWC;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EWCChat {
	// WebDriver driver;

	@Given("^agent login into Workspace$")
	public void login() {
		System.out.println("Agent login into Workspace");
	}

	@When("^customer send ewc$")
	public void sendEWC() {
		System.out.println("cus send ewc");
	}

	@And("^agent accepts$")
	public void accept() {
		System.out.println("accept");
	}

	@And("^agent chat (.*)$")
	public void agentChat(String chat1) {
		System.out.println("agent chat: " + chat1);
	}

	@And("^customer chat (.*)$")
	public void cusChat(String cus1) {
		System.out.println("cus chat: " + cus1);
	}

	@And("^agent switch to ewc 2$")
	public void swWorkcard2() {
		int i = 2;
		System.out.println("Switch to work card " + i);
	}

	@And("^agent switch to ewc 1$")
	public void swWorkcard1() {
		int i = 2;
		System.out.println("Switch to work card " + i);
	}

	@And("^agent unhold$")
	public void unhold() {
		System.out.println("unhold");
	}

	@And("^agent close ewc1 va ewc2$")
	public void closeEWC() {
		System.out.println("Close EWC");
	}

	@And("^set ACW$")
	public void setACW() {
		System.out.println("Set ACW");
	}

	@Then("^check ACW code displayed on Workspaces$")
	public void checkACW() {
		System.out.println("Check ACW");
	}

	@And("^print to console$")
	public void print() {
		System.out.println("print");
	}
}
//Given agent login into Workspace
//#Chat
//When customer send ewc
//And agent accepts
//And agent chat <chat1>
//And customer chat <cus1>
//#New chat
//And customer send ewc
//And agent switch to ewc 2
//And agent accepts
//And agent chat <chat1>
//And customer chat <cus1>
//#Set acw
//And agent switch to ewc 1
//And agent unhold
//And agent close ewc1 va ewc2
//And Set ACW
//Then check ACW code displayed on Workspaces
//And print to console