package webdriver;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;


public class Topic_20_javascript_executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		// ép kiểu tường minh
		//từ kiểu dữ liệu này sang kiểu dữ liệu khác
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


	}

	@Test
	public void TC_01_() {
//		driver.get("http://live.techpanda.org/");
		executeForBrowser("window.location = 'http://live.techpanda.org/'");
		sleepInSecond(4);

		String PaDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(PaDomain,"live.techpanda.org");

		String PaUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(PaUrl,"http://live.techpanda.org/");

		hightlightElement("//a[text()='Mobile']");
		// không giống hành vi thưc của end user
		clickToElementByJS("//a[text()='Mobile']");
		// giả lập những hành vi thực của end user
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();


		hightlightElement("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']/button");
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		String SerTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(SerTitle, "Customer Service");

		scrollToBottomPage();

		hightlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", getEmailAddress() );
		hightlightElement("//span[text()='Subscribe']");
		clickToElementByJS("//span[text()='Subscribe']");
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("https://www.facebook.com/");
		Assert.assertEquals(executeForBrowser("return document.domain"), "facebook.com");
		


	}

	@Test
	public void TC_02_Logo() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		driver.findElement(By.cssSelector("button[value='Đăng ký']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"), "Please fill out this field."); // sử dụng js để get message ra
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("automation");
		driver.findElement(By.cssSelector("button[value='Đăng ký']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"), "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("testing");
		driver.findElement(By.cssSelector("button[value='Đăng ký']")).click();
		sleepInSecond(3);
			Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "lease fill out this field.");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aa@bb@aa");
		driver.findElement(By.cssSelector("button[value='Đăng ký']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "A part following '@' should not contain the symbol '@'.");



	}

	@Test
	public void TC_03_creat_account() {
		executeForBrowser("return window.location = 'http://live.techpanda.org/'");
		hightlightElement("//div[@class='page-header-container']//a[@title='My Account']");
		clickToElementByJS("//div[@class='page-header-container']//a[@title='My Account']");
		hightlightElement("//span[text()='Create an Account']");
		clickToElementByJS("//span[text()='Create an Account']");

		sendkeyToElementByJS("//input[@name='firstname']","elegent");
		sendkeyToElementByJS("//input[@name='middlename']","abc");
		sendkeyToElementByJS("//input[@name='lastname']","rabbit");
		sendkeyToElementByJS("//input[@id='email_address']",getEmailAddress());
		sendkeyToElementByJS("//input[@id='password']","123456");
		sendkeyToElementByJS("//input[@id='confirmation']","123456");

		clickToElementByJS("//span[text()='Register']");
		Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));

		navigateToUrlByJS("http://live.techpanda.org/index.php/");
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'This is demo site for')]")).isDisplayed());






	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String executeForBrowser(String javaScript) {
		return (String) jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public String getEmailAddress () {
		Random rand = new Random();
		return "nhung" + rand.nextInt(99999) + "@gmail.net";
	}
}
