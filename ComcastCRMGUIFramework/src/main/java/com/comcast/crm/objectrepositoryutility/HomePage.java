package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage extends WebDriverUtility {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;

	@FindBy(xpath = "//a[text()='Contacts']")
	private WebElement contactLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;

	@FindBy(linkText = "Vendors")
	private WebElement vendorsLink;

	@FindBy(linkText = "Purchase Order")
	private WebElement purchaseOrderLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(linkText = "Products")
	private WebElement productsLink;

	@FindBy(linkText = "Invoice")
	private WebElement invoiceLink;

	@FindBy(xpath = "//img[contains(@src,'user')]")
	private WebElement adminImg;

	@FindBy(xpath = "//a[contains(@href,'Logout')]")
	private WebElement signOutLink;

	@FindBy(xpath = "//img[@title='vtiger-crm-logo.gif']")
	private WebElement pageLogo;

	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement homeLink;

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getVendorsLink() {
		return vendorsLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getInvoiceLink() {
		return invoiceLink;
	}

	public void navigateToCampaignPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignLink.click();
	}

	public WebElement getPurchaseOrderLink() {
		return purchaseOrderLink;
	}

	public void navigateToVendorsPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		vendorsLink.click();
	}

	public void navigateToPurchaseOrderPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		purchaseOrderLink.click();
	}

	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(adminImg)).click();
//		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		wait.until(ExpectedConditions.elementToBeClickable(signOutLink)).click();
	}

}
