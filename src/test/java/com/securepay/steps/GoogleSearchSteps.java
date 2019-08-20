package com.securepay.steps;

import com.securepay.framewrok.WebDriverController;
import com.securepay.pages.ContactUsPage;
import com.securepay.pages.GoogleSearchPage;
import com.securepay.pages.SecurePayPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearchSteps extends WebDriverController {
    private GoogleSearchPage googleSearch = null;
    private SecurePayPage securePay = null;
    private ContactUsPage contactUsPage = null;

    @Before
    public void beforeScenario() throws Exception {
        openBrowser("http://www.google.com.au");
        googleSearch = new GoogleSearchPage(driver);
        securePay = new SecurePayPage(driver);
        contactUsPage = new ContactUsPage(driver);
    }

    @After
    public void afterScenario() {
        quitBrowser();
    }

    @Given("I go to google")
    public void i_go_to_google() {
        googleSearch.navigateTo("http://www.google.com.au");
    }

    @When("I search {string}")
    public void i_search(String string) throws Exception {
        googleSearch.search(string);
        googleSearch.clickSearchButton();
    }

    @Then("I should see {string}")
    public void i_should_see(String string) {
        googleSearch.selectSecurePay();
        securePay.navigateToContactUs();
        contactUsPage.enterContactUsDetails();
    }
}
