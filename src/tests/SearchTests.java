package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    //Тест который проверяет что у поискового запроса нет найденых элементов
    // проверка 1 - то что отображается элемент что ничего не найдено - или
    // проверка 2 - если найден какой либо элемент по поисковому запросу - будет ошибка
    public void testAmountOfEmptySearch() throws InterruptedException {
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("Moscowqweqweqwe");

        assertTrue(
                "amountResult isNot null",
                searchPageObject.getAmountElementsInSearchList() == 0
        );
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.elementPresentByXpath("Ничего не найдено");
    }

    @Test
    //Поиск списка элементов в выдаче поиска - и сравнение результата через Assert - если результатов больше нуля то тест пройден
    public void testAmountOfNotEmptySearch() throws InterruptedException {
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("Moscow");
        int amountResult = searchPageObject.getAmountElementsInSearchList();
        assertTrue(
                "amountResult isNot bigger than null",
                amountResult > 0
        );

    }
    @Test
    public void testStartSearch(){
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("apple");
        searchPageObject.waitForSearchResult("Apple Watch");
    }
    @Test
    public void testCancelSearch(){
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("apple");
        searchPageObject.clickToCloseButton();
        searchPageObject.clickSearchGoToBack();
    }
    @Test
    public void testClearSearch(){
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("apple");
        searchPageObject.clearInputSearch();
        searchPageObject.clickSearchGoToBack();

    }
}
