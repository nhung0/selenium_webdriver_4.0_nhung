package webdriver;

import org.openqa.selenium.support.ui.Select;
import org.apache.commons.lang3.math.Fraction;
import org.apache.xerces.impl.dv.xs.DayDV;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;

import java.awt.Robot;
import java.security.PublicKey;
import java.time.Year;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.Popup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_08_default_dropdown {
	WebDriver driver;
//	Select select;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname, emailaddress, companyname, password;
	String day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;
	
	
	
	
 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		emailaddress = "elonmusk" +  rand.nextInt(9999) + "@gmail.com" ;
		firstname = "elon";
		lastname = "musk";
		companyname = "spaceX";
		password = "123456";
		day = "9";
		month = "December";
		year= "1997";
		countryName = "prance";
		provinceName = "jem";
		cityName = "abc";
		addressName = "12 nhung";
		postalCode = "123345";
		phoneNumber = "0387889743";
	}

	@Test
	public void TC_01_register_new_account() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		
//		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
//		// chọn ngày 7 với giá trị index
//		select.selectByIndex(7);
//		// chọn ngày 7 với giá trị value
//		select.selectByValue("7");
//		// chọn ngày 7 với giá trị text, đc dùng nhiều nhất
//		select.selectByVisibleText("7");
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailaddress);
		driver.findElement(By.id("Company")).sendKeys(companyname);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("Comfirt Password")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		// verify
		
		Assert.assertEquals(driver.findElement(By.id("FisrtName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastname);
		
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyname);
		
		
	}

	@Test
	public void TC_02_add_address() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.address-button")).click();
		
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstname);
		driver.findElement(By.id("Address_LasttName")).sendKeys(lastname);
		driver.findElement(By.id("Address_Email")).sendKeys(emailaddress);
		driver.findElement(By.id("Address_Company")).sendKeys(companyname);
		driver.findElement(By.id("Address_CompanyId")).sendKeys("");
		new Select(driver.findElement(By.id("Address_CompanyId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
		driver.findElement(By.cssSelector("button.save-address-button")).click();
				
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstname + " " + lastname);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailaddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyname);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address")).getText(),addressName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zips")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zips")).getText().contains(provinceName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zips")).getText().contains(postalCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(),countryName);
	
	
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


// hàm random
	// public static int getRandomNumber() {
	// Random rand =  new Random();
	//	return rand.nextInt(99999);
	//}