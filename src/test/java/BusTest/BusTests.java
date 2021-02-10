package BusTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BusTests {
	private WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	private void setUp(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			// create chrome instance
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}

		// maximize browser window
//		driver.manage().window().maximize();
	}

	@Test
	public void BusBookTest() {
		System.out.println("Starting Test");

//		open test page
		String url = "https://www.redbus.in/bus-tickets";
		driver.get(url);
		System.out.println("Page is opened.");

//		Enter Source
		WebElement source = driver.findElement(By.id("txtSource"));
		source.sendKeys("Borivali East, Mumbai");
		source.sendKeys(Keys.ENTER);

//		Enter destination
		WebElement destination = driver.findElement(By.id("txtDestination"));
		destination.sendKeys("Wakad, Pune");
		destination.sendKeys(Keys.ENTER);

		WebElement date = driver.findElement(By.id("txtOnwardCalendar"));
		date.sendKeys("12-Feb-2021");

//		click login button
		WebElement searchbtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[3]/button"));
		searchbtn.click();
		System.out.println("clicked on Search");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		new url varification
		String expectedUrl = "https://www.redbus.in/search?fromCityName=Borivali%20East,%20Mumbai&fromCityId=66545&toCityName=Wakad,%20Pune&toCityId=89672&busType=Any&opId=0&onward=12-Feb-2021";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// Close browser
		driver.quit();
	}

}