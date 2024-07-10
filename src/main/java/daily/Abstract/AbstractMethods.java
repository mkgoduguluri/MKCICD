package daily.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import daily.SelFD.cartPage;

public class AbstractMethods {
	
	WebDriver driver;

	public AbstractMethods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[routerlink*=cart]")
	WebElement cartButton;
	
	public void waitForElementVisibility(By findby) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public void waitForElementInvisiblity(WebElement findby) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(findby));
	}
	
	public void javaScriptExecutor(String script) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript(script);
	}
	
	public void waitForElementvisiblity(WebElement findby) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(findby));
	}
	//header and same to the all pages
	public cartPage cartButton() {

		cartButton.click();
		cartPage cp = new cartPage(driver);
		return cp;
		
	}
}
