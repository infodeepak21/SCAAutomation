package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import commonUtilities.BasePage;
import commonUtilities.GenericUtils;

public class LoginPage extends BasePage{
	
	private static final String usernameTb="identification";
	private static final String passwordTb="password";
	private static final String loginBtn="//button[.='Login']";
	private static final String forgotPasswordLink="//a[.='Forgot your password?']";
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String userName) {
		getLogs(this.getClass());
		logger.info("Enter userName");
		GenericUtils.entervalueinTextbox("id", usernameTb, userName);
		logger.info("Username entered as :-" + userName);
	}

	public void enterPassword(String password) {
		getLogs(this.getClass());
		logger.info("Enter password");
		GenericUtils.entervalueinTextbox("id", passwordTb, password);
		logger.info("Password entered as :-" + password);
	}

	public void clickLogin() throws InterruptedException {
		getLogs(this.getClass());
		logger.info("Click Login");
		GenericUtils.clickElement("xpath" , loginBtn);
		logger.info("Clicked on login button");
	}
	
	public void clickonForgotPassword() {
		getLogs(this.getClass());
		logger.info("Click on forgotpassword link");
		GenericUtils.clickElement("xpath" , forgotPasswordLink);
		logger.info("Clicked on forgot password link");
	}

}
