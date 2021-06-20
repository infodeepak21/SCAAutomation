package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.BasePage;
import commonUtilities.GenericUtils;

public class DatastatusPage extends BasePage {

	@FindBy(xpath = "//div[@class='row']//button[contains(., 'Maintenance')]")
	private WebElement maintenance;

	@FindBy(xpath = "//div[@id='maintDiv']//span[.='Update Contract Price']")
	private WebElement updateContractPrice;

	@FindBy(xpath = "//div[@id='maintDiv']//span[.='Manage Other Information']")
	private WebElement manageOtherInformation;

	@FindBy(xpath = "//div[@id='maintDiv']//span[.='Continue Budget Process']")
	private WebElement continueBudgetProcess;

	public DatastatusPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickMaintenanceButton() {
		getLogs(this.getClass());
		logger.info("Click on maintenance button");
		maintenance.click();
		logger.info("Clicked on maintenance button");
	}

	public void clickUpdateContractPriceLinkText() {
		getLogs(this.getClass());
		logger.info("Click on update contract price text link ");
		GenericUtils.moveToElementAndClick(updateContractPrice);
		logger.info("Clicked on update contract price text link");
	}

	public void clickManageOtherInformationLinkText() {
		getLogs(this.getClass());
		logger.info("Click on manage other information text link ");
		GenericUtils.moveToElementAndClick(manageOtherInformation);
		logger.info("Clicked on manage other information text link");
	}

	public void clickContinueBudgetProcessLinkText() {
		getLogs(this.getClass());
		logger.info("Click on continue budget process text link ");
		GenericUtils.moveToElementAndClick(continueBudgetProcess);
		logger.info("Clicked on continue budget process text link");
	}
}
