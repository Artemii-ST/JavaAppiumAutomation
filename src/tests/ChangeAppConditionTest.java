package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTest extends CoreTestCase {
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
}
