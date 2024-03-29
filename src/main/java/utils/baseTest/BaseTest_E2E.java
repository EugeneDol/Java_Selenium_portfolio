package utils.baseTest;

import Pages.BasePage;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.driver.DriverProvider;
import utils.reports.E2EListener;

import java.net.MalformedURLException;



@Listeners({E2EListener.class})
public class BaseTest_E2E extends BaseTest{
    private static BasePage basePage = new BasePage();
    private static String testClassName;


    @BeforeMethod
    public void testSetUp_e2e(ITestContext context) throws MalformedURLException {
        testClassName = context.getCurrentXmlTest().getXmlClasses().get(0).getName();
        LOGGER.info("Test started: " + testClassName);
        BasePage.webDriver.set(DriverProvider.getDriver("Local"));
    }

    @AfterMethod
    public void closeRun_e2e(ITestResult testResult) {
        try {
            clearData();
            if(BasePage.webDriver != null) BasePage.getWebDriver().quit();
            //if(DriverProvider.instance.get() != null) DriverProvider.closeDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * empty method for override
     * put test specific clear actions at the end of the test
     * */
    protected void clearData() { }
}
