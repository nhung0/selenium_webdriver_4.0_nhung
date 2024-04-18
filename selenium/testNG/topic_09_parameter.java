package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class topic_09_parameter {

    WebDriver driver;



    @Parameters({"browser", "version"})
    @BeforeClass
    public void beforeClass(String browsername, String versionname) {
        driver = getBrowserDriver(browsername) ;
        System.out.println("browser" + browsername +"with" + versionname);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Parameters("environment")
    @Test()
    public void TC_01_Login(@Optional("live") String environmentName)  { // bắt buộc chạy name là live, set mặc định
        driver.get(getenvironmentUrl(environmentName)+"/index.php/customer/account/login/");

        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
        driver.findElement(By.xpath("//*[@id='send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));


    }

    private WebDriver getBrowserDriver(String browsername) {
        if (browsername.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browsername.equals("chrome")) {
            driver = new ChromeDriver();
        }else if (browsername.equals("edge")){
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("browser is not valid");
        }
        return driver;
    }

    private String getenvironmentUrl(String environmentname) {
        String urldriver;
        if (environmentname.equals("dev")) {
            urldriver = "http://dev.techpanda.org/";
        } else if (environmentname.equals("testing")) {
            urldriver = "http://testing.techpanda.org/";
        }else if (environmentname.equals("staying")){
            urldriver = "http://staying.techpanda.org/";
        } else if (environmentname.equals("live")) {
            urldriver = "http://live.techpanda.org/";
         }else {
            throw new RuntimeException("browser is not valid");
        }
        return urldriver;
    }




    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
