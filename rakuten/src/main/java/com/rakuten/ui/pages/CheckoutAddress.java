package com.rakuten.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class CheckoutAddress extends PageBase{

    static String checkoutAddress = "//div[@id='content']";

    @FindBy(xpath = "//input[@id='delivery-address-invoice']")
    private WebElement myDeliveryAddress;

    @FindBy(xpath = "//button[@id='go_to_next_step']")
    private WebElement goToNextStepBottom;

    @FindBy(xpath = "//div[@class='form-element']//div[@class='jq-transform-container']")
    private WebElement salutationField;

    @FindBy(xpath= "//div[@class='form-element']//ul//li[2]")
    private WebElement firtsSalutationOption;

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='street']")
    private WebElement streetField;

    @FindBy(xpath = "//input[@id='street-number']")
    private WebElement streetNumberField;

    @FindBy(xpath = "//input[@id='zip-code']")
    private WebElement zipCodeField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @Override
    public boolean isLoaded() {
        return (waitAllElementToBeClickable(By.xpath(checkoutAddress), 15) && isMyDeliveryAddressClickable());
    }

    public CheckoutPayment clickGoToNextStepBottom(){
        waitClick(goToNextStepBottom, 15);
        return new CheckoutPayment();
    }

    public boolean isMyDeliveryAddressClickable(){
        return waitElementToBeClickable(myDeliveryAddress, 15);
    }

    public boolean clickSalutation(){
        return waitClick(salutationField, 15);
    }

    public boolean clickFirtSalutationOption(){
        return waitClick(firtsSalutationOption, 15);
    }

    public boolean clickFirstName(){
        return waitClick(firstNameField, 15);
    }

    public void typeFirstName(String firstName){
        firstNameField.sendKeys(firstName);
    }

    public boolean clickLastName() {
        return waitClick(lastNameField, 15);
    }

    public void typeLastName(String addressLastname) {
        lastNameField.sendKeys(addressLastname);
    }

    public boolean clickStreet() {
        return waitClick(streetField, 15);
    }

    public void typeStreet(String addressStreet) {
        streetField.sendKeys(addressStreet);
    }

    public boolean clickStreetNumber() {
        return waitClick(streetNumberField, 15);
    }

    public void typeStreetNumber(String addressStreetNumber) {
        streetNumberField.sendKeys(addressStreetNumber);
    }

    public boolean clickZipCode() {
        return waitClick(zipCodeField, 15);
    }

    public void typeZipCode(String addressZipCode) {
        zipCodeField.sendKeys(addressZipCode);
    }

    public boolean City() {
        return waitClick(cityField, 15);
    }

    public void typeCity(String addressCity) {
        cityField.sendKeys(addressCity);
    }

    public boolean Email() {
        return waitClick(emailField, 15);
    }

    public void typeEmail(String addressEmail) {
        emailField.sendKeys(addressEmail);
    }
}
