package com.infy.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.infy.base.Base;

public class Listener extends Base implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Sorry Your test is failed");
		captureScreenshot(result.getTestName());
		//it will pass the name of @Test
//		captureScreenshot(result.getMethod().getMethodName()+".png");
//		//it will pass method name under @Test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Congratulations Your test is passed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}
	

}
