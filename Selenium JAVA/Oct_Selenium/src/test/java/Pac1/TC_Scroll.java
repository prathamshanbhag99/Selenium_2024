package Pac1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Scroll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 // Automatically sets up the correct version of ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        // Initialize the Chrome browser
        WebDriver driver = new ChromeDriver();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        
        // Navigate to Google homepage
        driver.get("https://demo.opencart.com/");
        
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        
        //for scrolling
        JavascriptExecutor js = (JavascriptExecutor)driver; 
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        
        driver.findElement(By.linkText("Contact Us")).click();
        
        
        

	}

}
