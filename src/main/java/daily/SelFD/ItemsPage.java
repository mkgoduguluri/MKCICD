package daily.SelFD;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import daily.Abstract.AbstractMethods;

public class ItemsPage extends AbstractMethods {

	WebDriver driver;

	public ItemsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> items;

	@FindBy(css = ".mb-3 button[class='btn w-10 rounded']")
	List<WebElement> addButtons;

	@FindBy(css = ".ng-animating")
	WebElement animation;

	

	By listItems = By.cssSelector(".mb-3");
	By prodName = By.cssSelector("div div h5");
	By cardButton = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");

	public List<WebElement> listProducts() {

		waitForElementVisibility(listItems);
		return items;

	}

	/*
	 * public List<WebElement> listbuttons() {
	 * 
	 * return addButtons;
	 * 
	 * }
	 */

	public void prodFind(ArrayList<String> a1) throws InterruptedException {
		for (WebElement item1 : items) {

			Thread.sleep(2000);

			if (a1.contains(item1.findElement(prodName).getText())) {

				item1.findElement(cardButton).click();

			} // buttons.get(a).click();

		}
		waitForElementVisibility(toast);
		waitForElementInvisiblity(animation);
	}

	

}
