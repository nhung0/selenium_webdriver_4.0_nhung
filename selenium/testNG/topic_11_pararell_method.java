package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Random;

public class topic_11_pararell_method {
   private WebDriver driver;

    @BeforeMethod // khởi tạo riêng cho từng testcase
        public void beforeMethod() throws InterruptedException {

            driver = new FirefoxDriver();
            driver.get("https://www.oxfordlearnersdictionaries.com/");
            Thread.sleep(5000);
        }
    @Test
        public void TC_01_Login_() {
            System.out.println("run testcase 1");

        }

    @Test
        public void TC_02_Login_() {
            System.out.println("run testcase 2");


        }

    @AfterMethod
        public void afterClass() {
            driver.quit();
        }
    }




