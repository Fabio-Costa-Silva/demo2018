package com.rakuten.framework;

import com.rakuten.framework.driver.factory.DriverForBrowser;
import com.rakuten.framework.driver.factory.DriverForChrome;
import org.openqa.selenium.WebDriver;

/**
 * Created by FabioCosta
 */
public class Browser {

    private DriverForBrowser driverForBrowser;

    public Browser(String browser) throws Exception {
        switch (browser) {
            case "CHROME":
                try {
                    driverForBrowser = new DriverForChrome();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "FIREFOX":
                //TBD
                break;
            case "IE":
                //TBD
                break;
            case "SAFARI":
                //TBD
                break;
            case "EDGE":
                //TBD
                break;
            default:
                throw new IllegalArgumentException(String.format("Browser [%s] not supported.", browser));
        }
    }

    public WebDriver createDriver() throws Exception {
        return driverForBrowser.create();
    }

    public String getDriverName() {
        return driverForBrowser.driverName();
    }

}
