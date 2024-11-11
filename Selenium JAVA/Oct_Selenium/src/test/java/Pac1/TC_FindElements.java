package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TC_FindElements {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/en-gb?route=account/register");

        List<WebElement> ele = driver.findElements(By.xpath("//input[@class='form-control']"));

        for (WebElement i : ele) {
            System.out.println("Webelement:" + ele);
        }

    }

}
