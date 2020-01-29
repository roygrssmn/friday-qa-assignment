package com.friday.framework;

import com.google.common.io.Files;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Optional;

import static com.friday.framework.DataFactory.getCurrentDate;

/**
 * WebDriverExtension handles all executions before a test is run,
 * what happens after a successful or failed test and when the test is finished.
 *
 */

public class WebDriverExtension implements TestWatcher, BeforeEachCallback, AfterEachCallback {

    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext context) throws Exception{
        Object test = context.getRequiredTestInstance();
        Field a = test.getClass().getDeclaredField("driver");
        a.setAccessible(true);
        driver = (WebDriver) a.get(test);
        System.out.println("\nLOGGING: Test started - " + context.getDisplayName() + "\n");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {

    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("\nLOGGING: Test succeeded - " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("\nLOGGING: Test aborted - " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("\nLOGGING: Test failed - " + context.getDisplayName());
    }

    public void saveScreenshot(String testName) {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            Files.move(screenshot, new File("src/test/resources/screenshots/" + testName + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Object test = context.getRequiredTestInstance();
        Field a = test.getClass().getDeclaredField("driver");
        a.setAccessible(true);
        driver = (WebDriver) a.get(test);
        if (context.getExecutionException().isPresent()){
            saveScreenshot(context.getDisplayName());
        }
        driver.quit();
        System.out.println("\nLOGGING: Test ended - " + context.getDisplayName() + getCurrentDate("ddMMYYYY") + "\n");
    }
}
