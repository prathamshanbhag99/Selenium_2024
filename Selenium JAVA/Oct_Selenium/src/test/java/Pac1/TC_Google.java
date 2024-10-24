package Pac1;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Google {

    public static void main(String[] args) throws InterruptedException {
        
        // Automatically sets up the correct version of ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        // Initialize the Chrome browser
        WebDriver driver = new ChromeDriver();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        
        // Navigate to Google homepage
        driver.get("https://www.google.com/");
        
        
        
        JavascriptExecutor js = (JavascriptExecutor) driver;js.executeScript("alert('testing methods')");
        Thread.sleep(5000);
        
        
        
        //for page loading sync
        
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        
    	
		//IMPLICITLY WAIT
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//Thread.sleep(15000);

        
        // Print the title of the initial Google page
        System.out.println("Title of the page is: " + driver.getTitle());
        
 
        //Explicitly Wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));  // Wait for up to 10 seconds
		WebElement ele1= wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));  // Wait for a condition to be met

		Wait wait1 = new FluentWait(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		
        // Locate the search box using its 'name' attribute
        WebElement ele = driver.findElement(By.name("q"));
        
        // Enter the search query "Testing Methods" into the search box
        ele.sendKeys("Testing Methods");
        
        // Submit the form (this simulates hitting the Enter key)
        ele.submit();
       
        
        // Print the title of the page after the search
        System.out.println("Title of the page is: " + driver.getTitle());
        
        
        driver.navigate().to("https://www.yahoo.com/");
        System.out.println("Title of the page is: " + driver.getTitle());
        
        driver.navigate().back();
        System.out.println("Title of the page is: " + driver.getTitle());
        
        driver.navigate().forward();
        System.out.println("Title of the page is: " + driver.getTitle());
        
        System.out.println(" URL is: " + driver.getCurrentUrl());
        
        //System.out.println("Page source: " + driver.getPageSource());
        System.out.println("Page source is : " + driver.getPageSource().contains("yahoo"));
        
        
        // Close the browser window
        driver.quit();
    }
}
