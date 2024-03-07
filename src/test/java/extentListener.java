import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class extentListener implements ITestListener {
    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
        Reporter.log("Test case PASSED");
    }

    public void onTestFailure(ITestResult result) {
        Reporter.log("Test case FAILED");
    }

    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test case SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }
}
