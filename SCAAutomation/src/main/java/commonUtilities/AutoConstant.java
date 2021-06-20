package commonUtilities;

/*
 *  This interface is created with the intension of handling all the static, final and public properties.
 *  In this we will be having below things:-
 *   a.) Path of the file 
 *   b.) Path of driver executable of each and every browsers used for test
 *   c.) key of each driver executable 
 *   d.) Location of the final files etc.
 *   We will be defining the same in future if something more or new is added. 
 *   
 *   
 *   @author Abhishek Shandilya
 */

public interface AutoConstant {

	public String chromedriver = "webdriver.chrome.driver";
	public String chromepath = "./drivers/chromedriver.exe";
	public String firefoxriver = "webdriver.gecko.driver";
	public String firefoxpath = "./drivers/geckodriver.exe";
	public String internetExplorerDriver = "webdriver.ie.driver";
	public String internetExplorerpath = "./drivers/IEDriverServer.exe";

	public String fileName = "";

}