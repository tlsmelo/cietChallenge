package utils;

import global.SharedWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementUtils {
    public static final int DEFAULT_WAIT_TIME = 10;

    public static void waitForElementToLoad(WebElement webElement, int waitForSeconds) {
        WebDriver driver = SharedWebDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, waitForSeconds);
        wait.until((ExpectedConditions.visibilityOf(webElement)));
    }

    public static void waitForElementToLoad(WebElement webElement) {
        waitForElementToLoad(webElement, DEFAULT_WAIT_TIME);
    }

    public static void explicitWaitMs (Integer waitMs) {
        try {
            Thread.sleep(waitMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static boolean elementExists(String xPath){
        boolean elementExists;
        try {
            getElement(By.xpath(xPath));
            elementExists = true;
        } catch (NoSuchElementException | TimeoutException nsee) {
            elementExists = false;
        }
        return elementExists;
    }

    public static boolean elementNotExists (String xPath) {
        return !elementExistsWithTimeOut(xPath, 1);
    }

    public static WebElement getElement(By by) {
        WebDriver driver = SharedWebDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        wait.until((ExpectedConditions.presenceOfElementLocated(by)));
        return driver.findElement(by);
    }

    public static WebElement getElementWithTimeout (By by, int timeoutSeconds) {
        WebDriver driver = SharedWebDriver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until((ExpectedConditions.presenceOfElementLocated(by)));
        return driver.findElement(by);
    }

    public static boolean elementExistsWithTimeOut(String xPath, int timeoutSeconds) {
        boolean elementExists;
        try {
            getElementWithTimeout(By.xpath(xPath), timeoutSeconds);
            elementExists = true;
        } catch (NoSuchElementException | TimeoutException nsee) {
            elementExists = false;
        }
        return elementExists;
    }
}
