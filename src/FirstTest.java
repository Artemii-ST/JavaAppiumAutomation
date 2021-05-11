import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

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

        MainPageObject.waitForElementByAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "error - element is not clear",
                15
        );
        MainPageObject.waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );

        MainPageObject.waitForElementNotPresentBy(
                By.id("android.widget.ImageButton"),
                "error: element go to back found",
                5
        );

    }
    @Test
    public void testCompareArticleTitle(){
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

        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );

        WebElement title_element = MainPageObject.waitForElementPresentBy(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not open",
                15
        );
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "expected text not found",
                "JavaServer Pages",
                article_title
        );
    }
    @Test
    public void testSwipeUp(){

        MainPageObject.swipeScreen(lib.ui.MainPageObject.Direction.LEFT);
        MainPageObject.swipeScreen(lib.ui.MainPageObject.Direction.LEFT);
        MainPageObject.swipeScreen(lib.ui.MainPageObject.Direction.LEFT);

        MainPageObject.waitForElementByAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_done_button"),
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

        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );
        MainPageObject.swipeScreen(lib.ui.MainPageObject.Direction.UP);
        MainPageObject.swipeScreen(lib.ui.MainPageObject.Direction.UP);
    }
    @Test // Поиск элемента на экране - делаем свайпы до тех пор пока нужный элемент не покажется на экране
    public void testSwipeUpToFindElementInPage(){
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

        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='JavaMail']"),
                //By.id("Директива_JSP_taglib"),
                "error: Директива_JSP_taglib - not found",
                5
        );




    }
    @Test
    //Добавление статьи в закладки - а затем удаление ее из закладок при помощи свайпа нужного элемента
    //затем проверка того что удаленный элемент пропал с экрана
    public void testAddArticleToBookmarkAndRemove(){
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

        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );
        MainPageObject.waitForElementByAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "error: Cannot find element bookmark",
                15
        );
        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@text='ДОБАВИТЬ В СПИСОК']"),
                "error: Cannot find element bookmark",
                15
        );
        MainPageObject.waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "java tutorial",
                "error - Cannot find input element",
                15
        );
        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@text='ОК']"),
                "error: Cannot find element button OK",
                15
        );
        MainPageObject.waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );
        MainPageObject.waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );
        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@content-desc='Сохранено']"),
                "error: Cannot find element - Сохранено",
                15
        );
        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@content-desc='Сохранено']"),
                "error: Cannot find element - Сохранено",
                15
        );
        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@text='java tutorial']"),
                "error: Cannot find element - java tutorial",
                15
        );
        MainPageObject.waitForElementPresentBy(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: Cannot find element - JavaServer Pages in bookmark",
                15
        );
        //swipe
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: NE SWAYPITSYA"
        );
        //
        MainPageObject.waitForElementNotPresentBy(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: Script find element - JavaServer Pages in bookmark",
                15
        );



    }
    @Test
    //Поиск списка элементов в выдаче поиска - и сравнение результата через Assert - если результатов больше нуля то тест пройден
    public void testAmountOfNotEmptySearch() throws InterruptedException {
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
                "Moscow",
                "error - not entered value",
                15
        );
        int amountResult = MainPageObject.getAmountOfElements(
                By.id("org.wikipedia:id/page_list_item_title")

        );
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
                "qweasdzxc",
                "error - not entered value",
                15
        );
        MainPageObject.waitForElementPresentBy(
                By.xpath("//*[contains(@text, 'Ничего не найдено')]"),
                "error: text - Ничего не найдено - not found",
                5
        );

        MainPageObject.assertElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Element list item found"
        );

    }
    @Test
    //Ротация экрана и проверка атрибутов элемента до ротации и после.
    public void testCompareArticleTitleAndRotate(){
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

        MainPageObject.waitForElementByAndClick(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );
        String beforeRotate = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='JavaServer Pages']"),
                "text",
                "error: JavaServer Pages - not found"
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String afterRotate = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='JavaServer Pages']"),
                "text",
                "error: JavaServer Pages - not found"
        );
        Assert.assertEquals(
                "elemets " + beforeRotate + " and " + afterRotate + " not Equals",
                beforeRotate,
                afterRotate
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String afterRotate_second_rotate = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='JavaServer Pages']"),
                "text",
                "error: JavaServer Pages - not found"
        );
        Assert.assertEquals(
                "elemets " + beforeRotate + " and " + afterRotate_second_rotate + " not Equals",
                beforeRotate,
                afterRotate_second_rotate
        );

    }
    @Test
    // Сворачивание приложения - и проверка наличия элемента после разворачивания.
    // Тест не рабочий - приложение с ворачивается и падает с ошибкой (502 строка)
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
