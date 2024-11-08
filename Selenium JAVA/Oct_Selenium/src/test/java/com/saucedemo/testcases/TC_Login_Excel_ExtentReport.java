package com.saucedemo.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.saucedemo.utilities.ReadExcelData;
import com.saucedemo.utilities.Readconfig;
import com.saucedemo.pageobjects.Login_PageFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TC_Login_Excel_ExtentReport {

    private static final String REPORTS_PATH = "C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\Reports\\";
    private static final String SCREENSHOTS_PATH = "C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\Screenshots\\";

    @Test(dataProvider = "login", dataProviderClass = ReadExcelData.class)
    public void loginTest(String uname, String pwod) throws IOException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        String url = Readconfig.getUrl();
        driver.get(url);
        driver.manage().window().maximize();

        // Generate unique file names with date and time
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFileName = REPORTS_PATH + "LoginReport_" + timestamp + ".html";
        String screenshotFileName = SCREENSHOTS_PATH + "LoginFail_" + timestamp + ".jpg";

        // Initialize ExtentReports with dynamic file name
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportFileName);
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Login Test");

        // Perform login
        Login_PageFactory loginPage = PageFactory.initElements(driver, Login_PageFactory.class);
        loginPage.enterusername(uname);
        loginPage.enterpassword(pwod);
        loginPage.Loginbtn();

        // Check login success
        if (!driver.findElements(By.xpath("//*[@id='header_container']/div[2]/span")).isEmpty()
                && driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText().equals("Products")) {
            test.pass("Login Successful");
        } else {
            test.fail("Login Failed");

            // Take screenshot if login fails
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(screenshotFileName));
            test.addScreenCaptureFromPath(screenshotFileName);
        }

        // Finalize report and close driver
        extent.flush();
        driver.quit();
    }
}
