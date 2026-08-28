package com.comcast.crm.generic.listenerUtility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass extends JavaUtility implements ITestListener, ISuiteListener {

	public ExtentReports report;
	public ExtentSparkReporter spark;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		spark = new ExtentSparkReporter("./AdvanceReport/report_" + getCurrentTimeStamp() + ".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "Chrome-150");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("==== ====>" + result.getMethod().getMethodName() + "<====START====");
		ExtentTest test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		UtilityClassObject.getTest().log(Status.INFO, result.getMethod().getMethodName() + "===> STARTED <===");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("==== ====>" + result.getMethod().getMethodName() + "<====END====");
		UtilityClassObject.getTest().log(Status.PASS, result.getMethod().getMethodName() + "===> COMPLETED <===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getDriver();
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		String time = getCurrentTimeStamp();

		UtilityClassObject.getTest().addScreenCaptureFromBase64String(filePath, testName + "_" + time);
		UtilityClassObject.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "===> FAILED <===");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

}
