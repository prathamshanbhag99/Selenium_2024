package pac1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Supertails_POM {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        // Initialize SuperTailsPage
        SuperTailsPage superTailsPage = new SuperTailsPage(driver);

        // Navigate to SuperTails website
        driver.get("https://www.supertails.com/");
        driver.manage().window().maximize();

        // Search for a product
        String productName = "Dog Food";
        superTailsPage.searchForProduct(productName);

        // Click on the product
        superTailsPage.clickOnProduct();

        // Switch to new window (if needed)
        superTailsPage.switchToNewWindow();

        // Print URL and product details
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Product Price: " + superTailsPage.getProductPrice());
        System.out.println("Applicable Offer: " + superTailsPage.getApplicableOffer());

        // Scroll and print specifications
        System.out.println("Specifications: " + superTailsPage.getSpecifications());

        // Add to cart and print message
        superTailsPage.addToCart();
        System.out.println("Item added to the cart!");

        // Open cart and simulate user interaction
        superTailsPage.openCart();
        Thread.sleep(5000); // Sleep for demo purposes

        // Remove from cart and print message
        superTailsPage.removeFromCart();
        System.out.println("Cart is empty");

        // Close the driver
        driver.quit();
    }
}
