package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletePage {
    WebDriver driver;
    
    public OrderCompletePage(WebDriver driver) {
    	this.driver=driver;
    }
    
   @FindBy(xpath="//div[@class='order-number']/strong[contains(text(),'Order number')]")
   WebElement orderNumber;
   
   @FindBy(xpath="//div[@class='details-link']/a")
   WebElement orderDetailButton;
   
   public OrderDetailPage accessOrderDetailPage() {
	   orderDetailButton.click();
	   return PageFactory.initElements(driver, OrderDetailPage.class);
   }
   
   public Boolean isOrderNumberShown() {
	   if(orderNumber!=null) {
		   return true;
	   }else {
		   return false;
	   }
   }
   
}
