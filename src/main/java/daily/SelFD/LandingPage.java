package daily.SelFD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import daily.Abstract.AbstractMethods;

public class LandingPage extends AbstractMethods {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement passWord;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orderButton;

	public ItemsPage loginSys(String user, String pword) {

		username.sendKeys(user);
		passWord.sendKeys(pword);
		login.click();
		ItemsPage it = new ItemsPage(driver);
		return it;

	}
	
	public String errorMessage() {
		waitForElementvisiblity(errorMessage);
		return errorMessage.getText();
		
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public OrderPage orderButton() {
		orderButton.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}

}
