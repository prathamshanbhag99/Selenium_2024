package Pac1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class TC_Login_XML {

    public static void main(String[] args) throws InterruptedException, IOException, ParserConfigurationException, SAXException {

        // Specify the path to the XML file
        File xmlfile = new File("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\login.xml");

        // Parse the XML file and load it into a Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document DBDoc = dBuilder.parse(xmlfile);

        // Normalize the XML structure
        DBDoc.getDocumentElement().normalize();

        // Get the values of the "url", "username", and "password" elements
        NodeList NL = DBDoc.getElementsByTagName("login");
        Node n = NL.item(0);
        Element ele = (Element) n;

        String url = ele.getElementsByTagName("url").item(0).getTextContent();
        String uname = ele.getElementsByTagName("username").item(0).getTextContent();
        String pwod = ele.getElementsByTagName("password").item(0).getTextContent();

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
