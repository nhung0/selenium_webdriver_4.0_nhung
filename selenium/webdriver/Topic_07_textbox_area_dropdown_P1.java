package webdriver;
import org.openqa.selenium.By;import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Checkbox;
import java.awt.Desktop.Action;
import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DefaultEditorKit.CopyAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.gargoylesoftware.htmlunit.javascript.host.html.Option;
import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

import okio.Timeout;


public class Topic_07_textbox_area_dropdown_P1 {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passNumber = "11-22-33-44";
	String commentInput = "Chúc các đội tuyển\nhi HSG Quốc gia năm học 2023-2024 sẽ thật thành công.";
 
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
	public void TC_create_new_employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		driver.findElement(By.name("firstName")).sendKeys("automation");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("auto" + employeeID );
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Nhungpham03@");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Nhungpham03@");
		
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepInSecond(8);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(commentInput);
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepInSecond(6);
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']/parent::button/following-sibling::button/i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentInput);
		
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("username")).sendKeys("auto" + employeeID);
		driver.findElement(By.name("password")).sendKeys("Nhungpham03@");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//span[text()='My Info']/parent::a")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']/parent::button/following-sibling::button/i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentInput);
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


// lưu ý:
	// mỗi html có các kiểu dữ liệu
	// nếu có attribute thì getattribute không thì gettext
	// String employeeID = String.valueOf(rand.nextInt(99999)); covert sang kiểu string vì nextint là kiểu số nguyên
	// những trang dùng công nghệ web js, vuejs, angular, react thì value sẽ không nằm trong html mà trong properties

	// trong trường hợp texbox không có name class id duy nhất thì dùng locator xpath axes anh em cha con để lấy