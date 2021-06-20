package extentReports;

import java.io.IOException;

/*
 * ReportsListener is responsible for generating the reports in representable manner. The report will be generated in pie 
 * diagram with the status of how many got passed and failed. It will also give the user details of the test run.
 * The reports will be in html format and can be opened via browser.
 * 
 * In order to use this we need to call the same in the testng.xml with below things:-
 * <suite name="<Name of the suite>" verbose="1" >
	 <listeners>
	        <listener class-name="<packageName>.TestListener"/>
	  </listeners>
	  <test name="<Name of the test>">
 * 
 * @author Abhishek Shandilya
 */

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import commonUtilities.BaseTest;
import screenShots.ExtentReportScreenshots;

public class TestListener extends BaseTest implements ITestListener {
	public static String screenshotPath;

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		ExtentTestManager.getTest().log(Status.FAIL, result.getName());
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		try {
			screenshotPath = ExtentReportScreenshots.getScreenshot(driver, result.getName());
			ExtentTestManager.getTest().addScreenCaptureFromPath("./screenshot/*.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}
