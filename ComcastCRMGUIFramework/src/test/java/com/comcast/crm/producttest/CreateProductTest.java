package com.comcast.crm.producttest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewProductPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.ProductInfoPage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;

@Listeners(com.comcast.crm.generic.listenerUtility.ListImpClass.class)
public class CreateProductTest extends BaseClass {

	@Test
	public void createProductTest() throws Throwable {

		/* read data from excel */
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String productName = elib.getDataFromExcel("product", 1, 2) + jlib.getRandomNumber();

		/* navigate to Products module */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Products Module");
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

		/* Click on "Create Product" Button" */
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Product");
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateNewProductBtn().click();

		/* Enter all the details and create a new product */
		UtilityClassObject.getTest().log(Status.INFO, "Enter all the details and create a new product");
		CreateNewProductPage cnp = new CreateNewProductPage(driver);
		cnp.getPrdNameEdt().sendKeys(productName);
		cnp.getSaveBtn().click();

		UtilityClassObject.getTest().log(Status.INFO, productName + " Product Created");

		/* Verify Product Name Expected Result */
		ProductInfoPage pip = new ProductInfoPage(driver);
		String actProdName = pip.getPrdName().getText().trim();
		Assert.assertEquals(actProdName, productName);

	}
}
