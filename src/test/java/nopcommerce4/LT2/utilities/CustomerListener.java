package nopcommerce4.LT2.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomerListener implements ITestListener {
	
	public static WebDriver driver;
	public static ExtentReports exReport;
	public static ExtentTest exTest;
	
	public void onTestSuccess(ITestResult result) {
		//exTest.log(LogStatus.INFO, "测试方法执行成功：" + result.getName());
		
		try {
			String path=Screenshots.getScreenshots(driver,result.getName());
			String imagepath= exTest.addScreenCapture(path);
			
			exTest.log(LogStatus.PASS, result.getName()+"is success", imagepath);
			exReport.endTest(exTest);
			exReport.flush();
			
						
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.getStackTrace();
		}
		
		
	}

	public void onTestFailure(ITestResult result) {
		exTest.log(LogStatus.FAIL, "测试方法执行失败：" + result.getName());
		exReport.endTest(exTest);
		exReport.flush();
		
	}

	public void onFinish(ITestContext context) {

	}

	public void onStart(ITestContext context) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestStart(ITestResult result) {

	}

}
