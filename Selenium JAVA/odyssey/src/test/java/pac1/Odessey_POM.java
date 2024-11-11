package pac1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

public class Odessey_POM {

    public static void main(String[] args) throws InterruptedException, IOException {

        Properties prob = new Properties();
        FileInputStream propInput = new FileInputStream("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\odyssey\\config.properties");
        prob.load(propInput);
        String url = prob.getProperty("url");

        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Odessey_report.html");
        extent.attachReporter(spark);
        ExtentTest test1 = extent.createTest("Account Creation Test");
        
        ExtentTest test2 = extent.createTest("Logout Test");
        ExtentTest test3 = extent.createTest("Login Test");

        FileInputStream input = new FileInputStream("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\odyssey\\testdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int noofrows = sheet.getPhysicalNumberOfRows();
        System.out.println("Rows: " + noofrows);

        for (int i = 0; i < noofrows; i++) {
            String fname = sheet.getRow(i).getCell(0).getStringCellValue();
            String lname = sheet.getRow(i).getCell(1).getStringCellValue();
            String email = sheet.getRow(i).getCell(2).getStringCellValue();
            String password = sheet.getRow(i).getCell(3).getStringCellValue();

            System.out.println("Name: " + fname + " " + lname);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            try {
                OdysseyPage odysseyPage = new OdysseyPage(driver);
                driver.get(url);
                driver.manage().window().maximize();

                // Test Case 1: Account Creation (test1)
                odysseyPage.createAccount(fname, lname, email, password);
                Thread.sleep(3000);
                driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
                Thread.sleep(5000);
                driver.findElement(By.xpath("//a[@class='popover__link-item'][normalize-space()='My addresses']")).click();
                Thread.sleep(5000);
                if (!driver.findElements(By.xpath("//button[@class='button button--small button--primary']")).isEmpty()) {
                	File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(ssfile, new File("screenshots/Accountcreated.jpg"));
                    test1.pass("Account Created" + test1.addScreenCaptureFromPath("screenshots/Accountcreated.jpg"));
                    
                } else {
                    File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(ssfile, new File("screenshots/AccountCreation_Failed.jpg"));
                    test1.fail("Account Not Created" + test1.addScreenCaptureFromPath("screenshots/AccountCreation_Failed.jpg"));
                }
                
                // Test Case 2: Logout 
                
                
                odysseyPage.logout();
                Thread.sleep(5000);
                driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
                Thread.sleep(5000);
                if (driver.findElements(By.xpath("//*[@id=\"header_customer_login\"]/header/h2")).isEmpty()) {
                	File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(ssfile, new File("screenshots/Logout_Successful.jpg"));
                    test2.pass("Account Created" + test2.addScreenCaptureFromPath("screenshots/Logout_Successful.jpg"));
                } else {
                    File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(ssfile, new File("screenshots/Logout_Failed.jpg"));
                    test2.fail("Logout Not Successful" + test2.addScreenCaptureFromPath("screenshots/Logout_Failed.jpg"));
                }

                // Test Case 3: Login 
                odysseyPage.login(email, password);
                Thread.sleep(3000);
                driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
                Thread.sleep(5000);
                driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[3]/div[2]/div/a[2]")).click();
                if (!driver.findElements(By.xpath("//*[@id=\"main\"]/section/div[1]/div[2]/div[2]/div/div[1]/h1")).isEmpty()) {
                	File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(ssfile, new File("screenshots/Login_Successful.jpg"));
                    test3.pass("Account Created" + test3.addScreenCaptureFromPath("screenshots/Login_Successful.jpg"));
                } else {
                    File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(ssfile, new File("screenshots/Login_Failed.jpg"));
                    test3.fail("Login Not Successful" + test3.addScreenCaptureFromPath("screenshots/Login_Failed.jpg"));
                }

            

            } 
            
            catch (Exception e) 
            {
                e.printStackTrace();
                File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(ssfile, new File("screenshots/Error.jpg"));
                test1.fail("Test Failed: " + e.getMessage() + test1.addScreenCaptureFromPath("screenshots/Error.jpg"));
            } 
            finally 
            {
                driver.quit();
            }
        }
        

        
        extent.flush();
        workbook.close();
        input.close();
    }
}

