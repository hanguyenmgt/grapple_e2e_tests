package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class LoginPageObject extends AbstractPage {

	WebDriver driver;
	
	public LoginPageObject (WebDriver mappingDriver) {
		
		driver = mappingDriver;
	}
		
	public String getLoginPageURL() {
		
		return getCurrentURL(driver);
	} 
	
}
