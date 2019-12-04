package com.selenium.framewrok;

import com.selenium.pages.GoogleSearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public abstract class UserActions {

    private Logger logger = LogManager.getLogger(GoogleSearchPage.class);

    public WebDriver driver;

    public UserActions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigate to url
     *
     * @param url url
     */
    protected void navigate(String url) {
        driver.navigate().to(url);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    /**
     * Fluent Wait
     *
     * @param element element
     * @param timeout timeOut
     */
    private void fluentWait(WebElement element, int timeout) {
        try {
            Wait wait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(5))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));

        } catch (ElementNotVisibleException e) {
            logger.error(e);
        }
    }

    /**
     * Click
     *
     * @param element element
     */
    protected void click(WebElement element) {
        try {
            fluentWait(element, 20);
            element.click();
        } catch (ElementNotSelectableException ex) {
            logger.error(ex);
        }
    }

    /**
     * Click when Ready
     *
     * @param element locator
     * @param timeout timeout
     */
    public void clickWhenReady(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
            ele.click();
        } catch (ElementNotInteractableException ex) {
            logger.error(ex);
        }

    }

    /**
     * Get Title
     * @return String
     */
    protected String getTitle(){
        return driver.getTitle();
    }
    /**
     * javascript Scroll
     */
    protected void javascriptScroll() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500)");
        } catch (JavascriptException jex) {
            logger.error(jex);
        }
    }

    /**
     * Submit
     *
     * @param element element
     */
    protected void submit(WebElement element) {
        try {
            element.submit();
        } catch (ElementNotInteractableException ex) {
            logger.error(ex);
        }
    }

    /**
     * Enter
     *
     * @param element element
     * @param value   value
     */
    protected void enter(WebElement element, String value) {
        try {
            element.sendKeys(value);
        } catch (ElementNotInteractableException ex) {
            logger.error(ex);
        }
    }

    /**
     * Select
     *
     * @param element element
     * @param value   value
     */
    protected void select(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (ElementNotSelectableException ex) {
            logger.error(ex);
        }
    }

    /**
     * Wait Until Element visible
     *
     * @param element element
     */
    protected void waitUntilElementVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (ElementNotVisibleException ex) {
            logger.error(ex);
        }
    }

    /**
     * Wait until element clickAble
     *
     * @param element element
     */
    protected void waitUntilElementClickAble(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (ElementNotSelectableException ex) {
            logger.error(ex);
        }
    }

    /**
     * MouseOver
     *
     * @param element1 elementOne
     * @param element2 elementTwo
     */
    protected void mouseOver(WebElement element1, WebElement element2) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element1).pause(Duration.ofSeconds(2)).moveToElement(element2).click().build().perform();
        } catch (ElementNotInteractableException ex) {
            logger.error(ex);
        }
    }

    /**
     * Is Displayed
     *
     * @param element element
     * @return boolean
     */
    protected Boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }
}
