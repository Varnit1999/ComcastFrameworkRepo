package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewVendorPage extends WebDriverUtility {
	WebDriver driver;

	public CreateNewVendorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "vendorname")
	private WebElement vendorNameEdt;

	@FindBy(xpath = "//select[@name='glacct']")
	private WebElement glAccntDD;

	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveBtn;

	public WebElement getVendorName() {
		return vendorNameEdt;
	}

	public WebElement getGlAccnt() {
		return glAccntDD;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createVendor(String vendorName, String glAccnt) {
		vendorNameEdt.sendKeys(vendorName);
		selectByValue(glAccntDD, glAccnt);
		saveBtn.click();
	}

}
