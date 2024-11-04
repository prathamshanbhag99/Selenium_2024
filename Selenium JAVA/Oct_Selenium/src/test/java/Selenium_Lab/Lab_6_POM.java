package Selenium_Lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab_6_POM {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        SnapdealPage snapdealPage = new SnapdealPage(driver);

        // Navigate to Snapdeal
        driver.get("https://www.snapdeal.com/");

        // Search for a product
        String productName = "A1 MEN WATCHES Men Sunglasses Combo";
        snapdealPage.searchForProduct(productName);
        
        // Click on the product
        snapdealPage.clickOnProduct();
        
        // Switch to new window
        snapdealPage.switchToNewWindow();
        
        // Print URL and product details
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("\nProduct Name: " + productName);
        System.out.println("\nThe price of the product is: " + snapdealPage.getProductPrice());
        System.out.println("\nCurrent applicable offer: " + snapdealPage.getApplicableOffer());
        
        // Scroll and print specifications
        snapdealPage.scrollDown();
        System.out.println("Specifications:\n" + snapdealPage.getSpecifications());
        
        // Add to cart and print message
        snapdealPage.addToCart();
        System.out.println("Item Added to the cart!");
        
        // Open cart and increase quantity
        snapdealPage.openCart();
        
        // Simulate user interaction (alerts and sleep for demo purposes)
        Thread.sleep(5000);
        snapdealPage.increaseQuantity();
        System.out.println("Increased the quantity of the product to 3");
        
        // Simulate user interaction (alerts and sleep for demo purposes)
        Thread.sleep(5000);
        snapdealPage.removeFromCart();
        System.out.println("Cart is Empty");

        // Close the driver
        driver.quit();
    }
}
