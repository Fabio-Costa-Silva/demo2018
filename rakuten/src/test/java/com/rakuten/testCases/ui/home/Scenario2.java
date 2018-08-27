package com.rakuten.testCases.ui.home;

import com.rakuten.framework.logs.ReportsListener;
import com.rakuten.framework.session.SessionManager;
import com.rakuten.ui.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by FabioCosta
 */
@Listeners(ReportsListener.class)
public class Scenario2 {

    static String url;
    public static String defineBrowser;
    public static String addressFirstname;
    public static String addressLastname;
    public static String addressStreet;
    public static String addressStreetNumber;
    public static String addressZipCode;
    public static String addressCity;
    public static String addressEmail;

    public Scenario2() throws Exception{
        url = "https://www.rakuten.de/produkt/03-hersteller-apple-airpods-in-ear-headset-mmef2bea-white-2013450460";
        defineBrowser = "CHROME";
        addressFirstname = "Rakuten";
        addressLastname = "Automation";
        addressStreet = "Geisfelder Str.";
        addressStreetNumber = "16";
        addressZipCode = "96050";
        addressCity = "Bamberg";
        addressEmail = "rakuten.automation@gmail.com";

        SessionManager.createSession();
        SessionManager.openBrowser(defineBrowser);
        SessionManager.openURL(url);
    }

    @Test(description = "[Scenario2] Step 1: Close consent pop-up")
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

    @Test(description = "[Scenario2] Step 2: Open Product page", dependsOnMethods = "closeConsentPopUpIfOpen")
    public void validateProductPage() throws Exception{
        try{
            ProductPage productPage = new ProductPage();
            Assert.assertTrue(productPage.isLoaded(), "Product page was not loaded");
            Assert.assertTrue(productPage.isAddCarBottomLoaded(), "Add to cart bottom was not loaded");
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @Test(description = "[Scenario2] Step 3: Add Product to cart and go to Checkout", dependsOnMethods = "validateProductPage")
    public void addProductToCartAndGoToCheckoutPage() throws Exception{
        try{
            ProductPage productPage = new ProductPage();
            AddToShopCartPopUp addToShopCartPopUp = productPage.clickCarBottom();
            Assert.assertTrue(addToShopCartPopUp.isLoaded(), "Add to shop cart pop-up was not loaded");
            CartPage cartPage = addToShopCartPopUp.clickGoCarBottom();
            Assert.assertTrue(cartPage.isLoaded(),"Cart page was not loaded");
            CheckoutLogin checkoutLogin = cartPage.clickGoToCheckOutBottom();
            Assert.assertTrue((checkoutLogin).isLoaded(), "Checkout Login was not Loaded");
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @Test(description = "[Scenario2] Step 4: At Checkout login page select guest and go to Checkout Address page", dependsOnMethods = "addProductToCartAndGoToCheckoutPage")
    public void chooseGuestModeAndGoToCheckoutAddressPage() throws Exception{
        try{
            CheckoutLogin checkoutLogin = new CheckoutLogin();
            Assert.assertTrue((checkoutLogin).isLoaded(), "Checkout Login page was not Loaded");
            Assert.assertTrue(checkoutLogin.isGuestModeClickable(),"Could not select guest mode");
            Assert.assertTrue(checkoutLogin.clickGuestMode(), "Could not select guest mode");
            CheckoutAddress checkoutAddress = checkoutLogin.clickGoToNextStepBottom();
            Assert.assertTrue(checkoutAddress.isLoaded(),"Checkout address page was not loaded");
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @Test(description = "[Scenario2] Step 5: At Checkout address fulfill the fields and go to XXX page", dependsOnMethods = "chooseGuestModeAndGoToCheckoutAddressPage")
    public void fulfillRequiredFieldsAndGoToCheckoutAddressPage() throws Exception{
        try{
            CheckoutAddress checkoutAddress = new CheckoutAddress();
            Assert.assertTrue(checkoutAddress.isLoaded(),"Checkout address page was not loaded");
            //Salutation
            Assert.assertTrue(checkoutAddress.clickSalutation());
            Assert.assertTrue(checkoutAddress.clickFirtSalutationOption());
            //First Name
            Assert.assertTrue(checkoutAddress.clickFirstName());
            checkoutAddress.typeFirstName(addressFirstname);
            //Last Name
            Assert.assertTrue(checkoutAddress.clickLastName());
            checkoutAddress.typeLastName(addressLastname);
            //Street
            Assert.assertTrue(checkoutAddress.clickStreet());
            checkoutAddress.typeStreet(addressStreet);
            //StreetNumber
            Assert.assertTrue(checkoutAddress.clickStreetNumber());
            checkoutAddress.typeStreetNumber(addressStreetNumber);
            //ZipCode
            Assert.assertTrue(checkoutAddress.clickZipCode());
            checkoutAddress.typeZipCode(addressZipCode);
            //City
            Assert.assertTrue(checkoutAddress.City());
            checkoutAddress.typeCity(addressCity);
            //Email
            Assert.assertTrue(checkoutAddress.Email());
            checkoutAddress.typeEmail(addressEmail);

            CheckoutPayment checkoutPayment = checkoutAddress.clickGoToNextStepBottom();
            Assert.assertTrue(checkoutPayment.isLoaded(),"Checkout address page was not loaded");
        } catch (Exception e){
            Assert.fail("Reason for error: " + e.getMessage());
        }
    }

    @AfterClass(description = "[Scenario2] Step 7: Close browser")
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
