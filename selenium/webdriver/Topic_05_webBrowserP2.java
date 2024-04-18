package webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Checkbox;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DefaultEditorKit.CopyAction;
import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.gargoylesoftware.htmlunit.javascript.host.html.Option;
import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

import okio.Timeout;


public class Topic_05_webBrowserP2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// tương tác với browser thì sẽ thông qua biến webDriver driver
		// tương tác với element thì sẽ thông qua biến webElement element
		
		
	}

	@Test
	public void TC_01_url() {
		driver.get("http://live.techpanda.org/");
		
		// click vào my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//		driver.findElement(By.cssSelector("div[class='footer' a[title='My Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		// click vào create new account
		driver.findElement(By.cssSelector("a[title= 'Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_title() {
		driver.get("http://live.techpanda.org/");
		
		// click vào my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//		driver.findElement(By.cssSelector("div[class='footer' a[title='My Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// click vào create new account
		driver.findElement(By.cssSelector("a[title= 'Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");



	}

	@Test
	public void TC_03_navigate() {
		driver.get("http://live.techpanda.org/");
		
		// click vào my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		// click vào create new account
		driver.findElement(By.cssSelector("a[title= 'Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		// back lại
		driver.navigate().back();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		// forward(chuyển tiếp đến trang trc kia nhấn back)
		driver.navigate().forward();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		}
	
	@Test
	public void TC_04_pagesourse() {
		driver.get("http://live.techpanda.org/");
		// click vào my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		// verify page HTML chứa chuỗi login or create an account
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		// click vào create new account
		driver.findElement(By.cssSelector("a[title= 'Create an Account']")).click();
		sleepInSecond(3);
		// verify page HTML chứa chuỗi login or create an account
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
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
}
