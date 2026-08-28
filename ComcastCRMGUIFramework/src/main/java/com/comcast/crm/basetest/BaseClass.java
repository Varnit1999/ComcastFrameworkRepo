package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtitlity;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	public DatabaseUtility dblib = new DatabaseUtility();
	public FileUtitlity flib = new FileUtitlity();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();

	public WebDriver getDriver() {
		return UtilityClassObject.getDriver();
	}

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws SQLException {
		System.out.println("===connect to DB, Report config===");
		dblib.getDbConnection();
	}

//	@Parameters("BROWSER")
//	@BeforeTest(groups = { "smokeTest", "regressionTest" })
//	public void configBC(String browser) throws Exception {
//		WebDriver driver;
//		System.out.println("===Launch the Browser===");
////		String BROWSER = flib.getDataFromPropertiesFile("browser");
//		String BROWSER = browser;
//		if (BROWSER.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (BROWSER.equals("firefox")) {
//			driver = new FirefoxDriver();
//		} else if (BROWSER.equals("edge")) {
//			driver = new EdgeDriver();
//		} else {
//			driver = new ChromeDriver();
//		}
//		UtilityClassObject.setDriver(driver);
//		getDriver().manage().window().maximize();
//	}

	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	public void configBC() throws Exception {
		WebDriver driver;
		System.out.println("===Launch the Browser===");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		UtilityClassObject.setDriver(driver);
		getDriver().manage().window().maximize();
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws Throwable {
		System.out.println("==login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(getDriver());
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("=Logout=");
		HomePage hp = new HomePage(getDriver());
		hp.logout();
	}

//	@AfterTest(groups = { "smokeTest", "regressionTest" })
//	public void configAC() {
//		System.out.println("==Close the Browser==");
//		getDriver().quit();
//		UtilityClassObject.removeDriver();
//	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAC() {
		System.out.println("==Close the Browser==");
		getDriver().quit();
		UtilityClassObject.removeDriver();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("===close DB, Report Backup===");
		dblib.closeDbConnection();
	}
}
