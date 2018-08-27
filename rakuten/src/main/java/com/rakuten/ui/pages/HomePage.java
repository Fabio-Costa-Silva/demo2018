package com.rakuten.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FabioCosta
 */
public class HomePage extends PageBase {


    @FindBy(xpath = "//form[@id='search']//button[@type='submit']")
    private WebElement searchBottom;

    @FindBy(id = "search-input")
    private WebElement searchInput;

    @FindBy(xpath = "//ul//li[1]//div[1]")
    private WebElement autoCompleteFirstItem;

    @FindBy(xpath = "//title[contains(text(),'Rakuten.de')]")
    private  WebElement title;

    @Override
    public boolean isLoaded() {
        return title.isEnabled();
    }

    public boolean isSearchLoaded() {
        boolean result = false;
        if (waitElementToBeClickable(searchBottom, 15) && waitElementToBeClickable(searchInput, 15)){
            return true;
        }else{
            return false;
        }
    }

    public HomePage populateSearchInput(String seatchCriteria){
        waitClick(searchInput, 15);
        searchInput.sendKeys(seatchCriteria);
        return this;
    }

    public ProductPage clickOnFirstOptionFromAutoComplete(){
        waitClick(autoCompleteFirstItem, 15);
        ProductPage productPage = new ProductPage();
        return productPage;
    }
}