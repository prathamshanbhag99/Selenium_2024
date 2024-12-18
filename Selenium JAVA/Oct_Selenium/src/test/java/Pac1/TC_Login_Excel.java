package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;

public class TC_Login_Excel {

    public static void main(String[] args) throws InterruptedException, IOException, ParserConfigurationException, SAXException {
//
//        // Specify the path to the XML file
//        File xmlfile = new File("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\login.xml");
//
//        // Parse the XML file and load it into a Document
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document DBDoc = dBuilder.parse(xmlfile);
//
//        // Normalize the XML structure
//        DBDoc.getDocumentElement().normalize();
//
//        // Get the values of the "url", "username", and "password" elements
//        NodeList NL = DBDoc.getElementsByTagName("login");
//        Node n = NL.item(0);
//        Element ele = (Element) n;
//
//        String url = ele.getElementsByTagName("url").item(0).getTextContent();
//        String uname = ele.getElementsByTagName("username").item(0).getTextContent();
//        String pwod = ele.getElementsByTagName("password").item(0).getTextContent();


        FileInputStream input = new FileInputStream("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\login.xlsx");

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


            // Setup ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Initialize the Chrome browser
            WebDriver driver = new ChromeDriver();

            // Use the PageFactory to initialize elements
            Login_PageFactory obj = PageFactory.initElements(driver, Login_PageFactory.class);

            // Navigate to the webpage
            driver.get(url);

            driver.manage().window().maximize();
            Thread.sleep(2000); // Wait for the page to load

            // Perform login steps
            obj.enterusername(uname);
            Thread.sleep(2000);
            obj.enterpassword(pwod);
            Thread.sleep(2000);
            obj.Loginbtn();

            // Close the browser
            Thread.sleep(2000); // Wait to observe login
            driver.quit();
        }
    }
}
