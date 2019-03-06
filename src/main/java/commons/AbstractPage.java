package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AbstractPage {

	WebDriverWait wait;
	private int longTimeout = 30;
	private int shortTimeout = 10;

	public void overwriteTimeout(WebDriver driver, int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public void openAnyURL(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, longTimeout);
	}

	public void sleep(int minute) {
		try {
			Thread.sleep(minute * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String inputValue, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(inputValue);
	}

	public void sendkeyToElement(WebDriver driver, String locator, Keys key, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(key);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String selectValue, String... values) {
		locator = String.format(locator, (Object[]) values);
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(selectValue);
	}

	public void selectCustomDropdownList(WebDriver driver, String dropdown, String listitem, String valueitem) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath(dropdown)).click();
		List<WebElement> allitem = driver.findElements(By.xpath(listitem));
		wait.until(ExpectedConditions.visibilityOfAllElements(allitem));
		for (WebElement item : allitem) {
			if (item.getText().equals(valueitem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				Thread.sleep(3000);
				break;
			}
		}
	}

	public String getFirstSelectedItem(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}

	public String getAttributeValue(WebDriver driver, String locator, String attribute, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attribute);
	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int getSizetElement(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}

	public void checkTheCheckbox(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement checkbox = driver.findElement(By.xpath(locator));
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void unCheckTheCheckbox(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement checkbox = driver.findElement(By.xpath(locator));
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlUnDisplayed(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		overwriteTimeout(driver, shortTimeout);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		overwriteTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isControlSelected(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendkeyToAlert(WebDriver driver, String locator) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(locator);
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		driver.switchTo().window(windowID);
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void switchToWindowByIndex(WebDriver driver, int tabIndex) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabIndex));
	}

	public void switchBackToDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToDynamicIframe(WebDriver driver, String iframe) {
		driver.switchTo().frame(iframe);
	}

	public void doulbeClickToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element);
	}

	public void hoverToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element);
	}

	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		WebElement sourceElement = driver.findElement(By.xpath(sourceLocator));
		WebElement targetElement = driver.findElement(By.xpath(targetLocator));
		Actions action = new Actions(driver);
		action.clickAndHold(targetElement).moveToElement(sourceElement).release().perform();
	}

	public void keyPress(WebDriver driver, Keys key) {
		Actions action = new Actions(driver);
		action.keyDown(key).build().perform();
	}

	public void upload(WebDriver driver, String fileName) {
		String proDir = System.getProperty("user.dir");
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath = proDir + "\\src\\test\\resources\\fileUpload\\" + fileName;
		uploadElement.sendKeys(filePath);
	}

	public void uploadMultiFields(WebDriver driver, String fileNames) {
		String[] file = fileNames.split(";");
		String filePath = "";
		for (int i = 0; i < file.length; i++) {
			String proDir = System.getProperty("user.dir");
			filePath += proDir + "\\src\\test\\resources\\fileUpload\\" + file[i] + "\n";
		}
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		filePath = filePath.trim();
		uploadElement.sendKeys(filePath);
	}

	public Object executeJSToBrowser(WebDriver driver, String Js) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(Js);
	}

	public Object executeJSToElement(WebDriver driver, String locator, String Js) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(Js, element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeInDOM(WebDriver driver, String locator, String attribute, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object openAnyURLByJS(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location= '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator, String style) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='" + style + "'", element);
	}

	public boolean checkAnyImageLoaded(WebDriver driver, String locator) {
		WebElement image = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);
	}

	public void waitForControlVisible(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		By byElement = By.xpath(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
	}

	public void waitForControlInVisible(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		By byElement = By.xpath(locator);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byElement));
	}

	public void waitForControlPresence(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		By byElement = By.xpath(locator);
		wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
	}

	public void waitForControlClickable(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		By byElement = By.xpath(locator);
		wait.until(ExpectedConditions.elementToBeClickable(byElement));
	}

	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeout);
		wait.until(ExpectedConditions.alertIsPresent());
	}
}
