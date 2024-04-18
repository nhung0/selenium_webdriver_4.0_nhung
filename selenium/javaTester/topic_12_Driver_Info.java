package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class topic_12_Driver_Info {
    WebDriver driver;
    @Test
    public void testDriverInfomation() {
        //ở trên OS nào
        //browser nào
        //browseer class
        // id của driver
        driver = new FirefoxDriver();
        System.out.println(driver.toString());
        if (driver.toString().contains("firefox")) {
            //scroll
        }

        driver.quit();
    }
}
