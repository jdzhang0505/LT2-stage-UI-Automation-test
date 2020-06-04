package nopcommerce4.LT2.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage {
	WebDriver driver;

	public WishlistPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//input[@name='removefromcart']")
	List<WebElement> removeCheckboxes;
	
	@FindBy(xpath="//button[@name='updatecart']")
    WebElement updateCartButton;
	
	@FindBy(xpath="//button[@value='Email a friend']")
	WebElement emailButton;
	
	public void removeFirstItem() {
		removeCheckboxes.get(0).sendKeys(Keys.SPACE);
		updateCartButton.click();
	}
	
}
