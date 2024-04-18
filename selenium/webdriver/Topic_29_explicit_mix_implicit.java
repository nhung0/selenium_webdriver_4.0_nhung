package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;


public class Topic_29_explicit_mix_implicit {
	WebDriver driver;
	WebDriverWait explicitWait;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String quaname = "quá.jpg";
	String canhname = "cảnh.jpg";
	String depname = "đẹp.jpg";

	String quaFile = projectPath + File.separator +"uploadFiles" + File.separator + quaname;
	String canhFile = projectPath + File.separator +"uploadFiles" + File.separator + canhname;
	String depFile = projectPath + File.separator +"uploadFiles" + File.separator + depname;


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

		driver.manage().window().maximize();





	}
	@Test
	public void TC_01_only_implicit_found() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/");
		// khi vào tìm element thì nó sẽ tìm thấy ngay, rất nhanh, k cần đợi hết timeout
		driver.findElement(By.cssSelector("input#email"));





	}

	@Test
	public void TC_02_only_implicit_not_found() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/");
		// khi vào tìm element thì nó sẽ k tm thấy và polling tìm lại, khi hết timeout sẽ fail testcase va throw exception là noSuchElement
		driver.findElement(By.cssSelector("input#auto"));


	}


	@Test
	public void TC_03_only_explicit_found() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/");
		// tìm thấy ngay
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));



	}

	@Test
	public void TC_04_only_explicit_not_found_param_by() {

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/");
		// khi vào tìm element thì nó sẽ k tm thấy và polling tìm lại, khi hết timeout sẽ fail testcase va throw exception là timeoutexception
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("inputauto")));


	}

	@Test
	public void TC_05_only_explicit_not_found_param_element() {

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://www.facebook.com/");
		// khi vào tìm element thì nó sẽ k tm thấy và polling tìm lại, khi hết timeout sẽ fail testcase va throw exception là timeoutexception
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("inputauto"))));


	}

	@Test
	public void TC_06_only_explicit_mix_implicit() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		System.out.println("start time: " + getTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#auto")));
		} catch (Exception e) {
			System.out.println("end time: " + getTime());
			throw new RuntimeException(e);
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

	public String getTime() {
		Date date = new Date();
		return date.toString();
	}
}
