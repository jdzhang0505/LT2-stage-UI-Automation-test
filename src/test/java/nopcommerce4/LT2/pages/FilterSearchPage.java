package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterSearchPage {
	WebDriver driver;

	public FilterSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='item-grid']")
	WebElement itemGrid;

	@FindBy(xpath = "//button[@value='Add to wishlist']")
	WebElement addToWishlistButton;

	public void addToWishlist() {
		if (itemGrid.isDisplayed()) {
			addToWishlistButton.click();
		} else {
			System.out.println("There is no search result");
		}
	}
}
