package daily.Test;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;
import daily.Base.BaseTest;
import daily.SelFD.CheckOutPage;
import daily.SelFD.ItemsPage;
import daily.SelFD.cartPage;

public class ErrorValidation extends BaseTest {

	@Test (retryAnalyzer = daily.Base.Retry.class)

	public void loginErrorvalidation() throws InterruptedException, IOException {

		lp.loginSys("manojkumar99143@gmail.com", "Man0709");
		Assert.assertEquals(lp.errorMessage(), "Incorrect email password.");

	}
	@Test
	public void productFinal() throws InterruptedException, IOException {
		
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("ZARA COAT 3");
		a1.add("ADIDAS ORIGINAL");
		ItemsPage it = lp.loginSys("manojkumar99143@gmail.com", "Manoj@0709");
		Thread.sleep(2000);
		it.listProducts();
		it.prodFind(a1);
		cartPage cp = it.cartButton();
		cp.cartCheckout(a1);
		CheckOutPage CO = cp.checkOutforPayment();
		CO.addDetails("India", "664", "Manoj", "rahulshettyacademy");
		CO.proceedtoPayment();
		System.out.println(CO.couponMessage());
		CO.orderPlacement();
		System.out.println(CO.getMessage());

	}

}