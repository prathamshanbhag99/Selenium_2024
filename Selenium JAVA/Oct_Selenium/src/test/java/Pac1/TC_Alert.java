package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_Alert {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");

        driver.findElement(By.id("login1")).sendKeys("uname1");
        driver.findElement(By.name("proceed")).click();

        Alert simplealert = driver.switchTo().alert();

        System.out.println("The Alert Message is :" + simplealert.getText());
        simplealert.accept();

        driver.navigate().to("https://letcode.in/alert");

        //simple allert

        driver.findElement(By.id("accept")).click();
        Alert simplealert1 = driver.switchTo().alert();

        System.out.println("The Alert Message is :" + simplealert1.getText());
        simplealert.accept();


        //confirm allert


        driver.findElement(By.id("confirm")).click();
        Alert confirmalert = driver.switchTo().alert();
        System.out.println("The Alert Message is :" + confirmalert.getText());
        simplealert.accept();

        //prompt alert
        driver.findElement(By.id("prompt")).click();
        Alert prompt = driver.switchTo().alert();
        System.out.println("The Alert Message is :" + prompt.getText());
        prompt.sendKeys("qwerty");
        prompt.accept();

    }

}
