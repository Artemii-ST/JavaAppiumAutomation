package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }
    String ADD_TO_LIST = "//*[@text='ДОБАВИТЬ В СПИСОК']",
    BOOKMARK = "org.wikipedia:id/article_menu_bookmark",
    OK = "//*[@text='ОК']";

    public WebElement waitForTitleElement(String VALUE){
        return this.waitForElementPresentBy(
                    By.xpath("//*[@text='" +VALUE+"']"),
                    "error: Title element - not found",
                    5
            );
    }
    public String getArticleTitle(String VALUE){
        WebElement title_element = waitForTitleElement(VALUE);
        return title_element.getAttribute("text");
    }
    public void swipeLEFT(){
        this.swipeScreen(Direction.LEFT);
    }
    public void swipeRIGHT(){
        this.swipeScreen(Direction.RIGHT);
    }
    public void swipeUP(){
        this.swipeScreen(Direction.UP);
    }
    public void swipeDOWN(){
        this.swipeScreen(Direction.DOWN);
    }
    public void AddArticleInNewFolderBookmark(String FOLDER_NAME){
        this.waitForElementByAndClick(
                By.id(BOOKMARK),
                "error: Cannot find element bookmark",
                15
        );
        this.waitForElementByAndClick(
                By.xpath(ADD_TO_LIST),
                "error: Cannot find element bookmark",
                15
        );
        this.waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                FOLDER_NAME,
                "error - "+FOLDER_NAME+" Cannot find input element",
                15
        );
        this.waitForElementByAndClick(
                By.xpath(OK),
                "error: Cannot find element button OK",
                15
        );
    }
    public void clickGoToBack(){
        this.waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );
    }
    public void openTheDesiredFolderInBookmarksAndDeleteNeedArticle(String name_folder, String need_article){
        this.waitForElementByAndClick(
                By.xpath("//*[@content-desc='Сохранено']"),
                "error: Cannot find element - Сохранено",
                15
        );
        this.waitForElementByAndClick(
                By.xpath("//*[@text='"+name_folder+"']"),
                "error: Cannot find element - "+name_folder+"/",
                15
        );
        this.waitForElementPresentBy(
                By.xpath("//*[@text='"+need_article+"']"),
                "error: Cannot find element - "+need_article+"/",
                15
        );
        //swipe
        this.swipeElementToLeft(
                By.xpath("//*[@text='"+need_article+"']"),
                "error: NE SWAYPITSYA"
        );
        //
        this.waitForElementNotPresentBy(
                By.xpath("//*[@text='"+need_article+"']"),
                "error: Script find element - "+need_article+" in bookmark",
                15
        );
    }
    public void elementPresentByXpath(String name_Xpath){
        this.waitForElementPresentBy(
                By.xpath("//*[@text='"+name_Xpath+"']"),
                "error: Script find element - "+name_Xpath+"/",
                15
        );
    }

}
