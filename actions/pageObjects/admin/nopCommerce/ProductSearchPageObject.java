package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCoomerce.ProductSearchPageUI;

public class ProductSearchPageObject extends BasePage{
	WebDriver driver;
	public ProductSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToProductNameTextbox(String productName) {
		waitForElementClickable(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
		sendkeyToElement(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX, productName);
		
	}
	public void clickToSearchButton() {
		waitForElementClickable(driver, ProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
		
	}
	public ProductDetailPageObject clickToEditButtonByProductName(String productName) {
		waitForElementClickable(driver, ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME);
		clickToElement(driver, ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		
		return PageGeneratorManager.getProductDetailPage(driver);
	}
	public boolean isSuccessMessageDisplayed(String messageName) {
		
		waitForElementVisible(driver, ProductSearchPageUI.SUCCESS_MESSAGE_NAME, messageName);
		
		
		return isElementDisplayed(driver, ProductSearchPageUI.SUCCESS_MESSAGE_NAME, messageName);
	}

	public boolean isPictureImageUpdated(String productImageName, String productName) {
		productImageName = productImageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productImageName, productName);
		return isElementDisplayed(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productImageName, productName);
	}
}
