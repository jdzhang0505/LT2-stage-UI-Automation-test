package nopcommerce4.LT2.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage {
	WebDriver driver;
	
	
	public CategoryPage(WebDriver driver){
		this.driver=driver;
		
	}
	
	@FindBy(xpath="//div[@class='details']//a[@href='/gameoflife-banner-3-x-7']")
	WebElement product;
	
	public ProductPage accessProductPage() {
		
	   try {
		if(product!=null) {
			product.click();
			
		}
	   }catch(Exception e) {
		   e.getStackTrace();
	   }
	   return PageFactory.initElements(driver, ProductPage.class);
	}
	

}
