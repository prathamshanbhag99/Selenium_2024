package Pac1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_WindowHandle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				WebDriverManager.chromedriver().setup();
				WebDriver driver=new ChromeDriver();
				driver.get("https://letcode.in/windows");
				System.out.println("Window Handle:"+driver.getWindowHandles());
				
				driver.findElement(By.id("multi")).click();
				
				List<String> wh=new ArrayList(driver.getWindowHandles());
				
				for(String i: wh)
				{
					System.out.println(i);
				
				}
				driver.switchTo().window(wh.get(0));
				System.out.println("URL:"+driver.getCurrentUrl());
				
				driver.switchTo().window(wh.get(1));
				System.out.println("URL:"+driver.getCurrentUrl());
				
				driver.switchTo().window(wh.get(2));
				System.out.println("URL:"+driver.getCurrentUrl());


				driver.quit();
	}

}
