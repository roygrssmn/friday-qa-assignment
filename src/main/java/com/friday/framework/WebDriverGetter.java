package com.friday.framework;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.friday.framework.WebDriverTest.getBrowser;
import static com.friday.framework.WebDriverTest.getDockerPort;

/**
 * Browser profiles
 */

public class WebDriverGetter {

    public WebDriver getDriver() throws MalformedURLException {

        BROWSER browser = BROWSER.valueOf(getBrowser().toUpperCase());

        switch (browser) {
            case CHROME: {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-web-security");
                options.addArguments("--ignore-certificate-errors");
                WebDriver driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                return driver;
            }
            case FIREFOX: {
                WebDriver driver =  new FirefoxDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case CHROME_DOCKER: {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-web-security");
                options.addArguments("--start-maximized");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--headless");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                WebDriver driver =  new RemoteWebDriver(new URL("http://localhost:" + getDockerPort() + "/wd/hub"), capabilities);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                return driver;
            }
            case FIREFOX_DOCKER: {
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                WebDriver driver = new RemoteWebDriver(new URL("http://localhost:" + getDockerPort() + "/wd/hub"), options);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                return driver;
            }
            default: {
                throw new RuntimeException("no browser profile found for: " + browser);
            }
        }
    }

    private enum BROWSER {
        CHROME,
        FIREFOX,
        CHROME_DOCKER,
        FIREFOX_DOCKER
    }
}
