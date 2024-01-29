package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.config.Config;

import java.util.Arrays;

public class BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    private static int defaultTimeout = 15;

    private static By waitEl = By.xpath("//div[contains(@class, 'wrapper')]");
    private final static String BASE_URL = Config.getConfigProperty("baseUrl");
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();



    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
        }
    }

      public static void open() {
        String message = "Opening the page: " + "\"" + BASE_URL + "\"";
        LOGGER.info(message);
        getWebDriver().get(BASE_URL);
    }

    private static int getTimeoutSetting() {
        String timeoutProp = Config.getConfigProperty("defaultTimeout");
        int resultTimeout;

        if (timeoutProp == null) {
            LOGGER.error("defaultTimeout parameter was not found");
            resultTimeout = defaultTimeout;
        } else {
            resultTimeout = Integer.parseInt(timeoutProp);
        }

        return resultTimeout;
    }

    public static WebElement findElement(By element) {
        WebElement webElement;

        try {
            (new WebDriverWait(getWebDriver(), defaultTimeout))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            webElement = getWebDriver().findElement(element);
        }
        catch (Exception exc) {
            try {
                scrollToElement(getWebDriver().findElement(element));
                webElement = getWebDriver().findElement(element);
            } catch (Exception exc1) {
                throw new RuntimeException("Failure finding element");
            }
        }

        return webElement;
    }

    public static void clickOnElement(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? defaultTimeout : timeout[0];

        try {
            (new WebDriverWait(getWebDriver(), timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            getWebDriver().findElement(element).click();
        } catch (Exception exc) {
            try {
                scrollToElement(getWebDriver().findElement(element));
                getWebDriver().findElement(element).click();
            } catch (Exception exc_1) {
                //AllureReport.attachText(DataCreation.getStackTrace(exc));
                throw new RuntimeException("Failure clicking on element");
            }
        }
    }

    public static void scrollToElement(WebElement element) {
        waitPageLoad();
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
        ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0,-150);");
    }

    private static void waitPageLoad(){
        WebDriverWait wait = new WebDriverWait(webDriver.get(), defaultTimeout);

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(waitEl));

    }


    public static String getFieldValue(By fieldXpath) {
        return findElement(fieldXpath)
                .getAttribute("value");
    }

    public static void clearField(By fieldXpath) {

        LOGGER.info("Clear field \"" + fieldXpath.toString() + "\"");
        new Actions(getWebDriver())
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
    }

    // TODO: 1/29/2024 - fix clearing
    public static void fillField(String value, By fieldXpath) {
        LOGGER.info("Insert \"" + value + "\" to the \"" + fieldXpath.toString() + "\" field");
        findElement(fieldXpath)
                .sendKeys(value);
        String fieldValue = getFieldValue(fieldXpath);
        if (!fieldValue.startsWith(value)) {
            clearField(fieldXpath);
            fillFieldOneByOneCh(value, fieldXpath);
        }
    }

    public static void fillFieldOneByOneCh(String value, By fieldXpath) {
        LOGGER.info("Insert \"" + value + "\" to the \"" + fieldXpath.toString() + "\" input field one by one character");
        WebElement field = findElement(fieldXpath);
        Arrays.asList(value.split("")).stream()
                .forEach(c -> {
                    field.sendKeys(c);
                    waitFor(1);
                });
    }
}
