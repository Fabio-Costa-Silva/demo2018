package com.rakuten.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class CartPage extends PageBase{

    static String cartOverview = "//div[@id='cart-overview']";

    @FindBy(xpath = "//button[@id='go_to_checkout']")
    private WebElement goToCheckOutBottom;

    @Override
    public boolean isLoaded() {
        return waitAllElementToBeClickable(By.xpath(cartOverview), 15);
    }

    public CheckoutLogin clickGoToCheckOutBottom(){
        waitClick(goToCheckOutBottom, 15);
        return new CheckoutLogin();
    }
}
