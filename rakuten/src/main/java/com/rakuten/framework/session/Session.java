package com.rakuten.framework.session;

import com.rakuten.framework.Browser;
import com.rakuten.framework.driver.DriverManager;
import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;

/**
 * Created by FabioCosta
 */
public class Session {
    private WebDriver driver;
    private Browser browser;

    public void closeBrowser() throws Exception {
        if (driver == null || browser == null) {
            return;
        }

        driver.quit();
        if ((OS.isFamilyWindows())) {
            DriverManager.killProcess(browser.getDriverName());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void open(String defineBrowser) throws Exception {
        browser = new Browser(defineBrowser);
        driver = browser.createDriver();
        driver.manage().window().maximize();
    }

    public void openURL(String url) {
        if (driver == null) {
            throw new RuntimeException("Browser needs to be open prior to open an URL. Aborting.");
        }
        driver.get(url);
    }
}
