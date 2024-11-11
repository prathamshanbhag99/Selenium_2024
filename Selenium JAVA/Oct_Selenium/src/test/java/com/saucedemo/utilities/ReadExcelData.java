package com.saucedemo.utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelData {

    @DataProvider(name = "login")
    public Object[][] readdatafromexcel() throws IOException {
        FileInputStream input = new FileInputStream("C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\src\\test\\java\\com\\saucedemo\\testdata\\login.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheet("login");

        int noofrows = sheet.getPhysicalNumberOfRows();
        Object[][] data = new Object[noofrows][2]; // Two columns: uname and pwod

        for (int i = 0; i < noofrows; i++) {
            String uname = sheet.getRow(i).getCell(0).getStringCellValue();
            String pwod = sheet.getRow(i).getCell(1).getStringCellValue();
            data[i][0] = uname;
            data[i][1] = pwod;
        }

        workbook.close();
        return data;
    }
}
