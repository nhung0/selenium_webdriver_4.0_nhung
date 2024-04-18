package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class topic_11_timeout {
    WebDriver driver;
    Random rand = new Random();
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String employeeID = String.valueOf(rand.nextInt(99999));
    String passNumber = "11-22-33-44";
    String commentInput = "Chúc các đội tuyển\nhi HSG Quốc gia năm học 2023-2024 sẽ thật thành công.";




    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test(timeOut = 10000) // giới hạn chạy của testcase
    public void TC_create_new_employee() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();

        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

        driver.findElement(By.name("firstName")).sendKeys("automation");
        driver.findElement(By.name("lastName")).sendKeys("FC");
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));

        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeID);
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label")).click();

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("auto" + employeeID );
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Nhungpham03@");
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Nhungpham03@");


        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"automation");
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);


        driver.findElement(By.xpath("//button[text()=' Save ']")).click();


        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(commentInput);
        driver.findElement(By.xpath("//button[text()=' Save ']")).click();

        driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']/parent::button/following-sibling::button/i[@class='oxd-icon bi-pencil-fill']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentInput);

        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        driver.findElement(By.name("username")).sendKeys("auto" + employeeID);
        driver.findElement(By.name("password")).sendKeys("Nhungpham03@");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        driver.findElement(By.xpath("//span[text()='My Info']/parent::a")).click();
        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"automation");
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']/parent::button/following-sibling::button/i[@class='oxd-icon bi-pencil-fill']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentInput);
    }






    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}


