package com.mytrip.qa.pages;

import java.text.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mytrip.qa.base.BaseTest;
import com.mytrip.qa.datautil.TestUtil;

public class HomePage1 extends BaseTest{
	
	@FindBy(xpath="//li[@data-cy='account']")
	WebElement CreateAccountLink;
	
	@FindBy(xpath ="//ul[@class='fswTabs latoBlack greyText']/li[2]")
	WebElement RoundTripButton;
	
	@FindBy(xpath ="//div[@class='fsw_inputBox searchCity inactiveWidget ']")
	WebElement From;
	
	@FindBy(xpath ="//input[@placeholder='From']")
	WebElement FromTextbox;
	
	@FindBy(xpath ="//div[text()='DEL']")
	public WebElement SelectFromCity;
	
	@FindBy(xpath ="//div[@class='fsw_inputBox searchToCity inactiveWidget ']")
	WebElement To;
	
	@FindBy(xpath ="//input[@placeholder='To']")
	WebElement ToTextbox;
	
	@FindBy(xpath ="//div[text()='BLR']")
	public WebElement SelectToCity;
	
	@FindBy(xpath ="//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")
	WebElement SearchButton;
	
	@FindBy(xpath ="//p[@data-cy='departureDate']")
	public WebElement DepartureDate_xpath;
	
	@FindBy(xpath ="//p[@data-cy='returnDate']")
	public WebElement ReturnDate_xpath;
	
	@FindBy(xpath ="//input[@id='fromCity']")
	public WebElement FromCityLabel;
	
	@FindBy(xpath ="//*[contains(@title,'BLR')]")
	public WebElement ToCityLabel;
	
	public static FlightsPage obj=null;
	//Initializing the object
		public HomePage1(){
			PageFactory.initElements(driver, this);
		 }
		
		//Actions (or) Features
		public String validateHomePageTitle(){
			return driver.getTitle();
		}
		public void CreateAccountLink() throws InterruptedException {
			CreateAccountLink.click();
			Thread.sleep(2000);
		}
		public boolean TripSelect() {
			RoundTripButton.click();
			return RoundTripButton.isDisplayed();
		}
		public void FromCity() throws InterruptedException {
			From.click();
			FromTextbox.sendKeys(prop.getProperty("DepartureCity"));
			Thread.sleep(2000);
			SelectFromCity.click();
		}
		public void ToCity() throws InterruptedException {
			To.click(); 
			ToTextbox.sendKeys(prop.getProperty("ReturnCity"));
	    	Thread.sleep(3000);
	    	SelectToCity.click(); 
		}
		public void DataPicker() throws ParseException, InterruptedException {
			TestUtil util = new TestUtil();
			util.DataToBeSelected(); 
		}
		
		public void Search() throws InterruptedException {
			SearchButton.click();
	    	driver.manage().deleteAllCookies();
	    	Thread.sleep(6000);	
		}
		public FlightsPage NavigateToFlightsPage(){
			return HomePage1.obj = new  FlightsPage();
		}
}
