package synchronizerHelper;

/**
 * Wait class is written to handle both implicit and explicit wait condition
 * while the automation script is in progress.
 * 
 * @author abhishek shandilya
 *
 */

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtilities.BaseTest;
import commonUtilities.PropertyManager;

public class Wait extends BaseTest {

	public static WebDriverWait wait;
	public static long time = Long.parseLong(PropertyManager.prop.getProperty("time"));
	public static long milliseconds = Long.parseLong(PropertyManager.prop.getProperty("milliseconds"));
	public static long implicitTime = Long.parseLong(PropertyManager.prop.getProperty("implicitTime"));

	public static void loadConfig() {
		PropertyManager.loadConfig();
	}

	public static void timeouts(long timeout) {
		wait = new WebDriverWait(driver, timeout);

	}

	public Wait(WebDriver driver) {
		super();
	}

	/**
	 * Method to handle implicit wait for two methods findElement and findElements
	 * 
	 * @param time to wait
	 * 
	 * @return by waiting for that particular duration for the entire test
	 */
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.MINUTES);
	}

	/**
	 * Method to sleep and pause for a particular moment
	 * 
	 * @param time to wait or sleep
	 * 
	 * @return by waiting for that particular duration for the particular step or
	 *         action
	 */
	public static void sleep() {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait
	 * 
	 * @return by waiting till the alert comes
	 */
	public static void alertIsPresent() {
		timeouts(time);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and title
	 * 
	 * @return by waiting till the title comes
	 */
	public static void titleIs(String title) {
		timeouts(time);
		wait.until(ExpectedConditions.titleIs(title));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and title
	 * 
	 * @return by waiting till the part of title comes or matches
	 */
	public static void titleContains(String title) {
		timeouts(time);
		wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the visibility of particular element
	 */
	public static void visibilityOf(WebElement element) {
		timeouts(time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and all the matching elements
	 * 
	 * @return by waiting till the visibility of all matching elements
	 */
	public static void visibilityOfAllElements(List<WebElement> element) {
		timeouts(time);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and elements via locators value
	 * 
	 * @return by waiting till the visibility of all elements located
	 */
	public static void visibilityOfAllElementsLocatedBy(By by) {
		timeouts(time);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and list of all elements
	 * 
	 * @return by waiting till the visibility of element located
	 */
	public static void visibilityOfElementLocated(List<WebElement> element) {
		timeouts(time);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait , element and text
	 * 
	 * @return by waiting till the visibility of text
	 */
	public static void textToBePresentInElement(WebElement element, String text) {
		timeouts(time);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait , element via locator value and text
	 * 
	 * @return by waiting till the visibility of text
	 */
	public static void textToBePresentInElementLocated(By by, String text) {
		timeouts(time);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait , element and text
	 * 
	 * @return by waiting till the visibility of text
	 */
	public static void textToBePresentInElementValue(WebElement element, String text) {
		timeouts(time);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element via locator value
	 * 
	 * @return by waiting till the presence of element
	 */
	public static void presenceOfAllElementsLocatedBy(By by) {
		timeouts(time);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element via locator value
	 * 
	 * @return by waiting till the presence of all the elements
	 */
	public static void presenceOfElementLocated(By by) {
		timeouts(time);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the element is clickable
	 */
	public static void elementToBeClickable(WebElement element) {
		timeouts(time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the element gets selected
	 */
	public static void elementToBeSelected(WebElement element) {
		timeouts(time);
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	/*
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till invisibility of the element
	 */
	/*
	 * public static void invisibilityOfElement(WebElement element) {
	 * timeouts(time); wait.until(ExpectedConditions.invisibilityOf(element)); }
	 */
	/*
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the invisibility of all the elements
	 */
	public static void invisibilityAllElementsLocatedBy(By by) {
		timeouts(time);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/*
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the invisibility of all the elements with the help of
	 * locator and the text present
	 */
	public static void invisibilityAllElementsLocatedByLocatorAndText(By by, String text) {
		timeouts(time);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
	}

	/*
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the frame is available by locator
	 */
	public static void frameAvailableByLocator(By by) {
		timeouts(time);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

	/*
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the frame is available by the number of frame
	 */
	/*
	 * public static void frameAvailableByframeNumber(int number) { timeouts(time);
	 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(number)); }
	 */
	/*
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the frame is available by frame id
	 */
	public static void frameAvailableByframeId(String id) {
		timeouts(time);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));
	}

	/*
	 * Method to handle explicit wait for all the methods including findElement and
	 * findElements
	 * 
	 * @param time to wait and element
	 * 
	 * @return by waiting till the frame is available by WebElement
	 */
	/*
	 * public static void frameAvailableByframeElement(WebElement element) {
	 * timeouts(time);
	 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element)); }
	 */
}
