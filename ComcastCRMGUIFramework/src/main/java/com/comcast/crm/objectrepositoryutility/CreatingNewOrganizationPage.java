package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;

	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDD;

	@FindBy(name = "accounttype")
	private WebElement typeDD;

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement phoneNoEdt;

	public WebElement getPhoneNoEdt() {
		return phoneNoEdt;
	}

	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}

	public void createOrg(String orgName, String industry, String type) {
		orgNameEdt.sendKeys(orgName);
		Select sel = new Select(industryDD);
		sel.selectByVisibleText(industry);
		Select sel1 = new Select(typeDD);
		sel1.selectByVisibleText(type);
		saveBtn.click();
	}

	public void createOrg(String orgName, String phNo) {
		orgNameEdt.sendKeys(orgName);
		phoneNoEdt.sendKeys(phNo);
		saveBtn.click();
	}

}
