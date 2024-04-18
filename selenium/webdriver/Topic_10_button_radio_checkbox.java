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


import com.google.common.base.Verify;

public class Topic_10_button_radio_checkbox {
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
		
	}
	
//	@Test
	public void TC_01_button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();
		By loginbutton = By.cssSelector("li.popup-login-tab-item");
		//Verify login button là disabled
		Assert.assertFalse(driver.findElement(loginbutton).isEnabled());
		
		String loginbuttonbackground = driver.findElement(loginbutton).getCssValue("background-image");       // lấy ra giá trị của thuộc tính css
		Assert.assertTrue(loginbuttonbackground.contains("rgba(224,224,224,1)"));
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0986794722");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("1234589");
		sleepInSecond(2);
		
		// verify login button enabled
		Assert.assertTrue(driver.findElement(loginbutton).isEnabled());
		
		loginbuttonbackground = driver.findElement(loginbutton).getCssValue("background");
//		Color loginbuttonbackgroundColor = Color.fromString(loginbuttonbackground)
//		Assert.assertEquals(loginbuttonbackground.asHex().toUpperCase(), "#C92127");
//		System.out.println(loginbuttonbackground);
		
	}

	@Test
	public void TC_02_checkbox_radio_single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// click chọn một checkbox
		driver.findElement(By.xpath("//label[contains(text(), 'Ulcerative Colitis')]/preceding-sibling::input")).click();
		// click 1 radio button
		driver.findElement(By.xpath("//label[contains(text(), \" I don't drink \")]/preceding-sibling::input")).click();
		// verify các checkbox/radio đã đc chọn rôi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Ulcerative Colitis')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \" I don't drink \")]/preceding-sibling::input")).isSelected());
		// checkbox có thể bỏ chọn
		driver.findElement(By.xpath("//label[contains(text(), 'Ulcerative Colitis')]/preceding-sibling::input")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(), 'Ulcerative Colitis')]/preceding-sibling::input")).isSelected());
		//radio thì không	
		driver.findElement(By.xpath("//label[contains(text(), \" I don't drink \")]/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \" I don't drink \")]/preceding-sibling::input")).isSelected());
	}

	@Test
	public void TC_02_checkbox_multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allcheckBoxs =  driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// dùng vòng lặp để duyêt qua và click vào tất cả checkbox
		for (WebElement tempboxed : allcheckBoxs) {
			tempboxed.click();
			sleepInSecond(1);
		}
		// verify tất cả checkbox đc chọn thành công
		for (WebElement tempboxed : allcheckBoxs) {
			Assert.assertTrue(tempboxed.isSelected());
		}
		// nếu như gặp một checkbox có tên mình mong muốn click thì click không thì thôi
		for (WebElement tempboxed : allcheckBoxs) {
			if (tempboxed.getAttribute("value").equals("Anemia")) {
				tempboxed.click();
			}
			sleepInSecond(1);
		}
		
	}
	
//	@Test
	public void TC_04_default_checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//chọn nó
		checktoCheckbox(By.xpath("https://demos.telerik.com/kendo-ui/checkbox/index"));
		Assert.assertTrue(driver.findElement(By.xpath("https://demos.telerik.com/kendo-ui/checkbox/index")).isSelected());
		
		// bỏ chọn
		unchecktoCheckbox(By.xpath("https://demos.telerik.com/kendo-ui/checkbox/index"));
		Assert.assertFalse(driver.findElement(By.xpath("https://demos.telerik.com/kendo-ui/checkbox/index")).isSelected());
		
		
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
