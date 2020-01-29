package com.friday.framework;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * WebDriverTest is the base class for all test classes
 */

public abstract class WebDriverTest {

    WebDriverGetter webDriverGetter = new WebDriverGetter();

    public WebDriver getDriver() throws MalformedURLException {
        return webDriverGetter.getDriver();
    }

    public static String getBrowser() {
        final String browser = System.getenv("BROWSER");
        if (isNullOrEmpty(browser))
            return "Chrome_docker";
        return browser;
    }

    public static String getDockerPort() {
        final String dockerPort = System.getenv("DOCKER_PORT");
        if (isNullOrEmpty(dockerPort))
            return "4444";
        return dockerPort;
    }
}
