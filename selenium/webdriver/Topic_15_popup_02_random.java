package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_15_popup_02_random {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Window")) {
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
	public void TC_01_random_in_dom() {
		driver.get("https://vnk.edu.vn/");

		findElement(By.cssSelector("button.btn-danger")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");
	}
	// step nào thao tác ở màn hình home mới gọi đến hàm này
	public WebElement findElement(By locator) {
		// khi popup xuâất hiện thì nó sẽ close đi
		if (driver.findElement(By.cssSelector("div#tve-p-scroller>article")).isDisplayed()) { //>0 là render ra nhưng chưa biết là hiển thị hay k hiển thị,
			System.out.println("hiển thị");
			driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
			sleepInSecond(2);
		}
		return driver.findElement(locator);

	}


	@Test
	public void TC_02_not_in_dom() {

		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(10);

		By newsletterpopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		// nếu hiển thị thì nhảy vào close nó đi, element luôn cos trong dom
		if (driver.findElements(newsletterpopup).size()>0 && driver.findElements(newsletterpopup).get(0).isDisplayed()) { //>0 là render ra nhưng chưa biết là hiển thị hay k hiển thị,
			System.out.println("không hiển thị");
			driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a")).click();
			sleepInSecond(2);
		} else { // nếu không hiển thị thì qua step tiếp
			System.out.println("hiển thị");
		}

		//nhập vào field search dữ liệu
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("agile");
		//click
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(2);
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Product Roadmap in Jira']")).isDisplayed());

	}


	@Test
	public void TC_03_not_in_dom2() {

		driver.get("https://dehieu.vn/");
		sleepInSecond(10);

		By dehieupopup = By.cssSelector("div.popup-content");
		// nếu hiển thị thì nhảy vào close nó đi, element luôn cos trong dom, trường hợp bị elêmnt  khác cover
		if (driver.findElements(dehieupopup).size()>0 && driver.findElements(dehieupopup).get(0).isDisplayed()) { //>0 là render ra nhưng chưa biết là hiển thị hay k hiển thị,
			System.out.println("không hiển thị");
			int heightBROWSER = driver.manage().window().getSize().getHeight();
			if (heightBROWSER<1920){
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
			}else {
				driver.findElement(By.cssSelector("button#close-popup")).click();

			}
			sleepInSecond(5);
		}

		//nhập vào field search dữ liệu
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("agile");
		//click
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(2);
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Product Roadmap in Jira']")).isDisplayed());

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





// ctrl + shift + delete : xóa cache