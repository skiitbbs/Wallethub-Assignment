package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Lib.ExcelDataConfig;
	
	//I deliberately made LightAccount because if tomorrow you required to add light account by code you can do this also. Including email verification.
	// But for this Assignment we will run Login Method only. If you want to create light account then you have to give credential accordingly at Excel. 

public class LightAccountPage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
		
	public LightAccountPage(WebDriver driver1){
		
		//Constructor is made to make sure communication between driver(TestCasesSuite) and LightAccountPage happen smoothly.
		
		driver=driver1;
		wait= new WebDriverWait(driver, 60);
		 
	}
	
	//Importing all the required data from excel sheet and storing in a variable. So that it can be reuse where ever Automation required for Login or Creating User(s).
	
	ExcelDataConfig excel = new ExcelDataConfig("//home//sumit//workspace//ASSIGNMENT//Testexcel.xlsx");	
	String emailID = excel.getData(1, 1, 0);
	String password = excel.getData(1, 1, 1);
	String firstName = excel.getData(1, 1, 3);
	String lastName = excel.getData(1, 1, 4);
	String userName = excel.getData(1, 1, 5);
	
	
	//String url for again reusing the data. This will execute only if you want to create Light account @WalletHub.
	
	String url1 = "https://www.google.com/gmail/";
	String url2 = "http://wallethub.com/profile/test_insurance_company/";
	
	
	public void logIn(){
		
		// This method is going to log in into WalletHub Account. According to Assignment we will run this part only.
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[1]/input")).sendKeys(emailID);
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[2]/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[5]/button[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='burger-menu menu-loggedIn']")));
	}
	
	public void createLightAccount(){
		
		//This method is used for Creating a light User Account in WalletHub. This is BONUS task done by me to create account at WalletHub.
		
		System.out.println("User Creations process has been Started");
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id='join-light']/form/div/div[1]/input")).sendKeys(emailID); 	
		driver.findElement(By.xpath("//*[@id='join-light']/form/div/div[2]/input")).sendKeys(password); 
		driver.findElement(By.xpath("//*[@id='join-light']/form/div/div[3]/input")).sendKeys(password); 
		driver.findElement(By.xpath("//*[@id='join-light']/form/div/div[4]/label/span")).click(); 		
 		driver.findElement(By.xpath("//*[@id='join-light']/form/div/div[5]/button/span/span")).click(); //Submit button has been clicked here.
 		
 		System.out.println("User should verify a link thourgh mail which is send to user's given email account.");
 		
	} 	// User need to "Verify Link" from Email account. So below Method is for verifying mail from Email Account. 
	
	
	
	public void loginGmail(){
		
		//This whole method contain Login into Gmail, Verify the link. Again login into WalletHub Account and confirm the account at WalletHub Domain.
		//Whole things will go in one go. If we want then we can separate the things also. But I have done here in one go.
		
		System.out.println("User is getting login into email account.");
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				
		driver.get(url1);
		
		driver.findElement(By.id("identifierId")).sendKeys(emailID);
		driver.findElement(By.xpath("//*[@id='identifierNext']/content/span")).click(); 		
		driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")).click();
		
		
		System.out.println("User successfully logged in.");
		
		driver.findElement(By.id("gbqfq")).sendKeys(excel.getData(1, 1, 2)); //Email Subject line gets entered here through excel.
		driver.findElement(By.xpath("//*[@id='gbqfb']/span")).click();
		driver.findElement(By.xpath("//*[@id=':2']/div/div[2]/div[5]/div[1]")).click();
		
		String winHandleBefore = driver.getWindowHandle(); //Storing the current Window.
		
		driver.findElement(By.xpath("//a[contains(@href,'https://wallethub.com/join/confirm')]")).click(); //email Link has been clicked.
		
		System.out.println("Verify link has been clicked now.");
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='join-login']/form/div[1]/input")));
		//Since it is switching the window Explicit Wait is perfect example for this. Thread will wait till it will get the given elements on the page.
				
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[1]/input")).sendKeys(emailID);
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[2]/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='join-login']/form/div[5]/button[2]")).click();
		
		//User need to Confirm them self by giving their First, Last and User Name. Below code is doing the same. 
		
		driver.findElement(By.xpath("//*[@id='join-ppc']/form/div/button")).click();
		driver.findElement(By.xpath("//*[@id='join-ppc']/form/div[1]/input")).sendKeys(firstName);
		driver.findElement(By.xpath("//*[@id='join-ppc']/form/div[2]/input")).sendKeys(lastName);
		driver.findElement(By.xpath("//*[@id='join-ppc']/form/div[3]/input")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id='join-ppc']/form/div[5]/button")).click();
		driver.close();
		
		driver.switchTo().window(winHandleBefore);  //Window is switching to previously handled page.
		
		driver.get(url2);
		
		System.out.println("User has been created successfully");
		
	}

}
