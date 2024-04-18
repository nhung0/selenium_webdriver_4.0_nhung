package webdriver;
import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Checkbox;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DefaultEditorKit.CopyAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.gargoylesoftware.htmlunit.javascript.host.html.Option;
import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

import okio.Timeout;


public class Topic_05_webBrowserP1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// tương tác với browser thì sẽ thông qua biến webDriver driver
		// tương tác với element thì sẽ thông qua biến webElement element
		
		
	}

	@Test
	public void TC_01() {
	// java document: cách sử dụng hàm này như thế nào
		
		//>= 2 tab: đóng tab/window mà nó đang đứng
		// = 1 tab: nó cũng đóng browser
		driver.close(); //*
		
		
		// không quan tâm bao nhiêu tab/window mà sẽ đóng hết trình duyệt
		driver.quit(); //**
		
		// 1 element
		driver.findElement(By.xpath("")); //**
		// có thể lưu nó vào môt biến để sử dụng cho các step sau- dùng lại nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath(""));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		// khi khong luu bien, bad code
		driver.findElement(By.xpath("")).clear();
		driver.findElement(By.xpath("")).sendKeys("");
		// có thể sử dụng luôn không cần tạo biến
		driver.findElement(By.xpath("//button[@id='login']")).click();
		
		
		//số nhiều elements
		List<WebElement> Elements = driver.findElements(By.xpath("")); //*

		
		
		// get: mở ra 1 url
		driver.get("/www.youtube.com/watch?v=9S7_ODHZmQ8&t=3036s"); //**
		// click vào link tiếng viêt trên page
		
		
		
		// trả về url của page hiện tại
		driver.getCurrentUrl();
		// có thể lưu nó vào môt biến để sử dụng cho các step sau- dùng lại nhiều lần
		String vietnampage = driver.getCurrentUrl();
		assertEquals(vietnampage, "/www.youtube.com/watch?v=9S7_ODHZmQ8&t=3036s");
		// có thể sử dụng luôn không cần tạo biến
		assertEquals(driver.getCurrentUrl(), "/www.youtube.com/watch?v=9S7_ODHZmQ8&t=3036s");
		
		
		
		// trả về source code html của page hiện tại
		// verify tương đối
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("nhung")); // lấy ra text chứ nhung trong code html
		
		
		
		// trả về title của page hiện tai
		driver.getTitle(); //vd: facebook đăng nhập hoặc đăng ký
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		
		
		
		
	// webdriver api- window/ tab
		// lấy ra được id của tab/ window mà driver đang đứng (active)
		String loginwindowid = driver.getWindowHandle(); //**
		// lấy ra id tất cả
		Set allIds = (Set) driver.getWindowHandles(); //*
		
		
		
		
	// trả về kiểu option	
		// cookie/cache
		Options opt = driver.manage();
		// login thành công -> lưu lại
		
		opt.getCookies(); //*
	   // testcase khác -> set cookie vào lai -> không cần set cookie nữa
		
		
		opt.logs();
		
		Timeouts time = opt.timeouts();
		// khoảng thời gian cho element xuất hiện
		time.implicitlyWait(5, TimeUnit.SECONDS); //5s
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS); // 5000ms
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS); //5000000μs
		// khoảng thời gian chờ page load xong trong vòng bao nhiêu giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		// khoảng thời gian chờ script đc thưc thi xong trong vòng bao nhiêu giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);

		Window win = opt.window();
		win.fullcreen();
		win.maximize(); //**
		
		// test FUI: functional
		// test GUI: font/size/color/position...
		win.getPosition();//**
		win.getsize(); //**
		
		
		
		
		// navigation
		Navigation nav =	driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		
		nav.to("/www.youtube.com/watch?v=9S7_ODHZmQ8&t=3036s");
		driver.get("/www.youtube.com/watch?v=9S7_ODHZmQ8&t=3036s");
		
		
		
		// switchTo
		TargetLocator tar = driver.switchTo();
		// webdriver API alert
		tar.alert(); //*
		// webdriver API frame/Iframe
		tar.frame(""); //*
		// webdriver API window/tab
		tar.window(""); //*
		
		
	}

	@Test
	public void TC_02_Logo() {


	}

	@Test
	public void TC_03_Form() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
