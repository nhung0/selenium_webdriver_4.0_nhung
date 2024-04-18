package webdriver;
import org.openqa.selenium.By;import org.openqa.selenium.By.ByXPath;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Checkbox;
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


public class Topic_06_webElementP2 {
	WebDriver driver;
	Random rand ;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddressString;
	
	
	
	// by chưa đi tìm element ngay
	By emailTextbox = By.id("email");
	By ageUnder18radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#development");
	By myAccount = By.cssSelector("div[class='footer'] a[title='My Account']");
	By idLoginButton = By.cssSelector("button[id='send2']");
	By emailAccount = By.xpath("//input[@id='email']");
	By passAccount =By.xpath("//input[@id='pass']");
	
	
	
	
	
	
 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		rand = new Random();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		emailAddressString = "automation" +  rand.nextInt(9999) + "@gmail.com" ;
		// tương tác với browser thì sẽ thông qua biến webDriver driver
		// tương tác với element thì sẽ thông qua biến webElement element
		
		
	}

//	@Test
	public void TC_01_displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// câu lệnh in ra console trong java
		// in ra xuống dòng: System.out.println(""); 
		// không xuống dòng: System.out.print(""); 
		
		// email textbox nếu có hiện thị thì nhập text vào và in ra console 
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("nhung pham");
			System.out.println("email textbox is displayed");
			
		} else {
			System.out.println("email textbox is not displayed");
		}
		
		// edu textarea nếu có hiện thị thì nhập text vào và in ra console 
		if(driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("nhung pham");
			System.out.println("education textarea is displayed");
			
		} else {
			System.out.println("education textarea is not displayed");
		}
		// radio
		if(driver.findElement(ageUnder18radio).isDisplayed()) {
			driver.findElement(ageUnder18radio).click();
			System.out.println(" age under 18 is displayed");
			
		} else {
			System.out.println("age under is not displayed");
		}
		
		//name user 
		if(driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println(" name user is displayed");
			
		} else {
			System.out.println("name user is not displayed");
		}
		
		
	}

//	@Test
	public void TC_02_enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// verify xem có nhập đc dữ liệu hay là không 
		if (driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("password is enabled");
			
		} else {
			System.out.println("password is disabled");
		}
		
		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("biography is enabled");
			
		} else {
			System.out.println("biography is disabled");
		}
		
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("email is enabled");
			
		} else {
			System.out.println("email is disabled");
		}

	}

//	@Test
	public void TC_03_selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// verify checkbox, radio button are desselected
		Assert.assertFalse(driver.findElement(ageUnder18radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		sleepInSecond(3);
		// click vào ckeckbox/radio
		driver.findElement(ageUnder18radio).click();
		driver.findElement(developmentCheckbox).click();
		sleepInSecond(3);
		// verify checkbox, radio button are selected
		Assert.assertTrue(driver.findElement(ageUnder18radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
		sleepInSecond(3);
		
		
	}
	
//	@Test
	public void TC_03_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("nhungpham@gmail.com");
		
		By passwordTextbox = By.id("new_password");
	
		
		driver.findElement(passwordTextbox).sendKeys("abc");

		sleepInSecond(3);
		// verify lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='8-char not-completed']")).isDisplayed());
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		
		sleepInSecond(3);
		// verify uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='8-char not-completed']")).isDisplayed());
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		
		sleepInSecond(3);
		// verify number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("#$%");
		
		sleepInSecond(3);
		// verify special
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='8-char not-completed']")).isDisplayed());
		
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ASDFGHJTR");
		
		sleepInSecond(3);
		// verify character >= 8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='8-char completed']")).isDisplayed());
		
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("12Ab@asdd");
		
		sleepInSecond(3);
		// verify correct
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class ='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class ='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class ='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class ='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class ='8-char completed']")).isDisplayed());
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void TC_03_login () {
		//empty email và password
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccount).click();
		sleepInSecond(3);
		driver.findElement(idLoginButton).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email validation-failed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password validation-failed']")).isDisplayed());
		sleepInSecond(3);
		
		//invalid email 
		driver.findElement(emailAccount).sendKeys("1234567@23456.123456");
		driver.findElement(passAccount).sendKeys("123456");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class= 'validation-advice']")).isDisplayed());
		
		sleepInSecond(3);
		
		// password <6 char
		driver.findElement(passAccount).clear();
		driver.findElement(emailAccount).clear();
		driver.findElement(passAccount).sendKeys("1234");
		driver.findElement(emailAccount).sendKeys(emailAddressString);
		driver.findElement(idLoginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).isDisplayed());
		
		// incorrect email và password
		driver.findElement(passAccount).clear();
		driver.findElement(emailAccount).clear();
		driver.findElement(passAccount).sendKeys("123456789");
		driver.findElement(emailAccount).sendKeys(emailAddressString);
		sleepInSecond(3);
		driver.findElement(idLoginButton).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");	
		sleepInSecond(3);
		
		// create a new account
		driver.findElement(passAccount).clear();
		driver.findElement(emailAccount).clear();
		driver.findElement(passAccount).sendKeys("123456789");
		driver.findElement(emailAccount).sendKeys(emailAddressString);
		sleepInSecond(3);
		driver.findElement(idLoginButton).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");	
		sleepInSecond(3);
				
		
	
		

		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}


// lưu ý:
	// lỗi webdriver không khớp với trình duyệt: topic 20, 55p00s
	// các trường hợp test thử trên bất kì web nào không nên để lại thông tin của mình, sẽ cho vào spam của họ trở thành rác
	// sử dụng locator gọn nhất có thể 
	// dùng hàm verify cho hợp  lý, sử dụng gettext()
	// driver.findElement(emailAccount).sendKeys(emailAddressString); là truyền vào  biến đc gán 1 email ngẫu nhiên chứ không phải đoạn text chứa email
