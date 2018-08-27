package com.rakuten.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class LoginPage extends PageBase {

    static String loingContainer = "//body/div[@id='frame']/div[@class='login acc-container']/div[@class='acc-content']/div[@class='address-container']/div[1]/div[1]";

    @FindBy(xpath = "//title[contains(text(),'Kundenkonto - Rakuten.de')]")
    private  WebElement title;

    @FindBy(xpath = "//input[@placeholder='E-Mail-Adresse']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Passwort']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@value='login']")
    private WebElement loginBottom;

    @FindBy(xpath = "//font[contains(text(),'Forgot Password')]")
    private WebElement forgotPasswordBottom;

    @FindBy(xpath = "//div[@class='message-error']")
    private WebElement errorMessage;

    @Override
    public boolean isLoaded() {
        return (waitAllElementToBeClickable(By.xpath(loingContainer), 15) && title.isEnabled());
    }

    public boolean clickEmail() {
        return waitClick(emailField, 15);
    }

    public void typeEmail(String loginEmail) {
        emailField.sendKeys(loginEmail);
    }

    public boolean clickPassword() {
        return waitClick(passwordField, 15);
    }

    public void typePassword(String loginPassword) {
        passwordField.sendKeys(loginPassword);
    }

    public void clickLoginBottom() {
        waitClick(loginBottom, 15);
    }

    public boolean isErrorMessageLoaded() {
        return waitVisibilityOf(errorMessage,15);
    }

    public String getErrorMessageContent() {
        String result = "";
        if (isErrorMessageLoaded()){
            result = errorMessage.getText();
        }
        return result;
    }
}