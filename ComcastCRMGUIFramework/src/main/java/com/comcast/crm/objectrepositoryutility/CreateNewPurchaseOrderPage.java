package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewPurchaseOrderPage extends WebDriverUtility {

	WebDriver driver;

	public CreateNewPurchaseOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement subjectEdt;

	@FindBy(xpath = "//input[@name='vendor_id']/following-sibling::img[@title='Select']")
	private WebElement addVendorIcon;

	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement vendorSearchEdt;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement vendorSearchBtn;

	@FindBy(xpath = "//textarea[@name='bill_street']")
	private WebElement billAddrEdt;

	@FindBy(xpath = "//b[contains(text(), 'Billing')]/preceding-sibling::input")
	private WebElement cpyBillAddrBtn;

	@FindBy(xpath = "//img[@title='Products']")
	private WebElement addProductIcon;

	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement productSearchEdt;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement productSearchBtn;

	@FindBy(xpath = "//input[@id='qty1']")
	private WebElement qtyEdt;

	@FindBy(xpath = "//input[@name='pagenum']")
	private WebElement pageNumEdt;

	@FindBy(xpath = "//input[contains(@title,'Save')]")
	private WebElement saveBtn;

	public void createPurchaseOrderWithVendorAndProduct(String subjName, String vdrName, String billAddr,
			String pdtName, String qty) {
		subjectEdt.sendKeys(subjName);
		addVendorIcon.click();
		switchToTabOnURL(driver, "module=Vendors");
		vendorSearchEdt.sendKeys(vdrName);
		vendorSearchBtn.click();
		driver.findElement(By.xpath("//a[text()='" + vdrName + "']")).click();
		switchToTabOnURL(driver, "module=Purchase");
		billAddrEdt.sendKeys(billAddr);
		cpyBillAddrBtn.click();
		addProductIcon.click();
		switchToTabOnURL(driver, "module=Products");
		waitForElementToBePresent(driver, pageNumEdt);
		productSearchEdt.sendKeys(pdtName);
		productSearchBtn.click();
		driver.findElement(By.xpath("//a[text()='" + pdtName + "']")).click();
		switchToTabOnURL(driver, "module=Purchase");
		qtyEdt.sendKeys(qty);
		saveBtn.click();
	}

}
