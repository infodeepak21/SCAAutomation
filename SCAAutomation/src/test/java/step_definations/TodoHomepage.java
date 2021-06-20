package step_definations;

import org.openqa.selenium.WebDriver;

import commonUtilities.GenericUtils;
import commonUtilities.IdType;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class TodoHomepage extends  GenericUtils  {
	
	private String todo="//body/section[1]/section[1]/ul[1]/li[1]/div[1]/input[1]";
	private String alloption="//a[contains(text(),'All')]";
	private String activeoption="//a[contains(text(),'Active')]";
	private String completedoption="//a[contains(text(),'Completed')]";
	private String serchText="//header/input[1]";
	private String	clearCompleted="//button[contains(text(),'Clear completed')]";
	private String documentation="//a[contains(text(),'Documentation')]";
	private String reference="//a[contains(text(),'API Reference')]";
	private String examples="//a[contains(text(),'Examples')]";
	private String gitHub="//a[contains(text(),'Vue.js on GitHub')]";
	private String twitter="//a[contains(text(),'Twitter')]";
	private String gitterChannel="//a[contains(text(),'Gitter Channel')]";
	private String discussions="//a[contains(text(),'Discussions on GitHub')]";
	private String vlauejslink="//body/aside[1]/blockquote[1]/footer[1]/a[1]";
	
			

	public TodoHomepage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

@Given("^user login to the application(.+)$")
public void user_login_to_application(String testcase) throws InterruptedException {
	
	driver.navigate().to("http://todomvc.com/examples/vue/");
	 waitForPageLoadFully();
	 
	
	
	
	
}
@When("^user search the search item in search field")
public void userserch_the_search_item_search_field() {
	 click(IdType.Xpath, todo);
		waitForSomeTime(2);
		typeText(IdType.Xpath,serchText,"todo");
}
@And("^remove the all test")
public void remove_the_all_test() {
	
	 click(IdType.Xpath, alloption);
		waitForSomeTime(2);
		
}

@And("^display number search option")
public void display_number_search_option() {

	 click(IdType.Xpath, activeoption);
		waitForSomeTime(2);
		
}

@And("^click on clear completed")
public void click_clear_completed() {
	
	 click(IdType.Xpath, clearCompleted);
		waitForSomeTime(2);
		
}

@And("^click on click Vuelink")
public void click_Vuelink() {
	
	 click(IdType.Xpath, clearCompleted);
		waitForSomeTime(2);
		
} 


@And("^click on documentation link")
public void click_documentation_link() {
	
	 click(IdType.Xpath, documentation);
		waitForSomeTime(2);
		
}
@And("^click on API referece link")
public void click_API_referece_link() {
	
	 click(IdType.Xpath, reference);
		waitForSomeTime(2);
		
}

@And("^click on Example link")
public void click_Example_link() {
	
	 click(IdType.Xpath, examples);
		waitForSomeTime(2);
		
}
@And("^click on Vuejs GitHub link")
public void click_VueGitHub_link() {
	
	 click(IdType.Xpath, gitHub);
		waitForSomeTime(2);
		
}

@And("^click on twitter link")
public void click_twitter_link() {
	
	 click(IdType.Xpath, twitter);
		waitForSomeTime(2);
		
}

@And("^click on Gitter Channel link")
public void click_Gitter_Channellink() {
	
	 click(IdType.Xpath, gitterChannel);
		waitForSomeTime(2);
		
}
}
