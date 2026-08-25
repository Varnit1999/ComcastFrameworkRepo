package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {
	WebDriver driver;

	public CreateNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lstName;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgBtn;

	@FindBy(xpath = "//input[@id='search_txt']")
	private WebElement searchOrgTxt;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchOrgBtn;

	@FindBy(xpath = "//input[@name='support_start_date']")
	private WebElement suppStDate;

	@FindBy(xpath = "//input[@name='support_end_date']")
	private WebElement suppEndDate;

	public WebElement getSearchOrgBtn() {
		return searchOrgBtn;
	}

	public WebElement getOrgBtn() {
		return orgBtn;
	}

	public WebElement getSearchOrgTxt() {
		return searchOrgTxt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getLstName() {
		return lstName;
	}

	public WebElement getSuppStDate() {
		return suppStDate;
	}

	public WebElement getSuppEndDate() {
		return suppEndDate;
	}

	public void createContact(String lastName) {
		lstName.sendKeys(lastName);
		saveBtn.click();
	}

	public void createContactWithSupportDate(String lastName, String startDate, String endDate) {
		lstName.sendKeys(lastName);
		suppStDate.clear();
		suppStDate.sendKeys(startDate);
		suppEndDate.clear();
		suppEndDate.sendKeys(endDate);
		saveBtn.click();
	}

	public void createContactWithOrg(String lastName, String orgName) {
		lstName.sendKeys(lastName);
		orgBtn.click();
		switchToTabOnURL(driver, "module=Accounts");
		searchOrgTxt.sendKeys(orgName);
		searchOrgBtn.click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		switchToTabOnURL(driver, "module=Contacts");
		saveBtn.click();
	}

}
