package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Topic_26_explicit_wait {
	WebDriver driver;
	WebDriverWait explicitWait; // khai báo, chưa khởi tạo


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
		// khởi tạo 1 explicitwait có tổng thời gian là 10s, polling là 0.5s măc định
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// khởi tạo 1 explicitwait có tổng thời gian là 10s, polling là 0.3s tự set
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));



	}

	@Test
	public void TC_01_Url() {
		// chờ cho 1 alert presence trong html trc khi thao tác lên
			Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        // chờ cho element k còn xuất hiên trong dom nữa
		 explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));
         // chơ cho element có trên dom k quan tâm có trên ui hay k
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));
		// chơ cho list element có trên dom k quan tâm có trên ui hay k
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
		// chờ cho 1 đến nhiều element hiển thị trên giao diện-visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
		// chờ cho element đc phép click: button, link, checkbox
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
		// chơ page hiện tại có title như mong đợi
		explicitWait.until(ExpectedConditions.titleIs(""));
		// kết hợp nhiều điều kiện- 2điều kiện đều đúng và 1 trong 2 điều kiện đúng
		explicitWait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")), ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.or(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")), ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));
		// chờ cho element có attribute chứa giá trị mong đợi, truyền thiếu mong đợi cũng đc, đương đối, các attribute nằm trong element
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire store here"));
		//truyền đủ mong đợi cũng đc, tuyêt đối
		explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placeholder", "Search entire store here"));
		//  chờ 1 element có 1 attribute khác null
		explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("input#search")), "placeholder"));
		// lấy ra các attribute có trong dom
		explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("input#search")), "nameplaceURL", "https:/"));
		explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("input#search")), "nameplaceURL", "https:/"));
		// chờ cho element đc selected thành công : checkbox, button...
		explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
		// chờ cho element đc selected thành công
		explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));
		// chờ cho element  chưa đc selected thành công
		explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

		// chờ cho ifame hoăặc fame đc avaiable va switch qua
		//by or element
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
		// index
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		// id or name
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("name"));
		// chờ cho 1 element biến mất(k hiển thị trên ui)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
		// chờ cho 1 đoạn code js đc thực thi mà k trả ra ngoại lệ nào hết trả về true, có ngoại lệ llà false
		explicitWait.until(ExpectedConditions.jsReturnsValue("return argument[0].validationMessage;"));
		explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return argument[0].validationMessage;"));
		// chờ số lượng element bằng 1 số lượng cố định
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 10));
		explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 10)); // mong đợi nhỏ hơn 10
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 10)); // mong đợi lớn hơn 10
		// chờ cho window/ tab là bao nhiêu
		explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));
		//
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "")); // giá trị tuyệt đối

		Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
		explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), pattern)); // truyền 1 pattern
		// bắt buộc phải có trong dom có thể k có trong uii cũng đc
		explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""), "")); // xem có visible hay k
		// url
		explicitWait.until(ExpectedConditions.urlContains("")); // tương đối
		explicitWait.until(ExpectedConditions.urlMatches("[^abc]"));
		//chơ cho 1 điều kiện element này nó b update trang thái - load lại html
		explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(""))));









		}

	@Test
	public void TC_02_Logo() {
		Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
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
}
