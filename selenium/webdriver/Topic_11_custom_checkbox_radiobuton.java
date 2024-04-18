package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.apache.commons.net.io.FromNetASCIIInputStream;
import org.apache.http.client.protocol.RequestExpectContinue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.google.common.base.CaseFormat;
import com.google.common.base.Verify;

public class Topic_11_custom_checkbox_radiobuton {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
//	@Test
	public void TC_01_custom() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");		
		/* Case 1*/
		//thẻ input bị che nên không thao tác đc click
		//thẻ input dùng để verify đc vì hàm isselected chỉ work với thẻ input
		
		// thao tác chọn
		driver.findElement(By.xpath("//span[text()='Hà Nội']/parent::div/parent::div/preceding-sibling::div/input")).click();
		// verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Hà Nội']/parent::div/parent::div/preceding-sibling::div/input")).isSelected());
		//// sử dung trang có radio có input nhưng ở đây page bị die nên là chỉ ghi mẫu
	
	
	}

	@Test
	public void TC_02() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");		
		/* Case 2*/
		//thẻ khác input để click span, div, label, đang hiển thị là đc
		//thẻ này không dùng để verify được vì hàm selected chỉ work với input
		
		// thao tác chọn
		driver.findElement(By.xpath("//span[text()='Hà Nội']/parent::div/parent::div/preceding-sibling::div")).click();
		// verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Hà Nội']/parent::div/parent::div/preceding-sibling::div")).isSelected());
		// the span div label luon tra ve false
	}

	@Test
	public void TC_03() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");		
		/* Case 3*/
		//thẻ khác input để click span, div, label, đang hiển thị là đc
		//thẻ  input dùng để verify đc vì hàm isselected chỉ work với thẻ input
		// kết hợp cả 2 trường hợp trên dùng div để click và dùng input để verify (trong element này có 2 thẻ div và input đều chỉ tới vị trí của nó)
		
		// thao tác chọn
		driver.findElement(By.xpath("//span[text()='Hà Nội']/parent::div/parent::div/preceding-sibling::div")).click();
		// verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Hà Nội']/parent::div/parent::div/preceding-sibling::div/input")).isSelected());
		// sử dung trang có radio có input nhưng ở đây page bị die nên là chỉ ghi mẫu
	
		// ở trường hợp viết demo thì dc
		// nếu apply vào 1 framework, dự án thực tế thì k
		// vì một element phải define với nhiều locator (dễ bị hiểu lần, mất thời gian để maintain, không tập trung)
		
	
	}
	
	@Test
	public void TC_04() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");		
		/* Case 4*/
		//thẻ input bị ẩn nhưng vẫn dùng để click
		// hàm click của webelement không thao tác đc với element bị ẩn đc
		// nên sẽ dùng hàm click của javascript để click
		// thẻ element phải có chiều cao và chiều rộng
		//thẻ input dùng để verify đc vì hàm isselected chỉ work với thẻ input
		// selenium cung cấp 1 thư viên để nhúng javascript code vào
		
		By radioButton = By.xpath("//span[text()='Hà Nội']/parent::div/parent::div/preceding-sibling::div/input");
		// thao tác chọn
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		// verify
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		// sử dung trang có radio có input nhưng ở đây page bị die nên là chỉ ghi mẫu
		
	}
	
	@Test
	public void TC_05() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");		
		sleepInSecond(3);
		/* Case 5*/
		//thẻ khác input để click span, div, label, đang hiển thị là đc
		// hàm click của webelement không thao tác đc với element bị ẩn đc
		// nên sẽ dùng hàm click của javascript để click
		// thẻ element phải có chiều cao và chiều rộng
		//dùng isdisplay để verify
		// selenium cung cấp 1 thư viên để nhúng javascript code vào
		By  checkboxbuttonform =By.cssSelector("div[aria-label=\"Quảng Ngãi\"]");
		By  radiobuttonform =By.cssSelector("div[aria-label=\"Hà Nội\"]");
		// thao tác chọn 
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkboxbuttonform));
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radiobuttonform));
		sleepInSecond(2);
		// verify
		// cách 1 
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		// cách 2
		Assert.assertEquals(driver.findElement(radiobuttonform).getAttribute("aria-checked"),"true");
		Assert.assertEquals(driver.findElement(checkboxbuttonform).getAttribute("aria-checked"),"true");
	
	// hiếm gặp case này
	}
	public void checktoCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
		
	}
	
	public void unchecktoCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
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
//		driver.quit();
	}
}
// element bi ẩn đang bi element khác đè lên nên không thể tương tác được