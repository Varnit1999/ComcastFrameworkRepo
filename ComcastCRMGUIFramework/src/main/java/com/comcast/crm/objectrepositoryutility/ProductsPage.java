package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Product...']")
	private WebElement createNewProductBtn;
	
	@FindBy(xpath = "//input[@class='txtBox']")
	private WebElement searchEdt;

	public WebElement getCreateNewProductBtn() {
		return createNewProductBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}
	
	
	
	

}
