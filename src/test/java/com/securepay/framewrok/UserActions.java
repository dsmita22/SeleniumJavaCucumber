package com.securepay.framewrok;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public abstract class UserActions {

    public WebDriver driver;

    public UserActions(WebDriver driver) {
        this.driver = driver;
    }

    protected void navigate(String url) {
        driver.navigate().to(url);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    private void fluentWait(WebElement element, int timeout) {
        try {
            Wait wait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(5))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));

        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        }
    }

    protected void click(WebElement element) {
        fluentWait(element, 20);
        element.click();
    }

    public void clickWhenReady(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement toReturn = driver.findElement(locator);
                if (toReturn.isDisplayed()) {
                    return toReturn;
                }
                return null;
            }
        };
    }

    public void javascriptClick() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('[class=\"r\"] a[href=\"https://www.securepay.com.au/\"]').click()");
    }

    public void click(WebElement element, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(specifiedTimeout));
        ExpectedCondition<Boolean> elementIsClickable = arg0 -> {
            try {
                element.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(elementIsClickable);
    }

    protected void submit(WebElement element) {
        element.submit();
    }

    protected void enter(WebElement element, String value) {
        element.sendKeys(value);
    }

    protected void select(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    protected void waitUntilElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilElementClickAble(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void mouseOver(WebElement element1, WebElement element2) {
        Actions action = new Actions(driver);
        action.moveToElement(element1).pause(Duration.ofSeconds(2)).moveToElement(element2).click().build().perform();
    }
}
