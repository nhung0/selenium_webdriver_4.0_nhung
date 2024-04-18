package webdriver;

import graphql.language.SelectionSet;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class Topic_14_user_interaction1_demo {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}


	@Test
	public void TC_1_hover_tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement ageTextBox = driver.findElement(By.cssSelector("#age"));
		action.moveToElement(ageTextBox).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes." );

	}

	public void TC_2_hover_menu_login() {
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.cssSelector(".icon_menu"))).perform();
		sleepInSecond(2);
		action.moveToElement(driver.findElement(By.cssSelector("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector(".breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIỆN SỐ"); // cách 1 lấy text theo kiểu người dùng nhìn thấy
		Assert.assertTrue(driver.findElement(By.cssSelector("ol.breadcrumb strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed()); // cách 2 lấy text theo gốc html

	}

	public void TC_3_click_and_hold() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	// tổng số chưa chọn
		List<WebElement> numbertotal = driver.findElements(By.cssSelector("li.ui-state-default"));
		Assert.assertEquals(numbertotal,20);
		// chon theo block ngang/dọc
		// chọn tử 1-> 15 theo hàng và cột là chỉ chọn đươc các số từ 1 đn 15 theo hàng và cột chứa số từ 1 đến 15
		action.clickAndHold(numbertotal.get(0))
				.pause(2000)
				.moveToElement(numbertotal.get(14))
				.pause(2000)
				.release()
				.perform();
		sleepInSecond(3);


	}



	
	
	public void sleepInSecond(long timeInSecond) {
			try {
				Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}