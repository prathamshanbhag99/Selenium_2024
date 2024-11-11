package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC_MouseHover {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.snapdeal.com/");
        Thread.sleep(5000);


        //BY USING CLICK
        driver.findElement(By.xpath("//*[@id=\"leftNavMenuRevamp\"]/div[1]/div[2]/ul/li[1]/a/span[2]")).click();
        //driver.findElement(By.linkText("Sunglasses")).click();

        //USING MOUSE HOVER

        WebElement ele = driver.findElement(By.linkText("Sunglasses"));

        Actions act = new Actions(driver);
        act.moveToElement(ele).build().perform();


    }

}
