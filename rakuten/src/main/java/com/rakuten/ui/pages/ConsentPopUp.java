package com.rakuten.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class ConsentPopUp extends PageBase{

    @FindBy(id = "consent_prompt_submit")
    private WebElement agreeBottom;

    @Override
    public boolean isLoaded() {
       boolean result = false;
        try {
            result = waitElementToBeClickable(agreeBottom, 5);
       }catch (Exception e){
            result = false;
       }
        return result;
    }

    public boolean clickAgree() {
        return waitClick(agreeBottom, 5);
    }

}
