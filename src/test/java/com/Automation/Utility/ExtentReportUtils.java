package com.Automation.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ExtentReportUtils {
	
	public static ExtentReports extentReport;
	public static ExtentSparkReporter sparkReport;
	public static ExtentTest extentReportLogger;
	
	private static ExtentReportUtils extentReportObject;
	
	private ExtentReportUtils() { 
		
		//Declared as Private that makes this class as Singleton class. 
		//one of the Java design patterns used in Project implementation.
		
	}
	
	public static ExtentReportUtils getInstance()
	{
		if(extentReportObject == null)
		{
			extentReportObject = new ExtentReportUtils();
		}
		return extentReportObject;
		
	}
	
	public void startExtentReport() {
		extentReport= new ExtentReports();
		sparkReport = new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);
		extentReport.setSystemInfo("Host Name", "Sales Force Developer Edition");
		extentReport.setSystemInfo("OS", "MAC");
		extentReport.setSystemInfo("User Name", "Amirtha");
		
		sparkReport.config().setDocumentTitle("POC Hybrid Framework");
		sparkReport.config().setReportName("Web Application Automation");
		sparkReport.config().setTheme(Theme.DARK);
		extentReport.attachReporter(sparkReport);	
	}
	//Initiated at start of each Suite level
	public void startEachTestcase(String methodName) {
		extentReportLogger = extentReport.createTest(methodName);
	}
	//End of each suite
	public void endExtentReport() {
		extentReport.flush();
	}
	
	public void logTestInfo(String text) {
		//System.out.println("test logger object="+testLogger);
		extentReportLogger.log(Status.INFO,text);
		//testLogger.info(text);
	}
	
	public void logTestpassed(String text) {
		extentReportLogger.log(Status.PASS,MarkupHelper.createLabel(text, ExtentColor.GREEN));
	
		//testLogger.pass(MarkupHelper.createLabel(text, ExtentColor.GREEN));
	}
	
	public void logTestFailed(String text) {
		extentReportLogger.log(Status.FAIL,MarkupHelper.createLabel(text, ExtentColor.RED));
	}
	
	public void logTestFailedWithException(Throwable e) {
		extentReportLogger.log(Status.FAIL,e);
	
		}
	
	public void logTestWithscreenshot(String path) {
		//testLogger.addScreenCaptureFromBase64String(path);
		extentReportLogger.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
	}
	
	
}
