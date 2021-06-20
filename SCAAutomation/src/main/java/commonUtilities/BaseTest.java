package commonUtilities;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import applicationUtilities.DeleteScreenRecorded;
import atu.testrecorder.ATUTestRecorder;

/*
 * This class is responsible for implementing AutoConstant as well the main use of this class is to drive the test.
 * This class will be inherited by all the Test classes in order to meet the prerequisite.
 * This class deals with different annotations of TestNG except @Test and thus makes the class as abstract.
 * Methods which will be implemented in this test are as follows:-
 *   a.) calling the method of BrowserSelectionAndAction class
 *   b.) test initialization
 *   c.) test completion
 *   d.) database connection making
 *   e.) closing the database connection
 *   f.) connection of excel
 *   g.) closing the excel 
 *   
 *   @author Abhishek Shandilya
 */
public abstract class BaseTest implements AutoConstant {
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Workbook wb;
	public static ATUTestRecorder recorder;
	public static DateFormat dateFormat;
	public static Date date;
	
	protected static RemoteWebDriver webDriver;

	/*
	 * This method is responsible to establish the connection of the jdbc.
	 */
	@BeforeSuite
	public void connectDataBase() {
//		DatabaseConnection.connectDB();
		DeleteScreenRecorded.deleteScreenRecorded(System.getProperty("user.dir") + "\\recordings");
	}
	/*
	 * @BeforeTest public void openExcel(String file) { }
	 */

	/*
	 * This method is used for opening the browser and entering the url as providing
	 * the implicit wait time before starting the test.It will accept one Parameter
	 * of type integer and it will implict wait is in Minute which is further linked
	 * with Wait class implicit wait method.
	 * 
	 *
	 * Method to open browser
	 * 
	 * @param wait time
	 * 
	 * @return opening of respective browser, passing of url, and providing implicit
	 * wait time to the execution.
	 *
	 * 
	 * 
	 */
	@BeforeClass
	@Parameters({ "waitTime" })
	public void openBrowser(@Optional("10") int waitTime) {
		BrowserSelectionAndAction.browserOption();
		BrowserSelectionAndAction.browserAction(waitTime);
		BrowserSelectionAndAction.signIn();
	}

	/*
	 * The use of this method is to indicate the user to have a clear picture of
	 * Test getting initiated even if one Test class is having multiple test
	 * methods.Before starting any test it will refresh the page which is currently
	 * made mandatory so that all the elements gets loaded on the page and the test
	 * can start in a smoother manner.
	 */
	@BeforeMethod
	public void testIntialization() {
		driver.navigate().refresh();
		dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		date = new Date();	
		try {
			recorder = new ATUTestRecorder(System.getProperty("user.dir") + "\\recordings\\", "TestVideos-" +dateFormat.format(date) , false);
			Reporter.log("Location found to save the file" , true);
		}catch (Exception e) {
			Reporter.log("Unable to get location" , true);
		}
		
		Reporter.log(
				"              -------------------------------------- Test Started --------------------------------------              ",
				true);
		try {
			recorder.start();
			Reporter.log("Recording started" , true);
		}catch (Exception e) {
			Reporter.log("Unable to record" , true);
		}
	}

	/*
	 * The use of this method is just to indicate the end user about the test has
	 * been executed.
	 */
	@AfterMethod
	public void testCompletion() {
		Reporter.log(
				"              -------------------------------------- Test Completed --------------------------------------              ",
				true);
		try {
			recorder.stop();
			Reporter.log("Recording ended" , true);
		}catch (Exception e) {
			Reporter.log("Unable to stop recording" , true);
		}
	}

	/*
	 * The use of this method is to close the browser once the entire test suites
	 * test has been executed.
	 */
	@AfterClass
	public void closeBrowser() {
		BrowserSelectionAndAction.browserClosing();
	}

	/*
	 * The use of this method is to call the method present in DatabaseConnection
	 * class for closing all the jdbc connections
	 */
	@AfterTest
	public void closeConnection() {
//		DatabaseConnection.disconnectDB();
	}

	/*
	 * The use of this method is to send the mail after the execution of the suite.
	 */
	@AfterSuite
	public void sendMailAfterExecution() {
//		MailNotification.sendMailWithAttachment();
	}

	public static RemoteWebDriver getInstance() {
		String browser=null;
		if(webDriver==null || webDriver.getSessionId()==null) {
			 if(browser.equals("chrome")){
				 webDriver = (RemoteWebDriver) webDriver.getCapabilities();
				 
			 }
			
		}
		return webDriver;
	}
}
