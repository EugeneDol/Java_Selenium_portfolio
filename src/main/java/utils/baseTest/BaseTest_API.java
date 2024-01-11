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

import java.net.MalformedURLException;


public class BaseTest_API extends BaseTest{

    private static String testClassName;
    private RestProcessing restHandler;

    @BeforeMethod
    public void testSetUp_api(ITestContext context) throws MalformedURLException {
        testClassName = context.getCurrentXmlTest().getXmlClasses().get(0).getName();
        LOGGER.info("API test started: " + testClassName);
        restHandler = new RestProcessing();
        String token = restHandler.getAuthToken("defaultUsername", "defaultPass");
        LOGGER.info("Auth token: " + token);
    }

    /**
     * empty method for override
     * put test specific clear actions at the end of the test
     * */
    protected void clearData() { }
}
