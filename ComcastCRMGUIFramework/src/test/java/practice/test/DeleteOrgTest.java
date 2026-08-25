package practice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtitlity;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
	public static void main(String[] args) throws Exception {
		FileUtitlity flib = new FileUtitlity();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// read TestScript data from Excel file
		String orgName = elib.getDataFromExcel("org", 10, 2) + jlib.getRandomNumber();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		// step 1: login to application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

		// step 2 : navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
//		hp.navigateToCampaignPage();

		// step 3 : click on "Create Organization" Button
		OrganizationsPage onp = new OrganizationsPage(driver);
		onp.getCreateNewOrgBtn().click();

		// step 4 : Enter all the details and create an Organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify Header message Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified==PASS");
		} else {
			System.out.println(orgName + " name is not verified==FAIL");
		}

		// go back to Organizations Page
		hp.getOrgLink().click();

		// search for Organization
		onp.getSearchEdt().sendKeys(orgName);
		wlib.select(onp.getSearchDD(), 1);
		onp.getSearchBtn().click();

		// In dynamic webtable select & delete organization
		driver.findElement(By.xpath("//a[text()='" + orgName + "']/parent::td/following-sibling::td/a[text()='del']"))
				.click();
		wlib.switchToAlertAndAccept(driver);

		// logout
		hp.logout();

		driver.quit();

	}
}
