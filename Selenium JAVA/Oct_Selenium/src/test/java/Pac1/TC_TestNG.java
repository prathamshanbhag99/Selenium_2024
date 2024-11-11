package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TC_TestNG {

    WebDriver driver;

    @Test(dataProvider = "dp")
    public void f(String username, String password) throws InterruptedException {
        System.out.println("This is the test");

        // Interacting with the web elements using the driver
        driver.findElement(By.id("input-email")).sendKeys(username);
        driver.findElement(By.id("input-password")).sendKeys(password);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,100)");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        // Pausing for demonstration purposes
        Thread.sleep(5000);
    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();  // Initialize the class-level driver variable
        driver.manage().window().maximize();

        // Opening the login page
        driver.get("https://demo.opencart.com/index.php?route=account/login");

        System.out.println("This is the @BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is the @AfterMethod");

        // Scroll down using JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;  // Properly casting to JavascriptExecutor
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        // Logout and close the driver
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    @DataProvider
    public Object[][] dp() {
        return new Object[][]{
                new Object[]{"prathamshanbhag99_111@gmail.com", "Pratham@2002"},
                new Object[]{"prathamshanbhag99_11@gmail.com", "Pratham@2002"}

        };
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is the @BeforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("This is the @AfterClass");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("This is the @BeforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("This is the @AfterTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("This is the @BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("This is the @AfterSuite");
    }
}
