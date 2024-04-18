package webdriver;
import org.openqa.selenium.By;import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.OutputType;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DefaultEditorKit.CopyAction;

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

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.gargoylesoftware.htmlunit.javascript.host.html.Option;
import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

import okio.Timeout;


public class Topic_06_webElementP1 {
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
	public void TC_01() {
//	// tương tác với element phải tìm đc element đó thông qua locator của nó, by id, by class, by xpath, by css, by linktext,...
//	driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
//	
//	// khi element dùng lại nhiều lần thì khai báo biến, khi dùng 1 lần thì không cần
//	WebElement emailTextbox = driver.findElement(By.id("email"));
//	emailTextbox.clear();
//	emailTextbox.sendKeys("nhung");
//	
//	// khi dùng 1 lần thì không cần
//	driver.findElement(By.id("password")).click();
	
	WebElement element = driver.findElement(By.className("div.hearder-links"));
	
	element.clear(); //* dùng cho các textbox, textarea, dropdown dạng editable -> xóa dữ liêu đi trc khi nhập text
	
	element.sendKeys(); //** dùng cho các textbox, textarea, dropdown dạng editable -> nhập dữ liêu 
	
	element.click(); //** click vào các button, textbox, checkbox, radio...
	
	driver.findElement(By.cssSelector("div.hearder-links a.ico-login")); //** cách thông thường
	
	driver.findElement(By.className("div.hearder-links")).findElement(By.className("a.ico-login"));// cách 1 tìm nhiều element
	element.findElement(By.className("a.ico-login")); // cách 2
	
	// GUI: font, size, color, locator,...
	String searchAttribute = element.getAttribute("placeholder"); //** lấy đc ra chữ gợi ý trong text box email
	
	element.getCssValue("color"); //*lấy ra css 
	
	Point point = element.getLocation();   // lấy ra cái vị trí bên ngoài của element so với web 
	point.x =309;
	point.y=349;
	
	org.openqa.selenium.Dimension di =	element.getSize(); // lấy ra kích thước bên trong
	di.getheight();
	System.out.println(di.height);
	
	Rectangle rec =	element.getRect(); // lấy cả location và size
	rec.width = 234;
	element.getScreenshotAs(OutputType.BASE64);  //* chụp hình bi lỗi khi testcase fail
	element.getTagName(); //lấy ra tên thẻ html của element đó
	element.getText(); //** lấy text từ error / success message / label	/ header...
	//-> khi text mình lấy nằm bên ngoài box thì dùng getText, còn nằm trong thì dùng Attribute
	
	Assert.assertTrue(element.isDisplayed());//** dùng để verify xem 1 element hiển thị hoặc k, cho tất cả element
	Assert.assertTrue(element.isEnabled()); // dùng để verify xem element có thao tác, nhập đc hay k
	Assert.assertFalse(element.isEnabled());
	Assert.assertFalse(element.isSelected());//* dùng để verify xem element có đc chon hay chưa, checkbox, radio, default dropdown
	
	element.submit(); // element nằm trong thẻ form, tương ứng với hành vi end user (enter)
	
	
	}

	@Test
	public void TC_02_Logo() {


	}

	@Test
	public void TC_03_Form() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
