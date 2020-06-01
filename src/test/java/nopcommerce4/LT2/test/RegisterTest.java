package nopcommerce4.LT2.test;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import nopcommerce4.LT2.pages.RegisterPage;
import nopcommerce4.LT2.utilities.Contents;
import nopcommerce4.LT2.utilities.CustomerListener;
import nopcommerce4.LT2.utilities.ExcelUtilities;

@Listeners(CustomerListener.class)
public class RegisterTest extends CommonTest{
	RegisterPage page;
	String url;
	
  @Parameters({"browser"})
  @BeforeClass
  public void beforeClass(String browser) throws MalformedURLException {
	  super.beforeClass(browser);
	  ExcelUtilities.setExcelFile(Contents.FilePath+Contents.FileName, "Register");
	  
	  url="http://lifetouch2-stage.taylordigital.io/register";
	  
	  page=new RegisterPage(driver);
	  driver.get(url);
	  
	  test= exReport.startTest("Verify the registration");
	  CustomerListener.exTest=test;
	  
  }
  
  @DataProvider(name="registerinfo")
  public Object[][] dataProvider() {
		/*
		 * String[][] userRegisterinfo=
		 * {{"testLT001","testLT001","testLT001@test.com","testschool","111111",
		 * "111111"} };
		 */
	  Object[][] userRegisterinfo=ExcelUtilities.getData("Registerinfo");
	  
	  return userRegisterinfo;
  }
  
  
  @Test(dataProvider = "registerinfo")
  public void registerTest(String firstname, String lastname, String email, String schoolname, String password, String confirmpsw) {
	  page.registerUser(firstname, lastname, email, schoolname, password, confirmpsw);
	  
	  Boolean result=page.checkRegisterSuccess("Your registration completed");
	  Assert.assertTrue(result);
  }

  @AfterClass
  public void afterClass() throws InterruptedException {
	  super.afterClass();
  }

}
