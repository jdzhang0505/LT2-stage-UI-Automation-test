package nopcommerce4.LT2.test;

import java.net.MalformedURLException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import nopcommerce4.LT2.pages.LoginPage;
import nopcommerce4.LT2.pages.PasswordRecoveryPage;
import nopcommerce4.LT2.utilities.CustomerListener;

@Listeners(CustomerListener.class)
public class ForgotPasswordTest extends CommonTest{
  String url;
  LoginPage loginPage;
  PasswordRecoveryPage recoverPage;
	
  @Parameters({"browser"})
  
  @BeforeClass
  public void beforeClass(String browser) throws MalformedURLException {
	  super.beforeClass(browser);
	  url="http://lifetouch2-stage.taylordigital.io/login";
	  driver.get(url);
	  
	  loginPage=PageFactory.initElements(driver, LoginPage.class);
	  test=exReport.startTest("verify forgot passwrod");
	  CustomerListener.exTest=test;
  }
  @Test
  public void testForgotPassword() {
	  recoverPage = loginPage.forgotPassword();
	  recoverPage.Recovery("jdzhang@nltechdev.com");
	  Boolean ifSent=recoverPage.checkResult("Email with instructions has been sent to you.");
	  Assert.assertTrue(ifSent);
  }

  @AfterClass
  public void afterClass() throws InterruptedException {
	  super.afterClass();
  }

}
