package daily.stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import daily.Base.BaseTest;
import daily.SelFD.CheckOutPage;
import daily.SelFD.ItemsPage;
import daily.SelFD.LandingPage;
import daily.SelFD.cartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	public LandingPage lp;
	public ItemsPage it;
	public cartPage cp;
	public CheckOutPage CO;
	private ArrayList<String> cart;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {

		//initializeDriver();
		lp = LunchApplication();

	}

	// (.+) - regex expression
	// "^.........$ - for regular expression we need to declare these"

	@Given("^I logged in with (.+) and password (.+)$")
	public void I_logged_in_with_username_and_password(String username, String password) {

		it = lp.loginSys(username, password);
	}

	@When("^I add the product (.+) and (.+) from cart$")
	public void i_add_the_products_and_to_the_cart(String productname1, String productname2)
			throws InterruptedException {
		cart = new ArrayList<>();
		cart.add(productname1);
		cart.add(productname2);
		it.prodFind(cart);
		System.out.println("Added to cart: " + cart);
	}

	@And("^check out (.+) and (.+) in cart$")
	public void check_out_from_cart(String productname1, String productname2) throws InterruptedException {
		cart = new ArrayList<>();
		cart.add(productname1);
		cart.add(productname2);
		cp = it.cartButton();
		cp.cartCheckout(cart);
		CO = cp.checkOutforPayment();
		CO.addDetails("India", "664", "Manoj", "rahulshettyacademy");
		CO.proceedtoPayment();

	}
	@Then ("I verify the {string} in step")
	public void message_verification(String string) {
		
		System.out.println(CO.couponMessage());
		CO.orderPlacement();
		System.out.println(CO.getMessage());
		
		
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, lp.errorMessage());
    	driver.close();
    }

}
