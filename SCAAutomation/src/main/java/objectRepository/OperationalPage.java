package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.BasePage;

public class OperationalPage extends BasePage{
	
	@FindBy(xpath = "//div[@class='yui-g panelBody']//span[.='COGS Forecast']")
	private WebElement cogsForecast;

	@FindBy(xpath = "//div[@class='yui-g panelBody']//span[.='RM Risk Management']")
	private WebElement rmRiskManagement;

	@FindBy(xpath = "//div[@class='yui-g panelBody']//span[.='Historical']")
	private WebElement historical;
	
	public OperationalPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickCogsForecastButton() {
		getLogs(this.getClass());
		logger.info("Click on cogs forecast button");
		cogsForecast.click();
		logger.info("Clicked on cogs forecast button");
	}

	public void clickRmRiskManagementButton() {
		getLogs(this.getClass());
		logger.info("Click on rm risk management button");
		rmRiskManagement.click();
		logger.info("Clicked on rm risk management button");
	}

	public void clickHistoricalButton() {
		getLogs(this.getClass());
		logger.info("Click on historical button");
		historical.click();
		logger.info("Clicked on historical button");
	}
	

}
