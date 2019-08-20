package com.securepay.pages;

import com.securepay.framewrok.WebDriverParent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurePayPage extends WebDriverParent {

    private Logger logger = LogManager.getLogger(SecurePayPage.class);

    @FindBy(css = "li[id=\"menu-item-3367\"] a")
    @CacheLookup
    private WebElement supportLink;

    @FindBy(css = "li[id=\"menu-item-179\"] a")
    private WebElement contactUsLink;

    @FindBy(name = "first-name")
    @CacheLookup
    private WebElement firstName;

    public SecurePayPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to Contact us page
     */
    public void navigateToContactUs() {
        try {
            javascriptScroll();
            mouseOver(supportLink, contactUsLink);
            logger.info("Navigated to contact us page");
        } catch (TimeoutException timeOut) {
            waitUntilElementVisible(firstName);
        }
    }

}
