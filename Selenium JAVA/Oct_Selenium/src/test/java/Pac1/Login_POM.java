package Pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_POM {
    WebDriver driver;

    // Instance variables for the locators
    By username = By.id("user-name");
    By password = By.id("password");
    By loginbutton = By.id("login-button");

    // Constructor to initialize the WebDriver
    public Login_POM(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter username
    void enterusername(String name) {
        driver.findElement(username).click();
        driver.findElement(username).sendKeys(name);
    }

    // Method to enter password
    void enterpassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    // Method to click login button
    void Loginbtn() {
        driver.findElement(loginbutton).click();
    }
}
