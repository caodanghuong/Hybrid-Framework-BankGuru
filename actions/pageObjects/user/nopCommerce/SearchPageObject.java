package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.SearchPageUI;

public class SearchPageObject extends BasePage{
	WebDriver driver;
	
	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}


//	public MyAccountPageObject OpenMyAccountPage() {
		//waitForElementClickable(driver, SearchPageUI.MY_ACCOUNT_PAGE_FOOTER);
	//	clickToElement(driver, SearchPageUI.MY_ACCOUNT_PAGE_FOOTER);
	//	return PageGeneratorManager.getMyAccountPage(driver);
	//}



}
