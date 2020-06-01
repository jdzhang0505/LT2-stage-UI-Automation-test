package nopcommerce4.LT2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
	}

	// FirstName 文本框
	public WebElement firstNameBox(WebDriver driver) {
	  WebElement firstName = driver.findElement(By.id("FirstName"));
		return firstName;
	}

	// LastName 文本框
	public WebElement lastNameBox(WebDriver driver) {
		WebElement lastName = driver.findElement(By.id("LastName"));
		return lastName;
	}

	// Email 文本框
	public WebElement emailBox(WebDriver driver) {
		WebElement email = driver.findElement(By.id("Email"));
		return email;
	}

	// School name 文本框
	public WebElement schoolNameBox(WebDriver driver) {
		WebElement schoolName = driver.findElement(By.id("Company"));
		return schoolName;
	}

	// Password 文本框
	public WebElement PasswordBox(WebDriver driver) {
		WebElement password = driver.findElement(By.id("Password"));
		return password;
	}

	// ConfirmPassword 文本框
	public WebElement confirmPasswordBox(WebDriver driver) {
		WebElement confirmPWD = driver.findElement(By.id("ConfirmPassword"));
		return confirmPWD;
	}

	// Register-button 按钮
	public WebElement registerButton(WebDriver driver) {
		WebElement registerButton = driver.findElement(By.id("register-button"));
		return registerButton;
	}
	
	// Registration complete prompt information
	public WebElement registerSuccessInfo(WebDriver driver) {
		WebElement successInfo=driver.findElement(By.className("result"));
		return successInfo;
	}
	
	
	// Registration action
	public void registerUser(String firstname, String lastname, String email, String schoolname, String password, String confirmpsw) {
		firstNameBox(driver).sendKeys(firstname);
		lastNameBox(driver).sendKeys(lastname);
		emailBox(driver).sendKeys(email);
		schoolNameBox(driver).sendKeys(schoolname);
	    PasswordBox(driver).sendKeys(password);
	    confirmPasswordBox(driver).sendKeys(confirmpsw);
	    registerButton(driver).click();
				
	}
	
	public Boolean checkRegisterSuccess(String str) {
		Boolean ifRegisterSuccess=registerSuccessInfo(driver).getText().contentEquals(str);
		return ifRegisterSuccess;
	}

}
