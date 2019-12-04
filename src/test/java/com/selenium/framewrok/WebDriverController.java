package com.selenium.framewrok;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
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
        String browserName = System.getenv("browserName");
        String grid = System.getenv("grid");
        try {
            if (browserName == null && grid == null) {
                browserName = "firefox";
                grid = "NO";
            }
            switch (browserName) {
                case "chrome":
                    driver = new ChromeDriver(getChromeOptions());
                    logger.info("Selected browser is Chrome");
                    if (grid.equalsIgnoreCase("YES")) {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getChromeOptions());
                        logger.info("Running in Chrome grid");
                    }
                    break;
                case "firefox":
                    driver = new FirefoxDriver(getFirefoxOptions());
                    logger.info("Selected browser is Firefox");
                    if (grid.equalsIgnoreCase("YES")) {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getFirefoxOptions());
                        logger.info("Running in Firefox grid");
                    }
                    break;
                case "ie":
                    driver = new InternetExplorerDriver(getIEOptions());
                    logger.info("Selected browser is IE");
                    break;
                default:
                    logger.error("Please check the browser");
            }
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e);
        }
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
