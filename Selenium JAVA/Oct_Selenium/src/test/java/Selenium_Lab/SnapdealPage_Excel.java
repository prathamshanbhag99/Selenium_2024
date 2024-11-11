package Selenium_Lab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;

public class SnapdealPage_Excel {
    public static void main(String[] args) throws InterruptedException, IOException {


//    	String projectpath=System.getProperty("user.dir");
//    	//System.out.println(projectpath);
//    	Properties prob=new Properties();
//    	InputStream input=new FileInputStream(projectpath+"\\snapdeal.properties");
//    	prob.load(input);
//    	String url=prob.getProperty("url");
//    	String product=prob.getProperty("product");
//    


        FileInputStream input = new FileInputStream("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\snapdeal.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheet("product");

        int noofrows = sheet.getPhysicalNumberOfRows();
        System.out.println("rows: " + noofrows);

        for (int i = 0; i < noofrows; i++) {
            String url = sheet.getRow(i).getCell(0).getStringCellValue();
            String product = sheet.getRow(i).getCell(1).getStringCellValue();


            System.out.println("url: " + url);
            System.out.println("Product: " + product);


            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            // Initialize Lab_6_PageFactory using PageFactory
            Lab_6_PageFactory snapdealPage = new Lab_6_PageFactory(driver);

            // Navigate to Snapdeal
            driver.get(url);
            driver.manage().window().maximize();

            // Search for a product
            //String productName = "Vertical9 Smart Watch Combo Type C True Wireless (TWS) In Ear 10 Hours Playback Active Noise cancellation IPX4(Splash ";
            snapdealPage.searchForProduct(product);

            // Click on the product
            snapdealPage.clickOnProduct();

            // Switch to new window
            snapdealPage.switchToNewWindow();

            // Print URL and product details
            System.out.println("URL: " + driver.getCurrentUrl());
            System.out.println("\nProduct Name: " + product);
            System.out.println("\nThe price of the product is: " + snapdealPage.getProductPrice());
            System.out.println("\nCurrent applicable offer: " + snapdealPage.getApplicableOffer());

            // Scroll and print specifications
            snapdealPage.scrollDown();
            System.out.println("Specifications:\n" + snapdealPage.getSpecifications());

            // Add to cart and print message
            snapdealPage.addToCart();
            System.out.println("Item Added to the cart!");

            // Open cart and increase quantity
            snapdealPage.openCart();

            // Simulate user interaction (alerts and sleep for demo purposes)
            Thread.sleep(5000);
            snapdealPage.increaseQuantity();
            System.out.println("Increased the quantity of the product to 3");

            // Simulate user interaction (alerts and sleep for demo purposes)
            Thread.sleep(5000);
            snapdealPage.removeFromCart();
            System.out.println("Cart is Empty");

            // Close the driver
            driver.quit();
        }
    }
}
