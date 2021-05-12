package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }
//    String TITLE = "//*[@text='+VALUE+']",
//    VALUE;
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
}
