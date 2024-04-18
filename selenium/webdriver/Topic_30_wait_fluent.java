package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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


public class Topic_30_wait_fluent {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
//	FluentWait<WebElement> fluenElement;
//	FluentWait<String> fluenString;
	private long fulltimeoutsecond = 30;
	private  long pollingtimeoutmilies = 300;



	String projectPath = System.getProperty("user.dir");
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
		fluentDriver = new FluentWait<WebDriver>(driver);

		driver.manage().window().maximize();





	}
	@Test
	public void TC_00_knowleage() {
//		// khởi taạo
//		fluenDriver = new FluentWait<WebDriver>(driver);
//		fluenElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));
//		fluenString = new FluentWait<String>("nhung");
//		//setting
//		// tổng thời gian
//		fluenDriver.withTimeout(Duration.ofSeconds(10));
//		// tổng polling time
//		fluenDriver.pollingEvery(Duration.ofMillis(300));
//		//ignore nosuch  exceptions
//		fluenDriver.ignoring(NoSuchElementException.class);
//		//ignore timeout exceptions
//		fluenDriver.ignoring(TimeoutException.class);
//		// điều kiện
//
//		fluenDriver.until(new Function<WebDriver, Object>() {
//
//
//			@Override
//			public Object apply(WebDriver webDriver) {
//				return null; // trả về kiểu object
//			}
//		});
//
//		fluenDriver.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class, TimeoutException.class).until(new Function<WebDriver, String>() {
//			@Override
//			public String apply(WebDriver webDriver) {
//				return null;
//			}
//		});

		







	}

	@Test
	public void TC_01_() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		// gọi 1 hàm đã có sẵn
		waitendFindElement(By.cssSelector("div#start button")).click();

//		// điều kiện cách 1
//		fluentDriver.until(new Function<WebDriver, Boolean>() { // trả về xem hello có hiển thị k
//			@Override
//			public Boolean apply(WebDriver driver) {
//				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
//			}
//		});
		// điều kiện cách 2
		String hellotext = waitendFindElement(By.xpath("//div[@id='finish']/h4")).getText();

		Assert.assertEquals(hellotext, "Hello World!");


//		// lấy ví dụ với noAlert
//		fluentDriver.withTimeout(Duration.ofSeconds(10))
//				.pollingEvery(Duration.ofMillis(100))
//				.ignoring(NoAlertPresentException.class);
//
//
//		fluentDriver.until(new Function<WebDriver,Alert>() {
//			@Override
//			public Alert apply(WebDriver webDriver) {
//
//				return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
//
//			}
//		});
	
	}


	@Test
	public void TC_02_() {

		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
		fluentElement = new FluentWait<WebElement>(countdown);

		fluentElement.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class, TimeoutException.class);
		// điều kiện
		fluentElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement element) {
				return element.getText().endsWith("00");  // cho 2 chữ cái cuối cùng là 00 thì thảo mãn điều kiện
			}
		});



	}

	public WebElement waitendFindElement(By locator) {
		FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(fulltimeoutsecond))
				.pollingEvery(Duration.ofMillis(pollingtimeoutmilies))
				.ignoring(NoAlertPresentException.class);
		return fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver webDriver) {
				return webDriver.findElement(locator);
			}
		});



	}

	@Test
	public void TC_04_only_explicit_not_found_param_by() {




	}

	@Test
	public void TC_05_only_explicit_not_found_param_element() {



	}

	@Test
	public void TC_06_only_explicit_mix_implicit() {



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
