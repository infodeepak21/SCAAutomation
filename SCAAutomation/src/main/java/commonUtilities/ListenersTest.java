package commonUtilities;

/* 
 * ListernersTest class is implementing the methods of ITestListener which contains unimplemented method in order to take 
 * screenshot on script failure we are only implementing the methods of "onTestFailure" 
 * To take the screenshot we are are using "EventFiringWebDriver" Class
 * 
 * In order to use this we need to call it in testng.xml file in below ways:-
 * 
 <suite name="<Name of the suite>" verbose="1" >
	 <listeners>
	        <listener class-name="<packageName>.ListenersTest"/>
	  </listeners>
	  <test name="<Name of the test>">
 * 
 * @author Abhishek Shandilya
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import jiraServices.JiraPolicy;
import jiraServices.JiraServiceProvider;

public class ListenersTest implements ITestListener {
	
	public static final String url = "<jira_url>";
	public static final String userName = "<username_jira>";
	public static final String password = "<password_jira>";
	public static final String project = "<jira_ProjectName>";
	public static final String issueType = "<JiraIssueType>";
	public static final String reporterName = "<IssueReporterName>";
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	/*
	 * The screenshot will be captured once the test is failed and it will get
	 * stored in screenshot folder with name same as "Method Name"
	 */
	public void onTestFailure(ITestResult result) {
		/*
		 * try { String name = result.getMethod().getMethodName(); EventFiringWebDriver
		 * efw = new EventFiringWebDriver(BaseTest.driver); File src =
		 * efw.getScreenshotAs(OutputType.FILE); File des = new File("./screenshot/" +
		 * name + System.currentTimeMillis() + ".png"); FileUtils.copyFile(src, des); }
		 * catch (IOException e1) { e1.printStackTrace(); }
		 */
		
		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean ticketReady = jiraPolicy.logTicketReady();
		if(ticketReady) {
			System.out.println("Is ticket ready for jira ? :-" + ticketReady);
			JiraServiceProvider serviceProvider = new JiraServiceProvider(url, userName, password, project);
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName() + "Failed due to some assertion or exception";
			String issueDescription = result.getThrowable().getMessage() + "\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			serviceProvider.createJiraTicket(issueType, issueSummary, issueDescription, reporterName);
		}	
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
