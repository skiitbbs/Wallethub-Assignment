package TestCaseSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.FacebookLoginPage;
import Pages.LightAccountPage;
import Pages.ProfilePage;

public class AssignementTestCase {
	
	
	
	
     	@BeforeTest
     	private WebDriver getBrowser() 
     	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","//home//sumit//Downloads//chromedriver");
		driver= new ChromeDriver(); // Create a new instance for the Chrome Driver.
		driver.manage().window().maximize();
		return driver;
	     }
	
	
     	@Test
     	public void VerifyUserIsAbleToPostMessageOnFaceboook(){
		
     		//this is the first test case which will login into facebook and post the status.
     		
		WebDriver driver;
		driver = getBrowser();
		
		String url = "https://www.facebook.com/";
		driver.get(url); 
		
		
		FacebookLoginPage fb = new FacebookLoginPage(driver);
		
		
		fb.login();
		fb.writePost();
		
		System.out.println("User has beeen succcessfuly Logged In");
		System.out.println("Hello World there");
		
		driver.quit();
	
		
		
	}

	
     	@Test
     	public void  VerifyUserIsAbleToReviewAndRate()
		{
		  WebDriver driver;
			
			driver = getBrowser();
			
			LightAccountPage lightAccountPage = new LightAccountPage(driver);
			String url1 = "https://wallethub.com/join/login";
			
			/*
			String url1 = "https://wallethub.com/join/light";
			
			If you want to create Light user then instead of given url, give the above url and comment out the login url.
			
			*/
			
			driver.get(url1); 
			
			lightAccountPage.logIn();
	
			/*
			lightAccountPage.createLightAccount();
			lightAccountPage.loginGmail();
			
			If you want to create Light Account user then comment out above two method and comment the login method. */
			
			
			String url = "https://wallethub.com/profile/test_insurance_company/activity/";
			driver.get(url); 
			
			//this testcase will run for giving Rating and reviews and after Submit verify by AssertTrue condition. 
			
			ProfilePage profilePage = new ProfilePage(driver);
			profilePage.reviewForm();
			
			Assert.assertTrue(profilePage.verifyRating());	 //Using the Assert true function to verifying Reviews and Rating. 
			driver.quit();
			
		  
			
		}
	
	
}
