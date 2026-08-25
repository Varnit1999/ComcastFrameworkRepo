package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorInfoPage {

	WebDriver driver;

	public VendorInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement headerInfo;
	
	@FindBy(xpath = "//span[@id='dtlview_Vendor Name']")
	private WebElement vdrName;

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getVdrName() {
		return vdrName;
	}
	
	
	
	
	
}
