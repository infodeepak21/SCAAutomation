package commonUtilities;

/**
 * Generic Class is basically the collection of all the actions that selenium can handle.
 * The methods in the Generic Class will handle below actions :-
 * 		a.) Select Class (for dropdown list)
 *      b.) Actions class (for dropdown menu and mouse hover operations)
 *      c.) Frames (for handling the frames present on the webpage)
 *      d.) Popups (All the popup which we will generated in the application including file upload and file download)
 *      e.) JavascriptExecutor (for handling hidden element in the webapge)
 *      f.) Robot Class (for handling scrolling the webpage)
 * 
 * @author Abhishek Shandilya
 */

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import synchronizerHelper.Wait;

public class GenericUtils extends BaseTest {

	public static List elements;
	public static WebElement element;
	public static Select select;
	public static Actions actions;
	public static Alert alert;
	public static Robot robot;
	public static Runtime runtime;
	public static List<WebElement> list;
	public static String text;
	public static LocalDate today;

	public static void commonObjects() {
		select = new Select(element);
		actions = new Actions(driver);
		alert = driver.switchTo().alert();
		runtime = Runtime.getRuntime();
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public GenericUtils(WebDriver driver) {
		super();
	}

	/**
	 * Method to select by value in the dropdown list.
	 * 
	 * @param Value , WebElement
	 * 
	 * @return Selected Value in the dropdownlist
	 */
	public static void selectByValue(String value) {
		select.selectByValue(value);
	}

	/**
	 * Method to select by index in the dropdown list.
	 * 
	 * @param Index , WebElement
	 * 
	 * @return Selected Value in the dropdownlist
	 */
	public static void selectByIndex(int index) {
		select.selectByIndex(index);
	}

	/**
	 * Method to select by visibleText in the dropdown list.
	 * 
	 * @param visibleText , WebElement
	 * 
	 * @return Selected Value in the dropdownlist
	 */
	public static void selectByVisibleText(String visibleText) throws InterruptedException {
		select.selectByVisibleText(visibleText);
	}

	/**
	 * Method to deselect by value in the dropdown list.
	 * 
	 * @param Value , WebElement
	 * 
	 * @return By Deselecting Value in the dropdownlist
	 */
	public static void deselectByValue(String value) {
		select.deselectByValue(value);
	}

	/**
	 * Method to deselect by index in the dropdown list.
	 * 
	 * @param Index , WebElement
	 * 
	 * @return By Deselecting Value in the dropdownlist
	 */
	public static void deselectByIndex(int index) {
		select.deselectByIndex(index);
	}

	/**
	 * Method to deselect by visibleText in the dropdown list.
	 * 
	 * @param VisibleText , WebElement
	 * 
	 * @return By Deselecting Value in the dropdownlist
	 */
	public static void deselectByVisibleText(String visibleText) {
		select.deselectByVisibleText(visibleText);
	}

	/**
	 * Method to deselect the elements which is already selected in the dropdown
	 * list.
	 * 
	 * @return By Deselecting all the selected values in the dropdownlist
	 */
	public static void deselectAll() {
		select.deselectAll();
	}

	/**
	 * Method to get all the selected elements which is present in the dropdown
	 * list.
	 * 
	 * @return All selected options which is there in the dropdown list
	 */
	public static void getAllSelectedOption() {
		list = select.getAllSelectedOptions();
		for (WebElement name : list) {
			text = name.getText();
			Reporter.log(text, true);
		}
	}

	/**
	 * Method to get the first selected elements which is present in the dropdown
	 * list.
	 * 
	 * @return First selected option which is there in the dropdown list
	 */
	public static void getFirstSelectedOption() {
		text = select.getFirstSelectedOption().getText();
		Reporter.log(text, true);
	}

	/**
	 * Method to get the option of elements which is present in the dropdown list.
	 * 
	 * @return Elements which is there in the dropdown list
	 */
	public static void getOptions() {
		list = select.getOptions();
		for (WebElement name : list) {
			text = name.getText();
			Reporter.log(text, true);
		}
	}

	/**
	 * Method to check whether the dropdown is a multi select dropdown or not list.
	 * 
	 * @return Boolean value in form of true or false
	 */
	public static boolean isMultiple() {
		return select.isMultiple();
	}

	/**
	 * Method for mouse hovering and click on the hovered element
	 */
	public static void moveToElementAndClick(WebElement element) {
		actions.moveToElement(element).click().perform();
	}

	/**
	 * Method for mouse hovering on the element
	 */
	public static void moveToElement(WebElement element) {
		actions.moveToElement(element).perform();
	}

	/**
	 * Method to click on the element
	 */
	public static void clickElement(WebElement elements) {
		elements.click();
	}

	/**
	 * Method to right click on the element
	 */
	public static void rightClick() {
		actions.contextClick(element).sendKeys(Keys.CONTROL, "T");
	}

	/**
	 * Method to click on the element and pass some text
	 * 
	 * @param text to pass
	 */
	public static void sendKeys(String text) throws InterruptedException {
		actions.sendKeys(text).perform();
		actions.click().perform();
	}

	/**
	 * Method to click on the OK or Accept button of the popup
	 */
	public static void alertPopupAccept() {
		alert.accept();
	}

	/**
	 * Method to click on the Cancel or Dismiss button of the pop-up
	 */
	public static void alertPopupCancel() {
		alert.dismiss();
	}

	/**
	 * Method to get the text present on the pop-up
	 */
	public static void alertPopupGetText() {
		alert.getText();
	}

	/**
	 * Method to handle calendar pop-up
	 */
	public static void calendarPopUp() {

	}

	/**
	 * Method to handles frames present by switching on the frame
	 * 
	 * @param index value of the frame
	 */
	public static void frameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * Method to handles frames present by switching on the frame
	 * 
	 * @param Value of the frame
	 */
	public static void frameByvalue(String value) {
		driver.switchTo().frame(value);
	}

	/**
	 * Method to handles frames present by switching on the frame
	 * 
	 * @param WebElement present on the webpage
	 */
	public static void frameByWebElement(WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * Method to switch from frames to webpage.
	 */
	public static void defaultFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Method to scroll down the page
	 */
	public static void scrollDown() throws AWTException {
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	/**
	 * Method to upload a file
	 */
	public static void scrollUp() throws AWTException {
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}

	/**
	 * Method to upload a file
	 * 
	 * @param path of the file
	 */
	public static void fileUpload(String filePath) throws IOException {
		runtime.exec(filePath);
	}

	/**
	 * Method to get the element from the UI
	 * 
	 * @param lcocator type , locator value
	 */
	public static WebElement getElement(String locatorType, String locatorValue) {
		try {
			if (locatorType.equalsIgnoreCase("id")) {
				Wait.presenceOfElementLocated(By.id(locatorValue));
				element = driver.findElement(By.id(locatorValue));
				return element;
			} else if (locatorType.equalsIgnoreCase("tag") || locatorType.equalsIgnoreCase("tagName")) {
				Wait.presenceOfElementLocated(By.tagName(locatorValue));
				element = driver.findElement(By.tagName(locatorValue));
				return element;
			} else if (locatorType.equalsIgnoreCase("name")) {
				Wait.presenceOfElementLocated(By.name(locatorValue));
				element = driver.findElement(By.name(locatorValue));
				return element;
			} else if (locatorType.equalsIgnoreCase("classname")) {
				Wait.presenceOfElementLocated(By.className(locatorValue));
				element = driver.findElement(By.className(locatorValue));
				return element;
			} else if (locatorType.equalsIgnoreCase("cssSelector")) {
				Wait.presenceOfElementLocated(By.cssSelector(locatorValue));
				element = driver.findElement(By.cssSelector(locatorValue));
				return element;

			} else if (locatorType.equalsIgnoreCase("linkText")) {
				Wait.presenceOfElementLocated(By.linkText(locatorValue));
				element = driver.findElement(By.linkText(locatorValue));
				return element;

			} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
				Wait.presenceOfElementLocated(By.partialLinkText(locatorValue));
				element = driver.findElement(By.partialLinkText(locatorValue));
				return element;
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				Wait.presenceOfElementLocated(By.xpath(locatorValue));
				element = driver.findElement(By.xpath(locatorValue));
				return element;
			} else {
				Reporter.log("Sorry, No Matches found. Please check arguments passed", true);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * Method to click on the element
	 * 
	 * @param lcocator type , locator value
	 */
	public static void clickElement(String idtype, String locatorValue) {
		getElement(idtype, locatorValue);
		Wait.elementToBeClickable(element);
		element.click();
	}

	/**
	 * Method to enter the value in the text
	 * 
	 * @param lcocator type , locator value , value
	 */
	public static void entervalueinTextbox(String locatorType, String locatorValue, String textBoxValue) {
		GenericUtils.getElement(locatorType, locatorValue);
		element.sendKeys(textBoxValue);
	}
	
	public void openUrl(String url) throws TimeoutException {
		//getinstance().get()
		System.out.println("welkcome to url");
	}
	public static void waitForSomeTime(int inSeconds) {
		try {
			Thread.sleep(inSeconds*1000);			 
		}catch(InterruptedException in){
			in.printStackTrace();
		}
	}
	
	private WebDriverWait webdriverwait() {
		return new WebDriverWait( getInstance(), (long) 0.150);
	}
	public  void waitForPageLoadFully() throws InterruptedException {
		ExpectedCondition<Boolean> pageloadcondition=new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readystate").equals("complete");
				
			}
		};
		
		WebDriverWait wait= new WebDriverWait(driver, 60);
		webdriverwait().until(pageloadcondition);
	}
	
	public void clickUsingJavascript(String idtype, String idValue) {
		JavascriptExecutor javascriptExecutor= (JavascriptExecutor)getInstance();
		//javascriptExecutor.executeAsyncScript("argument[0].click();", clickElement(idtype, idValue));
		
	}
	
	public WebElement locateElement (IdType idType, String idValue ) {
		
		WebElement element= null;
		boolean sucess= false;
		int count=0;
		Instant start= null;
		
		while(sucess==false) {
			try {
				start.MIN.now();
				count++;
				switch(idType) {
				case Id:
					return element=FindElement(By.id(idValue));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		}
		
		
		return element;
	}

	private org.openqa.selenium.WebElement FindElement(By id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void click(IdType idType, String idvalue) {
		locateElement(idType,idvalue).click();
	}
	
	public void typeText(IdType idType, String idvalue, String textToType) {
		try {
			WebElement element=locateElement(idType,idvalue);
			element.clear();
			element.sendKeys(textToType);
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	
	}
	
	
	
}
