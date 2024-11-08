package Pac1;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_KeyWordDriven {

	// Define WebDriver as a class-level variable to make it accessible in all
	// methods
	private static WebDriver driver;

	public static void main(String[] args) throws Exception {
		FileInputStream input = new FileInputStream(
				"C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\Login_SwagLab.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet moduleSheet = workbook.getSheet("Module");
		XSSFSheet testcaseSheet = workbook.getSheet("Testcase");
		XSSFSheet teststepSheet = workbook.getSheet("Teststep");

		int moduleRows = moduleSheet.getPhysicalNumberOfRows();
		System.out.println("Module rows: " + moduleRows);

		for (int i = 0; i < moduleRows; i++) {
			String moduleExe = moduleSheet.getRow(i).getCell(2).getStringCellValue();
			if (moduleExe.equals("Y")) {
				String modId = moduleSheet.getRow(i).getCell(0).getStringCellValue();
				System.out.println("Module ID: " + modId);

				int testcaseRows = testcaseSheet.getPhysicalNumberOfRows();
				for (int j = 0; j < testcaseRows; j++) {
					String testcaseModuleId = testcaseSheet.getRow(j).getCell(3).getStringCellValue();
					String testExe = testcaseSheet.getRow(j).getCell(2).getStringCellValue();
					if (testcaseModuleId.equals(modId) && testExe.equals("Y")) {
						String testcaseId = testcaseSheet.getRow(j).getCell(0).getStringCellValue();
						System.out.println("Testcase ID: " + testcaseId);

						int teststepRows = teststepSheet.getPhysicalNumberOfRows();
						for (int k = 0; k < teststepRows; k++) {
							String teststepTestcaseId = teststepSheet.getRow(k).getCell(4).getStringCellValue();
							if (teststepTestcaseId.equals(testcaseId)) {

								String keyword = teststepSheet.getRow(k).getCell(3).getStringCellValue();

								switch (keyword) {
								case "ln":
									login();
									break;
								case "ca":
									close();
									break;
								default:
									System.out.println("No action defined for keyword: " + keyword);
								}
							}
						}
					}
				}
			}
		}

		workbook.close();
		input.close();
	}

	private static void close() {
		System.out.println("This is the close code");
		driver.quit();

	}

	private static void login() throws Exception {
		System.out.println("This is the login code");

		// Set up ExtentReports for logging
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

		// Initialize WebDriver only once
		if (driver == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		for (int i = 0; i < noofrows; i++) {
			String url = sheet.getRow(i).getCell(0).getStringCellValue();
			String uname = sheet.getRow(i).getCell(1).getStringCellValue();
			String pwod = sheet.getRow(i).getCell(2).getStringCellValue();

			System.out.println("username: " + uname);
			System.out.println("password: " + pwod);

			// Navigate to the URL and maximize window
			driver.get(url);
			driver.manage().window().maximize();
			Thread.sleep(2000);

			// Initialize the page factory object for login
			Login_PageFactory obj = PageFactory.initElements(driver, Login_PageFactory.class);

			// Perform login steps
			obj.enterusername(uname);
			Thread.sleep(2000);
			obj.enterpassword(pwod);
			Thread.sleep(2000);
			obj.Loginbtn();

			// Validate login success
			if (!driver.findElements(By.xpath("//*[@id='header_container']/div[2]/span")).isEmpty() && driver
					.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText().equals("Products")) {
				test.pass("Login Successful");
			} else {
				test.fail("Login Failed");

				// Take screenshot if login fails
				File ssfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(ssfile, new File("s1.jpg"));
			}

			Thread.sleep(2000);
		}

		extent.flush();
		workbook.close();
		input.close();
	}
}
