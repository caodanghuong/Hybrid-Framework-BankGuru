package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCoomerce.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage{
	WebDriver driver;
	public ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToExpandPanelByName(String panelName) {
		waitForAllElementVisible(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME);
		String toogleIconStatus = getElementAttribute(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME, "class", panelName);
		
		if(toogleIconStatus.contains("fa-plus")) {
			waitForElementClickable(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME, "class", panelName);
			clickToElement(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		}
		
	}

	public boolean isPictureUploadedsuccessByFileName(String fileName) {
		fileName = fileName.split("\\.")[0];
		waitForElementVisible(driver, ProductDetailPageUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
		
		return isElementDisplayed(driver, ProductDetailPageUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
	}
	public void enterToAltTextbox(String productImageAlt) {
		waitForElementVisible(driver, ProductDetailPageUI.ALT_TEXBOX_ADD_NEW, productImageAlt);
		sendkeyToElement(driver, ProductDetailPageUI.ALT_TEXBOX_ADD_NEW, productImageAlt);
		
	}
	public void enterToTitleTextbox(String productImageTitle) {
		waitForElementVisible(driver, ProductDetailPageUI.TITLE_TEXBOX_ADD_NEW, productImageTitle);
		sendkeyToElement(driver, ProductDetailPageUI.TITLE_TEXBOX_ADD_NEW, productImageTitle);
		
	}
	public void enterToDisplayedOrderTextbox(String productImageDisplayOrder) {
		waitForElementVisible(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXBOX_ADD_NEW, productImageDisplayOrder);
		sendkeyToElement(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXBOX_ADD_NEW, productImageDisplayOrder);
	}
	public void clickToAddProductPictureButton() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
	}
	public boolean isPictureImageDisplayed(String imageName, String displayOrder, String imageAlt, String imageTitle) {
		// Adobe Photoshop CS4 
		// adobe-photoshop-cs4
		imageName = imageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXBOX_ADD_NEW, imageName, displayOrder, imageAlt, imageTitle);
		return isElementDisplayed(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXBOX_ADD_NEW, imageName, displayOrder, imageAlt, imageTitle);
	}
	public ProductSearchPageObject clickToSaveButton() {
		waitForElementVisible(driver, ProductDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.SAVE_BUTTON);
		return PageGeneratorManager.getProductSearchPage(driver);
	}

	public void clickToDeleteButtonAtPictureName(String productTitle) {
		waitForElementClickable(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, productTitle);
		clickToElement(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, productTitle);
		acceptAlert(driver);
		
	}



}
