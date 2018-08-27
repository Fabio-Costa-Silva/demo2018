package com.rakuten.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class CheckoutPayment extends PageBase {

    static String checkoutLogin = "//div[@id='content']";

    @FindBy(xpath = "//input[@id='owner_cc']")
    private WebElement ownerField;

    @Override
    public boolean isLoaded() {
        if (waitAllElementToBeClickable(By.xpath(checkoutLogin), 15) && isOwnerFieldClickable())
            return true;
        else
            return false;
    }

    public boolean isOwnerFieldClickable(){
        return waitElementToBeClickable(ownerField, 15);
    }
}
