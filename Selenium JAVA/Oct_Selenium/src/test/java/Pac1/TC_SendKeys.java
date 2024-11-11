package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_SendKeys {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/en-gb?route=account/register");
        driver.findElement(By.id("input-firstname")).sendKeys("Pratham");
        driver.findElement(By.id("input-firstname")).sendKeys(Keys.TAB);

        driver.findElement(By.id("input-lastname")).sendKeys(Keys.NUMPAD7);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");


        WebElement subscribe = driver.findElement(By.cssSelector("input[type='checkbox'][id='input-newsletter']"));

        subscribe.click();


        if (subscribe.isSelected()) {
            System.out.println("Subscribed");
        } else {
            System.out.println("Not Subscribed");
        }
        driver.close();


    }

}
