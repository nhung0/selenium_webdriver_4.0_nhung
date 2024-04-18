package testNG;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class topic_03_assert {

    WebDriver driver;
    @Test
    public void tes01() {
        System.out.println("test 01");
       //equal=> kiểm tra 2 dữ liêu bằng nhau(string, boolean, float)
        String name = "nhung";
        Assert.assertEquals(name, "nhung", "actual fullname is not the same"); // nếu lỗi sẽ có hiển thih message
        //true-false =>điều kiện nhận vào là boolean, mong đợi trả về kêt quả đúng hoặc sai
      Assert.assertTrue(iselement(By.cssSelector("")), "element is not displayed");
    }

    private boolean iselement(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

}
