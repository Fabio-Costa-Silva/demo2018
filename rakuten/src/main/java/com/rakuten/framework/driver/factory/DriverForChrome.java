package com.rakuten.framework.driver.factory;

import com.rakuten.framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Created by FabioCosta
 */
public class DriverForChrome implements DriverForBrowser {

    public static final String DRIVER_PATH = "src/main/resources/chromedriver.exe";
    private static final String DRIVER_PROPERTY = "webdriver.chrome.driver";
    public static final String DRIVER_NAME = "chromedriver";

    public WebDriver create() throws Exception {
        WebDriver chromeDriver = null;
        try {
            DriverManager.setDriverLocation(DRIVER_PROPERTY, DRIVER_PATH);
            chromeDriver = new ChromeDriver();
        } catch (Exception e){
            e.printStackTrace();
        }
        return chromeDriver;
    }

    public String driverName() {
        return DRIVER_NAME;
    }
}
