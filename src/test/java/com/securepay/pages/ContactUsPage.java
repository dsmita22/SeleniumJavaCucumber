package com.securepay.pages;

import com.github.javafaker.Faker;
import com.securepay.framewrok.WebDriverParent;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends WebDriverParent {

    private Logger logger = LogManager.getLogger(SecurePayPage.class);
    private Faker faker = new Faker();

    @FindBy(name = "first-name")
    @CacheLookup
    private WebElement firstName;

    @FindBy(name = "last-name")
    @CacheLookup
    private WebElement lastName;

    @FindBy(name = "email-address")
    @CacheLookup
    private WebElement email;

    @FindBy(name = "phone-number")
    @CacheLookup
    private WebElement phoneNumber;

    @FindBy(name = "website-url")
    @CacheLookup
    private WebElement webSite;

    @FindBy(name = "business-name")
    @CacheLookup
    private WebElement companyName;

    @FindBy(name = "reason-for-enquiry")
    @CacheLookup
    private WebElement reason;

    @FindBy(css = "input[type=\"submit\"]")
    @CacheLookup
    private WebElement submit;

    public ContactUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify ContactUs PageLoad
     */
    public void verifyContactUsPageLoad() {
        waitUntilElementClickAble(firstName);
        if (isDisplayed(firstName)) {
            logger.info("Page loaded verified");
        } else {
            logger.error("Page not loaded");
            throw new RuntimeException("Page not loaded");
        }
    }

    /**
     * Enter ContactsUs Details
     */
    public void enterContactUsDetails() {
        enter(firstName, faker.name().firstName());
        enter(lastName, faker.name().lastName());
        enter(email, faker.internet().emailAddress());
        enter(phoneNumber, "0" + RandomStringUtils.randomNumeric(9));
        enter(webSite, faker.company().url());
        enter(companyName, faker.company().name());
        select(reason, "Support");
        logger.info("All contact details entered");
    }
// NEW BRANCH
}
