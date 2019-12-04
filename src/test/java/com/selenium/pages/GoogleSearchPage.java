package com.selenium.pages;

import com.selenium.framewrok.WebDriverParent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends WebDriverParent {

    private Logger logger = LogManager.getLogger(GoogleSearchPage.class);

    @FindBy(name = "q")
    @CacheLookup
    private WebElement searchArea;

    @FindBy(name = "btnK")
    @CacheLookup
    private WebElement searchButton;

    @FindBy(css = "[class=\"r\"] a[href=\"https://www.securepay.com.au/\"]")
    @CacheLookup
    private WebElement searchResult;

    @FindBy(css = "a[data-description=\"Support\"][data-category=\"nav:li\"]")
    @CacheLookup
    private WebElement supportLink;

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url) {
        navigate(url);
        logger.info("Navigated to url");
    }

    /**
     * Search for item
     * @param string item
     */
    public void search(String string) {
        enter(searchArea, string);
        logger.info("Search function completed");
    }

    /**
     * Click on search button
     */
    public void clickSearchButton() {
        submit(searchButton);
        logger.info("Clicked search button");
    }

    /**
     * Select Secure Pay
     */
    public void selectSecurePay() {
        try {
            waitUntilElementVisible(searchResult);
            click(searchResult);
            logger.info("Clicked on search result");
        } catch (TimeoutException timeOut) {
            waitUntilElementVisible(supportLink);
        }
    }

}
