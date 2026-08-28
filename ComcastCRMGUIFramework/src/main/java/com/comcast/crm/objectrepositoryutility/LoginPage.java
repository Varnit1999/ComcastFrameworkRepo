package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author Varnit
 * 
 *         Contains LoginPage elements and business libraries like loginToApp()
 */

public class LoginPage extends WebDriverUtility {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement usernameEdt;

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * login to application based on username, password, url arguments
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
//	public void loginToApp(String url, String username, String password) {
//		driver.get(url);
//		driver.manage().window().maximize();
//		usernameEdt.sendKeys(username);
//		passwordEdt.sendKeys(password);
//		loginBtn.click();
//	}
	public void loginToApp(String url, String username, String password) {

		System.out.println("1. Before driver.get()");
		driver.get(url);

		System.out.println("2. URL opened");

		waitForPageToLoad(driver);

		System.out.println("3. Implicit wait configured");

		driver.manage().window().maximize();

		System.out.println("4. Window maximized");

		usernameEdt.sendKeys(username);
		System.out.println("5. Username entered");

		passwordEdt.sendKeys(password);
		System.out.println("6. Password entered");

		loginBtn.click();
		System.out.println("7. Login button clicked");
	}
}
