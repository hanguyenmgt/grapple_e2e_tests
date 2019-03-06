package commons;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class AbstractTest extends AbstractPage {

	WebDriver driver;
	protected final Log log;

	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999) + 1;
		return number;
	}

	public boolean checkPassed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			log.info(e);
			// Add status (true/ false) to Report (ReportNG)
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	public boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			log.info(e);
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	public boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			log.info(e);
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public void switchToDynacmicWindow(int sleepTime, int tabIndex) {

		sleep(sleepTime);
		try {
			switchToWindowByIndex(driver, tabIndex);
		} catch (Exception e) {
			sleep(sleepTime);
			switchToWindowByIndex(driver, tabIndex);
		}
	}
}
