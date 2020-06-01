package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PaymentInfoPage {

	WebDriver driver;

	public PaymentInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "cphContent_ddlCreditCardType")
	WebElement cardType;

	@FindBy(id = "cphContent_txtCreditCardNumber")
	WebElement cardNumber;

	@FindBy(id = "cphContent_ddlExpirationMonth")
	WebElement expirationMon;

	@FindBy(id = "cphContent_ddlExpirationYear")
	WebElement expirationYear;

	@FindBy(id = "cphContent_txtCVC")
	WebElement securityCode;

	@FindBy(id = "cphContent_btnSubmit")
	WebElement placeOrderButton;

	private void getSelectOption(WebElement elem, String value) {
		Select sel = new Select(elem);
		sel.selectByValue(value);
	}

	public OrderCompletePage placeOrder() {

		driver.switchTo().frame("TPGFrame");
		getSelectOption(cardType, "VISA");
		cardNumber.sendKeys("4012888888881881");
		getSelectOption(expirationMon, "08");
		getSelectOption(expirationYear, "2029");
		securityCode.sendKeys("123");
		placeOrderButton.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return PageFactory.initElements(driver, OrderCompletePage.class);
	}

}
