package daily.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("ZARA COAT 3");
		a1.add("ADIDAS ORIGINAL");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("manojkumar99143@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Manoj@0709");
		driver.findElement(By.id("login")).click();
		Thread.sleep(5000);
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		List<WebElement> addButton = driver.findElements(By.cssSelector(".mb-3 button[class='btn w-10 rounded']"));

		/*
		 * using Streams /WebElement prod = products.stream().filter(product->
		 * product.findElement(By.cssSelector("b")).getText().equals(productName)).
		 * findFirst().orElse(null);
		 * prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 */

		int a = 0;
		for (WebElement item : items) {

			Thread.sleep(2000);

			if (a1.contains(item.findElement(By.cssSelector("div div h5")).getText())) {

				addButton.get(a).click();

			}
			a++;
		}

		WebElement toast = driver.findElement(By.cssSelector("#toast-container"));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		String s = w.until(ExpectedConditions.visibilityOf(toast)).getText();
		System.out.println(s);
		// ng-animating
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		System.out.println(cartItems.size());

		/*
		 * Using Streams Boolean match = cartProducts.stream().anyMatch(cartProduct->
		 * cartProduct.getText().equalsIgnoreCase(productName));
		 * Assert.assertTrue(match);
		 */

		for (WebElement cartItem : cartItems) {

			if (a1.contains(cartItem.getText())) {

				Assert.assertTrue(true);
			}
		}

		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		/*
		 * selecting India using actions class Actions a = new Actions(driver);
		 * a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']"
		 * )), "India").build().perform();
		 * 
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".ta-results")));
		 * 
		 * driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).
		 * click();
		 */
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		w.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"))));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.xpath("//div[@class='payment__cc']//div[2]//input[1]")).sendKeys("664");
		driver.findElement(By.xpath("//form/div/div[3]/div/input")).sendKeys("Manoj");
		driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[normalize-space()='Apply Coupon']")).click();
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[@class='mt-1 ng-star-inserted']"))));
		System.out.println(driver.findElement(By.xpath("//p[@class='mt-1 ng-star-inserted']")).getText());
		j.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		System.out.println(driver.findElement(By.xpath("//label[@class='ng-star-inserted'][1]")).getText());

	}

}