import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;
    @Before
public void setUp() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName","Android");
    capabilities.setCapability("deviceName","OnePlus");
    capabilities.setCapability("platformVersion","10");
    capabilities.setCapability("automationName","Appium");
    capabilities.setCapability("appPackage","org.wikipedia");
    capabilities.setCapability("appActivity",".main.MainActivity");
    capabilities.setCapability("app","/users/artem/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
}
@After
public void tearDown(){
    driver.quit();
}
@Test
public void firstTest(){
    waitForElementByAndClick(
            By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
            "error: element skip_button - not found",
            5
    );

    waitForElementByAndClick(
            By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
            "error: text - Поиск по Википедии - not found",
            5
    );

    waitForElementByAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            "java",
            "error - not entered value",
            15
    );

    waitForElementPresentBy(
            By.xpath("//*[@text='JavaServer Pages']"),
            "error: JavaServer Pages - not found",
            15
    );
}
    @Test
    public void cancelSearch(){
        waitForElementByAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "error: element skip_button - not found",
                5
        );

        waitForElementByAndClick(
                By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "error: text - Поиск по Википедии - not found",
                5
        );

        waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "error - not entered value",
                15
        );

        waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );

        waitForElementNotPresentBy(
                By.id("android.widget.ImageButton"),
                "error: element go to back found",
                5
        );


    }
    @Test
    public void clearSearch(){
        waitForElementByAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "error: element skip_button - not found",
                5
        );

        waitForElementByAndClick(
                By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "error: text - Поиск по Википедии - not found",
                5
        );

        waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "error - not entered value",
                15
        );

        waitForElementByAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "error - element is not clear",
                15
        );
        waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );

        waitForElementNotPresentBy(
                By.id("android.widget.ImageButton"),
                "error: element go to back found",
                5
        );

    }
    @Test
    public void testCompareArticleTitle(){
        waitForElementByAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "error: element skip_button - not found",
                5
        );

        waitForElementByAndClick(
                By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "error: text - Поиск по Википедии - not found",
                5
        );

        waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "error - not entered value",
                15
        );

        waitForElementByAndClick(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );

        WebElement title_element = waitForElementPresentBy(
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

        swipeScreen(Direction.LEFT);
        swipeScreen(Direction.LEFT);
        swipeScreen(Direction.LEFT);

        waitForElementByAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_done_button"),
                "error: element skip_button - not found",
                5
        );

        waitForElementByAndClick(
                By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "error: text - Поиск по Википедии - not found",
                5
        );

        waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "error - not entered value",
                15
        );

        waitForElementByAndClick(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: JavaServer Pages - not found",
                15
        );
        swipeScreen(Direction.UP);
        swipeScreen(Direction.UP);
    }
    @Test // Поиск элемента на экране - делаем свайпы до тех пор пока нужный элемент не покажется на экране
    public void swipeUpToFindElementInPage(){
        waitForElementByAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "error: element skip_button - not found",
                5
        );

        waitForElementByAndClick(
                By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "error: text - Поиск по Википедии - not found",
                5
        );

        waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "error - not entered value",
                15
        );

//        waitForElementByAndClick(
//                By.xpath("//*[@text='JavaServer Pages']"),
//                "error: JavaServer Pages - not found",
//                15
//        );
        swipeUpToFindElement(
                By.xpath("//*[@text='JavaMail']"),
                //By.id("Директива_JSP_taglib"),
                "error: Директива_JSP_taglib - not found",
                5
        );




    }

//============================Methods==============================================
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if(already_swiped > max_swipes){
                waitForElementPresentBy(by, "Cannot finde element by swiping up. \b" + error_message, 0);
            }
            swipeScreen(Direction.UP);
            already_swiped++;
        }

    }


public void swipeScreen(Direction dir) {
    System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

    // Animation default time:
    //  - Android: 300 ms
    //  - iOS: 200 ms
    // final value depends on your app and could be greater
    final int ANIMATION_TIME = 2000; // ms

    final int PRESS_TIME = 2000; // ms

    int edgeBorder = 10; // better avoid edges
    PointOption pointOptionStart, pointOptionEnd;

    // init screen variables
    Dimension dims = driver.manage().window().getSize();

    // init start point = center of screen
    pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

    switch (dir) {
        case DOWN: // center of footer
            pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
            break;
        case UP: // center of header
            pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
            break;
        case LEFT: // center of left side
            pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
            break;
        case RIGHT: // center of right side
            pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
            break;
        default:
            throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
    }

    // execute swipe using TouchAction
    try {
        new TouchAction(driver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
    } catch (Exception e) {
        System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
        return;
    }

    // always allow swipe action to complete
    try {
        Thread.sleep(ANIMATION_TIME);
    } catch (InterruptedException e) {
        // ignore
    }
}

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    //==========================================================================

    protected void swipeUp(int timeOfSwipe){            //ДОРЕШАТЬ НАДО
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.height / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.width * 0.2);

        //action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
        action.tap(PointOption.point(x,start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(timeOfSwipe))).moveTo(PointOption.point(x,end_y)).release().perform();

    }

    private Boolean waitForElementNotPresentBy(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }
    private WebElement waitForElementPresentBy(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private List<WebElement> waitForElementsPresentBy(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private WebElement waitForElementByAndClick(By by, String error_message, long timeoutInSecond){
        WebElement webElement = waitForElementPresentBy(by,error_message,timeoutInSecond);
        webElement.click();
        return webElement;
    }
    private WebElement waitForElementByAndSendKeys(By by, String value, String error_message, long timeoutInSecond){
        WebElement webElement = waitForElementPresentBy(by,error_message,timeoutInSecond);
        webElement.sendKeys(value);
        return webElement;
    }
    private WebElement waitForElementByAndClear(By by, String error_message, long timeoutInSecond){
        WebElement webElement = waitForElementPresentBy(by,error_message,timeoutInSecond);
        webElement.clear();
        return webElement;
    }


}
