package com.mytrip.qa.testcases;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mytrip.qa.base.BaseTest;
import com.mytrip.qa.datautil.TestData;
import com.mytrip.qa.datautil.TestUtil;
import com.mytrip.qa.pages.FlightsPage;
import com.mytrip.qa.pages.HomePage;

public class FlightsPageTest extends BaseTest{
	
	HomePage homepage;
	FlightsPage flightspage;
	TestUtil util;
	
	public FlightsPageTest(){
	    	 super();
	       }
	  @BeforeTest
	   public void setup() throws InterruptedException, ParseException {
		  intialization();
		  flightspage = new FlightsPage();
		  homepage = new HomePage();
		  flightspage = homepage.Search(); 
		  util = new TestUtil();
		        }
	  @Test(priority = 1)
	  public void ValidateFlightsPageTitleTest() {
		 String flightspagetilte= flightspage.ValidateFlightsPageTitle();
		 System.out.println("flight pg title ..."+flightspagetilte);
		 AssertJUnit.assertEquals("flightpage title not found", flightspagetilte, "MakeMyTrip");
		  }

	  @Test(priority = 2)
      public void NumberOfFlightsTest() throws InterruptedException {
		 Assert.assertTrue(flightspage.NumberOfFlights());
		  
	  }
	  //@Test(priority = 3)
	  public void OneStopFlightsTest() throws InterruptedException {
		  System.out.println("********  One-Stop option is selected  *********");
		  Assert.assertTrue(flightspage.OneStopFlights());
	  }
	  //@Test(priority = 3)
	  public void NonStopFlightsTest() throws InterruptedException {
		  System.out.println("********** Non-Stop option is selected **********");
		  Assert.assertTrue(flightspage.NonStopFlights());
	  }
	  @Test(priority = 3,dataProviderClass = TestData.class, dataProvider = "getData")
	  public void FlightsPriceCheckTest(int depaInx, int ArrivalIdx) throws InterruptedException {
		  flightspage.FlightsPriceCheck(depaInx,ArrivalIdx);
	      
	  }
	  @AfterTest
	  public void quit() {
		  //driver.close();
	  }
}
