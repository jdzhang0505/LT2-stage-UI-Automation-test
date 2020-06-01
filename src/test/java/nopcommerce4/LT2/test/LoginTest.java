package nopcommerce4.LT2.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import nopcommerce4.LT2.pages.LoginPage;
import nopcommerce4.LT2.utilities.CustomerListener;

@Listeners(CustomerListener.class)
public class LoginTest extends CommonTest {
	LoginPage login;
	String url;
	
	@BeforeClass
	public void beforeClass() {
		//super.beforeClass();
		url = "http://lifetouch2-stage.taylordigital.io/login";
		login = PageFactory.initElements(driver, LoginPage.class);
		driver.get(url);
		
		test= exReport.startTest("Login using common parameters");
		CustomerListener.exTest=test;
	}

	@Test
	public void testLoginCommonData() {

		login.login("jdzhang@nltechdev.com", "11111111");
		
	}

	@Test(enabled = false)
	public void testRegister() {
		login.register();
	   
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
           super.afterClass();
	}

}
