package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class topic_03_xpath_css {
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

    }

    @Test
    public void TC_Url() {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        // click my account link ở dưới footer
        driver.findElement(By.xpath("//div[@class ='footer']//a[@title='My Account']")).click();
        // cơ chế sele là luôn thao tác với cái elem đầu tiên nếu có nhiều hơn 1 node
        driver.findElement(By.xpath("//li[@class ='error-msg']//span[text()='Invalid login or password.']")).sendKeys("nhung");
        driver.get("http://live.techpanda.org/index.php/checkout/cart/");
        driver.findElement(By.xpath("//li[@class ='success-msg']//span[text()='Sony Xperia was added to your shopping cart.']")).click();


    }

    @Test
    public void TC_text() {
        driver.get("https://automationfc.github.io/basic-form/");
        //java: system.out.println
        //c#: console.Writeline
        //js: console.log


        System.out.println("text của thẻ H5: " + driver.findElement(By.xpath("//h5[@id='nested']")).getText());
        // code selenium hàm getText: sẽ lấy text từ thẻ cha cho tới thẻ con
        //xpath text: text()= text của thẻ đó thôi, k lấy thẻ con, text nằm ở trên cùng hàng với chính node(tagname) đó- không có nằm trong child node - không phải dang nested text, text có thể là index ở đầu giữa/cuối đều lấy đc so với các thẻ con khác, lấy text tuyệt đối là text không có khoảng trắng/ xuống dòng/ tab ở đầu hoăc cuối chuỗi
        //Contains(Text(),''): text nó nằm trên chính node đó, dạng nested text nhưng text phải nằm ở đầu tiên, nếu text nằm trực tiếp trong child node thì sẽ không lấy đươc, text	này có khoảng trắng/ xuống dòng/ tab ở đầu hoặc cuối text vẫn work được(đương đối), không dùng Contains(Text(='') mà dùng Contains(Text(),'')
        // contains(.,''): text nằm ở đâu cũng lấy đc
        // contains(string(),''): text nằm ở đâu cũng lấy đc, giống . ở trên
        // nối chuỗi: concat()  vd: //span[text()=concat('Hello "john". what', "'s happened")
        String firstname = "nhung";
        String lastname = "pham";
        System.out.println(firstname + " " + lastname);
        System.out.println(firstname.concat(" ").concat(lastname));


    }




    @Test
    public void TC_01_empty_data() {

        //action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
        // verify
        //1. Assert.assertTrue()  kiem tra 1 dieu kien tra ve la dung
        //2. Assert.assertFalse() kiem ta 1 dieu kien tra ve la sai
        //3. Assert.assertEquals(...,...) kiem tra thuc te voi mong doi nhu nhau

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");

    }

    @Test
    public void TC_02_invalid_email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // action
        driver.findElement(By.id("txtFirstname")).sendKeys("jonh wick");
        driver.findElement(By.id("txtEmail")).sendKeys("1234@");
        driver.findElement(By.id("txtCEmail")).sendKeys("1234@");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0357869876");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        // verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");


    }

    @Test
    public void TC_03_incorrect_email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // action
        driver.findElement(By.id("txtFirstname")).sendKeys("jonh wick");
        driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail");
        driver.findElement(By.id("txtCEmail")).sendKeys("123456@gmail");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0357869876");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        // verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_invalid_password () {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // action
        driver.findElement(By.id("txtFirstname")).sendKeys("jonh wick");
        driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail");
        driver.findElement(By.id("txtCEmail")).sendKeys("1234@gmail");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0357869876");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        // verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_incorrect_password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // action
        driver.findElement(By.id("txtFirstname")).sendKeys("jonh wick");
        driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail");
        driver.findElement(By.id("txtCEmail")).sendKeys("1234@gmail");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
        driver.findElement(By.id("txtPhone")).sendKeys("0357869876");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        // verify

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_invalid_phone() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // action 1
        driver.findElement(By.id("txtFirstname")).sendKeys("jonh wick");
        driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail");
        driver.findElement(By.id("txtCEmail")).sendKeys("1234@gmail");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("01578698");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        // verify 1

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // action 2
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("1578698890");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        // verify 2

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // action 3
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("015786988");

        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        // verify 3
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
