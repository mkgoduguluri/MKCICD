package daily.SelFD;

import java.util.ArrayList;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import daily.Abstract.AbstractMethods;

public class cartPage extends AbstractMethods {

	WebDriver driver;

	public cartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;

	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement checkOutButton;

	String script = "window.scrollBy(0,1000)";

	public void cartCheckout(ArrayList<String> a1) {

		for (WebElement cartItem : cartItems) {

			if (a1.contains(cartItem.getText())) {

				Assert.assertTrue(true);
			}
		}

	}

	public CheckOutPage checkOutforPayment() throws InterruptedException {

		javaScriptExecutor(script);
		Thread.sleep(1000);
		checkOutButton.click();
		CheckOutPage CO = new CheckOutPage(driver);
		return CO;
	}

}
