package utils.baseTest;

import Pages.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utils.config.Config;
import utils.driver.DriverProvider;

import java.net.MalformedURLException;


public class BaseTest_E2E {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    private static SoftAssert soft = new SoftAssert();
    private static BasePage basePage = new BasePage();
    private static String testClassName;

    @BeforeMethod
    public void testSetUp(ITestContext context) throws MalformedURLException {
        testClassName = context.getCurrentXmlTest().getXmlClasses().get(0).getName();
        LOGGER.info("Test started: " + testClassName);
        BasePage.webDriver.set(DriverProvider.getDriver("Remote"));
    }

    @AfterMethod
    public void closeRun(ITestResult testResult) {
        try {
            clearData();
            BasePage.getWebDriver().quit();
            //DriverProvider.closeDriver();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //if(BasePage.webDriver != null) BasePage.getWebDriver().quit();
            if(DriverProvider.instance.get() != null) DriverProvider.closeDriver();
        }
    }

    /**
     * empty method for override
     * put test specific clear actions at the end of the test
     * */
    protected void clearData() { }
}
