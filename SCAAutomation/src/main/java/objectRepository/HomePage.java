package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import commonUtilities.BasePage;
import commonUtilities.GenericUtils;
import synchronizerHelper.Wait;

public class HomePage extends BasePage {

	private static final String linkText = "//li[@class='lslide active']//h3[.='\"+text+\"']";
	@FindBy(xpath = "//div[@class='navbar-header']//p[.='SCAPlanner Home']")
	private WebElement expectedTitle;
	private static String actualTitle = "SCAPlanner Home";

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickCogslinkText(String linkText) {
		getLogs(this.getClass());
		logger.info("Click on " + linkText);
		String linkToClick = "//li[@class='lslide active']//h3[.='\"+linkText+\"']";
		GenericUtils.clickElement("xpath", linkToClick);
		logger.info("Clicked on" + linkText);
	}

	public void verifyLogin() {
		try {
			Wait.visibilityOf(expectedTitle);
			if (expectedTitle.getText().equalsIgnoreCase(actualTitle)) {
				Reporter.log("Logged in successfully", true);
			}
		} catch (Exception e) {
			Reporter.log("Unable to find whether it is logged or not", true);
		}
	}
}
