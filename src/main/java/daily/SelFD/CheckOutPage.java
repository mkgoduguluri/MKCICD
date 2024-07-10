package daily.SelFD;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import daily.Abstract.AbstractMethods;

public class CheckOutPage extends AbstractMethods {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryField;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement typeAhead;
	
	@FindBy(xpath = "//div[@class='payment__cc']//div[2]//input[1]")
	WebElement cvv;

	@FindBy(xpath = "//form/div/div[3]/div/input")
	WebElement custName;
	
	@FindBy(xpath = "//input[@name='coupon']")
	WebElement couponCode;

	@FindBy(xpath = "//button[normalize-space()='Apply Coupon']")
	WebElement applyCouponCode;
	
	@FindBy(xpath = "//p[@class='mt-1 ng-star-inserted']")
	WebElement couponCodeText;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeOrder;
	
	@FindBy(xpath = "//label[@class='ng-star-inserted'][1]")
	WebElement orderText;

	String script = "window.scrollBy(0,1000)";

	public void addDetails(String country, String cvvCode, String name, String cCode) {
		
		countryField.sendKeys(country);
		waitForElementvisiblity(typeAhead);
		typeAhead.click();
		cvv.sendKeys(cvvCode);
		custName.sendKeys(name);
		couponCode.sendKeys(cCode);
	}

	public void proceedtoPayment() {
		applyCouponCode.click();
		waitForElementvisiblity(couponCodeText);
		System.out.println();
		
	}
	public String couponMessage() {
		return couponCodeText.getText();
		
	}
	
	public void orderPlacement() {
		javaScriptExecutor(script);
		placeOrder.click();
		
	}
	public String getMessage() {
	return orderText.getText();
	}
}
