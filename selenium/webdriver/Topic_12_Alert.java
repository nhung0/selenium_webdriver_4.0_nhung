package webdriver;
import io.netty.handler.codec.Headers;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.xalan.templates.ElemExsltFuncResult;
import org.eclipse.jetty.util.security.Password;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "\\autoIT\\authen_firefox.exe";
	String username = "admin";
	String password = "admin";
	
 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

//	@Test
	public void TC_01_accept_alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		// có thể switch qua và tương tác luôn
//		alert = driver.switchTo().alert();
		// có thể wait trước có kết quả rôi mới switch qua va tương tác
//		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		// tương tác
//		alert.accept(); //chấp nhận nó
//		alert.dismiss(); // chọn cancel
//		alert.getText(); // lấy ra text của alert
//		alert.sendKeys(""); // ghi chữ vào promt
		
		
		//  wait trước có kết quả rôi mới switch qua va tương tác
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		// verify title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "Click for JS Alert");
		//accept cái alert này
		alert.accept();
		sleepInSecond(2);
		//verify với text của page
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
		
		
		
		
	}

//	@Test
	public void TC_02_custom_alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		// wait trước có kết quả rôi mới switch qua va tương tác
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		// verify title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		// cancel cái alert này
		alert.dismiss();
		sleepInSecond(2);
		// verify với text của page
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
	}

//	@Test
	public void TC_03_promt_alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(5);
		
		// wait trước có kết quả rôi mới switch qua va tương tác
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		// verify title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String courseName = "nhung pham";
		//nhập text bất kì
		alert.sendKeys(courseName);
		sleepInSecond(2);
		// accept cái alert này
		alert.accept();
		sleepInSecond(2);
		// verify với text của page
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ courseName );
		
	}
	
	@Test
	public void TC_04_anthentication_bypass_to_url_alert1() {
		// cách từ trang a sang trang b, pass
		driver.get("https://the-internet.herokuapp.com/");
		String autheUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(passUsernameandPasstoUrl(autheUrl, "admin", "admin"));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),' Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
//		// 2 cách này bị fail
//		// cách 1 truyền trực tiếp username và passwork vào url này , tự động sign in 
//		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth"); // http:// +username : Paswork @ automationfc.github.....
//		//cách 2 gọi hàm vừa tao ở dưới
//		driver.get(passUsernameandPasstoUrl("https://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
//		sleepInSecond(3);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),' Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
//	@Test
	public void TC_04_anthentication_autoIT_alert2() throws IOException {
		//dùng autoIT, trong trường hợp bắt buộc
		// đoạn này chạy trước
		if (driver.toString().contains("firefox")) {
			//Runtime.getRuntime().exec: thực thi 1 file exec trong code Java
			Runtime.getRuntime().exec(new String[] {authenFirefox, username, password });
			
		} else if (driver.toString().contains("Chrome")){
			Runtime.getRuntime().exec(new String[] {authenChrome, username, password });

		}
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),' Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public <HasDevTools> void TC_04_anthentication_alert_elenium4x()  {
		// cách 3
		// thư viện alert không sử dụng cho authentication alert đc
		// chrome devtool protocol-(CDP)-chrome/edge(chromium)
		// cốc cốc/ opera


		// Get DevTool object
		DevTools devTools = ((HasDevTools) driver).getDevTools();

		// Start new session
		devTools.createSession();

		// Enable the Network domain of devtools
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Encode username/ password
		Map<String, Object> headers = new HashMap<String, Object>();
		String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
		headers.put("Authorization", basicAuthen);

		// Set to Header
		devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

		driver.get("https://the-internet.herokuapp.com/basic_auth");
	}

	
	// sử dụng hàm để thêm usermane và paswork vào url 
	public String passUsernameandPasstoUrl(String url, String username, String passwork) {
	String[] arrayUrl = url.split("//");  // chia 2 phần của url để tao thành mảng để thêm user và pass
	return arrayUrl[0] + "//"+ username + ":" + passwork + "@" +arrayUrl[1];
		
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
