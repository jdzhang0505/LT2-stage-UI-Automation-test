package nopcommerce4.LT2.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
	WebDriver driver;
	JavascriptExecutor js;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//h1[text()='Shopping cart']")
	WebElement cartHeader;

	@FindBy(xpath = "//input[@name='removefromcart']")
	List<WebElement> removeCheckboxes;

	@FindBy(xpath = "//div[@class='schoolnamediv']/label[@class='schoolnamelabel']")
	WebElement schoolNameLabel;

	@FindBy(xpath = "//div[@class='title']/strong[text()='Discount Code']")
	WebElement discountLabel;

	@FindBy(id = "checkout_attribute_2")
	WebElement schoolNameTextBox;

	@FindBy(xpath = "//ul[@id='ui-id-1']/Li")
	List<WebElement> schoolNameAllItem;

	@FindBy(xpath = "//ul[@id='ui-id-1']/Li[1]")
	WebElement schoolNameFirstItem;

	@FindBy(id = "snError")
	WebElement schoolNameErrorMsg;

	@FindBy(name = "updatecart")
	WebElement updateCartButton;

	@FindBy(name = "continueshopping")
	WebElement continueShoppingButton;

	@FindBy(id = "discountcouponcode")
	WebElement discountInput;

	@FindBy(id = "giftcardcouponcode")
	WebElement giftcardInput;

	@FindBy(id = "checkout")
	WebElement checkoutButton;

	@FindBy(id = "estimate-shipping-button")
	WebElement estimateButton;
	
	public void removeProductItem() {
		
		//click the checkbox of the first item in the cart
		removeCheckboxes.get(0).sendKeys(Keys.SPACE);
		updateCartButton.click();		
	}
	

	public Boolean inputSchoolName(String schoolName) {
		schoolNameTextBox.clear();
		schoolNameTextBox.sendKeys(schoolName);

		try {
			Thread.sleep(2000);
		
			int size = schoolNameAllItem.size();
			System.out.println("list size is:" + size);

			if (size > 0) {
				// scroll to 搜索下拉列表的第一项
				js.executeScript("arguments[0].scrollIntoView(false);", schoolNameFirstItem);

				// js.executeScript("window.scrollBy(0,-100);");

				schoolNameFirstItem.click();

			} else {
				js.executeScript("window.scrollBy(0,-300);");
			}
			schoolNameLabel.click();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.getStackTrace();
		}

		String schoolNameValue = schoolNameTextBox.getAttribute("value");
		System.out.println(schoolNameValue);
		Boolean isShow = schoolNameErrorMsg.getText().contains("School Name is not recognized as a Lifetouch school");

		return isShow;
	}
	
	public BillingaddressPage checkout() {
		checkoutButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return PageFactory.initElements(driver, BillingaddressPage.class); 
		
	}
}
