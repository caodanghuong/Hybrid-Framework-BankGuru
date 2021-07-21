package pageFactory.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;


public class RegisterPageObject extends BasePageFactory{
	
	 WebDriver driver;
	 
	 
		@FindBy(css="input[@id='gender-male']")
		WebElement genderMaleRadio;
		
		@FindBy(id = "FirstName")
		WebElement firstNameTextbox;
		
		@FindBy(id = "LastName")
		WebElement lastNamwTextbox;
		
		@FindBy(id = "Email")
		WebElement emailTextbox;

		
		@FindBy(id = "password")
		WebElement passwordTextbox;
		
		@FindBy(id = "ConfirmPassword")
		WebElement confirmPasswordTextbox;
		
		@FindBy(id = "register-button")
		WebElement registerButton;
		
		@FindBy(xpath = "//div[@class='result' and text() = 'Your registration completed']")
		WebElement successMessage;
		
		@FindBy(className = "ico-logout")
		WebElement logoutLink;
		
	
	public  RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void ClickToGenderMaleRadioButton() {
		waitForElementClickable(driver, genderMaleRadio);
		clickToElement(driver, genderMaleRadio);
		
	}

	public void enterToFirstnameTextbox(String firstName) {
		waitForElementClickable(driver, firstNameTextbox);
		clickToElement(driver, firstNameTextbox);
		
		
	}

	public void enterToLastnameTextbox(String lastName) {
		waitForElementClickable(driver, lastNamwTextbox);
		clickToElement(driver, lastNamwTextbox);
		
		
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementClickable(driver, emailTextbox);
		clickToElement(driver, emailTextbox);

	}

	public void enterToPasswordTextbox(String password) {
		waitForElementClickable(driver, passwordTextbox);
		clickToElement(driver, passwordTextbox);
		
		
	}

	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementClickable(driver, confirmPasswordTextbox);
		clickToElement(driver, confirmPasswordTextbox);
		
		
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
		
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, successMessage);
		return isElementDisplayed(driver, successMessage);
	}

	public HomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(driver, logoutLink);
		return new HomePageObject(driver);
		
		
	}

}
