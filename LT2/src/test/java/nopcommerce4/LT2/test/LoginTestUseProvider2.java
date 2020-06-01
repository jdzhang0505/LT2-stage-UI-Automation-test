package nopcommerce4.LT2.test;

import java.net.MalformedURLException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import nopcommerce4.LT2.pages.HomePage;
import nopcommerce4.LT2.pages.LoginPage;
import nopcommerce4.LT2.utilities.Contents;
import nopcommerce4.LT2.utilities.CustomerListener;
import nopcommerce4.LT2.utilities.ExcelUtilities;

@Listeners(CustomerListener.class)
public class LoginTestUseProvider2 extends CommonTest2 {
	LoginPage login;
	HomePage homePage;
	String url;

	//@Parameters({"platform","browser"})
	@BeforeClass
	public void beforeClass() throws MalformedURLException{
		super.beforeClass();
		url = "http://lifetouch2-stage.taylordigital.io/login";
		System.out.println("url:"+url);
		// login = new LoginPage(driver);
		login = PageFactory.initElements(driver, LoginPage.class);

		driver.get(url);
		ExcelUtilities.setExcelFile(Contents.FilePath + Contents.FileName, Contents.SheetName);

		test = exReport.startTest("Verify login using dataprovider");
		CustomerListener.exTest = test;
	}
	

	@DataProvider(name = "LoginData")
	public Object[][] dataProvider() {

		// 获取用户登录成功的测试数据（用户名，密码）
		// Object[][] userInfo = ExcelUtilities.getData("valid_login");

		// 获取用户登录失败的测试数据（用户名，密码）
		// Object[][] userInfo = ExcelUtilities.getData("invalid_login");

		Object[][] userInfo = ExcelUtilities.getData(Contents.LoginType);
		System.out.println(userInfo);
		return userInfo;
	}

	@Test(dataProvider = "LoginData")
	public void testLoginUsingProvider(String username, String password) {

		homePage = login.login(username, password);
		homePage.clickPersonalMenu();

		Boolean isExistLogout = homePage.checkLogout();
		System.out.println("logout is:" + isExistLogout);

		if (Contents.LoginType.equals("valid_login")) {
			Assert.assertTrue(isExistLogout);

		} else if (Contents.LoginType.equals("invalid_login")) {
			Assert.assertFalse(isExistLogout);
		}
	}

	@Test(enabled = false)
	public void testRegister() {
		login.register();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		//super.afterClass();
		driver.quit();
	}

}
