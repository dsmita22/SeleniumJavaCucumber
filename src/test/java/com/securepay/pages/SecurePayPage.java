package com.securepay.pages;

import com.securepay.framewrok.WebDriverParent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurePayPage extends WebDriverParent {

    private Logger logger = LogManager.getLogger(SecurePayPage.class);

    @FindBy(css = "a[data-description=\"Support\"][data-category=\"nav:li\"]")
    @CacheLookup
    private WebElement supportLink;

    @FindBy(css = "a[data-description=\"Support:Contact Us\"][data-category=\"nav:li\"]")
    private WebElement contactUsLink;

    public SecurePayPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToContactUs() {
        mouseOver(supportLink, contactUsLink);
    }

}
