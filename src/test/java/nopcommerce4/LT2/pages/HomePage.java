package nopcommerce4.LT2.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(id = "header-links-opener")
	public WebElement personalMenu;

	// MyAccount链接
	@FindBy(xpath = "//a[contains(text(),'My account')]")
	public WebElement myAccount;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		// PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Log out']")
	public WebElement logout;

	// header navigation item:Product Type
	@FindBy(xpath = "//div[@class='header-menu']//a[text()='Product Type']")
	WebElement productTypeCategory;

	// header navigation sub-item of Product Type: Banners
	@FindBy(xpath = "//div[@class='header-menu']//a[text()='Banners']")
	WebElement bannersCategory;

	//a 登录后点击personalMenu
	public void clickPersonalMenu() {
		personalMenu.click();
	}
	
	public MyAccountPage clickMyAccount() {
		Actions action=new Actions(driver);
		clickPersonalMenu();
		action.moveToElement(myAccount).click().perform();
		
		return PageFactory.initElements(driver, MyAccountPage.class);
	}
	

	// a登录后验证my account
	public boolean checkMyAccount() {
		if (myAccount != null) {
			return true;
		} else {
			return false;
		}
	}
	

	//a 登录后验证logout
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
	
	// a 进入product Type category page
	public void accessProductTypePage() {
		productTypeCategory.click();
	}
	
	// a进入子菜单banners category page
	public CategoryPage accessBannersPage() {
		Actions action=new Actions(driver);
		action.moveToElement(productTypeCategory).perform();
		action.moveToElement(bannersCategory).click().perform();
		return PageFactory.initElements(driver, CategoryPage.class);
	}
}
