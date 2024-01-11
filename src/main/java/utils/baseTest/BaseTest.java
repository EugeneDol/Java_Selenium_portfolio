package utils.baseTest;

import Pages.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utils.api.RestProcessing;
import utils.config.Config;
import utils.driver.DriverProvider;

import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    public static SoftAssert soft = new SoftAssert();

}
