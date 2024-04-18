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


public class Topic_06_webElementP3 {
	WebDriver driver;
	Random rand ;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, LastName, passWord, fullName;	
	
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
		
		emailAddress = "automation" +  rand.nextInt(9999) + "@gmail.com" ;
		firstName = "automation";
		LastName = "FC";
		fullName= firstName + " " + LastName;
		passWord = "123456";
		// tương tác với browser thì sẽ thông qua biến webDriver driver
		// tương tác với element thì sẽ thông qua biến webElement element
		
		
	}
	

	@Test
	public void TC_1234_các_trường_hợp_login () {
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
		driver.findElement(emailAccount).sendKeys(emailAddress);
		driver.findElement(idLoginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).isDisplayed());
		
		// incorrect email và password
		driver.findElement(passAccount).clear();
		driver.findElement(emailAccount).clear();
		driver.findElement(passAccount).sendKeys("123456");
		driver.findElement(emailAccount).sendKeys(emailAddress);
		sleepInSecond(3);
		driver.findElement(idLoginButton).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");	
		sleepInSecond(3);
		
	}
	
	public void TC_5_create_a_new_account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccount).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(LastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("comfirmation")).sendKeys(passWord);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg li span")).getText(),"Thank you for registering with Main Website Store.");	
		String contactImformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactImformationText);
		Assert.assertTrue(contactImformationText.contains(fullName));	
		Assert.assertTrue(contactImformationText.contains(emailAddress));
		sleepInSecond(2);
		driver.findElement(By.cssSelector("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("//div[@class='links']//li[@class=' last']/a")).click();
		sleepInSecond(3);	
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, 'logo.png')]")).isDisplayed());	
	}
	public void TC_6_login_valid_info() {
		driver.findElement(myAccount).click();
		sleepInSecond(2);
		driver.findElement(passAccount).sendKeys("123456");
		driver.findElement(emailAccount).sendKeys(emailAddress);
		sleepInSecond(3);
		driver.findElement(idLoginButton).click();
		String contactImformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactImformationText);
		Assert.assertTrue(contactImformationText.contains(fullName));	
		Assert.assertTrue(contactImformationText.contains(emailAddress));
	
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
	// lỗi webdriver không khớp với trình duyệt: topic 20, 55p00s
	// các trường hợp test thử trên bất kì web nào không nên để lại thông tin của mình, sẽ cho vào spam của họ trở thành rác
	// sử dụng locator gọn nhất có thể 
	// dùng hàm verify cho hợp  lý, sử dụng gettext()
	// driver.findElement(emailAccount).sendKeys(emailAddressString); là truyền vào  biến đc gán 1 email ngẫu nhiên chứ không phải đoạn text chứa email
	// lên chia các trường hợp viết thành 1 hàm, mỗi hàm 1 testcase
	// sue dụng 2 lần trở lên thì nên tạo biến
	// verify tuyệt đối là dùng equal, tuyệt đối 