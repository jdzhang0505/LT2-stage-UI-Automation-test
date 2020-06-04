package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(className = "qty-label")
	WebElement qtyLabel;

	@FindBy(xpath = "//span[@class='increase']")
	WebElement qtyIncrease;

	@FindBy(xpath = "//input[@class='qty-input valid']")
	WebElement qtyInputBox;

	@FindBy(xpath = "//span[@class='decrease']")
	WebElement qtyDecrease;

	@FindBy(xpath = "//div[@class='add-to-cart']//button[@value='Continue to personalize']")
	WebElement personalizeButton;

	@FindBy(xpath = "//button/span[contains(text(),'Add to wishlist')]")
	WebElement wishList;

	public void increaseQty(int quantity) {
		int qtyInit = 1;
		if (qtyInit < quantity) {
			qtyIncrease.click();
			qtyInit++;
		}
	}

	public void decreaseQty() {
		qtyDecrease.click();
	}

	public void inputQty(String quantity) {
		qtyInputBox.sendKeys(quantity);

		// input quantity， then focus out the input box by clicking the qty label
		qtyLabel.click();
	}

	public PersonalizePage personalizeProduct() {
		personalizeButton.click();
		return PageFactory.initElements(driver, PersonalizePage.class);
	}
	
	public void addToWishlist() {
		wishList.click();
	}

}
