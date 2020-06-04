package nopcommerce4.LT2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordRecoveryPage {
   WebDriver driver;
   
   public PasswordRecoveryPage(WebDriver driver) {
	   this.driver=driver;
   }
   
   
   @FindBy(id="Email")
   WebElement emailBox;
 
   @FindBy(name="send-email")
   WebElement recoverButton;
   
   @FindBy(xpath="//div[@class='result']")
   WebElement result;
     
   public void Recovery(String email) {
	   emailBox.sendKeys(email);
	   recoverButton.click();
   }
   
   public Boolean checkResult(String str) {
	   if(result!=null) {
		 Boolean ifSendSuccess= result.getText().contentEquals(str);
		 return ifSendSuccess;
	   }else {
		 return false;
	   }
   }
}
