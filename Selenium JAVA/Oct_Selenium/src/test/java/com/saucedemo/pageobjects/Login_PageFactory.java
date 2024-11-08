package com.saucedemo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_PageFactory {
    WebDriver driver;

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement login;

    public Login_PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public void enterusername(String name) {
        username.sendKeys(name);
    }

    public void enterpassword(String pass) {
        password.sendKeys(pass);
    }

    public void Loginbtn() {
        login.click();
    }
}
