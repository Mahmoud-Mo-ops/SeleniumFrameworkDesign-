package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReortObject();
	ExtentTest test;
    ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest> (); //thread safe //tovaoid override

	@Override
	// this Result variable hold all information about that test
	public void onTestStart(ITestResult result) {
		this.test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id(ErrorValidationTest)>test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		try {
			//fields are assosociated to class level not method level
			// so i can not use test method to get the driver 
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String filePath = null;
		try {
			filePath = screenShoot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		// takeSceenShot , Attach to Report
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	}


	// Other methods from ITestListener interface can be implemented similarly
}
