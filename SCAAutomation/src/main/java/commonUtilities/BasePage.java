package commonUtilities;

import org.apache.log4j.Level;

/**
 * BasePage class will be extended by all the classes Present in the objectRepository package
 * The method in the BasePage class will be used for :-
 *     a.)to verify the title of the webpage
 *     b.)to verify the element present on the webpage
 *     c.)get Logs of each and every steps or actions done by the selenium browser 
 * @author Abhishek Shandilya
 */

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/*
 * BasePage will be inherited by all the POM classes as this class will have some functionality for verifying the elements 
 * verifying title of the page as well as it will be responsible as an main source of logging for each and every action done by 
 * the user as it will be calling loggers to log the actions for each and every line of codes and it will also be helpful for 
 * debugging our execution when so required.
 */
public class BasePage {

	public Logger logger;
	public WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Method to get Logs.
	 * 
	 * @param Test Class
	 * 
	 * @return Logs for each step
	 */
	public void getLogs(Class<?> cls) {
		logger = Logger.getLogger(cls);
		PropertyConfigurator.configure("log4ji.properties");
		logger.setLevel(Level.DEBUG);
	}

	/**
	 * Method to verify the title
	 * 
	 * @param WebPage Name
	 * 
	 * @return Title of every Page
	 */
	public void verifyTitle(String eTitle) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.titleIs(eTitle));
			Reporter.log("Title is matching :" + eTitle, true);
		} catch (Exception e) {
			Reporter.log("Title is not matching", true);
			Assert.fail();
		}
	}

	/**
	 * Method to verify elemnets present on the webpage
	 * 
	 * @param WebPage element that has to be verified
	 * 
	 * @return boolean value if the element is present
	 */
	public void verifyElement(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.visibilityOf(element));
			Reporter.log("Element is matching " + element.getText(), true);
		} catch (Exception e) {
			Reporter.log("Element is  not matching ", true);
			Assert.fail();
		}
	}
}
