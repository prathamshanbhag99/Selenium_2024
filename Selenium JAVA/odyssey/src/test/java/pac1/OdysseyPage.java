package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OdysseyPage {

    WebDriver driver;
    WebDriverWait wait;

    public OdysseyPage(WebDriver driver) {
        this.driver = driver;
        
        PageFactory.initElements(driver, this);
    }

    public void createAccount(String firstName, String lastName, String email, String password) {
        
    	driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
    	WebElement element = driver.findElement(By.xpath("//*[@id=\"header-login-panel\"]/div/div/p[1]/button"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	while (!element.isDisplayed()) {
    	    js.executeScript("window.scrollBy(0, 1000);");
    	}
    	driver.findElement(By.xpath("//*[@id=\"header-login-panel\"]/div/div/p[1]/button")).click();
        driver.findElement(By.id("register-customer[first_name]")).sendKeys(firstName);
        driver.findElement(By.id("register-customer[last_name]")).sendKeys(lastName);
        driver.findElement(By.id("register-customer[email]")).sendKeys(email);
        driver.findElement(By.id("register-customer[password]")).sendKeys(password);   
        driver.findElement(By.xpath("//*[@id=\"create_customer\"]/button")).click();
        
    }

 
    public void login(String email, String password) {
    	driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
    	  driver.findElement(By.id("login-customer[email]")).sendKeys(email);
          driver.findElement(By.id("login-customer[password]")).sendKeys(password);  
    }

  
    public void logout() {
    	driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
    	driver.findElement(By.xpath("//*[@id=\"account-popover\"]/div/div/a[4]")).click();
    }
}
