package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TC_Login_PageFactory {

    public static void main(String[] args) throws InterruptedException, IOException {

        String projectpath = System.getProperty("user.dir");
        System.out.println(projectpath);
        Properties prob = new Properties();
        InputStream input = new FileInputStream(projectpath + "\\login.properties");
        prob.load(input);

        String test_url = prob.getProperty("url");
        String uname = prob.getProperty("username");
        String pword = prob.getProperty("password");


        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Initialize the Chrome browser
        WebDriver driver = new ChromeDriver();

        // Use the PageFactory to initialize elements
        Login_PageFactory obj = PageFactory.initElements(driver, Login_PageFactory.class);

        // Navigate to the webpage
        // driver.get("https://www.saucedemo.com/");
        driver.get(test_url);

        driver.manage().window().maximize();
        Thread.sleep(2000);  // Wait for the page to load

        // Perform login steps
        //obj.enterusername("standard_user");
        obj.enterusername(uname);

        Thread.sleep(2000);
        // obj.enterpassword("secret_sauce");
        obj.enterpassword(pword);

        Thread.sleep(2000);
        obj.Loginbtn();

        // Close the browser
        Thread.sleep(2000);  // Wait to observe login
        driver.quit();
    }
}
