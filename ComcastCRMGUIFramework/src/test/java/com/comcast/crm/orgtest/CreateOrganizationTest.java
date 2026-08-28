package com.comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws Throwable {

		// read TestScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// step 2 : navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization Module");
		HomePage hp = new HomePage(getDriver());
		hp.getOrgLink().click();
//		hp.navigateToCampaignPage();

		// step 3 : click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Organization");
		OrganizationsPage onp = new OrganizationsPage(getDriver());
		onp.getCreateNewOrgBtn().click();

		// step 4 : Enter all the details and create an Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(getDriver());
		cnop.createOrg(orgName);

		UtilityClassObject.getTest().log(Status.INFO, orgName + " Organization Created");

		// verify Header message Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(getDriver());
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(actOrgName));
		UtilityClassObject.getTest().log(Status.INFO, orgName + " organization is verified");
	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustryTest() throws Throwable {
		// read TestScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		// step 2 : navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization Module");
		HomePage hp = new HomePage(getDriver());
		hp.getOrgLink().click();

		// step 3 : click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Organization");
		OrganizationsPage onp = new OrganizationsPage(getDriver());
		onp.getCreateNewOrgBtn().click();

		// step 4 : Enter all the details and create an Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization with industry and type");
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(getDriver());
		cnp.createOrg(orgName, industry, type);

		UtilityClassObject.getTest().log(Status.INFO, orgName + " Organization Created");

		// Verify the industries and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(getDriver());

		UtilityClassObject.getTest().log(Status.INFO, "Verify industry name");
		String actIndustries = oip.getIndustryName().getText().trim();
		Assert.assertEquals(actIndustries, industry);
		UtilityClassObject.getTest().log(Status.INFO, industry + " is verified");

		UtilityClassObject.getTest().log(Status.INFO, "Verify type name");
		String actType = oip.getTypeName().getText().trim();
		Assert.assertEquals(actType, type);
		UtilityClassObject.getTest().log(Status.INFO, type + " is verified");
	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithPhoneNumberTest() throws Throwable {
		// read TestScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("org", 7, 3);

		// step 2 : navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization Module");
		HomePage hp = new HomePage(getDriver());
		hp.getOrgLink().click();

		// step 3 : click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Organization");
		OrganizationsPage onp = new OrganizationsPage(getDriver());
		onp.getCreateNewOrgBtn().click();

		// step 4 : Enter all the details and create an Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization with phone number");
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(getDriver());
		cnp.createOrg(orgName, phoneNumber);

		UtilityClassObject.getTest().log(Status.INFO, orgName + " Organization Created");

		// Verify PhoneNumber Expected Result
		UtilityClassObject.getTest().log(Status.INFO, "Verify Phone Number");
		OrganizationInfoPage oip = new OrganizationInfoPage(getDriver());
		String actPhoneNo = oip.getPhoneNo().getText().trim();
		Assert.assertEquals(actPhoneNo, phoneNumber);
		UtilityClassObject.getTest().log(Status.INFO, phoneNumber + " is verified");

	}
}
