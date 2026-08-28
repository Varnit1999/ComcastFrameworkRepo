package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for Contact Module
 * 
 * @author Varnit
 * 
 */

public class SearchContactTest extends BaseClass {

	/**
	 * Scenario : login() ==> navigatetoContact ==> createContact() ==> verify
	 * 
	 */

	@Test
	public void searchContactTest() {
		/* step-1 : login to app */
		LoginPage lp = new LoginPage(getDriver());
	}
}
