package entrata;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;



public class NewTest {
	public String baseUrl = "https://www.entrata.com/";
	public WebDriver driver;
	String actual ;
	String expected ;
	@BeforeMethod
	public void launchBrowser() {
        ChromeOptions options = new ChromeOptions();

        // Disable cookies
        options.addArguments("--disable-notifications");  // Disable notifications
        options.addArguments("--disable-infobars");  // Disable infobars
        options.addArguments("--disable-extensions");  // Disable extensions
        options.addArguments("--disable-web-security");  // Disable web security
        options.addArguments("--disable-plugins");  // Disable plugins
        options.addArguments("--disable-popup-blocking");  // Disable popup blocking
        options.addArguments("--disable-extensions"); // Disable browser extensions

        // Set preferences to block cookies
        options.addArguments("--disable-blink-features=BlockCredentialedSubresources");
		driver = new ChromeDriver(options);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		actual = driver.getTitle();
		expected = "Property Management Software | Entrata";
		//Validate Title of Page
		Assert.assertEquals(actual, expected);
	}

	//TC01: Validate 'Do it all with Entrata' tiles headers and description on home page
	@Test
	public void validate_doItAllWithEntrata_Tiles() {
		HashMap<String, String> map = new HashMap<String, String>();
		String xpath = "(//*[@class='four-bucket-grid']/a)";
		int count = driver.findElements(By.xpath(xpath)).size();

		for (int i = 1; i <= count; i++) {
			String header = driver.findElement(By.xpath(xpath + "[" + i + "]/div/h3")).getText();
			String description = driver.findElement(By.xpath(xpath + "[" + i + "]/div/h4")).getText();
			map.put(header, description);
		}

		System.out.println(map);
		expected = "{Utilities=Centralize and manage reporting and expense recapture., Accounting=Manage and track property and portfolio performance., Marketing & Leasing=Attract and convert prospects faster., Property Management=Manage all your property operations in one place.}";
		actual = map.toString();
		//Validate headers and description of tiles.
		Assert.assertEquals(actual, expected);
	}
	
	
    //TC02: Validate user is able to navigate to base camp registration page.
	@Test
	public void validate_baseCamp_registration() {
		driver.findElement(By.xpath("(//a[@class=\"main-nav-link\" and text()=\"Base Camp\"])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();

		//Switch to the base camp window
		for (String handle : windowHandles) {
			if (!handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		actual = driver.getTitle();
		expected = "Base Camp 2024 | Entrata";
		//Validate title of base camp page
		Assert.assertEquals(actual, expected);
		driver.findElement(By.xpath("//*[@class='landing-register-btn w-inline-block']")).click();
		actual = driver.getTitle();
		expected = "Personal Information - Base Camp 2024";
		//Validate title of base camp registration page
		Assert.assertEquals(actual, expected);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Personal Information']")));
		boolean flag = driver.findElement(By.xpath("//h2[text()='Personal Information']")).isDisplayed();
		//Validate 'Personal Information' header is displayed
		Assert.assertEquals(flag, true);
	}

	//TC03: Validate error if username and passowrd is empty while login for Product Manager.
	@Test
	public void validate_error_on_PropertyManagerLoginPage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='button-default outline-dark-button']")));
		driver.findElement(By.xpath("//*[@class='button-default outline-dark-button']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Client Login Button']")));
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//a[@title='Client Login Button']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("entrata-login-error")));
		
		actual = driver.findElement(By.id("entrata-login-error")).getText();
		expected = "Please enter username and password";
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}
	
	//TC04: Validate All Solutions list on home page .
	@Test
	public void validate_solutions_list() {
		List<String> solutions = new ArrayList<String>();
		 Actions actions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='main-nav-link'and text()='Solutions']")));
        // Perform hover action on solution tab
        actions.moveToElement(driver.findElement(By.xpath("//*[@class='main-nav-link'and text()='Solutions']"))).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='main-nav-link'and text()='Solutions']/../div[2]/a[@class='fat-nav-links']")));
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='main-nav-link'and text()='Solutions']/../div[2]/a[@class='fat-nav-links']"));
		
		for(WebElement element : list) {
			solutions.add(element.getText());
		}
		
		actual = solutions.toString();
		System.out.print(actual);
		expected = "[Multifamily, Student, Affordable, Military, Commercial, Homebody]";
		//validate all options in solution list
		Assert.assertEquals(actual, expected);
	}

	@AfterMethod
	public void terminateBrowser() {
		driver.quit();
	}
}
