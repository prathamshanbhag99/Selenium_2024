package pac1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Supertails_POM {
    public static void main(String[] args) throws InterruptedException, IOException {
       
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String excelFilePath = "C:\\Users\\pratham.shanbhag\\eclipse-workspace\\SuperTails\\SuperTails.xlsx";
        FileInputStream input = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheet("product");

        int noofrows = sheet.getPhysicalNumberOfRows();
        System.out.println("Rows: " + noofrows);
        
       SuperTailsPage superTailsPage = new SuperTailsPage(driver);

        for (int i = 0; i < noofrows; i++) {
            String url = sheet.getRow(i).getCell(0).getStringCellValue();
            String product = sheet.getRow(i).getCell(1).getStringCellValue();
            System.out.println("URL: " + url);
            System.out.println("Product: " + product);

            driver.get(url);
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mainfrm")));

            
            superTailsPage.searchForProduct(product);

           
            superTailsPage.clickOnProduct();

           
            superTailsPage.addToCart();
            System.out.println("Item added to the cart!");

            // First Test Case
            superTailsPage.openCart();
            WebElement cartItem = driver.findElement(By.xpath("//*[@id=\"cartpage_form\"]/div/div[1]/div[2]/div[1]/div[1]/a"));
            if (cartItem != null) {
                System.out.println("Product found in cart: " + cartItem.getText());
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("cart_screenshot.png"));
                superTailsPage.removeFromCart();
                System.out.println("Product removed from cart.");
            } else {
                System.out.println("No product found in the cart.");
            }

            // Second Test Case
            superTailsPage.openCart();
            WebElement emptyCartMessage = driver.findElement(By.xpath("//*[@id=\"shopify-section-template--16703737790702__main\"]/div[3]/header/div/p[1]"));
            if (emptyCartMessage != null) {
                System.out.println("Cart is empty: " + emptyCartMessage.getText());
            } else {
                System.out.println("Cart is not empty.");
            }

            
            workbook.close();
            input.close();
        }

        driver.quit();
    }
}
