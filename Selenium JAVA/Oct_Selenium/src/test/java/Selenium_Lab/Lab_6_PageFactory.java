package Selenium_Lab;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Lab_6_PageFactory {
    private WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public Lab_6_PageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory locators
    @FindBy(id = "inputValEnter")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"sdHeader\"]/div[4]/div[2]/div/div[2]/button/span")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"660206463981\"]/div[1]/a/picture/img")
    private WebElement specificProduct;

    @FindBy(xpath = "//*[@id=\"buyPriceBox\"]/div[2]/div[1]/div[1]/div[1]/span[1]/span")
    private WebElement productPrice;

    @FindBy(xpath = "//*[@id=\"buyPriceBox\"]/div[2]/div[2]/div[2]/div[1]/div[1]/div")
    private WebElement applicableOffer;

    @FindBy(xpath = "//*[@id=\"id-tab-container\"]/div/div[1]/div[2]")
    private WebElement specifications;

    @FindBy(id = "add-cart-button-id")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='btn btn-theme-secondary open-cart']")
    private WebElement openCartButton;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div[1]/div/div[5]/ul/li/div[5]/div")
    private WebElement increaseQuantityButton;

    @FindBy(css = "li[rel='3'].undefined")
    private WebElement quantityOption;

    @FindBy(xpath = "//span[@class='remove-item-shortlist']")
    private WebElement removeFromCartButton;

    // Method to search for a product
    public void searchForProduct(String productName) {
        searchInput.sendKeys(productName);
        searchButton.click();
    }

    // Method to click on a specific product
    public void clickOnProduct() {
        specificProduct.click();
    }

    // Method to switch to the new window
    public void switchToNewWindow() {
        List<String> wh = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(wh.get(1));
    }

    // Method to get the product price
    public String getProductPrice() {
        return productPrice.getText();
    }

    // Method to get the applicable offer
    public String getApplicableOffer() {
        return applicableOffer.getText();
    }

    // Method to scroll down the page
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)");
    }

    // Method to get product specifications
    public String getSpecifications() {
        return specifications.getText();
    }

    // Method to add the product to the cart
    public void addToCart() {
        addToCartButton.click();
    }

    // Method to open the cart
    public void openCart() {
        openCartButton.click();
    }

    // Method to increase the product quantity
    public void increaseQuantity() {
        increaseQuantityButton.click();
        quantityOption.click();
    }

    // Method to remove the product from the cart
    public void removeFromCart() {
        removeFromCartButton.click();
    }
}