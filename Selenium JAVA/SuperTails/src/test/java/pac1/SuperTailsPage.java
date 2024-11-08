package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuperTailsPage {
    private WebDriver driver;
    public SuperTailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public void searchForProduct(String productName)
    {	
    	WebElement inputField = driver.findElement(By.xpath("/html/body/div[2]/div[5]/header/div/div[1]/div/form/input"));
    	inputField.click();
    	inputField.sendKeys(productName);
        driver.findElement(By.xpath("//*[@id=\"shopify-section-header-new-desktop\"]/header/div/div[1]/div/form/button[1]/svg")).click();
    }

    public void clickOnProduct() 
    {
        driver.findElement(By.xpath("//*[@id=\"searchResultsWrapper\"]/ul/li/a/div[2]/b/div[2]/img")).click();
    }

    public void addToCart() 
    {
        driver.findElement(By.id("AddToCart-template--16703736905966__main")).click();
    }
    
    public void openCart() 
    {
        driver.findElement(By.xpath("//*[@id=\"HeaderCartTrigger\"]")).click();
    }

    public void removeFromCart() 
    {
        driver.findElement(By.xpath("//*[@id=\"minusqty\"]/svg")).click();
    }

}
