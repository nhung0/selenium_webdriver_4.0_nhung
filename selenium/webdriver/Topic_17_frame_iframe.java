package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_17_frame_iframe {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_form_site() {
		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
		driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
		sleepInSecond(5);

		//iframe element
		WebElement iframeForm = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")); //tìm iframe từ thẻ cha của nó
		Assert.assertTrue(iframeForm.isDisplayed());

		//switch vào frame và iframe trc khi thao tác vào element bên trong
//		driver.switchTo().frame(0);
//		driver.switchTo().frame("frame-one85593366");
		driver.switchTo().frame(iframeForm);

		new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
		sleepInSecond(4);

		// thao tác với element khác ngoài trang chứa iframe, thì phải đổi ngược lại driver
		driver.switchTo().defaultContent();

		// thao tác với element khác ngoài trang chứa iframe
		driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-26")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button#login")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
		sleepInSecond(3);





	}

	@Test
	public void TC_02_Kyna_English() {
		driver.get("https://skills.kynaenglish.vn/");
		WebElement iframekyna = driver.findElement(By.cssSelector("div.face-content>iframe"));
		Assert.assertTrue(iframekyna.isDisplayed());

		//switch
		driver.switchTo().frame(iframekyna);
		//verify follower
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),"177K followers" );
		// swich về default
		driver.switchTo().defaultContent();
		//switch vào iframe chat
		driver.switchTo().frame("cs_chat_iframe");

		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector("input.input_name")).sendKeys("john wick");
		driver.findElement(By.cssSelector("input.input_phone ")).sendKeys("0975797543");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys("abcde");
		sleepInSecond(4);

		//switch
		driver.switchTo().defaultContent();
		//search
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("java");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button.search-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.content h4")).getText(), "Lập trình Java trong 4 tuần");
		sleepInSecond(3);

	}

	@Test
	public void TC_03_frame_hdfc() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("lus_les ");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("a.btn-primary")).click();
		sleepInSecond(10);
//		driver.switchTo().defaultContent();

		driver.findElement(By.cssSelector("input#keyboard")).sendKeys("234567789");
		sleepInSecond(3);


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
