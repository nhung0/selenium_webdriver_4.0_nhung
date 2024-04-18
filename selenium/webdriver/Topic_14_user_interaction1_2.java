package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class Topic_14_user_interaction1_2 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor javascriptExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		// nó đang giả lập laại hành vi của mouse/keyboard/pen nên khi nó đang chạy mình không nên sử dụng các thiết bị này, vì nó sẽ mất sư kiện
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test
	public void TC_00_demo() {
		action.click(driver.findElement(By.xpath(""))).perform(); // phải có perform ở cuối mỗi hàm để thực thi câu lêệnh
		action.click(driver.findElement(By.xpath(""))).build(); // không có build vẫn chạy đc, khi kết hợp 2 hành động cùng lúc, cần hoặc không cần build cũng đc
		action.clickAndHold(driver.findElement(By.xpath(""))).release(); // chỉ dùng với click and hold, hành động nhả chuột trái
		action.clickAndHold(driver.findElement(By.xpath("nguồn"))).scrollToElement(driver.findElement(By.xpath("đích"))).release().perform(); // động tác nhấn hover chuột rồi thực hiện kéo thả


	}

	@Test
	public void TC_01_hover_tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement ageTextbox = driver.findElement(By.cssSelector("#age"));
		action.moveToElement(ageTextbox).perform(); //hover thành công
		sleepInSecond(3);
		assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");


	}

	@Test
	public void TC_02_hover_menu_login() {
//trang này không demo test đc
//		driver.get("https://www.myntra.com/");
//		action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
//		sleepInSecond(2);
//    //driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']")).click(); // hàm click này bắt buộc element đó hiển thị mới click đc
//		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))).perform(); //trc khi click thì chính nó thực hiên hành động movetoElement
//
//		Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(),"Kids Home Bath");

		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.cssSelector(".icon_menu"))).perform();
		sleepInSecond(2);
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform(); //trc khi click thì chính nó thực hiên hành động movetoElement
		sleepInSecond(2);

		String submenu = "Thiết Bị Số - Phụ Kiện Số";

        driver.findElement(By.xpath("//div[contains(@class, 'fhs_menu_content')]//a[text()= 'Thiết Bị Số - Phụ Kiện Số']")).click();
        sleepInSecond(2);
        assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIỆN SỐ"); //cách 1
		Assert.assertTrue(driver.findElement(By.cssSelector("ol.breadcrumb strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed()); //cách 2


	}
    @Test
	public void TC_03_click_and_hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// tổng số chưa chọn
		List<WebElement> elementsnumber = driver.findElements(By.cssSelector("li.ui-state-default"));

		Assert.assertEquals(elementsnumber.size(), 20);
        //chọn theo block ngang/dọc
        //chọn  1->15 theo hàng/cột(là chỉ chọn các số 1 đến 15 trong cái hàng và cái cột chứa số 1 đến số 15)
        action.clickAndHold(elementsnumber.get(0)) //click lên số 1 và giữ chuột
                .pause(2000)
                .moveToElement(elementsnumber.get(14)) //di chuột trái đến số 15
//                .pause(2000)
//                .release() //nhả chuôt trái ra
                .perform(); //execute tất cả hành đông trên

		sleepInSecond(3);

		// tạo một mảng gồm các text mong đợi
//		List<String> allnumberTextExpected = new ArrayList<String>();
//		allnumberTextExpected.add("1");
//		allnumberTextExpected.add("2");
//		allnumberTextExpected.add("3");
//		allnumberTextExpected.add("5");
//		allnumberTextExpected.add("6");
//		allnumberTextExpected.add("7");
//		allnumberTextExpected.add("9");
//		allnumberTextExpected.add("10");
//		allnumberTextExpected.add("11");
//		allnumberTextExpected.add("13");
//		allnumberTextExpected.add("15");

		String[] allnumberTextExpectedArray = {"1","2","3","5","6","7","9","10", "11","13","14","15"};

		// tổng các số đã chọn
		List<WebElement> allnumbersSelected = driver.findElements(By.cssSelector("ui-selectee ui-selected"));
		Assert.assertEquals(allnumbersSelected.size(), 12);

		// tạo một mảng để chứa text đã chọn, cho lặp qua element đã chọn để get text
		List<String> allNumberTextActual = new ArrayList<String>();
		for (WebElement element: allnumbersSelected) {
			allNumberTextActual.add(element.getText());
//			 kiểm tra màu text xem có đúng với mong đợi không
//			Assert.assertEquals(element.getCssValue("background-color"), "");
		}

		// convert từ Array sang ArrayList (List)
		List<String> allnumberTextExpected = Arrays.asList(allnumberTextExpectedArray);
		// kiểm tra xem text đã chọn có phải như mình mong muốn không
		Assert.assertEquals(allnumberTextExpected, allNumberTextActual);

	}

	@Test
	public void TC_04_click_and_hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");


		// xét bàn phím cho window và macos
		String osName = System.getProperty("os.name");
		Keys keys;
		if (osName.startsWith("Window")) {
			keys = Keys.CONTROL;
		} else {
			keys = Keys.COMMAND;
		}
		// biểu thức tam nguyên, kiểm tra hệ điều hành và dùng phím ctrl
		Keys ctrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;


		// tổng số chưa chọn
		List<WebElement> elementsnumber = driver.findElements(By.cssSelector("li.ui-state-default"));
		Assert.assertEquals(elementsnumber.size(), 20);
		//chọn theo block ngang/dọc
		//chọn  1->12	 theo đủ hàng/cột (sử dung chuôt kéo)
		action.clickAndHold(elementsnumber.get(0)).moveToElement(elementsnumber.get(11)).release().perform();
		// chọn từ 13-> 15( sử dung chột và phím ctrl)
		action.keyDown(Keys.CONTROL).perform(); // nhấn phím ctrl xuống nhưng chưa nhả ra
		action.click(elementsnumber.get(12))
				.click(elementsnumber.get(13))
				.click(elementsnumber.get(14))
				.keyUp(Keys.CONTROL)
				.perform();
		//cách dùng vòng for
//		for (int i = 0; i< elementsnumber.size(); i++) {
//			action.keyDown(ctrl).perform();
//			if (i< 15) {
//				action.click(elementsnumber.get(i));
//			}
//
//			action.perform();
//			action.keyUp(ctrl).perform();
//		}


		sleepInSecond(3);

		// tổng các số đã chọn
		List<WebElement> allnumbersSelected = driver.findElements(By.cssSelector("ui-selectee ui-selected"));
		Assert.assertEquals(allnumbersSelected.size(), 15);




	}


	@Test
	public void TC_05_double_click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleclickbutton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		//cần scroll tới element rồi mới double click, chỉ riêng firefox
		if (driver.toString().contains("chrome")) {
		//scrollIntoView(true): kéo mép trên element lên phía trên cùng của viewport
		// scrollIntoView(false): kéo mép dưới của element xuống phía dươi cùng của viewport
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(false);", doubleclickbutton);
		}

		action.doubleClick(doubleclickbutton).perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
		Assert.assertTrue(driver.findElement(By.cssSelector("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());

	}

	@Test
	public void TC_06_right_click() {
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		// chưa click chuột phải lên nó sẽ không hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(2);
		
		// mới click chuột phải lên - các element sẽ đc visible
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(2);
		// được cập nhật lại class của element này, kiểm tra sự kiện hover thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		// click lên paste
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(2);

		driver.switchTo().alert().accept();
		sleepInSecond(2);
		//kiểm tra paste không còn hiên thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());



	}

	@Test
	public void TC_07_drag_drop_html4() {
	driver.get("https://automationfc.github.io/kendo-drag-drop/");
	WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
	WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
	action.dragAndDrop(smallCircle, bigCircle).pause(3000).perform();
	sleepInSecond(2);
	Assert.assertEquals(bigCircle.getText(),"You did great!");
	Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase() ,"#03a9f4");



	}


	@Test
	public void TC_08_drag_drop_html5_css() throws IOException {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		String columnA = "div#column-a";
		String columnB = "div#column-b";

        String projectPath = System.getProperty("user.dir");

        String dragAndDropFilePath = projectPath + "/drag and drop/drag_and_drop_helper.js";

        String jscontentFile = getContentFile(dragAndDropFilePath);

		jscontentFile = jscontentFile + "$('" + columnA + "').simulateDragDrop({ dropTarget: '" + columnB + "'});";

        //thực thi đoaạn lệnh js từ A sang b
		javascriptExecutor.executeScript(jscontentFile);
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

		//thực thi đoaạn lệnh js từ b sang a
		javascriptExecutor.executeScript(jscontentFile);
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");




	}
	@Test
	public void TC_09_drag_drop_html5_xpath() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		dragAndDropHTML5ByXpath("//div[@id='column-a","//div[@id='column-b]");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

		dragAndDropHTML5ByXpath("//div[@id='column-a","//div[@id='column-b]");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
		
	}



	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}



    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
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

}

//ver4: hay dùng
//	action.scrollFromOrigin();
//	action.getActiveKeyboard();
//	action.getActivePointer();
//	action.getActiveWheel();
//	action.scrollByAmount();
//	action.scrollToElement();
//	action.setActiveKeyboard();
//	action.setActivePointer();
//	action.setActiveWheel();


//set debugger
// setTimeout(() => {debugger};, 3000); nhập dòng này vào tab console


//nếu dùng hàm getText() nó sẽ lấy cái text trên UI
// nếu dùng text trong xpath text này sẽ là text dưới html => isDisplayed()