package daily.Base;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import daily.SelFD.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {

		Properties pr = new Properties();
		FileInputStream fs = new FileInputStream(
				"E:\\Selenium\\SelFD\\src\\main\\java\\daily\\Resources\\parameters.properties");

		pr.load(fs);
		//String name = pr.getProperty("browser");

		// ternary operator
		// where is the first condition satisfies, then it will execute the condition
		// after "?" mark
		// if it won't satify the condition then it executes the second condition after
		// ":".

		String name = System.getProperty("browser") != null ? System.getProperty("browser") : pr.getProperty("browser");

		if (name.contains("chrome")) {
			ChromeOptions c = new ChromeOptions();
			WebDriverManager.chromedriver().setup();// system set property
			if (name.contains("headless")) {
				c.addArguments("headless"); // headless
			}

			driver = new ChromeDriver(c);
			driver.manage().window().setSize(new Dimension(1440, 900)); // set screen to maximum

		} else if (name.equalsIgnoreCase("FireFox")) {
			// firefox

		} else {

			// safaribrowser
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@BeforeMethod(alwaysRun = true)

	public LandingPage LunchApplication() throws IOException {

		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}

	@AfterMethod(alwaysRun = true)

	public void closeBrowser() {

		driver.close();
	}

	public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {

		// convert json to string

		String strinFile = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// String to HashMap - jackson Databid

		ObjectMapper om = new ObjectMapper();
		List<HashMap<String, String>> data = om.readValue(strinFile,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;
	}

	public static ExtentReports extentReports() {
		// TODO Auto-generated method stub

		// ExtentSparkReports - reporter
		// ExtentReports - submit reporter to report

		String projectPath = System.getProperty("user.dir") + "//reports//sc.html";
		ExtentSparkReporter ex = new ExtentSparkReporter(projectPath);
		ex.config().setReportName("HTML Report for Selenium project");
		ex.config().setDocumentTitle("Test Results");
		ExtentReports er = new ExtentReports();
		er.attachReporter(ex);
		er.setSystemInfo("Analyst", "Manoj");
		return er;
	}

	public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = ((TakesScreenshot) driver);
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "//reports" + TestCaseName + ".png");
		FileUtils.copyFile(src, dest);
		// return path
		return System.getProperty("user.dir") + "//reports" + TestCaseName + ".png";

	}

}
