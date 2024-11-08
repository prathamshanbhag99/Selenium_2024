package Selenium_Lab;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealPage_XML {
	public static void main(String[] args) throws InterruptedException, IOException, Exception {

		// Specify the path to the XML file
		File xmlfile = new File("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\snapdeal.xml");

		// Parse the XML file and load it into a Document
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document DBDoc = dBuilder.parse(xmlfile);

		// Normalize the XML structure
		DBDoc.getDocumentElement().normalize();

		// Get the values of the "url", "username", and "password" elements
		NodeList NL = DBDoc.getElementsByTagName("snapdeal");
		Node n = NL.item(0);
		Element ele = (Element) n;

		String url = ele.getElementsByTagName("url").item(0).getTextContent();
		String product = ele.getElementsByTagName("product").item(0).getTextContent();

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Initialize Lab_6_PageFactory using PageFactory
		Lab_6_PageFactory snapdealPage = new Lab_6_PageFactory(driver);

		// Navigate to Snapdeal
		driver.get(url);
		driver.manage().window().maximize();

		// Search for a product
		// String productName = "Vertical9 Smart Watch Combo Type C True Wireless (TWS)
		// In Ear 10 Hours Playback Active Noise cancellation IPX4(Splash ";
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
