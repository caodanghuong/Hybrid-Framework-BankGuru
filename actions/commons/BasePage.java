package commons;



import java.util.Date;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.OrderPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPageObject;
import pageUIs.user.nopCommerce.UserBasePageUI;
import pageUIs.admin.nopCoomerce.AdminBasePageUI;


public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	
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
	
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public WebElement getElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, params)));
	}
	
	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}
	
	public String getDynamicLocator(String locator, String... params) {
		 return String.format(locator, (Object[])params);
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	public void clickToElement(WebDriver driver, String locator, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String value, String... params) {
		locator = getDynamicLocator(locator, params);
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}
	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}
	public int getElementSize(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElements(driver, locator).size();
	}
	
	
	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public String getSelectItemDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMutiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}
	

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, timeOut);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	
}
	
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... params ) {
		return getElement(driver, getDynamicLocator(locator, params)).getAttribute(attributeName);
	}
	
	public String getElemetText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}
	public String getElemetText(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).getText();
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if (!getElement(driver, locator).isSelected()) {
			getElement(driver, locator).click();
		}
	}
	
	public void uncheckToCheckboxOrRadio(WebDriver driver, String locator) {
		if (getElement(driver, locator).isSelected()) {
			getElement(driver, locator).click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			//Displayed: visible on UI + In DOM
			//UNDIsplayed: Invisible on UI + Not in DOM
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//Undisplayed: Invisible on UI + not in DOM
			return false;
		}
	}
	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		System.out.println("Start time ="  + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time ="  + new Date().toString());
			return true;
		} else if (elements.size()  > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible on UI");
			System.out.println("End time ="  + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
		
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
	}
	
	public boolean isElementEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	
	
	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}
	
	public WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}
	
	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick().doubleClick(getElement(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourcelocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourcelocator), getElement(driver, targetLocator)).perform();
	}
	
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
		}
		
		
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... params) {
			action = new Actions(driver);
			locator = getDynamicLocator(locator, params);
			action.sendKeys(getElement(driver, locator), key).perform();
	}
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, timeOut);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
		
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
		
	}
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
		
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}
	
	public SearchPageObject openSearchPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.SEARCH_PAGE_FOOTER);
		clickToElement(driver, UserBasePageUI.SEARCH_PAGE_FOOTER);
		return PageGeneratorManager.getsearchPage(driver);
		}
	
	public MyAccountPageObject openMyAccountPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.MY_ACCOUNT_PAGE_FOOTER);
		clickToElement(driver, UserBasePageUI.MY_ACCOUNT_PAGE_FOOTER);
		return PageGeneratorManager.getMyAccountPage(driver);
	}
	
	public OrderPageObject openOrderPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ORDER_PAGE_FOOTER);
		clickToElement(driver, UserBasePageUI.ORDER_PAGE_FOOTER);
		return PageGeneratorManager.getOrderPage(driver);
	}
	
	public void uploadMultipleFiles(WebDriver driver, String ... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		sendkeyToElement(driver, filePath, fullFileName, fileNames);
	}
	
	//User- NopCommerce
	// 1 ham cho ca 20 page
	
	// Truong hop 1: Page < 10
	public BasePage getFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		
		if (pageName.equals("Search")) {
			return PageGeneratorManager.getsearchPage(driver);
		} else if (pageName.equals("My account")) {
			return PageGeneratorManager.getMyAccountPage(driver);
		} else {
			return PageGeneratorManager.getOrderPage(driver);
		}
	} 
	// cach 2: multiple page
	public void openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
	}
	
	//Admin - Nop Commerce
	public void openSubMenuPageByName(WebDriver driver,String menuPageName, String subMenuPageName) {
		waitForElementClickable(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		clickToElement(driver,AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		
		waitForElementClickable(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, subMenuPageName);
		clickToElement(driver,AdminBasePageUI.SUB_MENU_LINK_BY_NAME, subMenuPageName);
	}
	
	public void uploadFileAtCardName(WebDriver driver,String cardName,  String ... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, AdminBasePageUI.UPLOAD_FILE_BY_CARD_NAME, cardName).sendKeys(fullFileName);
	}
	public boolean isMessageDisplayedInEmptyTable(WebDriver driver, String tableName) {
		waitForElementVisible(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
		return isElementDisplayed(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
	}
	
	private Alert alert;
	
	private WebDriverWait explicitWait;
	private long timeOut = 30;
	private Select select;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private JavascriptExecutor jsExecutor;
	private Actions action;
	
}
