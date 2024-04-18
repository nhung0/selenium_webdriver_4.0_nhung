package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class topic_02_selenium_locator {
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


        // mo trang register ra
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    // đây là cái html của firstname textbox
    // <input type="text" data-val="true" data-val-required="Yêu cầu điền tên." id="FirstName" name="FirstName">

    // ten the html(tagname in html): input
    // ten cua thuoc tinh(attribute name): type type data-val-required id name
    // gia tri cua thuoc tinh(attribute value): text true firstname  yeu cau dien ten



    @Test
    public void TC_01_ID() {
        // thao tac len element thi dau tien phai tim duoc element do: findElement
        // find theo cai gì: id/class/name/css...
        // find tìm thay element roi có the action len element do : click /sendkeys....
        driver.findElement(By.id("FirstName")).sendKeys("nhung");

    }

    @Test
    public void TC_02_Class() {
        // mo man hinh search
        driver.get("https://demo.nopcommerce.com/search");
        // nhap text vao search textbox
        driver.findElement(By.className("search-text")).sendKeys("pham");
    }

    @Test
    public void TC_03_Name() {
        // click vào advanced search textbox
        driver.findElement(By.name("advs")).click();

    }

    @Test
    public void TC_04_TagName() {
        // tìm ra bao nhieu thẻ input trên màn hình hiện tại
        System.out.println(driver.findElement(By.tagName("input")).getSize());
    }

    @Test
    public void TC_05_LinkText() {
        //click vào đường link addresses(tuyệt đối)
        driver.findElement(By.linkText("Addresses")).click();

    }

    @Test
    public void TC_06_Css() {
        // mở lại trang register
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        //1
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("selenium");

        //2
        driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("locatorlastname");

        //3
        driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("nameemail");


    }

    @Test
    public void TC_07_Xpath() {
        // mở lại trang register
        driver.get("https://demo.nopcommerce.com/register");
        //1
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("selenium");

        //2
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("locatorlastname");

        //3
        driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("nhungphamthi");

    }

    @Test
    public void TC_08_PartialLinkText() {
        // click vào đường link Apply for vendor account (tương đối)
        driver.findElement(By.partialLinkText("Apply for vendor"));
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
