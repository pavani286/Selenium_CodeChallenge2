package com.mytrip.qa.testcases;

import java.text.ParseException;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mytrip.qa.base.BaseTest;
import com.mytrip.qa.pages.FlightsPage;
import com.mytrip.qa.pages.HomePage;

public class HomePageTest extends BaseTest {
	HomePage homepage;
	FlightsPage flightspage;
	
  public HomePageTest(){
    	 super();
       }
  @BeforeTest
   public void setup() throws InterruptedException {
	  intialization();
	  homepage = new HomePage();
	        }
  
  @Test(priority=1)
  public void ValidateHomePageTitleTest() {
	 String homepagetilte= homepage.ValidateHomePageTitle();
	 System.out.println("home pg title ..."+homepagetilte);
	 AssertJUnit.assertEquals("homepage title not found", homepagetilte, "MakeMyTrip USA - #1 Travel Website for Flight Booking, Airline Tickets");
	  }
  
  @Test(priority=2)
	public void SearchTest() throws InterruptedException, ParseException  {
	  homepage.Search();
	/*  
	  String ExpectedCityText = homepage.FromCityLabel.getAttribute("value");
      Assert.assertEquals(prop.getProperty("DepartureCity"),ExpectedCityText);
    
      String ExpectedCityText1 = homepage.ToCityLabel.getAttribute("title");
  	//'BLR, Kempegowda International Airport India
  	  String[] arrOfStr = ExpectedCityText1.split(",");
  	  Assert.assertEquals(prop.getProperty("ReturnCity"),arrOfStr[0]);
	  
  	  String DepartureDate_Text = homepage.DepartureDate_xpath.getText();
	   Assert.assertEquals(prop.getProperty("DepartureDate"), DepartureDate_Text);
	   
	   String ReturnDate_Text = homepage.ReturnDate_xpath.getText();
	   Assert.assertEquals(prop.getProperty("ReturnDate"), ReturnDate_Text);
	  */
	}
  @AfterTest
  public void quit() {
	  driver.close();
  }
}





