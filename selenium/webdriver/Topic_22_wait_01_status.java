package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Topic_22_wait_01_status {
	WebDriver driver;
	WebDriverWait explicitWait;

	By recomfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/?locale=vi_VN");

	}

	@Test
	public void TC_01_visible() {
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("nhung@gmail.com");
		sleepInSecond(2);
        //điều kiện 1: tại thời điểm này comfirmemailtextbox đang hiện thị-visible wait hiển thị cả ở ui và dom
		//tại thời điểm này comfirmemailtextbox đang hiện thị-visible wait hiển thị cả ở ui và dom
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(recomfirmEmailTextbox));
		// kiểm tra 1 element đang hieern thị
		Assert.assertTrue(driver.findElement(recomfirmEmailTextbox).isDisplayed());



	}

	@Test
	public void TC_02_invisible_in_dom() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
		// điều kiện thứ 2: element k xuất hiện trong ui nhưng vẫn có trong html
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        //tại thời điểm này comfirmemailtextbox đang  k hiện thị-invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(recomfirmEmailTextbox));

        // kiểm tra 1 element k hieern thị
        Assert.assertFalse(driver.findElement(recomfirmEmailTextbox).isDisplayed());


	}

    public void TC_02_invisible_not_in_dom() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        // đóng popup
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(3);
        // điều kiện thứ 3: element k xuất hiện trong ui và html

        //tại thời điểm này comfirmemailtextbox đang  k hiện thị-invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(recomfirmEmailTextbox));

        // kiểm tra 1 element k hieern thị
//        Assert.assertFalse(driver.findElement(recomfirmEmailTextbox).isDisplayed()); // chạy lâu và fail vì k tìm thấy element


    }

	@Test
	public void TC_03_presence() {
        // element hiển thi trong dom
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("nhung@gmail.com");
        sleepInSecond(2);
        //điều kiện 1: tại thời điểm này comfirmemailtextbox đang hiện thị-visible wait hiển thị cả ở ui và dom
        //tại thời điểm này comfirmemailtextbox đang hiện thị-visible/displayed - presence là có trong html k quan tâm có hiển thị hay k
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(recomfirmEmailTextbox));
        // điều kiện thứ 2: element k xuất hiện trong ui nhưng vẫn có trong html
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
		sleepInSecond(2);

	}


	@Test
	public void TC_04_stale() {

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        // tai thơi điểm này element có xuất hiện và mình sẽ find element
        WebElement confirmElement = driver.findElement(recomfirmEmailTextbox);
        // đóng popup
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
        // điều kiện thứ 3: element k xuất hiện trong ui và html
        explicitWait.until(ExpectedConditions.stalenessOf(confirmElement)); // chỉ nhận element



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
