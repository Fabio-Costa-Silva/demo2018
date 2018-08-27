package com.rakuten.testCases.ui.home;

import com.rakuten.framework.logs.ReportsListener;
import com.rakuten.framework.session.SessionManager;
import com.rakuten.ui.pages.ConsentPopUp;
import com.rakuten.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by FabioCosta
 */
@Listeners(ReportsListener.class)
public class Scenario3{

    static String url;
    public static String defineBrowser;
    public static String loginEmailNotReister;
    public static String loginEmailNotValid;
    public static String loginPassword;
    public static String errorMessageForInvalidEmail;
    public static String errorMessageFordEmailNotRegistered;

    public Scenario3() throws Exception{
        url = "https://www.rakuten.de/kundenkonto";
        defineBrowser = "CHROME";
        loginEmailNotReister = "rakuten.automation@gmail.com";
        loginEmailNotValid = "rakuten.automation.gmail.com";
        loginPassword = "Automation";
        errorMessageForInvalidEmail = "Bitte überprüfen Sie Ihre Eingaben.";
        errorMessageFordEmailNotRegistered = "Diese E-Mail-Passwort-Kombination ist uns nicht bekannt. Bitte korrigieren Sie Ihre Eingabe.";

        SessionManager.createSession();
        SessionManager.openBrowser(defineBrowser);
        SessionManager.openURL(url);
    }

    @Test(description = "[Scenario3] Step 1: Close consent pop-up")
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

    @Test(description = "[Scenario3] Step 2: Open Login page and validate it", dependsOnMethods = "closeConsentPopUpIfOpen")
    public void validateIfLoginPageOpen() throws Exception{
        try{
            LoginPage loginPage = new LoginPage();
            Assert.assertTrue(loginPage.isLoaded(), "Login page was not loaded");
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @Test(description = "[Scenario3] Step 3: Login with invalidates email", dependsOnMethods = "validateIfLoginPageOpen")
    public void loginWithInvalidEmail() throws Exception{
        try{
            LoginPage loginPage = new LoginPage();
            Assert.assertTrue(loginPage.clickEmail(), "Was not able to click on Email field");
            loginPage.typeEmail(loginEmailNotValid);
            Assert.assertTrue(loginPage.clickPassword(), "Was not able to click on Login field");
            loginPage.typePassword(loginPassword);
            loginPage.clickLoginBottom();
            Assert.assertTrue(loginPage.isErrorMessageLoaded(),"Error message didn't show");
            String errorMessage = loginPage.getErrorMessageContent();
            Assert.assertTrue(errorMessageForInvalidEmail.equals(errorMessage),"Expected message didn't show | expected message: "+ errorMessageForInvalidEmail + " | result message: " + errorMessage);
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @Test(description = "[Scenario3] Step 4: Login with email not registered", dependsOnMethods = "loginWithInvalidEmail")
    public void loginWithEmailNotRegistered() throws Exception{
        try{
            LoginPage loginPage = new LoginPage();
            Assert.assertTrue(loginPage.clickEmail(), "Was not able to click on Email field");
            loginPage.typeEmail(loginEmailNotReister);
            Assert.assertTrue(loginPage.clickPassword(), "Was not able to click on Login field");
            loginPage.typePassword(loginPassword);
            loginPage.clickLoginBottom();
            Assert.assertTrue(loginPage.isErrorMessageLoaded(),"Error message didn't show");
            String errorMessage = loginPage.getErrorMessageContent();
            Assert.assertTrue(errorMessageFordEmailNotRegistered.equals(errorMessage),"Expected message didn't show | expected message: "+ errorMessageFordEmailNotRegistered+ " | result message: " + errorMessage);
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @AfterClass(description = "[Scenario3] Step 4: Close browser")
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
