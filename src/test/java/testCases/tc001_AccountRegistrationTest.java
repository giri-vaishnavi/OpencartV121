package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class tc001_AccountRegistrationTest extends TestBase.BaseClass{

  @Test (groups = {"Sanity", "Regression", "Master"})
  public void verif_account_registration() throws InterruptedException {
		
	  logger.info("-----Startin TC001_AccountRegistraionTest----");
	  try {
		  
	  
	  HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on Myaccount link" );
		hp.clickRegister();
		logger.info("clicked registration link");
		
		
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	    
		
		logger.info("Providing customer details");
		regpage.setFirstName(randomString().toUpperCase());
        regpage.setLastName(randomString().toUpperCase());
        regpage.SetEmail(randomString()+"@gmail.com");  //randomly generated the email
        regpage.setTelephone(randomNumer());
    
        String password = randomAlphanumeric();
        
        regpage.setPassword(password);
        regpage.setConfirmPassword(password);
        
        regpage.setPrivacyPolicy();
        regpage.clickContinue();
        
        
        logger.info("validating expected Message");
        String confmsg = regpage.getConfirmationMsg();
      
        if(confmsg.equals("Your Account Has Been Created!")) {
        	Assert.assertTrue(true);
        }else 
        {
        	logger.error("Test failed");
        	logger.debug("Debug logs...");
        	Assert.assertTrue(false);
        }
       
     //  Assert.assertEquals(confmsg, "Your Account Has Been Created!!!") ;
        	
        
	  }catch(Exception e){
		  logger.error("Test failed");
		  // logger.debug("Debug bugs" );
		  Assert.fail();
	  }
	  logger.info("***Finished TC001_AccountRegistrationTest ***");
  }
	   
 
}
