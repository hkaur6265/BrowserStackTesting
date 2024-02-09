package browserStack;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowserTesting {
	
public WebDriver driver = null;
	
	public static final String USERNAME = "harpreetkaur_P7ZdsD";
	public static final String AUTOMATE_KEY = "KLV2i68111JGuVbTzfgh";
	public static final String URL = "https://" +USERNAME + ":" +AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub/";
	
	@Parameters({"browser", "browser_Version", "OS", "OS_version"})
	@BeforeClass
	public void setup(String browser, String browser_Version, String OS, String OS_version, Method name) throws MalformedURLException, InterruptedException {
		
		System.out.println("Browser Name is: " +browser);
		String methodName = name.getName();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("OS", "Windows");
		caps.setCapability("OS_version", "11");
		caps.setCapability("browser_version", "121");
		caps.setCapability("name", "Harpreet's First Test");
		
		if(browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			caps.setCapability("browser", "Chrome");
		}else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			caps.setCapability("browser", "firefox");
		}
		
		driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
	}
	@Test
	public void verifyLogin() throws InterruptedException {
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
