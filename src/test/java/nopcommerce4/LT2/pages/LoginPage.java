package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	// Email 文本框
	@FindBy(id="Email")
	public WebElement EmailBox;
	
	// password 输入框
	@FindBy(id="Password")
	public WebElement PasswordBox;
	
	// login按钮
	@FindBy(xpath="//input[@class='button-1 login-button']")
	public WebElement LoginButton;
	
	// Forgot password 链接
	@FindBy(linkText = "Forgot password?")
	public WebElement ForgotPassword;
	
	// Register
	@FindBy(xpath="//input[@class='button-1 register-button']")
	public WebElement RegisterButton;
	
	// 初始化创建的页面元素
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	//	PageFactory.initElements(driver, this);
	}
	
	// 点击登录
	public HomePage login(String email,String password) {
		EmailBox.clear();
		EmailBox.sendKeys(email);
		PasswordBox.clear();
		PasswordBox.sendKeys(password);
		LoginButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

	// 点击注册
	public void register() {
		RegisterButton.click();
	}
	
	// 点击忘记密码
	public PasswordRecoveryPage forgotPassword() {
		ForgotPassword.click();
		return PageFactory.initElements(driver, PasswordRecoveryPage.class);
	}
}
