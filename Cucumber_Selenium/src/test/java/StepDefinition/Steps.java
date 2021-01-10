package StepDefinition;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;

import PageComponents.TestAutomationActions;
import Utilities.Utilities;
import base.BaseClass;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;
import cucumber.runtime.model.CucumberScenario;



public class Steps extends BaseClass {
	
	TestAutomationActions ta = new TestAutomationActions();

	@Given("Open Chrome and launch the application")	
	 public void open_the_Browser_and_launch_the_application() throws Throwable							
	    {		
		BaseClass.initialise();	
	    }
	@When("I Add two different variants of Vaio laptops in cart")
	     public void click_Laptop_Icon() throws Throwable{
		ta.addLaptops();
	    }
	@Then("I delete one variant of Vaio laptop and checkin")
		public void delete_one_variant() throws Throwable{
		ta.deleteLaptop();
	}
	 
	@Then("I fill up the details for purchasing the laptop variant")
		public void fill_checkinDetails() throws Throwable{
		ta.fillChekinDetails();
	}
	
	@Then("I verify the Purchase ID and purchase amount")
		public void verify_PurchaseID_Amount() throws Throwable{
		ta.getPurchaseID();
	}
	

}
