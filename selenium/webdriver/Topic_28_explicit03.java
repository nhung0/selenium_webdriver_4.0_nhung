package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;


public class Topic_28_explicit03 {
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().window().maximize();





	}
	@Test
	public void TC_01_ajax_loading() {


		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		By selectedlement = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");
		// có 1 đi chỉ id trc khi click
		Assert.assertEquals(driver.findElement(selectedlement).getText(),"No Selected Dates to display.");
		driver.findElement(By.xpath("//a[text()='21']")).click();

		// wait cho mất loading xuất hiện
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		// có 1 đia chỉ id khác sau khi mình click vào đia chỉ id trc lên ta thưc hiện đăt biến by chứ k đặt biến element
		Assert.assertEquals(driver.findElement(selectedlement).getText(),"Thursday, March 21, 2024");




	}

	@Test
	public void TC_02_upload_file() {

		driver.get("https://gofile.io/welcome");
		// wait + verify cái spinner biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
		// wait + click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();
		// wait + verify cái spinner biến mất trong lượt tiếp theo
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));


		driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(canhFile +"\n"+ depFile+"\n"+ quaFile);
		// wait + verify cái spinner biến mất trong lượt tiếp theo
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
		// wait  cho progress bar biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

		// wait + verify button Download c tại từng hình đc upload lên
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+depname+"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div/a//span"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+canhname+"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div/a//span"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+quaname+"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div/a//span"))).isDisplayed());


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
