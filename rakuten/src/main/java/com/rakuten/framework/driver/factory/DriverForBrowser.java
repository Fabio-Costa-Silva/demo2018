package com.rakuten.framework.driver.factory;

import org.openqa.selenium.WebDriver;

/**
 * Created by FabioCosta
 */
public interface DriverForBrowser {
    WebDriver create() throws Exception;
    String driverName();
}
