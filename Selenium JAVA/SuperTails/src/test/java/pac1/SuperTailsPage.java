package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuperTailsPage {

    private WebDriver driver;

    // Constructor
    public SuperTailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to search for a product
    public void searchForProduct(String productName) {
        driver.findElement(By.id("inputValEnter")).sendKeys(productName);
        driver.findElement(By.xpath("//*[@id=\"sdHeader\"]/div[4]/div[2]/div/div[2]/button/span")).click();
    }

    // Method to click on a specific product
    public void clickOnProduct() {
        driver.findElement(By.xpath("//*[@id=\"654909822552\"]/div[1]/a/picture/img")).click();
    }

    // Method to add the product to the cart
    public void addToCart() {
        driver.findElement(By.id("add-cart-button-id")).click();
    }

    // Method to open the cart
    public void openCart() {
        driver.findElement(By.xpath("//div[@class='btn btn-theme-secondary open-cart']")).click();
    }

    // Method to remove the product from the cart
    public void removeFromCart() {
        driver.findElement(By.xpath("//span[@class='remove-item-shortlist']")).click();
    }

    // Method to switch to new window
    public void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // Method to get the product price
    public String getProductPrice() {
        WebElement priceElement = driver.findElement(By.xpath("//span[@class='pdp-price']"));
        return priceElement.getText();
    }

    // Method to get the applicable offer
    public String getApplicableOffer() {
        WebElement offerElement = driver.findElement(By.xpath("//div[@class='pdp-promo']"));
        return offerElement.getText();
    }

    // Method to get product specifications
    public String getSpecifications() {
        WebElement specsElement = driver.findElement(By.xpath("//div[@class='specifications']"));
        return specsElement.getText();
    }
}
