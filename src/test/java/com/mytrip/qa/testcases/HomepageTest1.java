package com.mytrip.qa.testcases;

import java.text.ParseException;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.mytrip.qa.base.BaseTest;
import com.mytrip.qa.pages.FlightsPage;
import com.mytrip.qa.pages.HomePage1;

public class HomepageTest1 extends BaseTest{
	HomePage1 homepage;
	FlightsPage flightspage;
public HomepageTest1(){
    	 super();
       }
  @BeforeTest
   public void setup() throws InterruptedException {
	  intialization();
	  homepage = new HomePage1();
	        }
  @Test(priority = 1)
  public void ValidateHomePageTitleTest() {
	 String homepagetilte= homepage.validateHomePageTitle();
	 System.out.println("home pg title ..."+homepagetilte);
	 AssertJUnit.assertEquals("homepage title not found", homepagetilte, "MakeMyTrip USA - #1 Travel Website for Flight Booking, Airline Tickets");
	  }
  @Test(priority =2,dependsOnMethods="ValidateHomePageTitleTest")
  public void CreateAccountLinkTest() throws InterruptedException {
	  homepage.CreateAccountLink();
    }
  @Test(priority =3,dependsOnMethods="CreateAccountLinkTest")
	public void TripSelectTest() {
	     Assert.assertTrue(homepage.TripSelect());    
	}
  @Test(priority =4,dependsOnMethods="TripSelectTest")
	public void FromCityTest() throws InterruptedException  {
	  homepage.FromCity();
	  String ExpectedCityText = homepage.FromCityLabel.getAttribute("value");
      Assert.assertEquals(prop.getProperty("DepartureCity"),ExpectedCityText);
      
	}
   
	@Test(priority =5,dependsOnMethods="FromCityTest")
	public void ToCityTest() throws InterruptedException {
		
    	homepage.ToCity();
    	String ExpectedCityText1 = homepage.ToCityLabel.getAttribute("title");
    	//'BLR, Kempegowda International Airport India
    	String[] arrOfStr = ExpectedCityText1.split(",");
    	Assert.assertEquals(prop.getProperty("ReturnCity"),arrOfStr[0]);
	}
	
	@Test(priority =6,dependsOnMethods="ToCityTest")
	public void DataPickerTest() throws ParseException, InterruptedException {
	   homepage.DataPicker();
	   String DepartureDate_Text = homepage.DepartureDate_xpath.getText();
	   Assert.assertEquals(prop.getProperty("DepartureDate"), DepartureDate_Text);
	   
	   String ReturnDate_Text = homepage.ReturnDate_xpath.getText();
	   Assert.assertEquals(prop.getProperty("ReturnDate"), ReturnDate_Text);
		  
	}
	@Test(priority =7,dependsOnMethods="DataPickerTest")
	public void SearchTest() throws InterruptedException  {
		homepage.Search();
	}
	@Test(priority =8,dependsOnMethods="SearchTest")
	public void NavigateToFlightsPageTest(){
		flightspage = homepage.NavigateToFlightsPage();
	}
	
	/*
	public static void One_Stop() throws InterruptedException{
		//1-stop
		ScrollUp();
        System.out.println("********  One-Stop option is selected  *********");
        driver.findElement(By.xpath("//p[contains(text(),'New Delhi')]/parent::div/child::div//span[text()='1 Stop']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//p[contains(text(),'Bengaluru')]/parent::div/child::div//span[text()='1 Stop']")).click();
        ScrollDown();
        DepFilghtCount = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label"));
        System.out.println("No.of Departure Flights avialable ....."+ DepFilghtCount.size());
        
	    ArrFilghtCount  = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label"));
        System.out.println("No.of Return Flights avialable ....."+ ArrFilghtCount.size());
        
	                }
	
	public void Non_Stop() throws InterruptedException {
		System.out.println("********** Non-Stop option is selected **********");
        //non-stop
		ScrollUp();
        driver.findElement(By.xpath("//p[contains(text(),'New Delhi')]/parent::div/child::div//span[text()='Non Stop']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//p[contains(text(),'Bengaluru')]/parent::div/child::div//span[text()='Non Stop']")).click();
        
        ScrollDown();
        DepFilghtCount = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label"));
        System.out.println("No.of Departure Flights avialable ....."+ DepFilghtCount.size());
        
	    ArrFilghtCount  = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label"));
        System.out.println("No.of Return Flights avialable ....."+ ArrFilghtCount.size());
      
	          }
   @Test(priority=5,dependsOnMethods ="ReturnFilghtInfo", dataProviderClass = TestData.class, dataProvider = "getData")
	
   public static void FarePriceCheck(int depaInx, int ArrivalIdx) throws InterruptedException {
	  
		ScrollUp();
		SelectDepartureFlight(depaInx);
		Thread.sleep(1000);    
		
        List<WebElement> Price_Left = driver.findElements(By.xpath(""
        		+ "//div[@class='splitVw']/child::div[1]/child::div[2]/div/div"
        		+ "/label/descendant::div[@class='makeFlex column relative']/p"));
        		
        String Departure_Fare = driver.findElement(By.xpath("//div[@class='splitviewSticky makeFlex']//child::div[1]"
        		                                 + "/p[contains(text(),'Departure')]"
        		                                 + " //parent::div//following-sibling::div/span")).getText();
        		                                 
        if(Price_Left.get(depaInx).getText().equalsIgnoreCase(Departure_Fare)) {
        	System.out.println("Prices of Departureflight's are Equal ....");
                      }
        
        SelectArrivalFlight(ArrivalIdx);  
        Thread.sleep(1000);
        
	    List<WebElement> Price_Right = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/"
	    		+ "label/descendant::div[@class='makeFlex column relative']/p"));
	    		
	    String Return_Fare = driver.findElement(By.xpath("//div[@class='splitviewSticky makeFlex']//child::div[2]"
	    		+ "/p[contains(text(),'Return')]//parent::div//following-sibling::div/span")).getText();
	    		
	    if(Price_Right.get(ArrivalIdx).getText().equalsIgnoreCase(Return_Fare)) {
        	System.out.println("Prices of Returnflight's are Equal ....");
                      }
	}
   @Test(priority=6,dependsOnMethods ="FarePriceCheck")
   public static void TotalFarePriceCheck() {
	  String TotalFare_bottom = driver.findElement(By.xpath("//div[@class='textRight appendRight10']/p/span")).getText();
	  System.out.println("TotalFarePriceCheck string ..."+TotalFare_bottom);
       }
       */
	
	@AfterTest
    public void quit() {
	   //driver.quit();
	}
	
	
	/*
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
	
}





