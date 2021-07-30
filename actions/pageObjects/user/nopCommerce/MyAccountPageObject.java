package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.MyAccountPageUI;

public class MyAccountPageObject extends BasePage{
	WebDriver driver;
	
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}



	//public SearchPageObject openSearchPage() {
	//	waitForElementClickable(driver, MyAccountPageUI.SEARCH_PAGE_FOOTER);
	//	clickToElement(driver, MyAccountPageUI.SEARCH_PAGE_FOOTER);
	//	return PageGeneratorManager.getsearchPage(driver);
	//	}

	//public OrderPageObject openOrderPage() {
	//	waitForElementClickable(driver, MyAccountPageUI.ORDER_PAGE_FOOTER);
	//	clickToElement(driver, MyAccountPageUI.ORDER_PAGE_FOOTER);
	//	return PageGeneratorManager.getOrderPage(driver);
	//}

}
