package com.comcast.crm.contacttest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {

		// Read test data from Excel file utility
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		System.out.println("Test data created: " + lastName);

		// Step 2: Navigate to Contacts module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contacts Module");
		HomePage hp = new HomePage(getDriver());
		hp.getContactLink().click();

		// Step 3: Click on "Create Contacts" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Contact");
		ContactsPage cp = new ContactsPage(getDriver());
		cp.getCreateNewContactBtn().click();

		// Step 4: Enter all the details & create new Contact

		UtilityClassObject.getTest().log(Status.INFO, "Create a new contact with lastname");
		CreateNewContactPage cnc = new CreateNewContactPage(getDriver());
		cnc.createContact(lastName);

		UtilityClassObject.getTest().log(Status.INFO, lastName + " Contact Created");

		// Verify LastName info Expected Result
		ContactInfoPage cip = new ContactInfoPage(getDriver());

		String actHeader = cip.getHeaderInfo().getText();

		Assert.assertTrue(actHeader.contains(lastName));

		String actLastName = cip.getLastName().getText();

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);
		soft.assertAll();
		UtilityClassObject.getTest().log(Status.INFO, actLastName + " is verified");

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {

		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contacts Module");
		HomePage hp = new HomePage(getDriver());
		hp.getContactLink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Contact");
		ContactsPage cp = new ContactsPage(getDriver());
		cp.getCreateNewContactBtn().click();

		UtilityClassObject.getTest().log(Status.INFO,
				"Create a new contact with support start date and support end date");
		CreateNewContactPage cnp = new CreateNewContactPage(getDriver());

		String startDate = jlib.getSystemDateYYYYDDMM();
		String afterDateRequired = jlib.getRequiredDate(30);

		cnp.createContactWithSupportDate(lastName, startDate, afterDateRequired);

		ContactInfoPage cip = new ContactInfoPage(getDriver());

		String st_date = cip.getStDate().getText().trim();
		Assert.assertEquals(st_date, startDate);
		UtilityClassObject.getTest().log(Status.INFO, startDate + " support start date is verified");

		String end_date = cip.getEndDate().getText().trim();
		Assert.assertEquals(end_date, afterDateRequired);
		UtilityClassObject.getTest().log(Status.INFO, afterDateRequired + " support end date is verified");

	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {

		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// Step 2: Navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization Module");
		HomePage hp = new HomePage(getDriver());
		hp.getOrgLink().click();

		// Step 3: Click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Organization");
		OrganizationsPage onp = new OrganizationsPage(getDriver());
		onp.getCreateNewOrgBtn().click();

		// Step 4: Enter all the details and create an Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(getDriver());
		cnop.createOrg(orgName);

		UtilityClassObject.getTest().log(Status.INFO, orgName + " Organization Created");

		// Verify Header message
		OrganizationInfoPage oip = new OrganizationInfoPage(getDriver());
		String headerInfo = oip.getHeaderMsg().getText();
		Assert.assertTrue(headerInfo.contains(orgName));

		// Step 5: Navigate to Contacts module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contacts Module");
		hp.getContactLink().click();

		// Step 6: Click on "Create Contacts" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Contact");
		ContactsPage cp = new ContactsPage(getDriver());
		cp.getCreateNewContactBtn().click();

		// Step 7: Enter all the details and create new Contact
		CreateNewContactPage cnc = new CreateNewContactPage(getDriver());
		cnc.createContactWithOrg(contactLastName, orgName);

		UtilityClassObject.getTest().log(Status.INFO,
				contactLastName + " contact is created with Orgnaization " + orgName);

		// Verify LastName info
		ContactInfoPage cip = new ContactInfoPage(getDriver());
		headerInfo = cip.getHeaderInfo().getText();
		Assert.assertTrue(headerInfo.contains(contactLastName));

		UtilityClassObject.getTest().log(Status.INFO, contactLastName + " contact is verified");

		// Verify Organization Name
		String actOrgName = cip.getOrgName().getText().trim();
		Assert.assertEquals(actOrgName, orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName + " organization is verified");

	}
}