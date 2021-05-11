package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    private static final String
    SEARCH_INT_ELEMENT = "//*[contains(@text, 'Поиск по Википедии')]",
    SEARCH_INPUT = "org.wikipedia:id/search_src_text",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    public void initSearchInput(){
        this.waitForElementByAndClick(
                By.xpath(SEARCH_INT_ELEMENT),
                "error: text - Поиск по Википедии - not found",
                5
        );
    }
    public void typeInInput(String value){
        this.waitForElementByAndSendKeys(
                By.id(SEARCH_INPUT),
                value,
                "error - not entered value",
                5
        );
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresentBy(
                By.xpath(search_result_xpath),
                "error: Cannot find result with substring: " + substring,
                15
        );
    }
}
