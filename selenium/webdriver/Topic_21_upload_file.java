package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic_21_upload_file {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String quaname = "quá.jpg";
	String canhname = "cảnh.jpg";
	String depname = "đẹp.jpg";

	String quaFile = projectPath + File.separator +"uploadFiles" + File.separator + quaname;
	String canhFile = projectPath + File.separator +"uploadFiles" + File.separator + canhname;
	String depFile = projectPath + File.separator +"uploadFiles" + File.separator + depname;



	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
//		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
//		} else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
//		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_upload_single_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// file này nằm ở đâu , tạo file trong project này luôn, đóng vai trò như nơi lưu giữ file thay cho file explorer
//		String 	filePath = "D:\\auto class\\selenium_webdriver_4.0_nhung\\uploadfiles"; // máy khác k chạy đc đường dẫn này, để upload
		By uploadBy = By.cssSelector("input[name='files[]']");

		driver.findElement(uploadBy).sendKeys(canhFile);
		sleepInSecond(2);
		driver.findElement(uploadBy).sendKeys(depFile);
		sleepInSecond(2);
		driver.findElement(uploadBy).sendKeys(quaFile);
		sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+depname+"']")).isDisplayed()); // kiểm tra load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+canhname+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+quaname+"']")).isDisplayed());

        List<WebElement> startB = driver.findElements(By.cssSelector("td>button.start"));
//        for (int i = 0; i < startB.size(); i++) {
//            startB.get(i).click();
//            sleepInSecond(2);
//
//        } // dùng trong trường hợp có điều kiện để kiểm tra
        for (WebElement button: startB) {
            button.click();
            sleepInSecond(2);

        }

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+depname+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+canhname+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+quaname+"']")).isDisplayed());

	}

	@Test
	public void TC_02_upload_multiple_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// file này nằm ở đâu , tạo file trong project này luôn, đóng vai trò như nơi lưu giữ file thay cho file explorer
//		String 	filePath = "D:\\auto class\\selenium_webdriver_4.0_nhung\\uploadfiles"; // máy khác k chạy đc đường dẫn này, để upload
		By uploadBy = By.cssSelector("input[name='files[]']");

		driver.findElement(uploadBy).sendKeys(canhFile +"\n"+ depFile+"\n"+ quaFile);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+depname+"']")).isDisplayed()); // kiểm tra load lên
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+canhname+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+quaname+"']")).isDisplayed());

		List<WebElement> startB = driver.findElements(By.cssSelector("td>button.start"));
//        for (int i = 0; i < startB.size(); i++) {
//            startB.get(i).click();
//            sleepInSecond(2);
//
//        } // dùng trong trường hợp có điều kiện để kiểm tra
		for (WebElement button: startB) {
			button.click();
			sleepInSecond(2);

		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+depname+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+canhname+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+quaname+"']")).isDisplayed());

	}

	@Test
	public void TC_03_Form() {
		Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
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
