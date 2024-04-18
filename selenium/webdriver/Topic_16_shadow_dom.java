package webdriver;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic_16_shadow_dom {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_shadow_dom() {
		// driver đại diện cho real DOM bên ngoài
		driver.get("https://automationfc.github.io/shadow-dom/");
		sleepInSecond(5);
		//đi theo đúng cấu trúc của html/dom
		// shadowHost đại diên cho shadow Dom 1 bên trong
		WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
		SearchContext shadowContext = shadowHost.getShadowRoot();


		String someText = shadowContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
		System.out.println(someText);
		Assert.assertEquals(someText, "some text");
		sleepInSecond(3);

		WebElement checkboxShadow = shadowContext.findElement(By.cssSelector("input[type='checkbox']"));
		Assert.assertFalse(checkboxShadow.isSelected());
		sleepInSecond(2);

		List<WebElement> allinput = shadowContext.findElements(By.cssSelector("input"));
		System.out.println(allinput.size());


		// nestedShadowHost đại diện cho nested shadow DOM 2 (nằm trong shadow DOM 1)
		WebElement nestedShadowHost = shadowContext.findElement(By.cssSelector("div#nested_shadow_host"));
		SearchContext nestedShadowContext = nestedShadowHost.getShadowRoot();
		String nestedText = nestedShadowContext.findElement(By.cssSelector("div#nested_shadow_content")).getText();
		Assert.assertEquals(nestedText, "nested text");
		sleepInSecond(2);


	}

	@Test
	public void TC_02_shadow_dom_shopee() {
		driver.get("https://shopee.vn/");
		sleepInSecond(3);
		WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
		SearchContext shadowRootContext = shadowHostElement.getShadowRoot();
		if (shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).size()>0 && shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()) {
			//click để close popup đi
			shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			sleepInSecond(2);

		} else {
			//không hiển thị thif qua bước search dữ liệu
			driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iphone 15");
			sleepInSecond(3);
			driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();


		}
	}
	@Test
	public void TC_03_Form() {

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
