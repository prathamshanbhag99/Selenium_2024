package Pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_PageFactory {
    WebDriver driver;

    // Locate elements using PageFactory
    @FindBy(id="user-name")
    WebElement username;
    
    @FindBy(id="password")
    WebElement password;
    
    @FindBy(id="login-button")
    WebElement login;

    // Constructor to initialize the WebDriver
    public Login_PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter username
    public void enterusername(String name) {
        username.sendKeys(name);
    }

    // Method to enter password
    public void enterpassword(String pass) {
        password.sendKeys(pass);
    }

    // Method to click login button
    public void Loginbtn() {
        login.click();
    }
}
