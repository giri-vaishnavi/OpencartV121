package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.MyaccountPage;
import utilities.DataProviders;

/* Data is valid = login success - test pass = logout
 * Data is valid = login failed- test failed
 * 
 * Data is invalid = login success = test fail = logout
 * Data is invalid = login failed - test pass
 */
public class TC003_loginDDT extends BaseClass {
   
	@Test (dataProvider ="LoginData", dataProviderClass =DataProviders.class , groups= {"Datadriven","Master"}) //getting dta prvro
	public void verfiy_loginDDT(String email, String pwd , String exp) {
		
		logger.info("----starting TC003_loginDDT -----"  );
		try {
		System.out.println("Testing with: Email = " + email + " | Password = " + pwd + " | Expected = " + exp);
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		//login
		Login  lp= new Login(driver);
		lp.setEmail(email);
		lp.setpwd(pwd);
		lp.clickLogin();
		
		//MyAccount 
		MyaccountPage macc = new MyaccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		if (exp.equalsIgnoreCase("Valid")) 
		{
			if (targetPage == true) 
			{
				Assert.assertTrue(true);
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a[@class='list-group-item' and normalize-space()='Logout']")));
				macc.clickLogout();
			} 
			else 
			{
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("invalid")) 
		{
			if (targetPage == true) 
			{   WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a[@class='list-group-item' and normalize-space()='Logout']")));
				macc.clickLogout();
				Assert.assertTrue(false);
			} 
			else 
			{
				Assert.assertTrue(true);
			}
			
		}
		
	}catch(Exception e)
		{
		e.printStackTrace(); // Add this
		Assert.fail();
	}
	logger.info("----Finished TC003_loginDDT------");
	}
	}
		
		
		
		
	
	
