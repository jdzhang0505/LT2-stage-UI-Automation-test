package nopcommerce4.LT2.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShippingMethodPage {
	WebDriver driver;

	public ShippingMethodPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(className = "page-title")
	WebElement shippingMethodTitle;

	@FindBy(xpath = "//div[@class='method-name']/label[@for='shippingoption_0']")
	WebElement groundMethod;

	@FindBy(xpath = "//div[@class='method-name']/label[@for='shippingoption_1']")
	WebElement nextDayMethod;

	@FindBy(xpath = "//div[@class='method-name']/label[@for='shippingoption_2']")
	WebElement SecondDayMethod;

	@FindBy(name = "nextstep")
	WebElement nextButton;

	public Boolean isTitleShown() {
		Boolean isShow = shippingMethodTitle.getText().equalsIgnoreCase("Select shipping method");
		return isShow;
	}

	public PaymentInfoPage accessPaymentinfo() {
		nextDayMethod.click();
		nextButton.click();
		return PageFactory.initElements(driver,PaymentInfoPage.class);
	}
}
