package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
//		WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		// search product
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(brandName);
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		System.out.println(driver
				.findElement(By.xpath(
						"//span[contains(text(),'" + productName + "')]/../../../..//span[@class='a-price-whole']"))
				.getText());

		driver.quit();

	}

	@DataProvider
	public Object[][] getData() throws Exception {

		ExcelUtility eLib = new ExcelUtility();
		int rowCount = eLib.getRowCount("product");

		Object[][] objArr = new Object[rowCount][2];

		for (int i = 0; i < rowCount; i++) {
			objArr[i][0] = eLib.getDataFromExcel("product", i + 1, 0);
			objArr[i][1] = eLib.getDataFromExcel("product", i + 1, 1);
		}
		return objArr;
	}
}
