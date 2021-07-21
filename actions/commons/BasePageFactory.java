package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	
	/* Browser*/
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	
	public String getPageTitle(WebDriver driver) {
		//Neu khong return thi khong lay duowcj du lieu ra
		return driver.getTitle();
	}
	
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	
	public String getPageSource (WebDriver driver) {
		return driver.getPageSource();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, timeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		
		alert =waitForAlertPresence(driver);
		alert.accept();
	}
	public void cancelAlert(WebDriver driver) {
		
		alert =waitForAlertPresence(driver);
		alert.dismiss();
	}
	public void senkeysAlert(WebDriver driver, String value) {
		
		alert =waitForAlertPresence(driver);
		alert.sendKeys(value);
	}
	public String getAlertText(WebDriver driver) {
		
		alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public void switchToWindowByID (WebDriver driver, String parentID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for(String windowID : allWindowsID) {
			if(!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
		
	}
	public void switchToWindowByTitle(WebDriver driver, String expectedWindowTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String actualWindowTitle = driver.getTitle();
			if(actualWindowTitle.equals(expectedWindowTitle)) {
				break;
			}

		}
	}

	public void closeAllWindowExceptParent(WebDriver driver, String parentID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for(String windowID : allWindowsID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				driver.close();
				sleepInSecond(1);
			}
			if(driver.getWindowHandles().size() ==1) {
				driver.switchTo().window(parentID);
				break;
			}
		}
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep( timeInSecond *1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void refreshCurrentPage (WebDriver driver) {
		driver.navigate().refresh();
	}
	public void backToPage (WebDriver driver) {
		driver.navigate().back();
	}
	public void forwardToPage (WebDriver driver) {
		driver.navigate().forward();
	}

	
	/* Element */
	
	public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
	}
	public void sendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	public String getElemetText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	public boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}
	
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public void waitForAllElementVisible(WebDriver driver, List<WebElement> elements) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
	}
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable((element)));
		
	}
	
	private Alert alert;
	
	private WebDriverWait explicitWait;
	private long timeOut = 30;

}

