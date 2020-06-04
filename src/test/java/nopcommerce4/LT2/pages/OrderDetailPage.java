package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailPage {
   	
   WebDriver driver;
   public OrderDetailPage(WebDriver driver){
	   this.driver=driver;
   }
   
   @FindBy(xpath="//div[@class='page-title']/h1")
   WebElement orderInformation;
   
   @FindBy(xpath="//a[text()='Print']")
   WebElement printLink;
   
   @FindBy(xpath="//a[text()='PDF Invoice']")
   WebElement pdfInvoiceLink;
   
   @FindBy(xpath="//table[@class='data-table']/tbody/tr[1]//input[@value='Re-order']")
   WebElement firstItemReorderButton;
   
   public Boolean isOrderInformationShown() {
	   if(orderInformation!=null) {
		   return true;
       }else {
    	   return false;
       }
	   
   }
}
