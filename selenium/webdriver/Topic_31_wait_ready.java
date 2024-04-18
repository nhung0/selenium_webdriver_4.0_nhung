package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;


public class Topic_31_wait_ready {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String quaname = "quá.jpg";
	String canhname = "cảnh.jpg";
	String depname = "đẹp.jpg";

	String quaFile = projectPath + File.separator +"uploadFiles" + File.separator + quaname;
	String canhFile = projectPath + File.separator +"uploadFiles" + File.separator + canhname;
	String depFile = projectPath + File.separator +"uploadFiles" + File.separator + depname;


	String osName = System.getProperty("os.name");




	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.manage().window().maximize();


	}



	@Test
	public void TC_01_admin_nopcommer() {


		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSecond(2);
		Assert.assertTrue(isPageLoadedSuccess());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'fa-user')]/following-sibling::p"))).click();
		driver.findElement(By.xpath("//ul[contains(@stytle,'display: block;')]//i[contains(@class, 'fa-dot-circle')]/following-sibling::p[contains(string(),' Customers')]")).click();
		Assert.assertTrue(isPageLoadedSuccess());






	}



	@Test
	public void TC_02_orange_ipa() {

		driver.get("https://api.orangehrm.com/");
		// dùng ex để wait biến mất loading
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader div"))));
		// sau đó check hiển thị hiern thị chữ
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // dùng 1 mình im cũng check đc hiển thị vì có trong dom và chị kiểm tra element
		Assert.assertTrue(driver.findElement(By.cssSelector("div#project h1")).isDisplayed());





	}
	public boolean ajax_loaing_invisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	}


	public boolean isPageLoadedSuccess() {

		return new WebDriverWait(driver, Duration.ofSeconds(30)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		} );
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getTime() {
		Date date = new Date();
		return date.toString();
	}
}












