package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage { // Rule-1 create a separate java class
									// Rule-2 Object Creation

	WebDriver driver;

	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerMsg;

	@FindBy(xpath = "//span[@id='dtlview_Industry']")
	private WebElement industryName;

	@FindBy(xpath = "//span[@id='dtlview_Type']")
	private WebElement typeName;

	@FindBy(xpath = "//span[@id='dtlview_Phone']")
	private WebElement phoneNo;

	public WebElement getPhoneNo() {
		return phoneNo;
	}

	public WebElement getIndustryName() {
		return industryName;
	}

	public WebElement getTypeName() {
		return typeName;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

}
