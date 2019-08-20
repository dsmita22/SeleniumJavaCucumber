package com.securepay.framewrok;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class WebDriverController extends DriverOptions {
    private Logger logger = LogManager.getLogger(DriverOptions.class);

    public static WebDriver driver;

    /**
     * Open browser
     *
     * @param url url
     * @throws MalformedURLException Exception
     */
    public void openBrowser(String url) throws MalformedURLException {
        driver = new FirefoxDriver(getFirefoxOptions());
//        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    /**
     * Quit Browser
     */
    protected void quitBrowser() {
        for (String winHandle : driver.getWindowHandles()) {
            try {
                driver.switchTo().window(winHandle);
                driver.close();
                driver.quit();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
}
