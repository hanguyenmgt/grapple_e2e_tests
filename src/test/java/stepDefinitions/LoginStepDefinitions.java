package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumberOptions.Hooks;
import pages.LoginPageObject;
import pages.PageFactoryManager;

public class LoginStepDefinitions extends AbstractTest {

	WebDriver driver;
	private LoginPageObject loginPage;
	public static String userName, password, loginPageUrl;

	public LoginStepDefinitions() {
		driver = Hooks.openBrowser();
		loginPage = PageFactoryManager.openLoginPage(driver);
		loginPage.hashCode();
	}

}
