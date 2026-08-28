package com.comcast.crm.vendortest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewProductPage;
import com.comcast.crm.objectrepositoryutility.CreateNewPurchaseOrderPage;
import com.comcast.crm.objectrepositoryutility.CreateNewVendorPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;
import com.comcast.crm.objectrepositoryutility.PurchaseOrderInfoPage;
import com.comcast.crm.objectrepositoryutility.PurchaseOrdersPage;
import com.comcast.crm.objectrepositoryutility.VendorInfoPage;
import com.comcast.crm.objectrepositoryutility.VendorsPage;

//@Listeners(com.comcast.crm.generic.listenerUtility.ListImpClass.class)
public class CreateVendorTest extends BaseClass {

	@Test
	public void createVendorTest() throws Throwable {

		// read TestScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String vendorName = elib.getDataFromExcel("vendor", 1, 2) + jlib.getRandomNumber();
		String glAccnt = elib.getDataFromExcel("vendor", 1, 3);

		/* navigate to Vendors module */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Vendors Module");
		HomePage hp = new HomePage(getDriver());
		hp.navigateToVendorsPage();

		/* Click on "Create Vendor Button" */
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Vendor");
		VendorsPage vp = new VendorsPage(getDriver());
		vp.getCreateNewVendorBtn().click();

		/* Enter all the details and create a new vendor */
		UtilityClassObject.getTest().log(Status.INFO, "Enter all the details and create a new Vendor");
		CreateNewVendorPage cnvp = new CreateNewVendorPage(getDriver());
		cnvp.createVendor(vendorName, glAccnt);

		UtilityClassObject.getTest().log(Status.INFO, vendorName + " Vendor Created");

		/* Verify Header Message Expected Result */
		VendorInfoPage vip = new VendorInfoPage(getDriver());
		String actHeader = vip.getHeaderInfo().getText();
		Assert.assertTrue(actHeader.contains(vendorName));

	}

	@Test
	public void createPurchaseOrderWithVendorTest() throws Exception {
		String sbjName = elib.getDataFromExcel("product", 7, 2) + jlib.getRandomNumber();
		String vdrName = elib.getDataFromExcel("product", 7, 3) + jlib.getRandomNumber();
		String glAccnt = elib.getDataFromExcel("product", 7, 7);
		String pdtName = elib.getDataFromExcel("product", 7, 4) + jlib.getRandomNumber();
		String billAddr = elib.getDataFromExcel("product", 7, 5);
		String qty = elib.getDataFromExcel("product", 7, 6);

		// Navigate to Vendors Module
		HomePage hp = new HomePage(getDriver());
		hp.navigateToVendorsPage();

		/* Click on "Create Vendor" button */
		VendorsPage vp = new VendorsPage(getDriver());
		vp.getCreateNewVendorBtn().click();

		/* Enter all the details and create a new vendor */
		CreateNewVendorPage cnvp = new CreateNewVendorPage(getDriver());
		cnvp.createVendor(vdrName, glAccnt);

		/* Navigate To Products Module */
		hp.getProductsLink().click();

		/* Click on "Create Product" button */
		ProductsPage pp = new ProductsPage(getDriver());
		pp.getCreateNewProductBtn().click();

		/* Enter all the details and create a new product */
		CreateNewProductPage cnp = new CreateNewProductPage(getDriver());
		cnp.getPrdNameEdt().sendKeys(pdtName);
		cnp.getSaveBtn().click();

		/* Navigate to Purchase Order Module */
		hp.navigateToPurchaseOrderPage();

		/* Click on "Create New Purchase Order" Button */
		PurchaseOrdersPage pop = new PurchaseOrdersPage(getDriver());
		pop.getNewPurchaseOrderBtn().click();

		/* Enter all the details and create a new purchase order */
		CreateNewPurchaseOrderPage cpop = new CreateNewPurchaseOrderPage(getDriver());
		cpop.createPurchaseOrderWithVendorAndProduct(sbjName, vdrName, billAddr, pdtName, qty);

		/* Verify Header Message Expected Result */
		PurchaseOrderInfoPage pinp = new PurchaseOrderInfoPage(getDriver());
		String actHeaderMsge = pinp.getHeaderInfo().getText();
		Assert.assertTrue(actHeaderMsge.contains(sbjName));

	}
}
