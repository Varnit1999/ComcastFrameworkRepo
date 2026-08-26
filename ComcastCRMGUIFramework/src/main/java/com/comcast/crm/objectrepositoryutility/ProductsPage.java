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
<<<<<<< HEAD

	@FindBy(xpath = "//div[@id='basicsearchcolumns_real']//select[@id='bas_searchfield']")
	private WebElement productDD;
=======
	
	@FindBy(xpath = "//input[@class='txtBox']")
	private WebElement searchEdt;
>>>>>>> branch 'main' of https://github.com/Varnit1999/ComcastFrameworkRepo.git

	public WebElement getCreateNewProductBtn() {
		return createNewProductBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}
<<<<<<< HEAD
=======
	
>>>>>>> branch 'main' of https://github.com/Varnit1999/ComcastFrameworkRepo.git

}
