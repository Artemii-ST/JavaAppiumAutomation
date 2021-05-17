import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
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
    //Добавление статьи в закладки - а затем удаление ее из закладок при помощи свайпа нужного элемента
    //затем проверка того что удаленный элемент пропал с экрана
    public void testAddArticleToBookmarkAndRemove(){
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("java");
        searchPageObject.waitForNeedSearchResultAndClick("JavaServer Pages");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.AddArticleInNewFolderBookmark("java tutorial");
        articlePageObject.clickGoToBack();
        articlePageObject.clickGoToBack();
        articlePageObject.openTheDesiredFolderInBookmarksAndDeleteNeedArticle("java tutorial","JavaServer Pages");
    }
    @Test
    //Поиск списка элементов в выдаче поиска - и сравнение результата через Assert - если результатов больше нуля то тест пройден
    public void testAmountOfNotEmptySearch() throws InterruptedException {
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("Moscow");
        int amountResult = searchPageObject.getAmountElementsInSearchList();
        Assert.assertTrue(
                "amountResult isNot bigger than null",
                amountResult > 0
        );

    }
    @Test
    //Тест который проверяет что у поискового запроса нет найденых элементов
    // проверка 1 - то что отображается элемент что ничего не найдено - или
    // проверка 2 - если найден какой либо элемент по поисковому запросу - будет ошибка
    public void testAmountOfEmptySearch() throws InterruptedException {
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("Moscowqweqweqwe");

        Assert.assertTrue(
                "amountResult isNot null",
                searchPageObject.getAmountElementsInSearchList() == 0
        );
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.elementPresentByXpath("Ничего не найдено");
    }
    @Test
    //Ротация экрана и проверка атрибутов элемента до ротации и после.
    public void testCompareArticleTitleAndRotate(){
        new OnboardingPageObject(driver).justSkip();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeInInput("java");
        searchPageObject.waitForNeedSearchResultAndClick("JavaServer Pages");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String beforeRotate = articlePageObject.getArticleTitle("JavaServer Pages");

        this.rotateLANDSCAPE();

        String afterRotate = articlePageObject.getArticleTitle("JavaServer Pages");
        assertEquals(
                "elemets " + beforeRotate + " and " + afterRotate + " not Equals",
                beforeRotate,
                afterRotate
        );

        this.rotatePORTRAIT();

        String afterRotate_second_rotate = articlePageObject.getArticleTitle("JavaServer Pages");
        assertEquals(
                "elemets " + beforeRotate + " and " + afterRotate_second_rotate + " not Equals",
                beforeRotate,
                afterRotate_second_rotate
        );

    }
    @Test
    // Сворачивание приложения - и проверка наличия элемента после разворачивания.
    // ТЕСТ НЕ РАБОЧИЙ - приложение сворачивается и падает с ошибкой (502 строка)
    //org.openqa.selenium.WebDriverException: An unknown server-side error occurred while processing the command.
    // Original error: Could not get focusPackageAndActivity.
    // Original error: Could not parse activity from dumpsys (WARNING: The server did not provide any stacktrace information)
    //Command duration or timeout: 0 milliseconds
    public void testCheckRunAppInBackgroundAndCheckTitleArticle(){
        MainPageObject.waitForElementByAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "error: element skip_button - not found",
                5
        );

        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "error: text - Поиск по Википедии - not found",
                5
        );

        MainPageObject.waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "error - not entered value",
                15
        );

        MainPageObject.waitForElementPresentBy(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );

        //driver.runAppInBackground(Duration.ofSeconds(3));



        MainPageObject.waitForElementPresentBy(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );
    }


}
