package pages;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

	public static LoginPageObject openLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

}
