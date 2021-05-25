package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle(){
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("java");
        searchPageObject.waitForNeedSearchResultAndClick("JavaServer Pages");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String article_title = articlePageObject.getArticleTitle("JavaServer Pages");
        Assert.assertEquals(
                "expected text not found",
                "JavaServer Pages",
                article_title
        );
    }
    @Test // Поиск элемента на экране - делаем свайпы до тех пор пока нужный элемент не покажется на экране
    public void testSwipeUpToFindElementInPage(){
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("java");
        searchPageObject.swipeUpToFindNeedElement("DTO");
        searchPageObject.waitForNeedSearchResultAndClick("DTO");
    }
    @Test
    public void testSwipeUp(){

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.swipeLEFT();
        articlePageObject.swipeLEFT();
        articlePageObject.swipeLEFT();
        new OnboardingPageObject(driver).clickStartButton();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("java");
        searchPageObject.waitForNeedSearchResultAndClick("JavaFX");
        articlePageObject.swipeUP();
        articlePageObject.swipeUP();
    }
}
