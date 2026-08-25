package com.comcast.crm.contacttest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {

//	WebDriverUtility wlib = new WebDriverUtility();

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {

		// read testScript data from Excel file utility
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// step 2 : navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3 : click on "Create Contacts" Button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		// step 4 : enter all the details & create new Contact
		CreateNewContactPage cnc = new CreateNewContactPage(driver);
		cnc.createContact(lastName);

		// Verify LastName info Expected Result
		ContactInfoPage cip = new ContactInfoPage(driver);

		String actHeader = cip.getHeaderInfo().getText();
		Assert.assertTrue(actHeader.contains(lastName));

		String actLastName = cip.getLastName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {
		// read TestScript data from Excel file
		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// step 2 : navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3 : click on "Create Contacts" Button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		// step 4 : enter all the details & create new Contact
		CreateNewContactPage cnp = new CreateNewContactPage(driver);

		String startDate = jlib.getSystemDateYYYYDDMM();
		String afterDateRequired = jlib.getRequiredDate(30);

		cnp.createContactWithSupportDate(lastName, startDate, afterDateRequired);

		// Verify start date and end date expected info
		ContactInfoPage cip = new ContactInfoPage(driver);
		String st_date = cip.getStDate().getText().trim();
		Assert.assertEquals(startDate, st_date);

		String end_date = cip.getEndDate().getText().trim();
		Assert.assertEquals(afterDateRequired, end_date);
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// step 2 : navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3 : click on "Create Organization" Button
		OrganizationsPage onp = new OrganizationsPage(driver);
		onp.getCreateNewOrgBtn().click();

		// step 4 : Enter all the details and create an Organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify Header message Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		// step 5 : navigate to Contacts module
		hp.getContactLink().click();

		// step 6 : click on "Create Contacts" Button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		// step 7 : enter all the details & create new Contact
		CreateNewContactPage cnc = new CreateNewContactPage(driver);
		cnc.createContactWithOrg(contactLastName, orgName);

		// Verify LastName info Expected Result
		ContactInfoPage cip = new ContactInfoPage(driver);
		headerInfo = cip.getHeaderInfo().getText();
		if (headerInfo.contains(contactLastName)) {
			System.out.println(contactLastName + " information is verified==PASS");
		} else {
			System.out.println(contactLastName + " information is not verified==FAIL");
		}

		// Verify orgName info Expected Result
		String actOrgName = cip.getOrgName().getText().trim();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + " information is verified==PASS");
		} else {
			System.out.println(orgName + " information is not verified==FAIL");
		}

	}
}
