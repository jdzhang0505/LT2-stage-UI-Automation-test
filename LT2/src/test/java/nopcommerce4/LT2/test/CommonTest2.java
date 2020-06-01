package nopcommerce4.LT2.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import nopcommerce4.LT2.utilities.CustomerListener;
import nopcommerce4.LT2.utilities.ExtentReportsFactory;

public class CommonTest2 {
	WebDriver driver;
	ExtentReports exReport;
	ExtentTest test;

//	@Parameters({"platform","browser"})
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		//driver = new ChromeDriver();
		
		driver= getDriverInstance("winodws","chrome");
		System.out.println("test begin");
		exReport=ExtentReportsFactory.getExReportInstance();
		
//		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		 driver.manage().window().maximize();
        
		CustomerListener.driver=driver;
		CustomerListener.exReport=exReport;
		
			
		
	}
	
	
	public static WebDriver getDriverInstance(String platform,String browser) throws MalformedURLException {
		String nodeUrl="http://10.0.2.15:4444/wd/hub";
		WebDriver driver=null;
		DesiredCapabilities cap=new DesiredCapabilities();
		
		if(platform.equalsIgnoreCase("winodws")) {
			cap.setPlatform(Platform.WINDOWS);
		}
		if(platform.equalsIgnoreCase("Mac")) {
			cap.setPlatform(Platform.MAC);
		}
		if(browser.equalsIgnoreCase("chrome")) {
			cap=DesiredCapabilities.chrome();
			//cap.setBrowserName("chrome");
			
		}
		if(browser.equalsIgnoreCase("firefox")) {
			cap=DesiredCapabilities.firefox();
		}
		 driver=new RemoteWebDriver(new URL(nodeUrl),cap);
		 
		 
		 
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
