package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
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
}
