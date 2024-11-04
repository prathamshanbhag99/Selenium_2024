package Selenium_Lab;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SnapdealPage {
    private WebDriver driver;

    // Constructor
    public SnapdealPage(WebDriver driver) {
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

    // Method to switch to the new window
    public void switchToNewWindow() {
        List<String> wh = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(wh.get(1));
    }

    // Method to get the product price
    public String getProductPrice() {
        WebElement price = driver.findElement(By.xpath("/html/body/div[11]/section/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]/span"));
        return price.getText();
    }

    // Method to get the applicable offer
    public String getApplicableOffer() {
        WebElement offer = driver.findElement(By.xpath("//*[@id=\"buyPriceBox\"]/div[2]/div[2]/div[2]/div[1]/div[1]/div"));
        return offer.getText();
    }

    // Method to scroll down the page
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)");
    }

    // Method to get product specifications
    public String getSpecifications() {
        WebElement spcs = driver.findElement(By.xpath("//*[@id=\"id-tab-container\"]/div/div[1]/div[2]"));
        return spcs.getText();
    }

    // Method to add the product to the cart
    public void addToCart() {
        driver.findElement(By.id("add-cart-button-id")).click();
    }

    // Method to open the cart
    public void openCart() {
        driver.findElement(By.xpath("//div[@class='btn btn-theme-secondary open-cart']")).click();
    }

    // Method to increase the product quantity
    public void increaseQuantity() {
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[5]/ul/li/div[5]/div")).click();
        driver.findElement(By.cssSelector("li[rel='3'].undefined")).click();
    }

    // Method to remove the product from the cart
    public void removeFromCart() {
        driver.findElement(By.xpath("//span[@class='remove-item-shortlist']")).click();
    }
}
