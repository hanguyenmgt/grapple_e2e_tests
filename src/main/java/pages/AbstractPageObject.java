package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.AbstractPageUI;

public class AbstractPageObject extends AbstractPage {

	WebDriver driver;

	public AbstractPageObject(WebDriver mappingDriver) {

		driver = mappingDriver;
	}

	public boolean isDynamicValidationMessageDisplayed(String message) {
		waitForControlVisible(driver, AbstractPageUI.DYNAMIC_VALIDATION_MESSAGE, message);
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_VALIDATION_MESSAGE, message);
	}
	
}
