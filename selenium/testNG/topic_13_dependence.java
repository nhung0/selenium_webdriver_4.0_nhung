package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
@Listeners(listener.extentReport.class)
public class topic_13_dependence {
    WebDriver driver;





    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test(timeOut = 10000) // giới hạn chạy của testcase
    public void TC1_create_new_employee() {

    }
    @Test(dependsOnMethods = "TC1_create_new_employee")
    public void TC2_view_new_employee() {

    }
    @Test
    public void TC3_update_new_employee() {

    }
    @Test
    public void TC4_delete_new_employee() {

    }






    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}


// test và tái sử dụng dl test cho nhiều chức năng liên tiếp