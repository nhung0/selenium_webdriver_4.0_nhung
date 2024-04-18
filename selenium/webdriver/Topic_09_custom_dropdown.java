package webdriver;

import static org.testng.Assert.expectThrows;

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

public class Topic_09_custom_dropdown {
	WebDriver driver;
	WebDriverWait expliciWait;
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
		expliciWait =  new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

//	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//truyền đối số muốn kiểm tra vào hàm
		selectIteminDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Medium");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");		
		selectIteminDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		sleepInSecond(4);
		selectIteminDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Mr.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");
		
		
		
	}

	@Test
	public void TC_02_reactjs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		//truyền đối số muốn kiểm tra vào hàm
		selectIteminDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		selectIteminDropdown("i.dropdown.icon", "span.text", "Christian");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");	
		
		selectIteminDropdown("i.dropdown.icon", "span.text", "Matt");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
				
	}

	@Test
	public void TC_03_vuejs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		//truyền đối số muốn kiểm tra vào hàm
		selectIteminDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectIteminDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "First Option");	
		
		selectIteminDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Third Option");
		
		
	}
	
	@Test
	public void TC_04_editable() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		//truyền đối số muốn kiểm tra vào hàm
		enterAndselectIteminDropdown("input.search", "span.text", "Agola");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Agola");
		
		enterAndselectIteminDropdown("input.search", "span.textn", "Belgium");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");	
		
		enterAndselectIteminDropdown("input.search", "span.text", "Albania");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");
		
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
// tránh lặp lại code nhiều lần, chỉ gần gọi hàm ra dùng, nên define 1 cái tham số, 
	public void selectIteminDropdown(String parentCss, String allItemCss, String expectedItemText) {
	//1. click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown 
		driver.findElement(By.id(parentCss)).click();
	//2. chờ cho tất cả các item được load hết ra thành công
		// nếu chưa tìm thấy element thì sẽ chờ tiếp
			// nếu chờ tiếp mà thấy element thì cũng không cần chờ hết thời gian
			// nếu chờ tiếp mà không thấy element thì là fail
		// nếu tìm thấy rồi thì không cần chờ hêt thời gian
		// locator lấy phải là đại diện cho các item
		// lấy thẻ chứa text
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		// khai báo tất cả các item trong dropdown 
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		
	//3. tìm item xem đúng cái đang cần không(dùng vòng lặp để tìm)
		//3.1 nếu nó nằm trong khoảng nhìn thấy của user không cần phải scroll xuống
		//3.2 nếu nó không nằm trong khoảng nhìn thấy của user thì cần scroll xuống đến icon đó
		for (WebElement tempItem : speedDropdownItems) {
			
			
			if (tempItem.getText().trim().equals(expectedItemText)) {
				tempItem.click();
				// thoát ra khỏi vòng lặp không set các case còn lại nữa
				break;
			}
		}
	}

	
	
	public void enterAndselectIteminDropdown(String textboxcss, String allItemCss, String expectedItemText) {
		//1. nhâp expected item vào và sổ ra tất cả item matching
			driver.findElement(By.id(textboxcss)).clear();
			driver.findElement(By.id(textboxcss)).sendKeys(expectedItemText);
			sleepInSecond(2);
		//2. chờ cho tất cả các item được load hết ra thành công
			// nếu chưa tìm thấy element thì sẽ chờ tiếp
				// nếu chờ tiếp mà thấy element thì cũng không cần chờ hết thời gian
				// nếu chờ tiếp mà không thấy element thì là fail
			// nếu tìm thấy rồi thì không cần chờ hêt thời gian
			// locator lấy phải là đại diện cho các item
			// lấy thẻ chứa text
			expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
			
			// khai báo tất cả các item trong dropdown 
			List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
			
		//3. tìm item xem đúng cái đang cần không(dùng vòng lặp để tìm)
			//3.1 nếu nó nằm trong khoảng nhìn thấy của user không cần phải scroll xuống
			//3.2 nếu nó không nằm trong khoảng nhìn thấy của user thì cần scroll xuống đến icon đó
			for (WebElement tempItem : speedDropdownItems) {
				
				
				if (tempItem.getText().trim().equals(expectedItemText)) {
					tempItem.click();
					sleepInSecond(2);
					// thoát ra khỏi vòng lặp không set các case còn lại nữa
					break;
				}
			}
		}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
// fix lỗi dùng sai ver của selenium 1h20p
// trong cùng class hàm này có thể gọi đươc hàm kia ra để dùng
// vừa nhập vừa chọn sẽ nhanh hơn là chọn (editable)