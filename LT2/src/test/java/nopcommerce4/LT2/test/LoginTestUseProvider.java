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

import nopcommerce4.LT2.pages.BillingaddressPage;
import nopcommerce4.LT2.pages.CategoryPage;
import nopcommerce4.LT2.pages.HomePage;
import nopcommerce4.LT2.pages.LoginPage;
import nopcommerce4.LT2.pages.OrderCompletePage;
import nopcommerce4.LT2.pages.OrderDetailPage;
import nopcommerce4.LT2.pages.PaymentInfoPage;
import nopcommerce4.LT2.pages.PersonalizePage;
import nopcommerce4.LT2.pages.ProductPage;
import nopcommerce4.LT2.pages.ShippingMethodPage;
import nopcommerce4.LT2.pages.ShoppingCartPage;
import nopcommerce4.LT2.utilities.Contents;
import nopcommerce4.LT2.utilities.CustomerListener;
import nopcommerce4.LT2.utilities.ExcelUtilities;

@Listeners(CustomerListener.class)
public class LoginTestUseProvider extends CommonTest {
	LoginPage login;
	HomePage homePage;
	CategoryPage categoryPage;
	ProductPage productPage;
	PersonalizePage personalizePage;
	ShoppingCartPage shoppingcartPage;
	BillingaddressPage billingAddressPage;
	ShippingMethodPage shippingMethodPage;
	PaymentInfoPage paymentInfoPage;
	OrderCompletePage orderCompletePage;
	OrderDetailPage orderDetailPage;
	String url;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browser) throws MalformedURLException {
		super.beforeClass(browser);
		url = "http://lifetouch2-stage.taylordigital.io/login";
		System.out.println("url:" + url);

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

	@Test(enabled = true, dependsOnMethods = "testLoginUsingProvider")
	public void testAccessCategoryPage() {
		categoryPage = homePage.accessBannersPage();
		Assert.assertTrue(driver.getTitle().contains("Banners"));
	}

	@Test(enabled = true, dependsOnMethods = "testAccessCategoryPage")
	public void testAccessProductPage() {
		productPage = categoryPage.accessProductPage();
		Assert.assertTrue(driver.getTitle().contains("#GameofLife Banner 3' x 7'"));
	}

	@Test(enabled = true, dependsOnMethods = "testAccessProductPage")
	public void testPersonalizeProduct() {
		personalizePage = productPage.personalizeProduct();
	}

	@Test(enabled = true, dependsOnMethods = "testPersonalizeProduct")
	public void testAddToCart() {
		shoppingcartPage = personalizePage.addToCart();

		Assert.assertTrue(driver.getTitle().contains("Shopping Cart"));
	}

	@Test(enabled = true, dependsOnMethods = "testAddToCart")
	public void testInputSchoolName() {
		Boolean result = shoppingcartPage.inputSchoolName("atest");
		Assert.assertFalse(result);
	}
	
	@Test(enabled = true, dependsOnMethods = "testInputSchoolName")
	public void testCheckout() {
		billingAddressPage =  shoppingcartPage.checkout();
		Boolean result=billingAddressPage.isTitleShown();
		Assert.assertEquals(result,Boolean.TRUE);
	}
	
	@Test(enabled = true, dependsOnMethods = "testCheckout")
	public void testAccessShippingMethodPage() {
		shippingMethodPage=billingAddressPage.accessShippingMethodPage();
		Boolean result=shippingMethodPage.isTitleShown();
		Assert.assertEquals(result,Boolean.TRUE);
	}
	
	@Test(enabled = true, dependsOnMethods = "testAccessShippingMethodPage")
	public void testAccessPaymentinfo() {
		paymentInfoPage=shippingMethodPage.accessPaymentinfo();
		/*
		 * Boolean result=shippingMethodPage.isTitleShown();
		 * Assert.assertEquals(result,Boolean.TRUE);
		 */
	}
	
	@Test(enabled = true, dependsOnMethods = "testAccessPaymentinfo")
	public void testPlaceOrder() {
		orderCompletePage=paymentInfoPage.placeOrder();
		Boolean result=orderCompletePage.isOrderNumberShown();
		Assert.assertTrue(result);
	}
	

	@Test(enabled = true, dependsOnMethods = "testPlaceOrder")
	public void testAccessOrderDetailPage() {
		orderDetailPage=orderCompletePage.accessOrderDetailPage();
		Boolean result=orderDetailPage.isOrderInformationShown();
		Assert.assertTrue(result);
	}
	
	
	

	@AfterClass
	public void afterClass() throws InterruptedException {
		 super.afterClass();
		 driver.quit();
	}

}
