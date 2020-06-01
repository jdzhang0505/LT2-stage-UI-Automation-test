package nopcommerce4.LT2.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MyAccountPage {
	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='page-title']/h1[text()='My account']")
	WebElement myAccount;

	// AccountInfo tab
	@FindBy(xpath = "//li[@class='customer-info']//a[contains(text(),'Account info')]")
	WebElement accountInfoLink;

	@FindBy(id = "FirstName")
	WebElement firstNameInputBox;

	@FindBy(id = "LastName")
	WebElement lastNameInputBox;

	@FindBy(id = "Email")
	WebElement EmailInputBox;

	@FindBy(id = "Company")
	WebElement schoolInputBox;

	@FindBy(name = "save-info-button")
	WebElement saveButton;

	// Address tab
	@FindBy(xpath = "//li[@class='customer-addresses']//a[contains(text(),'Addresses')]")
	WebElement addressLink;

	@FindBy(xpath = "//div[@class='section address-item']//button[@value='Edit']")
	List<WebElement> addEditButtons;

	@FindBy(xpath = "//div[@class='section address-item']//button[@value='Delete']")
	List<WebElement> addDeleteButtons;

	@FindBy(xpath = "//input[@class='button-1 add-address-button']")
	WebElement addNewAddressButton;

	@FindBy(id = "Address_FirstName")
	WebElement addFirstNameInputBox;

	@FindBy(id = "Address_LastName")
	WebElement addLastNameInputBox;

	@FindBy(id = "Address_Email")
	WebElement addEmailInputBox;

	@FindBy(id = "Address_CountryId")
	WebElement addCountrySelect;

	@FindBy(id = "Address_StateProvinceId")
	WebElement addStateSelect;

	@FindBy(id = "Address_City")
	WebElement addCityInputBox;

	@FindBy(id = "Address_Address1")
	WebElement addAddress1InputBox;

	@FindBy(id = "Address_ZipPostalCode")
	WebElement addZipCodeInputBox;

	@FindBy(id = "Address_PhoneNumber")
	WebElement addPhoneNumInputBox;

	@FindBy(xpath = "//input[@class='button-1 save-address-button']")
	WebElement addSaveButton;
	
    // orders tab
	@FindBy(xpath = "//li[@class='customer-orders']//a[contains(text(),'Orders')]")
	WebElement ordersLink;
	
	@FindBy(xpath="//div[@class='order-list']/div[@class='section order-item'][1]//button[@value='VIEW ORDER DETAILS']")
	WebElement topOrderViewButton;

	@FindBy(xpath = "//li[@class='change-password']//a[contains(text(),'Change password')]")
	WebElement changePasswordLink;
	
	//footer links
	@FindBy(linkText = "Lifetouch.com")
	WebElement lifetouchDotCom; 
	
	@FindBy(linkText = "Privacy Notice")
	WebElement PrivacyDotNotice;
	
	@FindBy(linkText="Conditions of Use")
	WebElement ConditionsOfUse;
	
	@FindBy(linkText="About Us")
	WebElement AboutUs;
	
	@FindBy(linkText="Account info")
	WebElement Accountinfo;
	
	@FindBy(linkText="Orders")
	WebElement Orders;
		
	@FindBy(linkText="Shopping Cart")
	WebElement ShoppingCart;

	@FindBy(linkText="Wishlist")
	WebElement Wishlist;
	
	@FindBy(linkText="Contact Us")
	WebElement ContactUs;
	
	@FindBy(linkText="FAQs")
	WebElement FAQs;
	
	@FindBy(linkText="Shipping Table")
	WebElement ShippingTable;
	
	@FindBy(xpath="//div[@class='footer']//a")
	List<WebElement> allFooterlinks;
	
	public void accessCutomerInfo() {
		accountInfoLink.click();
	}

	public void editCutomerInfo(String firstName, String lastName, String schoolName) {
		firstNameInputBox.clear();
		firstNameInputBox.sendKeys(firstName);

		lastNameInputBox.clear();
		lastNameInputBox.sendKeys(lastName);

		schoolInputBox.clear();
		schoolInputBox.sendKeys(schoolName);
		saveButton.click();
	}

	public void accessAddressList() {
		addressLink.click();
	}

	public void addNewAddress(String addFirstName, String addLastName, String addEmail, String addCountry,
			String addState, String addCity, String addAddress1, String addZipcode, String addPhoneNum) {
		addNewAddressButton.click();
		addFirstNameInputBox.sendKeys(addFirstName);
		addLastNameInputBox.sendKeys(addLastName);
		addEmailInputBox.sendKeys(addEmail);
		selectOption(addCountrySelect, addCountry);
		selectOption(addStateSelect, addState);
		addCityInputBox.sendKeys(addCity);
		addAddress1InputBox.sendKeys(addAddress1);
		addZipCodeInputBox.sendKeys(addZipcode);
		addPhoneNumInputBox.sendKeys(addPhoneNum);
		addSaveButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void editNewAddress(String newAddress1) {
		WebElement newAddEdit = getLastAddButtons().get(0);
		newAddEdit.click();
		addAddress1InputBox.clear();
		addAddress1InputBox.sendKeys(newAddress1);
		addSaveButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteNewAddress() {
		WebElement newAddDelete = getLastAddButtons().get(1);
		newAddDelete.click();
		driver.switchTo().alert().accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<WebElement> getLastAddButtons() {
		WebElement lastAddEditButton = null;
		WebElement lastAddDeleteButton = null;
		List<WebElement> list = new ArrayList<WebElement>();

		int list_size = addEditButtons.size();
		lastAddEditButton = addEditButtons.get(list_size - 1);
		lastAddDeleteButton = addDeleteButtons.get(list_size - 1);

		list.add(lastAddEditButton);
		list.add(lastAddDeleteButton);

		/*
		 * for (int i = 0; i < list.size(); i++) {
		 * System.out.println(list.get(i).getAttribute("onclick")); }
		 */

		return list;

	}

	public void selectOption(WebElement elem, String text) {
		Select sel = new Select(elem);
		sel.selectByVisibleText(text);
	}
	
	public void accessOrdersList() {
		ordersLink.click();
	}
	
	public void viewTopOrderDetail() {
		topOrderViewButton.click();
		
		// back to the order history list page
		driver.navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void accessChangePassswordPage() {
		changePasswordLink.click();
	}
	
	// check all the footer links
	public void checkFooterLinks() {
		List<WebElement> footerLinks=getFooterLinks();
		for(WebElement link:footerLinks) {
			String href=link.getAttribute("href");
			String linkText=link.getText();
			try {
				String responseMsg=getLinkStatus(new URL(href));
				System.out.println(linkText+" is: "+responseMsg);
			} catch (MalformedURLException e) {
				
				e.getMessage();
			}
			
		}
	}
	
	
	// get all the clickable links in the footer
	public List<WebElement> getFooterLinks() {
		List<WebElement> linksToclick=new ArrayList<WebElement>();
		for(WebElement elem:allFooterlinks) {
			if(elem.getAttribute("href")!=null) {
				linksToclick.add(elem);
			}
		}
		
		return linksToclick;
	 }
	
	public String getLinkStatus(URL url) {
		try {
			HttpURLConnection http=(HttpURLConnection) url.openConnection();
			http.connect();
			String responseMessage=http.getResponseMessage();
			http.disconnect();
			return responseMessage;
		} catch (Exception e) {
			return e.getMessage();
		} 
	}

}
