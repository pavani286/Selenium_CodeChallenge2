package com.mytrip.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.mytrip.qa.datautil.WebEventListener;

public class BaseTest {
	public static  WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static com.mytrip.qa.datautil.WebEventListener eventListener;
	
	public BaseTest(){
		try {
		        prop = new Properties();
			   FileInputStream fs = new FileInputStream("/Users/pavanivemula/Documents/Workspace/Selenium_CodeChallenge2/src/main/java/"
			   		+ "com/mytrip/qa/Config/Config.properties");
				prop.load(fs);
				     }catch (FileNotFoundException e) {
					e.printStackTrace();
				              }catch (IOException e) {
					e.printStackTrace();
				         }
			}
		
	public void intialization(){
		String browsername =prop.getProperty("browser");
		if(browsername.equals("chrome")){
			System.setProperty("webdriver.chrome.driver","/Users/pavanivemula/Documents/Drivers/chromedriver");
			driver= new ChromeDriver();
		           }else if(browsername.equals("FF")){
			System.setProperty("webdriver.gecko.driver","/Users/pavanivemula/Documents/Drivers/geckodriver");
			driver= new FirefoxDriver();
			 }
		/*    for creating the log information in console     */
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	
	}
}
