package com.securepay.pages;

import com.securepay.framewrok.WebDriverParent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private WebElement searchButton;

    @FindBy(css = "[class=\"r\"] a[href=\"https://www.securepay.com.au/\"]")
    private WebElement searchResult;

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url) {
        navigate(url);
    }

    public void search(String string) {
        try {
            enter(searchArea, string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickSearchButton() {
        submit(searchButton);
    }

    public void selectSecurePay() {
        waitUntilElementVisible(searchResult);
        click(searchResult);
    }

}
