package com.securepay.framewrok;

import org.apache.commons.lang.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

abstract class DriverOptions {
    private Logger logger = LogManager.getLogger(DriverOptions.class);

    /**
     * get chrome options
     *
     * @return chrome
     */
    protected static ChromeOptions getChromeOptions() {
        setChromeSystemProperty();
        ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true);
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments(setChromeOWASP());
        //options.addArguments("--incognito");
        return options;
    }

    /**
     * get firefox options
     *
     * @return options
     */
    protected static FirefoxOptions getFirefoxOptions() {
        setFirefoxSystemProperty();
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("network.proxy.type", 0);
        //options.setHeadless(true);
        options.setCapability(FirefoxDriver.PROFILE, profile);
        //setFirefoxOWASP(options);
//        logger.info("Firefox options added");
        return options;
    }

    /**
     * get IE options
     *
     * @return options
     */
    protected InternetExplorerOptions getIEOptions() {
        System.setProperty("webdriver.ie.driver", "Driver/win/IEDriverServer.exe");
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        logger.info("IE options added");
        return options;
    }

    /**
     * Set chrome system property
     */
    private static void setChromeSystemProperty() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "Driver/win/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "Driver/linux/chromedriver");
        }
    }

    /**
     * Set firefox system property
     */
    private static void setFirefoxSystemProperty() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.gecko.driver", "Driver/win/geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", "Driver/linux/geckodriver");
        }
    }

    /**
     * logging preference
     *
     * @return prefs
     */
    private LoggingPreferences pref() {
        LoggingPreferences pref = new LoggingPreferences();
        pref.enable(LogType.BROWSER, Level.OFF);
        pref.enable(LogType.CLIENT, Level.OFF);
        pref.enable(LogType.DRIVER, Level.OFF);
        pref.enable(LogType.PERFORMANCE, Level.OFF);
        pref.enable(LogType.PROFILER, Level.OFF);
        pref.enable(LogType.SERVER, Level.OFF);
        logger.info("Performance capability added");
        return pref;
    }

    /**
     * Set firefox profile
     * @param capabilities capability
     * @return capability
     */
    private DesiredCapabilities fireFoxProfile(DesiredCapabilities capabilities) {
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile("WebDriver");
        if (myProfile == null) {
            File ffDir = new File(System.getProperty("user.dir") + File.separator + "ffProfile");
            if (!ffDir.exists()) {
                ffDir.mkdir();
            }
            myProfile = new FirefoxProfile(ffDir);
        }
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(true);
        myProfile.setPreference("webdriver.load.strategy", "unstable");
        if (capabilities == null) {
            capabilities = new DesiredCapabilities();
        }
        capabilities.setCapability(FirefoxDriver.PROFILE, myProfile);
        return capabilities;
    }

}
