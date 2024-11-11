package Selenium_Lab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lab_5 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.opencart.com/en-gb?route=account/register");

        System.out.println("Title of the page is: " + driver.getTitle());


        // Scroll to the bottom of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");


        // Wait for the page to load completely
        Thread.sleep(5000);
        // Locate the 'Continue' button and click it
        WebElement Continue_btn = driver.findElement(By.xpath("//button[@type='submit']"));
        Continue_btn.click();

        System.out.println("Warning : " + driver.findElement(By.id("alert")).isDisplayed());

        // Scroll to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        //First Name
        WebElement FN = driver.findElement(By.name("firstname"));
        FN.sendKeys("djniuncnincinucc");
        FN.submit();

        WebElement Continue_btn1 = driver.findElement(By.xpath("//button[@type='submit']"));
        Continue_btn1.click();
        Continue_btn1.click();


        if (driver.findElement(By.id("error-firstname")).isDisplayed()) {
            System.out.println("The error Message for first name is displayed.");
            System.out.println("Error msg for first name is: " + driver.findElement(By.id("error-firstname")).getText());
        } else {
            System.out.println("The entered first name is valid.");
        }

        //Last Name
        WebElement LN = driver.findElement(By.name("lastname"));
        LN.sendKeys("djniuncnincinucnucucununcunecuenunceuncencecnuencuencuencuencuencuenciuencnciencieceic");
        LN.submit();

        WebElement Continue_btn2 = driver.findElement(By.xpath("//button[@type='submit']"));
        Continue_btn2.click();
        Continue_btn2.click();
        if (driver.findElement(By.id("error-lastname")).isDisplayed()) {
            System.out.println("The error Message for last name is displayed.");
            System.out.println("Error msg for last name is: " + driver.findElement(By.id("error-lastname")).getText());
        } else {
            System.out.println("The entered last name is valid.");
        }

        //email
        //driver.navigate().refresh();

        WebElement EM = driver.findElement(By.name("email"));
        //EM.sendKeys("prathammmmmmmm");
        EM.sendKeys("pratham@gmail.com");
        EM.submit();

        WebElement Continue_btn3 = driver.findElement(By.xpath("//button[@type='submit']"));
        Continue_btn3.click();
        //Continue_btn3.click();

        if (driver.findElement(By.id("error-email")).isDisplayed()) {
            System.out.println("The error Message for email is displayed.");
            System.out.println("Error msg for email is: " + driver.findElement(By.id("error-email")).getText());
        } else {
            System.out.println("The entered email is valid.");
        }


    }
}
