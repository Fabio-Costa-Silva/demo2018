package com.rakuten.testCases.ui.home;

import com.rakuten.framework.logs.ReportsListener;
import com.rakuten.framework.session.SessionManager;
import com.rakuten.ui.pages.ConsentPopUp;
import com.rakuten.ui.pages.HomePage;
import com.rakuten.ui.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by FabioCosta
 */
@Listeners(ReportsListener.class)
public class Scenario1{

    public static String defineBrowser;
    public static String homeSearchCriteria;
    static String url;

    public Scenario1() throws Exception{
        url = "https://www.rakuten.de/";
        defineBrowser = "CHROME";
        homeSearchCriteria = "mobile";
        SessionManager.createSession();
        SessionManager.openBrowser(defineBrowser);
        SessionManager.openURL(url);
    }


    @Test(description = "[Scenario1] Step 1: Close consent pop-up")
    public void closeConsentPopUpIfOpen() throws Exception{
        try{
            ConsentPopUp consentPopUp = new ConsentPopUp();
            if(consentPopUp.isLoaded()) {
                consentPopUp.clickAgree();
            }
            Assert.assertFalse(consentPopUp.isLoaded(),"Consent pop-up was not closed");
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @Test(description = "[Scenario1] Step 2: Open home page and validate Title", dependsOnMethods = "closeConsentPopUpIfOpen")
    public void canOpenToHomePage() throws Exception{
        try{
            HomePage homePage = new HomePage();
            Assert.assertTrue(homePage.isLoaded());
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @Test(description = "[Scenario1] Step 3: Open home page and validate Title", dependsOnMethods = "canOpenToHomePage")
    public void seachInHomePage() throws Exception{
        try{
            HomePage homePage = new HomePage();
            Assert.assertTrue(homePage.isSearchLoaded(), "Search Filter or Search Bottom didn't loaded");
            homePage.populateSearchInput(homeSearchCriteria);
            ProductPage productPage = homePage.clickOnFirstOptionFromAutoComplete();
            Assert.assertTrue(productPage.isLoaded());
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @AfterClass(description = "[Scenario1] Step 4: Close browser")
    public void cleanUp(){
        closeDriverAndBrowserAtShutdown();
    }

    private static void closeDriverAndBrowserAtShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                SessionManager.deleteSession();
                System.out.println("[INFO] Closing driver and browser.");
            } catch (Exception e) {
                System.out.println("[ERROR] Reason: " + e.getMessage());
            }
        }));
    }
}
