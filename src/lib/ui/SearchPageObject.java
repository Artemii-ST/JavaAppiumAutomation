package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    private static final String
    SEARCH_INT_ELEMENT = "//*[contains(@text, 'Поиск по Википедии')]",
    SEARCH_INPUT = "org.wikipedia:id/search_src_text",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
    LIST_ITEM_TITLE = "org.wikipedia:id/page_list_item_title";

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
    public void clickToCloseButton(){
        this.waitForElementByAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "error: Cannot find search_close_btn",
                5
        );
    }
    public void clickSearchGoToBack(){
        this.waitForElementNotPresentBy(
        By.id("android.widget.ImageButton"),
                "error: element go to back found",
                5
        );
    }
    public void clearInputSearch(){
        this.waitForElementByAndClear(
                By.id(SEARCH_INPUT),
                "error - element is not clear",
                5
        );
    }
    public void waitForNeedSearchResultAndClick(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementByAndClick(
                By.xpath(search_result_xpath),
                "error: Cannot find need result with substring: " + substring + " and click",
                15
        );
    }
    public void swipeUpToFindNeedElement(String name_element){
        this.swipeUpToFindElement(
                By.xpath("//*[@text='"+name_element+"']"),
                //By.id("Директива_JSP_taglib"),
                "error: "+name_element+" - not found",
                5
        );
    }
    public int getAmountElementsInSearchList() throws InterruptedException {
        return this.getAmountOfElements(
                By.id(LIST_ITEM_TITLE)
        );
    }

}
