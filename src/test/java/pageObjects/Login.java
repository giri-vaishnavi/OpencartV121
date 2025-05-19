package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends BasePage{

	public Login(WebDriver driver) {
		super(driver);	
	}
    
	@FindBy(xpath ="//input [@id='input-email']"  )
	WebElement btnemail;
	
	@FindBy(xpath ="//input [@id='input-password']" )
	WebElement btnpwd ; 
	
	@FindBy(xpath ="//input [@value='Login']")
	WebElement btnlogin;
	
	public void setEmail(String email) {
		btnemail.sendKeys(email);;
	}
	
	public void setpwd(String pwd) {
		btnpwd.sendKeys(pwd);;
	}
	
	
	public void clickLogin() {
		btnlogin.click();
	}
	
	
	
	
	
}
