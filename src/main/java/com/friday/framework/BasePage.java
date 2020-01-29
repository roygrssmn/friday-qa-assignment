package com.friday.framework;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.commons.lang3.StringUtils.substringAfter;

/**
 * BasePage class is a base class for all pages classes, it defines basic element interactions
 */

public abstract class BasePage {

    private static final String ERROR = "\n\n Element not found in: '%s' within: %s seconds.\n\n";

    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static int waitTimeout() {
        return 10;
    }

    public void sendTextToElement(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            System.out.println(String.format("Logging: entering text '%s' to element: '%s'", text, cutLocatorString(element)));
        } catch (Exception ex) {
            waitForElementToBeVisible(driver, waitTimeout(), element);
            element.clear();
            element.sendKeys(text);
            System.out.println(String.format("Logging: entering text '%s' to element: '%s'", text, cutLocatorString(element)));
        }
    }

    public void clickOnElement(WebElement element) {
        try {
            element.click();
            System.out.println(String.format("Logging: clicked on element: '%s'", cutLocatorString(element)));
        } catch (Exception ex) {
            waitForElementToBeVisible(driver, waitTimeout(), element);
            element.click();
            System.out.println(String.format("Logging: clicked on element: '%s'", cutLocatorString(element)));
        }
    }

    public static void waitForElementToBeVisible(WebDriver driver, int seconds, WebElement element) {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, seconds)
                .ignoring(StaleElementReferenceException.class);
        wait.withMessage(String.format(ERROR, element, seconds));
        wait.until((ExpectedCondition<Boolean>) webDriver -> element != null && element.isDisplayed());
        System.out.println(String.format("Logging: waiting for element: '%s' to be visible", cutLocatorString(element)));
    }

    private static String cutLocatorString(WebElement element) {
        String x = substringAfter(element.toString(), "-> ");
        if (x == ""){
            return substringAfter(element.toString(), "By.");
        }
        else return x;
    }
}
