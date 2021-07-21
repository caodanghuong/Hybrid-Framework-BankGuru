package pageFactory.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import commons.BasePageFactory;


public class HomePageObject extends BasePageFactory{
	 WebDriver driver;
	
	@FindBy(id="invo-slider")
	WebElement homePageSlider;
	@FindBy(className = "ico-register")
	WebElement registerLink;
	@FindBy(className = "ico-login")
	WebElement loginLink;
	
	
	public  HomePageObject(WebDriver driver) {
		this.driver = driver;
		
	PageFactory.initElements(driver, this);
	}
	public boolean isHomePageLogoDisplayed() {
		waitForElementVisible(driver, homePageSlider);
		return isElementDisplayed(driver, homePageSlider);
	}
		

	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);
		return new RegisterPageObject(driver);
		
	}

	public LoginPageObject clickToLoginLink() {
		waitForElementVisible(driver, loginLink);
		clickToElement(driver, loginLink);
		return new LoginPageObject(driver);
		
	}

}
