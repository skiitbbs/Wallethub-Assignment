package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Lib.ExcelDataConfig;

public class ProfilePage {
	
public WebDriver driver;
public WebDriverWait wait;


	public ProfilePage(WebDriver driver2){
		
		//Constructor is made to make sure communication between driver(TestCases) and ProfilePages happen smoothly.
		
		driver=driver2;
		wait= new WebDriverWait(driver,100);
		
	}
		
		//importing the data from excel and strong in globle variable for reuse whenever and where ever required.
	
		ExcelDataConfig excel = new ExcelDataConfig("//home//sumit//workspace//ASSIGNMENT//Testexcel.xlsx");
		String reviewText = excel.getData(2, 0, 0);
		String value = excel.getData(2, 0, 0);
	
		
	public void reviewForm(){
		
		//This method is going to use for selecting the star by mouse hovering, selecting Health from dropdown, writing reviews and submitting the same.
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		WebElement ratingStar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/span")));
		//Thread will wait till it finds the given elements.
		
		
		//This statements are here to do the mouse hovering on Star.
		Actions hover = new Actions(driver);
		hover.moveToElement(ratingStar).moveToElement(driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div"))).build().perform();
		System.out.println("Hover over the Start is done.");
		hover.moveToElement(driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div/a[4]"))).click().build().perform();
		
		
		System.out.println("4 Start is clicked after hovering.");
		
		
		System.out.println("Now User is about to submit the Review");
		//After clicking on Star, Page redirect to submit review form. So User is at "Review Form" page and performing the work.
		
		
		driver.findElement(By.xpath("//*[@id='reviewform']/div[1]/div/div/span[2]")).click();
		driver.findElement(By.xpath("//*[@id='reviewform']/div[1]/div/ul/li[2]/a")).click();
		
		//I used Thread.sleep Method just for demo purpose. it is not recommended to use. It literally sleep without any condition for given period of time.  
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.getMessage();
		}
		
		driver.findElement(By.xpath("//*[@id='overallRating']/a[4]")).click(); 
		driver.findElement(By.xpath("//*[@id='review-content']")).sendKeys(reviewText);
		driver.findElement(By.xpath("//*[@id='reviewform']/div[3]/div[2]/input")).click();
		
		
		System.out.println("Review has been submitted successfully.");
		
		//If printing this line means User has successfully sublimtted the review.	
	}
	
	public boolean verifyRating(){
		
		//This method is going to use for verifying whether Given Review has been Submitted or not?
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		
		driver.findElement(By.xpath("//*[@id='viewport']/header/div/nav[3]/a[3]")).click();
		driver.findElement(By.xpath("//*[@id='m-user']/ul/li[4]/a")).click();
		driver.findElement(By.xpath("//*[@id='wh-body-inner']/div[1]/div[1]/div[2]/ul/li[2]/a")).click();
		
						
		if(driver.getPageSource().contains(reviewText))
		{
			System.out.println("Given review is present on the page. Test Case: PASS");
		    return true;
		    //If this statements are executing which means Given Review and Ratings are PRESENT on the Webpage.
		}

		
		else
		{
			System.out.println("Given review is not present on the page. Test Case: Fail");
			return false;
			//If this statements are executing which means Given Review and Ratings are NOT PRESENT on the Webpage.
		}
	}

}
