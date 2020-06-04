package nopcommerce4.LT2.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalizePage {
	WebDriver driver;
	JavascriptExecutor js;

	public PersonalizePage(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//div[@id='MainContent_divNextButton_UP']/a[@id='A1']")
	WebElement nextButton;

	@FindBy(id = "ContentPlaceHolder1_chbAcceptTerms")
	WebElement checkbox;

	@FindBy(xpath = "//a[@id='viewPDF']")
	WebElement viewPDF;

	@FindBy(id = "actionButton")
	WebElement approveButton;

	public ShoppingCartPage addToCart() {
		// scroll滚动条移动到next link可见的位置后再去点击
		// js.executeScript("window.scrollBy(0,700);");

		driver.switchTo().frame("navInk_DesignStudio");
		driver.switchTo().frame("productConfigFrame");

		Actions action = new Actions(driver);
		action.moveToElement(nextButton).click().perform();

		// nextButton.click();

		// wait for the popup method one:
		/*
		 * try { Thread.sleep(5000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// wait for the popup method two:
		// driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		// wait for the popup method three:
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divPopUp']")));
		driver.switchTo().frame("iframePopUp");

		checkbox.click();
		approveButton.click();

		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return PageFactory.initElements(driver, ShoppingCartPage.class);
	}

}
