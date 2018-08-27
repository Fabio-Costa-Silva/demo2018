package com.rakuten.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class ProductPage extends PageBase {

    static String productMain = "//div[@class='vw-productMain']";

    @FindBy(xpath = "//div[@class='cart']//a[@class='button add-cart -red']")
    private WebElement addToCarBottom;

    @Override
    public boolean isLoaded() {
        return waitAllElementToBeClickable(By.xpath(productMain), 15);
    }

    public boolean isAddCarBottomLoaded() {
        return waitVisibilityOf(addToCarBottom, 15);
    }

    public AddToShopCartPopUp clickCarBottom() {
        waitClick(addToCarBottom, 15);
        return new AddToShopCartPopUp();
    }
}