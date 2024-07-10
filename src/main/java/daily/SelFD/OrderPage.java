package daily.SelFD;

import java.util.ArrayList;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import daily.Abstract.AbstractMethods;

public class OrderPage extends AbstractMethods {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> items;

	public void prodFind(ArrayList<String> a1){
		for (WebElement item1 : items) {
			if (a1.contains(item1.getText())) {

				Assert.assertTrue(true);

			}
			else {
				Assert.assertTrue(false);
			}

		}
	}

}
