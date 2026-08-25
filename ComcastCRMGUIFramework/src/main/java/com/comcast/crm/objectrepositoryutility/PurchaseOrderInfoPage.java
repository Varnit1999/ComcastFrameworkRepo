package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderInfoPage {
	WebDriver driver;
	
	public PurchaseOrderInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement headerInfo;
	
	@FindBy(xpath = "//td[@id='mouseArea_Subject']")
	private WebElement sbjNameInfo;
	
	@FindBy(xpath = "//td[@id='mouseArea_Vendor Name']")
	private WebElement vdrNameInfo;

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getSbjNameInfo() {
		return sbjNameInfo;
	}

	public WebElement getVdrNameInfo() {
		return vdrNameInfo;
	}
	
	
}
