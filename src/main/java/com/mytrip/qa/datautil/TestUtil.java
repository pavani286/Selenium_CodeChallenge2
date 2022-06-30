package com.mytrip.qa.datautil;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mytrip.qa.base.BaseTest;
import com.mytrip.qa.pages.FlightsPage;
public class TestUtil extends BaseTest {

	static Date d1,d2,d3,d4;
	public String DepartureDt,ReturnDt,Dept,Ret;
    FlightsPage flightspage;
    public int  New_Dept_Fare,New_Return_Fare,New_TotalFare_bottom;
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	public void DataToBeSelected() throws ParseException, InterruptedException {
		SimpleDateFormat FormatDate = new SimpleDateFormat("dd MMM");
        DepartureDt =prop.getProperty("DepartureDate");
   	    ReturnDt =prop.getProperty("ReturnDate");
   	    d1 = FormatDate.parse(DepartureDt);
   	    d2 = FormatDate.parse(ReturnDt);
      	System.out.println("The departure date is ... " + FormatDate.format(d1));
        System.out.println("The Return date is ... " + FormatDate.format(d2));
        
        SimpleDateFormat FormatDate1 = new SimpleDateFormat("MMM dd");
        Dept = prop.getProperty("DeptDt");
        Ret = prop.getProperty("RetDt");
        d3 = FormatDate1.parse(Dept);
        d4 = FormatDate1.parse(Ret);
        System.out.println("The departure date 1 is ... " + FormatDate1.format(d3));
        System.out.println("The Return date 1 is ... " + FormatDate1.format(d4));
    	if(Differences(d3,d4)>=7) {
    	driver.findElement(By.xpath("//div[contains(@aria-label,'"+Dept+"')]")).click();
    	driver.findElement(By.xpath("//div[contains(@aria-label,'"+Ret+"')]")).click();

        }else {
          System.out.println("This Method is restericated to accept 7 days difference between"
                             + " departure and Arrival date. Please check Given Date again. "
                              + "!!Contact Git Admin for more info");

        }

	}
public static int Differences(Date one, Date two) {
		
		int difference = (int) (two.getTime() - one.getTime());
	    int daysBetween = (int) (difference / (1000*60*60*24));
	    System.out.println("Number of Days between dates: "+daysBetween);
		return daysBetween;
		
	                }
   public void ScrollDown() throws InterruptedException {
   	Actions act = new Actions(driver);
   	WebElement ScrollBar = driver.findElement(By.xpath("//html[@lang='en']"));
		for (int i = 0; i <= 30; i++){
             act.moveToElement(ScrollBar).sendKeys(Keys.PAGE_DOWN).build().perform(); //Page Down
             Thread.sleep(1000);  
		              }
		
       
       
	                                               }
	public void ScrollUp() throws InterruptedException {
   	Actions act1 = new Actions(driver);
   	WebElement ScrollBar = driver.findElement(By.xpath("//html[@lang='en']"));
		for (int i = 0; i <= 26; i++){
             act1.moveToElement(ScrollBar).sendKeys(Keys.PAGE_UP).build().perform(); //Page Up
             Thread.sleep(1000);  
   	               }
                                        }
	
	public  void SelectDepartureFlight(int Flight_Index) {
		flightspage = new  FlightsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int count = 0;
		if (flightspage.DepFilghtCount.size() > Flight_Index && Flight_Index > 0) {
			for (WebElement e : flightspage.DepFilghtCount) {
				if (count == Flight_Index) {
					js.executeScript("arguments[0].click();", e);
					break;
				                 }
				    count++;
				             }
	                   }
                 }	
	public void SelectArrivalFlight(int Flight_Index) {
		flightspage = new  FlightsPage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int count = 0;
		if (flightspage.ArrFilghtCount.size() > Flight_Index && Flight_Index > 0) {
			for (WebElement e :flightspage.ArrFilghtCount) {
				if (count == Flight_Index) {
					js.executeScript("arguments[0].click();", e);
					break;
				                 }
				    count++;
				             }
	                   }
	          }
	public void ConvertToIntPrice(String Dept_Fare,String Ret_Fare) {
		
		Dept_Fare = Dept_Fare.replaceAll("[^a-zA-Z0-9]","");
		Ret_Fare =  Ret_Fare.replaceAll("[^a-zA-Z0-9]","");
        New_Dept_Fare = Integer.parseInt(Dept_Fare);
	    New_Return_Fare = Integer.parseInt(Ret_Fare);
	}
	
}
