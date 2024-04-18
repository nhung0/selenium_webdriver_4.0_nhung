package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Topic_15_popup_P1 {
	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		}
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(new FirefoxProfile());
//		options.addPreference("dom.webnotifications.enabled", false);

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);


		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_fixed_in_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		//verify cho nó undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed()); // muốn nó không hiển thị phaải dùng assert false
		//click vào button login
		driver.findElement(By.cssSelector("button.login_ ")).click();
		//verify cho nó displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("button.login_ ")).isDisplayed());

		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		//verify cho nó hiển thị lôỗi
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Tài khoản không tồn tại!')]")).isDisplayed());


	}

	@Test
	public void TC_02_fixed_in_DOM2() {
		driver.get("https://skills.kynaenglish.vn/");
		By loginKyna = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(loginKyna).isDisplayed());

		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("a.login-btn")).isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(2);

	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}


	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
