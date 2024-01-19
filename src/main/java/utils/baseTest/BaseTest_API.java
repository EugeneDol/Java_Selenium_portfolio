package utils.baseTest;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import utils.api.ApiController;

import java.net.MalformedURLException;


public class BaseTest_API extends BaseTest{

    private static String testClassName;
    private ApiController apiHandler;


    @BeforeMethod
    public void testSetUp_api(ITestContext context) throws MalformedURLException {
        testClassName = context.getCurrentXmlTest().getXmlClasses().get(0).getName();
        LOGGER.info("API test started: " + testClassName);
        apiHandler = new ApiController();
        String token = apiHandler.getAuthToken("defaultUsername", "defaultPass");
        LOGGER.info("Auth token: " + token);
    }

    /**
     * empty method for override
     * put test specific clear actions at the end of the test
     * */
    protected void clearData() { }
}
