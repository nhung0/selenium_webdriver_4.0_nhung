package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic_23_wait_findelement {
	WebDriver driver;
    WebDriverWait explicitWait;
	FluentWait fluentWait;
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
        //implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        // set implicit cho selenium 4.0 trở  lên
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        // set implicit cho selenium 3.0 trở  xuống
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        // ver 4.0
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        // vể 3.0
//        //explicitWait = new WebDriverWait(driver, 10); k hỗ trợ 3.0 nữa
//		//fluent dùng ver 4.0
//		fluentWait = new FluentWait(driver);
//		fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500));
////		//ver 3.0
////		fluentWait.withTimeout(5000, TimeUnit.MILLISECONDS).pollingEvery(250,TimeUnit.MILLISECONDS);
		driver.get("https://www.facebook.com/");





	}

	@Test
	public void TC_01_findelement() {
		//case 1: element đc tìm thấy chỉ có 1
		// sẽ không cần chờ hết timeout
		//tìm thấy sẽ trả về 1 webelement
		// qua step tiêếp theo
		System.out.println("Start step: " + getDatetimeout());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("end step: " + getDatetimeout());

		//case 2: element đc tìm thấy nhưng có nhiều hơn 1
		// k chờ hêt timeout
		//ấy element đầu tiên dù có bao nhiêu node
		// qua step tiêếp theo
		System.out.println("Start step: " + getDatetimeout());
		driver.findElement(By.cssSelector("input[type='text'].[type='password']")).sendKeys("nhung@gmail.com");
		System.out.println("end step: " + getDatetimeout());
		// case 3: element không đc tìm thấy
		// cần chờ hêt timeout là 10s trong 10s cứ mỗi nửa giây sẽ tìm lại 1 lần
		// nếu tìm lại mà thấy thì cũng trả về element và qua bước tiếp theo
		// nếu tìm lại mà hết 10s k thấy thì fail và nosearchelement vaf các stepp sau không chạy nữa
		System.out.println("Start step: " + getDatetimeout());
		driver.findElement(By.cssSelector("input[name='lastname']"));
		System.out.println("end step: " + getDatetimeout());



	}

	@Test
	public void TC_02_findelements() {
		List<WebElement> elementList ;
		//case1: element đc tìm thấy chỉ có 1
		// k cần hết timeout 10s
		// trả về 1 list element chứa đúng  1 elem
		System.out.println("Start step: " + getDatetimeout());
		elementList = driver.findElements(By.cssSelector("input#email"));
		System.out.println("end step: " + getDatetimeout());
		//case2: element đc tìm thấy trong khoảng thời gian đc set
		// k cần hết timeout 10s
		// trả về 1 list element chứa nhiều elem
		System.out.println("Start step: " + getDatetimeout());
		elementList = driver.findElements(By.cssSelector("input[type='text'].[type='password']"));
		System.out.println("end step: " + getDatetimeout());
		//case3: element đc tìm thấy trong khoảng thời gian đc set
		// cần chờ hêt timeout là 10s trong 10s cứ mỗi nửa giây sẽ tìm lại 1 lần
		// nếu tìm lại mà thấy thì cũng trả về list element chứa element đó và qua bước tiếp theo
		// nếu tìm lại mà hết 10s k thấy thì trả về 1 list rỗng va k đánh fail và qua các step sau vẫn chạy
		System.out.println("Start step: " + getDatetimeout());
		elementList = driver.findElements(By.cssSelector("input[name='lastname']"));
		System.out.println("end step: " + getDatetimeout());

	}

	@Test
	public void TC_03_Form() {
		Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
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

	public String getDatetimeout() {
		Date date = new Date();
		return date.toString();
	}
}
