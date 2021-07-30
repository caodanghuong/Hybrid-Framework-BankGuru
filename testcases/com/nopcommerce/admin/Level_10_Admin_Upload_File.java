package com.nopcommerce.admin;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.admin.nopCommerce.DashboardPageObject;
import pageObjects.admin.nopCommerce.LoginPageObject;
import pageObjects.admin.nopCommerce.PageGeneratorManager;
import pageObjects.admin.nopCommerce.ProductDetailPageObject;
import pageObjects.admin.nopCommerce.ProductSearchPageObject;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;



public class Level_10_Admin_Upload_File  extends BaseTest {
	WebDriver driver;
	String productName = "Adobe Photoshop CS4";
	String productMacproImg = "Macpro.JPG";
	String productMacproAlt = "Macpro Alt";
	
	String productMacproTitle = "Macpro Title";
	
	String productMacproOrder = "1";
	
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ProductSearchPageObject productsearchPage;
	ProductDetailPageObject productDetailPage;


  @Parameters({"browser", "url"})
  @BeforeClass
  public void initBrowser(String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/");
	  loginPage = PageGeneratorManager.getLoginPage(driver);
	  loginPage.enterToEmailTexbox("admin@yourstore.com");
	  loginPage.enterToPasswordTextbox("admin");
	  
	  dashboardPage = loginPage.clickToLoginButton();
	  
	  dashboardPage.openSubMenuPageByName(driver, "Catalog", " Products");
	  productsearchPage = PageGeneratorManager.getProductSearchPage(driver);
	  
	  productsearchPage.enterToProductNameTextbox(productName);
	  productsearchPage.clickToSearchButton();
	  
	  productDetailPage = productsearchPage.clickToEditButtonByProductName(productName);
  }
  
  @Test
  public void Admin_01_Upload_File() {
	  productDetailPage.clickToExpandPanelByName("Pictures");
	  
	  productDetailPage.uploadFileAtCardName(driver, "pictures", productMacproImg);
	  
	  Assert.assertTrue(productDetailPage.isPictureUploadedsuccessByFileName(""));
	  
	  productDetailPage.enterToAltTextbox(productMacproAlt);
	  productDetailPage.enterToTitleTextbox(productMacproTitle);
	  productDetailPage.enterToDisplayedOrderTextbox(productMacproOrder);
	  
	  
	  productDetailPage.clickToAddProductPictureButton();
	  Assert.assertTrue(productDetailPage.isPictureImageDisplayed(productName, productMacproOrder, productMacproAlt, productMacproTitle));
	  
	  
	  
	  productsearchPage = productDetailPage.clickToSaveButton();
	  
	  Assert.assertTrue(productsearchPage.isSuccessMessageDisplayed(" The product has been updated successfully."));
	  
	  productsearchPage.enterToProductNameTextbox("Adobe Photoshop Cs4");
	  productsearchPage.clickToSearchButton();
	  
	  Assert.assertTrue(productsearchPage.isPictureImageUpdated(productName, productName));
	  
	  
	  productDetailPage = productsearchPage.clickToEditButtonByProductName(productName);
	  
	  productDetailPage.clickToExpandPanelByName("Pictures");
	  
	  productDetailPage.clickToDeleteButtonAtPictureName(productMacproTitle); // accept alert
	  
	  Assert.assertTrue(productDetailPage.isMessageDisplayedInEmptyTable(driver, "productpictures"));
	  
	  
	  productsearchPage = productDetailPage.clickToSaveButton();
	  
	  productsearchPage.enterToProductNameTextbox(productName);
	  productsearchPage.clickToSearchButton();
	  
	  Assert.assertTrue(productsearchPage.isPictureImageUpdated("default-image", productName));
	  
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {


  }
  @AfterClass
  public void cleanBrowser() {
	  driver.quit();
  }
}