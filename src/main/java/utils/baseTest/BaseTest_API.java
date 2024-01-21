package utils.baseTest;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;


public class BaseTest_API extends BaseTest{

    private static String testClassName;


    @BeforeMethod
    public void testSetUp_api(ITestContext context) throws MalformedURLException {
        testClassName = context.getCurrentXmlTest().getXmlClasses().get(0).getName();
        LOGGER.info("API test started: " + testClassName);
        completePreconditions();
    }

    @AfterMethod
    public void closeRun_api(){
        completePostconditions();
    }

    /**
     * empty method for override
     * complete specific actions before test actions
     * */
    protected void completePreconditions() { }

    /**
     * empty method for override
     * put test specific clear actions at the end of the test
     * */
    protected void completePostconditions() { }
}
