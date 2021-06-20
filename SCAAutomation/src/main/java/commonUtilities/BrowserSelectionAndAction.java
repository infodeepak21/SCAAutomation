package commonUtilities;

import java.util.HashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import objectRepository.HomePage;
import objectRepository.LoginPage;
import synchronizerHelper.Wait;

/*
 * BrowserSelectionAndAction inherits the property of BaseTest and class and allows the for the instantiation of
 * respective of browser.
 * In this class we have written two methods:-
 *  a.) browserOption
 *  b.) browserAction
 *  c.) browserClosing
 *  
 *  @author Abhishek Shandilya
 */
public class BrowserSelectionAndAction extends BaseTest {
	public static DesiredCapabilities capabilities;
	public static ChromeOptions options;
	public static HashMap<String, Object> chromePrefs;
	public static FirefoxProfile profile;
	public static String sep;
	

	/*
	 * browserOption checks and opens the browser mentioned by the user from the
	 * properties file if an invalid browser is passed then in that case it is
	 * capable of handling exception with an error message to the user.
	 * 
	 * For chrome we have provided the implementation to run in incognito mode with
	 * the option of executing the code in headless form with a default window size
	 * as 1440,600 pixel. chrome is also accepting all the ssl certificate with the
	 * help of the methods present in DesiredCapabilities class
	 */
	@SuppressWarnings("deprecation")
	public static void browserOption() {
		capabilities = new DesiredCapabilities();
		options = new ChromeOptions();
		profile = new FirefoxProfile();
		sep = System.getProperty("file.separator");
		try {
			PropertyManager.loadConfig();
			if (PropertyManager.prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty(chromedriver, chromepath);
				String downloadFilepath = System.getProperty("user.dir")+"\\Download";
				chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				/*
				 * options.addArguments("--headless");
				 * options.addArguments("--window-size=1400,600");
				 */
				options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--incognito");
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				dc.setCapability(ChromeOptions.CAPABILITY, options);
				dc.setBrowserName("chrome");
				dc.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				driver = new ChromeDriver(dc);
			} else if (PropertyManager.prop.getProperty("browser").equalsIgnoreCase("firefox")) {
				
				profile.setPreference("browser.download.folderList",2);
			    profile.setPreference("browser.download.manager.showWhenStarting",false);
			    profile.setPreference("browser.download.dir", System.getProperty("user.dir")+ sep + "Download_Location" + sep + "downloadFiles");
			    System.setProperty(firefoxriver, firefoxpath);
			    driver = new FirefoxDriver((Capabilities) profile);
			} else {
				System.setProperty(internetExplorerDriver, internetExplorerpath);
				capabilities.setBrowserName("iexplore");
				capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setJavascriptEnabled(true);
				driver = new InternetExplorerDriver(capabilities);
			}
		} catch (Exception e) {
			Reporter.log("Invalid Browser Selection", true);
		}
	}
	
	/*
	 * browserAction method is responsible for some basic browser related operation
	 * like deleting the cookies for any hassle free execution It also call the
	 * implicit wait method from Wait class and tell the browser that some time
	 * period has been defined by the user in respective type of duration a user is
	 * interested in holding the test. The method also deals with maximizing the
	 * browser rather than a default mode execution and it is also responsible for
	 * passing the application url what so mentioned by the user from the respective
	 * Properties file.
	 * 
	 */
	public static void browserAction(int time) {
		driver.manage().deleteAllCookies();
		Wait.implicitWait();
		driver.manage().window().maximize();
		driver.get(PropertyManager.prop.getProperty("appUrl"));
	}

	/*
	 * browseClosing deals basically for closing the browser when only one tab is
	 * opened in the respective browser and quitting the browser when multiple tabs
	 * are opened during execution by selenium
	 */
	public static void browserClosing() {
		driver.close();
		driver.quit();
	}
	
	public static void signIn() {
		try {
			PropertyManager.loadConfig();
			LoginPage lp = new LoginPage(driver);
			HomePage hm = new HomePage(driver);
			lp.enterUsername(PropertyManager.prop.getProperty("username"));
			lp.enterPassword(PropertyManager.prop.getProperty("password"));
			lp.clickLogin();
			hm.verifyLogin();
		} catch (Exception e) {
			Reporter.log("Login Unsuccessful" ,true);
		}
	}
}
