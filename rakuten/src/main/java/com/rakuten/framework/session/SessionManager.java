package com.rakuten.framework.session;

import org.openqa.selenium.WebDriver;

/**
 * Created by FabioCosta
 */
public class SessionManager {
    private static Session session;

    private static void assertSessionIsInitialized() {
        if (session != null) {
            return;
        }
        throw new RuntimeException("Session was not initialized. A session needs to exist prior to use it's features. Aborting.");
    }

    public static WebDriver getSessionDriver() {
        assertSessionIsInitialized();
        return session.getDriver();
    }

    public static void openURL(String url) {
        assertSessionIsInitialized();
        session.openURL(url);
    }
    public static void openBrowser(String defineBrowser) throws Exception {
        assertSessionIsInitialized();
        session.open(defineBrowser);
    }

    public static void createSession() {
        if (session == null) {
            session = new Session();
        }
    }

    public static void deleteSession() throws Exception {
        if (session != null) {
            session.closeBrowser();
        }
        session = null;
    }

}
