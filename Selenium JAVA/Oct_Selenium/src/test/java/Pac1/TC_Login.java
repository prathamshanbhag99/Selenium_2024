package Pac1;

import java.util.concurrent.TimeUnit;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Login {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.opencart.com/");
		
		//IMPLICITLY WAIT
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//Thread.sleep(15000);
		
		driver.findElement(By.linkText("My Account")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));  // Wait for up to 10 seconds
		WebElement ele= wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));  // Wait for a condition to be met


		driver.findElement(By.linkText("Login")).click();
		
	}

}
