package nopcommerce4.LT2.test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import nopcommerce4.LT2.utilities.CustomerListener;
import nopcommerce4.LT2.utilities.ExtentReportsFactory;


public class CommonTest {
	WebDriver driver;
	ExtentReports exReport;
	ExtentTest test;

	
	@BeforeClass
	public void beforeClass(String browser) throws MalformedURLException {
		
		
		driver= getDriverInstance(browser);
		
		exReport=ExtentReportsFactory.getExReportInstance();
        
		CustomerListener.driver=driver;
		CustomerListener.exReport=exReport;
				
	}
	
	
	public static WebDriver getDriverInstance(String browser) throws MalformedURLException {
		
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			
		}
		if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		
		
		return driver;
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(2);
		driver.quit();
	}

}
