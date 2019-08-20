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

public class GoogleSecurePaySteps extends WebDriverController {
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

    @When("I Click on the Link")
    public void i_Click_on_the_Link() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I should be navigate to SecurePay homePage")
    public void i_should_be_navigate_to_SecurePay_homePage() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I Click on ContactUs")
    public void i_Click_on_ContactUs() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("I should verify contact us page is loaded")
    public void i_should_verify_contact_us_page_is_loaded() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I should be able to fill the form")
    public void i_should_be_able_to_fill_the_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
