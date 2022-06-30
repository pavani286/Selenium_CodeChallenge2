package com.mytrip.qa.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mytrip.qa.base.BaseTest;
import com.mytrip.qa.datautil.TestUtil;

public class FlightsPage extends BaseTest {
	
	@FindBy(xpath="//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label")
	public  List<WebElement> DepFilghtCount;
	
	@FindBy(xpath="//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label")
	public  List<WebElement> ArrFilghtCount;
	
	@FindBy(xpath="//p[contains(text(),'New Delhi')]/parent::div/child::div//span[text()='1 Stop']")
	WebElement OneStop_DepCheckbox ;
	
	@FindBy(xpath="//p[contains(text(),'Bengaluru')]/parent::div/child::div//span[text()='1 Stop']")
	WebElement OneStop_RetCheckbox ;
	
	@FindBy(xpath="//p[contains(text(),'New Delhi')]/parent::div/child::div//span[text()='Non Stop']")
	WebElement NonStop_DeptCheckbox;
	
	@FindBy(xpath="//p[contains(text(),'Bengaluru')]/parent::div/child::div//span[text()='Non Stop']")
	WebElement NonStop_RetCheckbox;
	
	@FindBy(xpath="//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label/descendant::div[@class='makeFlex column relative splitfare ']/p")
	List<WebElement> Price_Left;
	
	@FindBy(xpath="//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label/descendant::div[@class='makeFlex column relative splitfare ']/p")
	List<WebElement> Price_Right;
	
	@FindBy(xpath="//div[@class='splitviewSticky makeFlex']//child::div[1]/p[contains(text(),'Departure')]//parent::div//following-sibling::div/span")
	WebElement Departure_Fare;


	@FindBy(xpath="//div[@class='splitviewSticky makeFlex']//child::div[2]/p[contains(text(),'Return')]//parent::div//following-sibling::div/span")
	WebElement Return_Fare;
	
	@FindBy(xpath="//div[@class='textRight appendRight10']/p/span")
	public WebElement TotalFare_bottom;
	
    public String Dept_Fare,Ret_Fare,TotalFare_NewAtBottom;
    public int Total;
	TestUtil util;
//Initializing the object
	public FlightsPage(){
	  PageFactory.initElements(driver, this);
	}
			
//Actions (or) Features
	public String ValidateFlightsPageTitle(){
	  return driver.getTitle();
	}
	public boolean NumberOfFlights() throws InterruptedException {
	  util = new TestUtil();
	  util.ScrollDown();
	  System.out.println("No.of Departure Flights avialable ..."+DepFilghtCount.size());
	  System.out.println("No.of Arrival Flights avialable ..."+ArrFilghtCount.size());
	  return true;
	}
	public boolean OneStopFlights() throws InterruptedException {
	   	util.ScrollUp();
	   	OneStop_DepCheckbox .click();
	   	Thread.sleep(2000);
	   	OneStop_RetCheckbox.click();
	   	util.ScrollDown();
	   	System.out.println("No.of Departure Flights avialable after One_Stop chechbox Selected ..."+DepFilghtCount.size());
	    System.out.println("No.of Arrival Flights avialable after One_Stop chechbox Selected  ..."+ArrFilghtCount.size());
		return OneStop_DepCheckbox.isSelected();
	}
	
	public boolean NonStopFlights() throws InterruptedException {
		util.ScrollUp();
		NonStop_DeptCheckbox.click();
		Thread.sleep(2000);
		NonStop_RetCheckbox.click();
		util.ScrollDown();
	   	System.out.println("No.of Departure Flights avialable after One_Stop chechbox Selected ..."+DepFilghtCount.size());
	    System.out.println("No.of Arrival Flights avialable after One_Stop chechbox Selected  ..."+ArrFilghtCount.size());
		return NonStop_DeptCheckbox.isSelected();
	}

	public void FlightsPriceCheck(int depaInx, int ArrivalIdx) throws InterruptedException {
		util = new TestUtil();
		util.ScrollUp();
		util.SelectDepartureFlight(depaInx);
		Thread.sleep(3000);    	
	
        if(Price_Left.get(depaInx).getText().equalsIgnoreCase(Departure_Fare.getText())) {
        	System.out.println("Prices of Departureflight's are Equal ....");
                      }
        Dept_Fare = Price_Left.get(depaInx).getText();
        util.SelectArrivalFlight(ArrivalIdx);  
        Thread.sleep(1000);
        Return_Fare.getText();
	    		
	    if(Price_Right.get(ArrivalIdx).getText().equalsIgnoreCase(Return_Fare.getText())) {
        	System.out.println("Prices of Returnflight's are Equal ....");
	           }
	    Ret_Fare = Price_Right.get(ArrivalIdx).getText();
	    
		TotalFare_NewAtBottom = TotalFare_bottom.getText();
		TotalFare_NewAtBottom = TotalFare_NewAtBottom.replaceAll("[^a-zA-Z0-9]","");
		System.out.println("TotalFarePriceCheck Price is  ..."+TotalFare_NewAtBottom);
		util.ConvertToIntPrice(Dept_Fare, Ret_Fare);
	    Total = util.New_Dept_Fare + util.New_Return_Fare;
		System.out.println(Total);
		  
	}  
	
}
