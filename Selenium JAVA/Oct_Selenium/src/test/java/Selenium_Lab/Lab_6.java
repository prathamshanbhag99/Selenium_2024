package Selenium_Lab;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab_6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		
		//Thread.sleep(10000);
		
		//driver.findElement(By.linkText("My Account")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.linkText("My Account")).click();
		//driver.findElement(By.linkText("Login")).click();
		
		//WebElement email = driver.findElement(By.name("email"));		 
		//email.sendKeys("prathamshanbhag99_111@gmail.com");
		//email.sendKeys(Keys.TAB);
		
		driver.findElement(By.id("inputValEnter")).sendKeys("A1 MEN WATCHES Men Sunglasses Combo");
		
		driver.findElement(By.xpath("//*[@id=\"sdHeader\"]/div[4]/div[2]/div/div[2]/button/span")).click();
		
		
		//driver.findElement(By.xpath("//*[@id=\"content_wrapper\"]/div[7]/div[5]/div[3]/div[1]/div/div[2]/div/span")).click();
		//driver.findElement(By.xpath("//*[@id=\"content_wrapper\"]/div[7]/div[5]/div[3]/div[1]/div/div[2]/ul/li[4]")).click();

		driver.findElement(By.xpath("//*[@id=\"654909822552\"]/div[1]/a/picture/img")).click();
		
        List<String> wh=new ArrayList(driver.getWindowHandles());
		
		driver.switchTo().window(wh.get(1));
		System.out.println("URL:"+driver.getCurrentUrl());
		System.out.println();
		WebElement price= driver.findElement(By.xpath("/html/body/div[11]/section/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]/span"));
		System.out.println();
		
		System.out.println("Product Name:A1 MEN WATCHES Men Sunglasses Combo");
		System.out.println();
		System.out.println("The price of the product is :"+price.getText());
		System.out.println();
		
		WebElement offer=driver.findElement(By.xpath("//*[@id=\"buyPriceBox\"]/div[2]/div[2]/div[2]/div[1]/div[1]/div"));
		System.out.println("Current aplicable offer :"+offer.getText());	
		System.out.println();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;  js.executeScript("window.scrollBy(0,50)");

		WebElement spcs=driver.findElement(By.xpath("//*[@id=\"id-tab-container\"]/div/div[1]/div[2]"));
		System.out.println("Specifications:");
		
		System.out.println(spcs.getText());
		driver.findElement(By.id("add-cart-button-id")).click();
		

		
		
		
		

	}

}
