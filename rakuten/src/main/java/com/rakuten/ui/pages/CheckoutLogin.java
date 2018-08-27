package com.rakuten.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class CheckoutLogin extends PageBase{

    static String checkoutLogin = "//div[@id='content']";

    @FindBy(xpath = "//input[@id='login-method1']")
    private WebElement guestOptionInput;

    @FindBy(xpath = "//button[@id='go_to_next_step']")
    private WebElement goToNextStepBottom;

    @Override
    public boolean isLoaded() {
        if (waitAllElementToBeClickable(By.xpath(checkoutLogin), 15) && isGuestModeClickable())
            return true;
        else
            return false;
    }

    public CheckoutAddress clickGoToNextStepBottom(){
        waitClick(goToNextStepBottom, 15);
        return new CheckoutAddress();
    }

    public boolean isGuestModeClickable(){
        return waitElementToBeClickable(guestOptionInput, 15);
    }

    public boolean clickGuestMode(){
        return waitClick(guestOptionInput, 15);
    }
}
