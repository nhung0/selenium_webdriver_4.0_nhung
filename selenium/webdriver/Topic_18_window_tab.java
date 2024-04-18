package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Topic_18_window_tab {
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
	public void TC_01_basic_form() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(4);

		// gọi hàm dưới, switch sang trang google
		switch_to_window_tabByTitle("Google");


		// thao tác ở trang google
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("selenium");
		sleepInSecond(3);

		//switch sang trang basic form rồi
		switch_to_window_tabByTitle("Selenium WebDriver");
		sleepInSecond(3);

		//click sang trang fb
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switch_to_window_tabByTitle("Facebook");

		//switch sang trang basic form rồi
		switch_to_window_tabByTitle("Selenium WebDriver");
		sleepInSecond(3);
	}

	@Test
	public void TC_02_kyna_english() {
		driver.get("https://skills.kynaenglish.vn/");
		String expectedParent = driver.getWindowHandle();
		driver.findElement(By.cssSelector("div.hotline div.social img[alt='facebook']")).click();
		sleepInSecond(2);
		switch_to_window_tabByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("nhung@gmail");
		sleepInSecond(3);
		switch_to_window_tabByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.cssSelector("div.hotline div.social img[alt='youtube']")).click();
		sleepInSecond(2);
		switch_to_window_tabByTitle("(1891) Kyna.vn - YouTube");

		Assert.assertEquals(driver.findElement(By.cssSelector("div#inner-header-container yt-formatted-string#text")).getText(), "Kyna.vn");
		closeAllWindowWithoutParent(expectedParent);
		sleepInSecond(3);
	}

	@Test
	public void TC_03_window() {
		driver.get("http://live.techpanda.org/");
		String expectedParent = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg li span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg li span")).getText(), "The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg li span")).getText(), "The product IPhone  has been added to comparison list.");

		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		switch_to_window_tabByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-buttons h1")).getText(),"COMPARE PRODUCTS");

		switch_to_window_tabByTitle("Mobile");
		driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
		sleepInSecond(3);

		closeAllWindowWithoutParent(expectedParent);


	}



	@Test
	public void tc_4_selenium_ver4() {
		driver.get("https://skills.kynaenglish.vn/");
		System.out.println("driver id = " + driver.toString());
		// create 1 window mới
		WebDriver coursedriver = driver.switchTo().newWindow(WindowType.WINDOW); // driver sẽ có 1 id khác, driver chuyển sang trang mới rồi, k còn là driver của trang ban đầu
		coursedriver.get("https://skills.kynaenglish.vn/guitar-fingerstyle-cho-nguoi-chua-biet-gi");
		System.out.println("driver fb id = " + coursedriver.toString());
		coursedriver.findElement(By.cssSelector("a.crs-btn-buy")).click();
		sleepInSecond(3);

		//mở từ win mới 1 tab nữa
		WebDriver fbdriver = coursedriver.switchTo().newWindow(WindowType.TAB);
		fbdriver.get("https://www.facebook.com/kyna.vn");
		driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("nhung@gmail");
		sleepInSecond(3);
		//switch về trang ban đầu
		switch_to_window_tabByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(3);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("java");
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

	public void switch_to_window_tabByTitle(String expectedId) {
		//lấy ra hết các tab/window id
		Set<String> allIDs = driver.getWindowHandles(); // set string

		// dùng vòng lăp duyệt qua từng ID
		for (String id: allIDs) {
			//cho switch vào từng id trc
			driver.switchTo().window(id);
			// lấy ra title của tab/window id hiện tại
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(expectedId)) {
				break; // thoát khỏi vòng lặp khoongn cân kiểm tra các id khác

			}
		}

	}
	public void closeAllWindowWithoutParent(String expectedParent) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if (!id.equals(expectedParent)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(expectedParent);
	}

}
