package utils.report;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class E2EListener  implements ITestListener {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        AllureHandler.attachScreenshot();
    }
}
