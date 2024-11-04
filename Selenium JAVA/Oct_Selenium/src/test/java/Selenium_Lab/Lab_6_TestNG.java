package Selenium_Lab;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Lab_6_TestNG {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Setup the WebDriver before each test method
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com/");
    }

    @Test(dataProvider = "productData")
    public void testProductSelectionAndCartModification(String productName) throws InterruptedException {
    	

        // Search for the product using the product name from the DataProvider
        driver.findElement(By.id("inputValEnter")).sendKeys(productName);
        driver.findElement(By.xpath("//*[@id=\"sdHeader\"]/div[4]/div[2]/div/div[2]/button/span")).click();

        
        if(driver.findElement(By.xpath("//*[@id=\"654909822552\"]/div[1]/a/picture/img")).isDisplayed())
        {
        	//product
            driver.findElement(By.xpath("//*[@id=\"654909822552\"]/div[1]/a/picture/img")).click();
            Assert.assertTrue(true);
            
        }
        else
        {
        	 Assert.assertTrue(false);
        }

        // Switch window
        List<String> wh = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(wh.get(1));
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println();

        // price
        WebElement price = driver.findElement(By.xpath("/html/body/div[11]/section/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]/span"));
        System.out.println("Product Name: " + productName);
        System.out.println("The price of the product is: " + price.getText());
        System.out.println();

        // offer
        WebElement offer = driver.findElement(By.xpath("//*[@id=\"buyPriceBox\"]/div[2]/div[2]/div[2]/div[1]/div[1]/div"));
        System.out.println("Current applicable offer: " + offer.getText());
        System.out.println();

        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)");

        //specifications
        WebElement spcs = driver.findElement(By.xpath("//*[@id=\"id-tab-container\"]/div/div[1]/div[2]"));
        System.out.println("Specifications:");
        System.out.println(spcs.getText());

        // Add to cart
        driver.findElement(By.id("add-cart-button-id")).click();
        System.out.println("Item Added to the cart!");

        // Open the cart
        driver.findElement(By.xpath("//div[@class='btn btn-theme-secondary open-cart']")).click();

        // Confirm increase in quantity to 3
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("alert('Do you want to make the Quantity as 3 ?')");
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[5]/ul/li/div[5]/div")).click();
        driver.findElement(By.cssSelector("li[rel='3'].undefined")).click();
        System.out.println("Increased the quantity of the product to 3");

        // Confirm removal from cart
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("alert('Remove product from cart?')");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[@class='remove-item-shortlist']")).click();
        System.out.println("Cart is Empty");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    
    @DataProvider(name = "productData")
    public Object[][] productData() {
        return new Object[][] {
            { "A1 MEN WATCHES Men Sunglasses Combo" }
            ,{ "Fasttrack Watches for Men" }
        };
    }
}
