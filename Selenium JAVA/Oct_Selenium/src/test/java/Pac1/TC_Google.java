package Pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Google {

    public static void main(String[] args) {
        
        // Automatically sets up the correct version of ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        // Initialize the Chrome browser
        WebDriver driver = new ChromeDriver();
        
        // Navigate to Google homepage
        driver.get("https://www.google.com/");
       
        
        // Print the title of the initial Google page
        System.out.println("Title of the page is: " + driver.getTitle());

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
        //driver.close();
    }
}
