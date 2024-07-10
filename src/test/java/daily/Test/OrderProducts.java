package daily.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import daily.Base.BaseTest;
import daily.SelFD.CheckOutPage;
import daily.SelFD.ItemsPage;
import daily.SelFD.OrderPage;
import daily.SelFD.cartPage;

public class OrderProducts extends BaseTest {

	ArrayList<String> a1;

	// we cannot declare arryalist at the classs level since it is an instance
	// variable
	// also, either we need to create a method to declare those
	// or we can declare them in the constructor.

	public OrderProducts() {
		a1 = new ArrayList<String>();
		a1.add("ZARA COAT 3");
		a1.add("ADIDAS ORIGINAL");
	}

	@Test(dataProvider = "getJsonData", groups = { "Reg" })

	// public void OrderItems(String email, String password) throws
	// InterruptedException, IOException {
	public void OrderItems(HashMap<String, String> input) throws InterruptedException, IOException {

		ItemsPage it = lp.loginSys(input.get("email"), input.get("password"));
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

	@Test(dependsOnMethods = { "OrderItems" })

	public void productValidations() throws InterruptedException {

		lp.loginSys("manojkumar99143@gmail.com", "Manoj@0709");
		Thread.sleep(2000);
		OrderPage op = lp.orderButton();
		op.prodFind(a1);

	}
	
	
	@DataProvider
	public Object[][] getJsonData() throws IOException{
		
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"//src//test//java//daily//data//data.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	

	//

//	@DataProvider
//
//	public Object[][] getHashData() {
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "manojkumar99143@gmail.com");
//		map.put("password", "Manoj@0709");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "manojkumar99143@gmail.com");
//		map2.put("password", "Manoj@0709");
//
//		return new Object[][] { { map }, { map2 } };
//
//	}

	// here we can also use this method to provide data to the test with the help of
	// Object.

//	@DataProvider
//
//	public Object[][] getData() {
//
//		Object[][] ob = new Object[2][2];
//		ob[0][0] = "manojkumar99143@gmail.com";
//		ob[0][1] = "Manoj@0709";
//		ob[1][0] = "manojkumar99143@gmail.com";
//		ob[1][1] = "Manoj@0709";
//		return ob;
//
//	}

	/*
	 * public List<HashMap<String, String>> dataJson(String filepath) throws
	 * IOException {
	 * 
	 * // read json to string
	 * 
	 * String jsonString = FileUtils.readFileToString(new File(filepath),
	 * StandardCharsets.UTF_8);
	 * 
	 * // String to HashMap - Jackson Data bid ObjectMapper ok = new ObjectMapper();
	 * 
	 * List<HashMap<String, String>> value = ok.readValue(jsonString, new
	 * TypeReference<List<HashMap<String, String>>>() { }); return value; }
	 */

}