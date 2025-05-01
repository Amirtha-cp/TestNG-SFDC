package com.Automation.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Automation.Base.BaseTest;

public class SFDCListeners extends BaseTest implements ITestListener, ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		logger.info(suite.getName()+" started for SFDC Developers Edition Web App Testing.");
		extentReportUtilsObj.startExtentReport();
	}
	
	@Override
	public void onFinish(ISuite suite) {
		logger.info(suite.getName()+" endedfor SFDC Developers Edition Web App Testing.");
		extentReportUtilsObj.endExtentReport();
	}
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
//		ITestListener.super.onTestStart(result);
//		logger.info(result.getMethod().getMethodName()+" Using getmethod().getmethodName()");
		logger.info(result.getName()+" Using getName()");
		extentReportUtilsObj.startEachTestcase(result.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info(result.getMethod().getMethodName());
		extentReportUtilsObj.logTestpassed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		logger.error(result.getMethod().getMethodName());
		extentReportUtilsObj.logTestFailedWithException(result.getThrowable());
		String screenshotFileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String path = Constants.SCREENSHOTS_DIRECTORY_PATH+screenshotFileName+".png";
		takeScreenshot(path);
		extentReportUtilsObj.logTestWithscreenshot(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		logger.warn(result.getMethod().getMethodName()+" skiped...........................");
		extentReportUtilsObj.logTestFailed(result.getMethod().getMethodName()+"end with skip...........");

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		logger.info(context.getName());
		logger.info(context.getClass());


	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		logger.info(context.getName());
		logger.info(context.getClass());

	}

}
