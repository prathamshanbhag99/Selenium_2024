package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TC_Login_Excel_ExtentReport {

	public static void main(String[] args)
			throws InterruptedException, IOException, ParserConfigurationException, SAXException {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("r1.html");
		extent.attachReporter(spark);
		ExtentTest test = extent.createTest("Login Successful");

		FileInputStream input = new FileInputStream(
				"C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\login.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet("login");

		int noofrows = sheet.getPhysicalNumberOfRows();
		System.out.println("rows: " + noofrows);

		for (int i = 0; i < noofrows; i++) {
			String url = sheet.getRow(i).getCell(0).getStringCellValue();
			String uname = sheet.getRow(i).getCell(1).getStringCellValue();
			String pwod = sheet.getRow(i).getCell(2).getStringCellValue();

			System.out.println("username: " + uname);
			System.out.println("password: " + pwod);

			WebDriverManager.chromedriver().setup();

			WebDriver driver = new ChromeDriver();

			Login_PageFactory obj = PageFactory.initElements(driver, Login_PageFactory.class);

			driver.get(url);

			driver.manage().window().maximize();
			Thread.sleep(2000);

			// Perform login steps
			obj.enterusername(uname);
			Thread.sleep(2000);
			obj.enterpassword(pwod);
			Thread.sleep(2000);
			obj.Loginbtn();

			if (!driver.findElements(By.xpath("//*[@id='header_container']/div[2]/span")).isEmpty()
					&& driver
					.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText().equals("Products")) {

				test.pass("Login Successful");
			}

			else {
				test.fail("Login Failed");

				// Take screenshot if login fails
				File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(ssfile, new File("s1.jpg"));
			}

			
			Thread.sleep(2000);
			extent.flush();
			driver.quit();
		}
	}
}
