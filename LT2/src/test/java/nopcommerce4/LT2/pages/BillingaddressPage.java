package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BillingaddressPage {

	WebDriver driver;

	public BillingaddressPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(className = "page-title")
	WebElement billingAddressTitle;

	@FindBy(xpath = "//div[@class='section select-billing-address']//div[@class='address-item'][3]//input")
	WebElement thirdBillingaddressButton;

	public Boolean isTitleShown() {
	   Boolean isShow = billingAddressTitle.getText().equalsIgnoreCase("Billing address");
	   return isShow;
	}
	
	public ShippingMethodPage accessShippingMethodPage()
	{
		thirdBillingaddressButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return PageFactory.initElements(driver, ShippingMethodPage.class);
	}
}
