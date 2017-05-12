package org.cimmtouchs.stepdefinitions;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.cimmtouchs.initializer.PageFactoryInitializer;

/**
 * Created by hemanthsridhar on 9/23/16.
 */
public class HomePageStepdefs extends PageFactoryInitializer {

    @Given("^I am in Home Page$")
    public void iAmInHomePage() throws Throwable {
        
        homePage().verifyDisplayOfLogo();


    }

    @Then("^I verify welcome message is displayed with the \"([^\"]*)\"$")
    public void iVerifyWelcomeMessageIsDisplayedWithThe(String verifyWelcomeMsg) throws Throwable {
        homePage().verifyWelcomeMessage(verifyWelcomeMsg);
    }

    @When("^I click on \"([^\"]*)\" link in home page$")
    public void iClickOnLink(String particularLink) throws Throwable {
            switch (particularLink) {
                case "login" : homePage().clickOnLoginLink();
                               break;
                case "logout": homePage().clickOnLogout();
                               break;
                default:
                    throw new Exception("Step not found");
            }

    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchText) throws Throwable {
        homePage().enterSearchText(searchText);
    }

    @And("^I click on search go button$")
    public void iClickOnSearchGoButton() throws Throwable {
        homePage().clickOnGoButton();
    }
}
