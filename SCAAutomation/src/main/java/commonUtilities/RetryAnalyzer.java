package commonUtilities;

/*
 * Retry Analyzer is considered as a best practice for handling test failure in case of any odd circumstances occurred by system
 * while the test is under running process.
 * To handle that we have use retry analyzer which is responsible for smooth run.
 * 
 * For our framework we have used the same with a counter of 3 as a maximum limit, in this case if a test fails then it will re-execute
 * the same for a maximum of three times if the test is passed in any of the three run it will be considered as pass it will stop 
 * executing it further.
 * 
 * @author Abhishek Shandilya
 */

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int count = 0;
	int limit = Integer.parseInt((PropertyManager.prop.getProperty("retryLimit")));

	public boolean retry(ITestResult result) {
		PropertyManager.loadConfig();
		if (count < limit) {
			count++;
			return true;
		}
		return false;
	}
}