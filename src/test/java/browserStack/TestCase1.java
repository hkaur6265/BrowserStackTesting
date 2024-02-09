package browserStack;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.github.dockerjava.transport.DockerHttpClient.Request.Method;

public class TestCase1 {
	
	public WebDriver driver = null;
	
	public static final String USERNAME = "harpreetkaur_P7ZdsD";
	public static final String AUTOMATE_KEY = "KLV2i68111JGuVbTzfgh";
	public static final String URL = "https://" +USERNAME + ":" +AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub/";
	
	@BeforeClass
	public void setup() throws MalformedURLException {
		
		MutableCapabilities capabilities = new MutableCapabilities();
		HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
		capabilities.setCapability("browserName", "Chrome");
		bstackOptions.put("os", "Windows");
		bstackOptions.put("osVersion", "11");
		bstackOptions.put("browserVersion", "latest");
		bstackOptions.put("consoleLogs", "info");
		bstackOptions.put("projectName", "Harpreet's First Test");
		bstackOptions.put("buildName", "Harpreet's First Test");
		capabilities.setCapability("bstack:options", bstackOptions);
		
		driver = new RemoteWebDriver(new java.net.URL(URL), capabilities);
		driver.manage().window().maximize();

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
