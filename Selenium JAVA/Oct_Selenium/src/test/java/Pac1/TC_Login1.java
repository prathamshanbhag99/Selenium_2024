package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_Login1 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Use the correct constructor
        Login_POM obj = new Login_POM(driver);

        // Navigate to the webpage
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);  // Wait for the page to load

        // Perform login steps
        obj.enterusername("standard_user");
        Thread.sleep(2000);
        obj.enterpassword("secret_sauce");
        Thread.sleep(2000);
        obj.Loginbtn();
    }
}
