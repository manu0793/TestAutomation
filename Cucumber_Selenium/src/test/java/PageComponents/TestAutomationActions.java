package PageComponents;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.Utilities;

import base.BaseClass;



public class TestAutomationActions extends BaseClass{
	String vaio5Price,vaio5CheckoutPrice,purchaseID,checkOut;
	boolean validator = false;
	Utilities ut = new Utilities();
	
	By laptops = By.xpath("//a[text()='Laptops']");
	By laptopType1 = By.xpath("//a[text()='Sony vaio i5']");
	By laptopType2 = By.xpath("//a[contains(text(),'Sony vaio i7')]");
	By addToCart = By.xpath("//a[text()='Add to cart']");
	By home = By.xpath("//a[contains(text(),'Home')]");
	By cart = By.xpath("//a[@id='cartur']");
	By deleteVaio7 = By.xpath("//td[contains(text(),'Sony vaio i7')]/following-sibling::td[2]/a");
	By vaioPrice = By.xpath("//td[contains(text(),'Sony vaio i5')]/following-sibling::td[1]");
	By placeOrder = By.xpath("//button[text()='Place Order']");
	By name = By.xpath("//input[@id='name']");
	By country = By.xpath("//input[@id='country']");
	By city = By.xpath("//input[@id='city']");
	By card = By.xpath("//input[@id='card']");
	By month = By.xpath("//input[@id='month']");
	By year = By.xpath("//input[@id='year']");
	By purchase = By.xpath("//button[text()='Purchase']");
	By checkout = By.xpath("//p[@class='lead text-muted ']");


public TestAutomationActions() {
	PageFactory.initElements(driver, this);
	
}


//Add Laptops to the cart
public  void addLaptops() throws Exception {	
	//add laptops in cart
	if(addMultiLaptops(laptopType1)) {
		driver.findElement(home).click();
		if(addMultiLaptops(laptopType2)) {	
			Assert.assertTrue(true, "Laptops were added successfully in cart");
		}
		else {
			Assert.assertTrue(true, "Both the variants of laptops added successfully");
		}
	}
	else {
		Assert.assertFalse(false, "Laptops were not added successfully in cart");
		ut.takeSnapShot(driver,prop.getProperty("screenshots"));
	}
}

//Delete the laptop
public void deleteLaptop() throws Exception {
	WebDriverWait wait = new WebDriverWait(driver,60);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	if(driver.findElement(cart).isEnabled()) {
		driver.findElement(cart).click();	
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(deleteVaio7)));
		vaio5Price = driver.findElement(vaioPrice).getText();
		js.executeScript("arguments[0].click();", driver.findElement(deleteVaio7));
		Thread.sleep(3000);
		
		//place order
		js.executeScript("arguments[0].click();", driver.findElement(placeOrder));
		Assert.assertTrue(true, "Laptop is deleted and order is placed");
		driver.switchTo().activeElement();
	}
	else {
		Assert.assertFalse(false, "Laptop is not deleted");
		ut.takeSnapShot(driver,prop.getProperty("screenshots"));
	}
	
}

//Fill up the Shipping address
public void fillChekinDetails() {
	WebDriverWait wait = new WebDriverWait(driver,60);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	//Enter name,country,etc details
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(name)));
		driver.findElement(name).sendKeys(prop.getProperty("name"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(country)));
		driver.findElement(country).sendKeys(prop.getProperty("country"));
		driver.findElement(city).sendKeys(prop.getProperty("city"));
		driver.findElement(card).sendKeys(prop.getProperty("card"));
		driver.findElement(month).sendKeys(prop.getProperty("month"));
		driver.findElement(year).sendKeys(prop.getProperty("year"));
		
		//click purchase
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(country)));
		driver.findElement(purchase).click();
}

//Get Purchase ID
public void getPurchaseID() throws Exception{
	if(driver.findElement(checkout).isDisplayed()) {
	  Assert.assertTrue(true, "Checkout is successfull");
	  checkOut = driver.findElement(checkout).getText();
	  String concatinate[] =checkOut.split("\\r?\\n");
	  purchaseID = concatinate[0].replace("Id:", "").replace(" ", "");
	  vaio5CheckoutPrice = concatinate[1].replace("Amount:", "").replace(" ", "").replace("USD", "");
		if(vaio5CheckoutPrice.equals(vaio5Price)) {
			Assert.assertTrue(true, "Price of Viao5 matched");
			ut.takeSnapShot(driver,prop.getProperty("screenshots"));
			driver.close();
		}
		else {
			Assert.assertFalse(false, "Price of Viao5 mismatched");
			ut.takeSnapShot(driver,prop.getProperty("screenshots"));
			driver.close();
		}
	}
	else {
		Assert.assertFalse(false, "Checkout is unsuccessfull");
		ut.takeSnapShot(driver,prop.getProperty("screenshots"));
		driver.close();
	}
	
	}
	
	
//Add multiple Laptops	
public boolean addMultiLaptops(By laptop) throws Exception {
	WebDriverWait wait = new WebDriverWait(driver,60);
	JavascriptExecutor js = (JavascriptExecutor) driver; 	
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(laptops)));
	
	if(driver.findElement(laptops).isEnabled()) {
		Assert.assertTrue(true, "Application is up and Laptopn icon is available");
		ut.takeSnapShot(driver,prop.getProperty("screenshots"));	
		driver.findElement(laptops).click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(laptop)));
		if(driver.findElement(laptop).isEnabled()) {
			js.executeScript("arguments[0].click();", driver.findElement(laptop));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(addToCart)));
			
			if(driver.findElement(addToCart).isEnabled()) {
				Assert.assertTrue(true, "Add to cart button is available");
				ut.takeSnapShot(driver,prop.getProperty("screenshots"));
				driver.findElement(addToCart).click();
				Thread.sleep(3000);
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				validator = true;
			}
			else {
				Assert.assertFalse(false, "Add To cart button is not available");
				ut.takeSnapShot(driver,prop.getProperty("screenshots"));
			}
		}
		else {
			Assert.assertFalse(false, "Sony Vaio i5 is not available");
			ut.takeSnapShot(driver,prop.getProperty("screenshots"));
		}
		
	}
	else {
		Assert.assertFalse(false, "Application is down and Laptopn icon is not available");
		ut.takeSnapShot(driver,prop.getProperty("screenshots"));
	}

	return validator;
	
}

}
