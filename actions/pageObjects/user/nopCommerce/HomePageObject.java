package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	
	
	
	public  HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isHomePageLogoDisplayed() {
		waitForElementVisible(driver, HomePageUI.HOME_PAGE_SLIDER );
		return isElementDisplayed(driver, HomePageUI.HOME_PAGE_SLIDER);
	}

	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
		
	}

	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
		
	}
//	public SearchPageObject openSearchPage() {
	//	waitForElementClickable(driver, HomePageUI.SEARCH_PAGE_FOOTER);
	///	clickToElement(driver, HomePageUI.SEARCH_PAGE_FOOTER);
	//	return PageGeneratorManager.getsearchPage(driver);
	//}

}
