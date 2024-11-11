package com.saucedemo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Readconfig {

    private static final String CONFIG_PATH = "C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\src\\test\\java\\com\\saucedemo\\testdata\\login.properties";

    // Static method to get the URL from the properties file
    public static String getUrl() throws IOException {
        Properties prob = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_PATH)) {
            prob.load(input);
        }
        return prob.getProperty("url");
    }

    // Method to get the browser name from the properties file
    public String getBrowser() throws IOException {
        Properties prob = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_PATH)) {
            prob.load(input);
        }
        return prob.getProperty("browser");
    }
}
