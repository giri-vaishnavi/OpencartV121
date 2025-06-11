package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.Login;
import pageObjects.MyaccountPage;

public class TC002_LoginTest extends TestBase.BaseClass{
    @Test (groups = {"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("----Strarting TC002_LoginTest Test---" );
	    try {
		//HomePage
	    HomePage hp = new HomePage(driver);
	    hp.clickMyAccount();
	    logger.info("clicking on my account");
	    hp.clicklogin();
	    logger.info("clicking on login option");

	    //Login
	    Login lp = new Login(driver);
	    lp.setEmail(p.getProperty("email"));
	    lp.setpwd(p.getProperty("password"));
	    lp.clickLogin();
	    
	    //Myaccount
	    MyaccountPage macc = new MyaccountPage(driver);
	    
	  boolean targetPage =  macc.isMyAccountPageExists();
	  //  Assert.assertEquals(targetPage, true, "Login failed");
	  Assert.assertTrue(targetPage);
	    }catch (Exception e)
	    {
	    	Assert.fail();
	    }
	  logger.info("----- Finished TC002_LoginTest Test---");
	}
	
}
