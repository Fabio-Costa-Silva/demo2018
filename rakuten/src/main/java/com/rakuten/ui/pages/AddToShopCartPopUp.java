package com.rakuten.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class AddToShopCartPopUp extends PageBase{

    @FindBy(xpath = "//a[contains(text(),'Zum Warenkorb')]")
    private WebElement goToCartBottom;

    static String addToShopCartPopUp = "//div[@class='vw-modalAddToShopCart -show']//div[@class='buttons']";

    @Override
    public boolean isLoaded() {
        return waitAllElementToBeClickable(By.xpath(addToShopCartPopUp), 15);
    }

    public CartPage clickGoCarBottom() {
        waitClick(goToCartBottom, 15);
        return new CartPage();
    }


}
