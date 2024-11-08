package pac1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SuperTails_Test {
    public static void main(String[] args) throws InterruptedException, IOException {

        // Set up ExtentReports
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("supertails_report.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Product Found");

        // Read Excel for test data
        FileInputStream input = new FileInputStream("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\snapdeal.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheet("product");

        int noofrows = sheet.getPhysicalNumberOfRows();
        System.out.println("Rows: " + noofrows);

        for (int i = 0; i < noofrows; i++) {
            String url = sheet.getRow(i).getCell(0).getStringCellValue();
            String product = sheet.getRow(i).getCell(1).getStringCellValue();

            System.out.println("URL: " + url);
            System.out.println("Product: " + product);

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            // Initialize SuperTailsPage using Page Object Model
            SuperTailsPage superTailsPage = new SuperTailsPage(driver);

            // Navigate to the URL
            driver.get(url);
            driver.manage().window().maximize();

            // Search for the product
            superTailsPage.searchForProduct(product);

            // Check if the product exists
            if (!driver.findElements(By.xpath("//*[@id='660206463981']/div[1]/a/picture/img")).isEmpty()) {
                test.pass("Product Found");
            } else {
                // Take a screenshot if the product is not found
                File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(ssfile, new File("S2.jpg"));
                test.fail("Product Not Found");
                extent.flush();
                driver.quit();
                continue;
            }

            // Click on the product
            superTailsPage.clickOnProduct();

            // Switch to new window
            superTailsPage.switchToNewWindow();

            // Print URL and product details
            System.out.println("URL: " + driver.getCurrentUrl());
            System.out.println("\nProduct Name: " + product);
            System.out.println("\nThe price of the product is: " + superTailsPage.getProductPrice());
            System.out.println("\nCurrent applicable offer: " + superTailsPage.getApplicableOffer());


            // Add to cart and print message
            superTailsPage.addToCart();
            System.out.println("Item added to the cart!");

            // Open cart and increase quantity
            superTailsPage.openCart();

            // Simulate user interaction (alerts and sleep for demo purposes)
            Thread.sleep(5000);
      
            // Simulate user interaction (alerts and sleep for demo purposes)
            Thread.sleep(5000);
            superTailsPage.removeFromCart();
            System.out.println("Cart is empty");

            extent.flush();

            // Close the driver
            driver.quit();
        }

        // Close the workbook and input stream
        workbook.close();
        input.close();
    }
}
