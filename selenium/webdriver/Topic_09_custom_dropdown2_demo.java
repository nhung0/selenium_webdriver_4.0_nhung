package webdriver;

import static org.testng.Assert.expectThrows;

import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.protocol.RequestExpectContinue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_custom_dropdown2_demo {
	WebDriver driver;
	WebDriverWait implicitlyWait;
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
		implicitlyWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		seclectdropdown("speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button span.ui-selectmenu-text")).getText(), "Slower");
		
	
	
	}

	@Test
	public void TC_02_Logo() {
		
	}

	@Test
	public void TC_03_Form() {
		
	}
	
	public void seclectdropdown(String textbox, String textItem, String expectedText) {
		
		//1. click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown 
			driver.findElement(By.id(textbox));
			//2. chờ cho tất cả các item được load hết ra thành công
			// nếu chưa tìm thấy element thì sẽ chờ tiếp
				// nếu chờ tiếp mà thấy element thì cũng không cần chờ hết thời gian
				// nếu chờ tiếp mà không thấy element thì là fail
			// nếu tìm thấy rồi thì không cần chờ hêt thời gian
			// locator lấy phải là đại diện cho các item
			// lấy thẻ chứa text
			
			implicitlyWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(textItem)));
			// khai báo tất cả các item trong dropdown 
			List <WebElement> itemDropdowns = driver.findElements(By.cssSelector(textItem));
			//3. tìm item xem đúng cái đang cần không(dùng vòng lặp để tìm)
					//3.1 nếu nó nằm trong khoảng nhìn thấy của user không cần phải scroll xuống
					//3.2 nếu nó không nằm trong khoảng nhìn thấy của user thì cần scroll xuống đến icon đó
			 for (WebElement tempItem : itemDropdowns) {
				//4. kiểm tra cái text của item đúng với cái mình mong muốn	
				if (tempItem.getText().trim().equals(expectedText)) {
					//5. click vào icon đó
					tempItem.click();
					break;
				}
			}
			
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
// fix lỗi dùng sai ver của selenium 1h20p