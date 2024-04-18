package webdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Topic_04_run_on_browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
 

	@Test
	public void TC_01_Run_Chrome() {
		//chrome
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.quit();
	}

	@Test
	public void TC_02_Run_Firefox() {
		// firefox
		if (osName.contains("Windows")) {	
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.quit();
	}

	@Test
	public void TC_03_Run_Edge() {
		// edge
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}
		driver = new EdgeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.quit();
	}

	
}
