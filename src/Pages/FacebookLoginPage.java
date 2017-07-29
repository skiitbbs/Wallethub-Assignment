package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Lib.ExcelDataConfig;


public class FacebookLoginPage {
	
	public WebDriver fbDriver;
	
	public FacebookLoginPage(WebDriver driver)
	{
		//Constructor is made to make sure communication between driver(TestCasesSuite) and FacebookLoginPage happen smoothly.
		
		fbDriver=driver;

	}
	
	//Importing all the required data from excel sheet and storing in a variable. So that it can be reuse where ever Automation required for Login User(s).
	
	ExcelDataConfig excel = new ExcelDataConfig("//home//sumit//workspace//ASSIGNMENT//Testexcel.xlsx");
	String email = excel.getData(0, 1, 0);
	String pass = excel.getData(0, 1, 1);
	
	public void login(){
	
	//Facebook Login Starts here. Importing credentials from excel.
		
	fbDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	//Driver will wait up to 60 second to find the elements. (Eg: if element available within 5 sec then program will execute without wait up to 60 Sec.)
	
	fbDriver.findElement(By.id("email")).sendKeys(email);
	fbDriver.findElement(By.id("pass")).sendKeys(pass);
	fbDriver.findElement(By.id("loginbutton")).click();
	
	
	System.out.println("User got logged in Successfully.");
	
	}
	
	public void writePost(){
	
	//User is going to 'News Feed' and then start writing Status. After status entry user click on Post. 	
	
	fbDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	
	fbDriver.findElement(By.xpath("//*[@id='navItem_4748854339']/a/div")).click();
	fbDriver.findElement(By.xpath("//textarea[@name='xhpc_message']")).sendKeys("Hello World There..!!");
	fbDriver.findElement(By.xpath("//span[text()='Post']")).click();
	
	
	}
}

