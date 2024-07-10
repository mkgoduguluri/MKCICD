package daily.Base;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports er = BaseTest.extentReports();
	ExtentTest test;
	ThreadLocal<ExtentTest> t = new ThreadLocal<ExtentTest>();

	@Override
	public synchronized void onTestStart(ITestResult result) {

		// getting methodname
		// start looking into the test on its start and generate reports

		test = er.createTest(result.getMethod().getMethodName());
		t.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		t.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		t.get().fail(result.getThrowable());
		String filePath = null;

		try {

			// here there is no life for the driver
			// so we are getting the test class inside that we are getting the class
			// followed by the field name and getting that instance
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	
	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		// flush is to end the seeing the test for report

		er.flush();
	}

}
