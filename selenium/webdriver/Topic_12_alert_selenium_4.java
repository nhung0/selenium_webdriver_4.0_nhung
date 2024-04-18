package webdriver;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Topic_12_alert_selenium_4 {
    WebDriver driver;
    WebDriverWait expliciWait;

    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
    String authenChrome = projectPath + "\\autoIT\\authen_firefox.exe";


    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }
        System.setProperty("webdriver.opera.driver", "url");
        ChromeOptions option = new ChromeOptions();// không hỗ trợ chạy opera cho ver selenium 4x, chỉ hỗ trợ cho ver selenium 3x
        option.setBinary("\"C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe\""); // chạy thử cốc cốc
        driver = new ChromeDriver(option);
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }




    @Test
    public void tc_01_alert_selenium_4_authen() {
    //cách 3:
        //thư viện không sử dung alert cho authenication được
        //chrom devtool protocol -chrome/edge (chromium)
        //cốc cốc/opera vẫn work đc
        String username = "admin";
        String password = "admin";

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),' Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }



    @Test
    public void TC_02_Run_On_Chrome() {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }



    @Test
    public void TC_03_Run_On_Edge() {
        driver = new EdgeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }



    // sử dụng hàm để thêm username và paswork vào url
    public String passUsernameandPasstoUrl(String url, String username, String passwork) {
        String[] arrayUrl = url.split("//");  // chia 2 phần của url để tao thành mảng để thêm user và pass
        return arrayUrl[0] + "//"+ username + ":" + passwork + "@" +arrayUrl[1];

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
//		driver.quit();
    }


}

// chrome browser ver 118, 119 và sử dụng selenium ver 4.14.1