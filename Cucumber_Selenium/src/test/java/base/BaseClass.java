package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public  static WebDriver driver;
	public  static Properties prop;
	
	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\Manu Dubey\\Documents\\myWorkspace\\Cucumber_Selenium\\src\\test\\java\\config\\config.properties");
			prop.load(fis);
			
		}
		catch(IOException e) {
			e.getMessage();
		}
	}	
	public static void initialise() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Manu Dubey\\Documents\\chromedriver.exe"); 					
		      driver= new ChromeDriver();  								
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	
}
