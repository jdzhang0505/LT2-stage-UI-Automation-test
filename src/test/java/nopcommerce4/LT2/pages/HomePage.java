package nopcommerce4.LT2.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		// PageFactory.initElements(driver, this);
	}

	@FindBy(id = "small-searchterms")
	WebElement searchBox;

	@FindBy(xpath = "//input[contains(@class,'search-box-button')]")
	WebElement searchButton;

	@FindBy(id = "header-links-opener")
	public WebElement personalMenu;

	// MyAccount link
	@FindBy(xpath = "//a[contains(text(),'My account')]")
	public WebElement myAccount;

	@FindBy(xpath = "//a[text()='Log out']")
	public WebElement logout;

	@FindBy(xpath = "//div[@class='wishlist-button']/a[@class='ico-wishlist']")
	WebElement wishlistButton;

	@FindBy(xpath = "//div[@class='shopping-cart-link']//a[@href='/cart']")
	WebElement cartButton;

	// header navigation item:Product Type
	@FindBy(xpath = "//div[@class='header-menu']//a[text()='Product Type']")
	WebElement productTypeCategory;

	// header navigation sub-item of Product Type: Banners
	@FindBy(xpath = "//div[@class='header-menu']//a[text()='Banners']")
	WebElement bannersCategory;

	public FilterSearchPage accessSearchPage(String sku) {
		searchBox.sendKeys(sku);
		searchButton.submit();
		return PageFactory.initElements(driver, FilterSearchPage.class);
	}

	// click personalMenu after login
	public void clickPersonalMenu() {
		personalMenu.click();
	}

	public MyAccountPage clickMyAccount() {
		Actions action = new Actions(driver);
		clickPersonalMenu();
		action.moveToElement(myAccount).click().perform();

		return PageFactory.initElements(driver, MyAccountPage.class);
	}

	// check menu "my account" after login
	public boolean checkMyAccount() {
		if (myAccount != null) {
			return true;
		} else {
			return false;
		}
	}

	// check menu "logout" after login
	public boolean checkLogout() {
		try {
			if (logout.isDisplayed()) {
				return true;
			}
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
		return true;

	}
	
	public WishlistPage accessWishlistPage() {
		wishlistButton.click();
		return PageFactory.initElements(driver, WishlistPage.class);
	}

	// access to product Type category page
	public void accessProductTypePage() {
		productTypeCategory.click();
	}

	// access to the sub-menu banners category page
	public CategoryPage accessBannersPage() {
		Actions action = new Actions(driver);
		action.moveToElement(productTypeCategory).perform();
		action.moveToElement(bannersCategory).click().perform();
		return PageFactory.initElements(driver, CategoryPage.class);
	}
}
