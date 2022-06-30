package com.mytrip.qa.pages;
import java.text.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mytrip.qa.base.BaseTest;
import com.mytrip.qa.datautil.TestUtil;

public class HomePage extends BaseTest{

	@FindBy(xpath="//li[@data-cy='account']")
	WebElement CreateAccountLink;
	
	@FindBy(xpath ="//ul[@class='fswTabs latoBlack greyText']/li[2]")
	public WebElement RoundTripButton;
	
	@FindBy(xpath ="//div[@class='fsw_inputBox searchCity inactiveWidget ']")
	public WebElement From;
	
	@FindBy(xpath ="//input[@placeholder='From']")
	public WebElement FromTextbox;
	
	@FindBy(xpath ="//div[text()='DEL']")
	public WebElement SelectFromCity;
	
	@FindBy(xpath ="//div[@class='fsw_inputBox searchToCity inactiveWidget ']")
	WebElement To;
	
	@FindBy(xpath ="//input[@placeholder='To']")
	public WebElement ToTextbox;
	
	@FindBy(xpath ="//div[text()='BLR']")
	public WebElement SelectToCity;
	
	@FindBy(xpath ="//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")
	WebElement SearchButton;
	
	@FindBy(xpath ="//p[@data-cy='departureDate']")
	public WebElement DepartureDate_xpath;
	
	@FindBy(xpath ="//p[@data-cy='returnDate']")
	public WebElement ReturnDate_xpath;
	
	@FindBy(xpath ="//*[contains(@title,'Delhi')]")
	public WebElement FromCityLabel;
	
	@FindBy(xpath ="//*[contains(@title,'BLR')]")
	public WebElement ToCityLabel;
	
	//Initializing the object
	public HomePage(){
				PageFactory.initElements(driver, this);
			 }
			
			//Actions (or) Features
	public String ValidateHomePageTitle(){
				return driver.getTitle();
			}
	
    public FlightsPage Search() throws InterruptedException, ParseException {
    	
		CreateAccountLink.click();
		Thread.sleep(2000);
		
		RoundTripButton.click();
	    RoundTripButton.isDisplayed();
	
		From.click();
		FromTextbox.sendKeys(prop.getProperty("DepartureCity"));
		Thread.sleep(2000);
		SelectFromCity.click();
	
		To.click(); 
		ToTextbox.sendKeys(prop.getProperty("ReturnCity"));
    	Thread.sleep(3000);
    	SelectToCity.click(); 

		TestUtil util = new TestUtil();
		util.DataToBeSelected(); 
	
	
		SearchButton.click();
    	driver.manage().deleteAllCookies();
    	Thread.sleep(6000);
    	
		return new FlightsPage();	
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	static List<WebElement> DepFilghtCount,ArrFilghtCount;
	

	
	public static void SelectDepartureFlight(int Flight_Index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int count = 0;
		if (DepFilghtCount.size() > Flight_Index && Flight_Index > 0) {
			for (WebElement e : DepFilghtCount) {
				if (count == Flight_Index) {
					js.executeScript("arguments[0].click();", e);
					break;
				                 }
				    count++;
				             }
	                   }
                 }	
	public static void SelectArrivalFlight(int Flight_Index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int count = 0;
		if (ArrFilghtCount.size() > Flight_Index && Flight_Index > 0) {
			for (WebElement e : ArrFilghtCount) {
				if (count == Flight_Index) {
					js.executeScript("arguments[0].click();", e);
					break;
				                 }
				    count++;
				             }
	                   }
	          }
*/






