package extentReports;

/*
 *  This can be used to create instance of ExtentTest and put into extentTestMap with current thread id.
 *  ExtentTest instance in the TestListiner or Selenium webdriver test classes, we can use getTest() method, 
 *  which returns ExtentTest instance from extentTestMap by using current thread id.
 *  
 *  @author Abhishek Shandilya
 */

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
