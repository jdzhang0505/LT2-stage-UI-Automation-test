package nopcommerce4.LT2.test;

import java.net.MalformedURLException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import nopcommerce4.LT2.pages.HomePage;
import nopcommerce4.LT2.pages.LoginPage;
import nopcommerce4.LT2.pages.MyAccountPage;
import nopcommerce4.LT2.utilities.Contents;
import nopcommerce4.LT2.utilities.CustomerListener;
import nopcommerce4.LT2.utilities.ExcelUtilities;

public class MyAccountTest extends CommonTest {

	String url;
	LoginPage login;
	MyAccountPage myAccountPage;
	HomePage homePage;

	@Parameters({ "browser" })

	@BeforeClass
	public void beforeClass(String browser) throws MalformedURLException {
		super.beforeClass(browser);

		url = "http://lifetouch2-stage.taylordigital.io/login";
		login = PageFactory.initElements(driver, LoginPage.class);
		driver.get(url);

		ExcelUtilities.setExcelFile(Contents.FilePath + Contents.FileName, Contents.SheetName);
		test = exReport.startTest("test login and myaccount");
		CustomerListener.exTest = test;
	}

	@DataProvider(name = "LoginData")
	public Object[][] dataProvider() {

		// get用户登录成功的测试数据（用户名，密码）
		// Object[][] userInfo = ExcelUtilities.getData("valid_login");

		// test用户登录失败的测试数据（用户名，密码）
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

	@Test(enabled = true, dependsOnMethods = "testLoginUsingProvider")
	public void testMyAccount() {
		homePage.clickPersonalMenu();
		myAccountPage = homePage.clickMyAccount();

		myAccountPage.accessCutomerInfo();
		myAccountPage.editCutomerInfo("jdtest1", "test", "NLTCtest");

		myAccountPage.accessAddressList();
		myAccountPage.addNewAddress("test", "LT2", "testLT001@1.com", "United States", "Arizona", "TJ", "Huayuan",
				"33001", "13389960908");
		myAccountPage.editNewAddress("addresstest1");
		myAccountPage.deleteNewAddress();

		myAccountPage.accessOrdersList();
		myAccountPage.viewTopOrderDetail();

		myAccountPage.accessChangePassswordPage();

		myAccountPage.checkFooterLinks();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		driver.quit();
	}

}
