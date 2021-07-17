package pageObject.LiveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LiveGuru.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage {
	 WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public Object isMyDasboardHeaderDisplayed() {
		waitForElementVisible(driver, MyDashboardPageUI.MY_DASHBOARD_HEADER_TEXT);
		return isElementDisplayed(driver, MyDashboardPageUI.MY_DASHBOARD_HEADER_TEXT);
	}

}
