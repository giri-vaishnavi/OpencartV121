package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyaccountPage extends  BasePage{

	public MyaccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[ normalize-space()='My Account']")
	WebElement msgheading;  //my account page heading
	
	@FindBy(xpath ="//div//a[@class='list-group-item' and normalize-space()='Logout']")
	WebElement lnklogout;  // added in step 6
    
	public boolean isMyAccountPageExists() {
		try {
			return(msgheading.isDisplayed());
		}
		catch(Exception e ) {
			return false;
		}
	}
           
    public void clickLogout() {
    	lnklogout.click();
    }
           
           
           
           
}
