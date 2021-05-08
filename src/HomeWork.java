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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofMillis;

public class HomeWork {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "OnePlus");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/users/artem/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void elementHasText() {
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
        assertElementHasText(
                By.xpath("//*[contains(@text, 'Поиск по Википедии')]"),
                "Поиск по Википедии",
                "error : text - Поиск по Википедии is not correct",
                5
        );

    }

    @Test
    public void cancelSearch() {
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
        waitForElementByAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "error: search form is not closed",
                5
        );
        waitForElementNotPresentBy(
                By.id("org.wikipedia:id/search_close_btn"),
                "error: element for close search form enabled",
                5
        );


    }
    @Test
    public void checkWordInSearch() {
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
        List<WebElement> list = waitForElementsPresentBy(
                By.id("org.wikipedia:id/page_list_item_title"),
                "error - list not found",
                15
        );
        boolean result = CheckHaveWordInSearchResult(list);
        Assert.assertTrue("Not all elements have word Java",result);
    }
    @Test
    //Добавление 2 х статей в закладки в одну и ту же папку- а затем удаление 1 статьи из закладок при помощи свайпа нужного элемента
    //затем проверка того что осталась 2 статья
    //Перейти в нее и убелиться что title совпадает
    public void AddArticleToBookmarkAndRemove(){
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
        waitForElementByAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "error: Cannot find element bookmark",
                15
        );
        waitForElementByAndClick(
                By.xpath("//*[@text='ДОБАВИТЬ В СПИСОК']"),
                "error: Cannot find element bookmark",
                15
        );
        waitForElementByAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "java tutorial",
                "error - Cannot find input element",
                15
        );
        waitForElementByAndClick(
                By.xpath("//*[@text='ОК']"),
                "error: Cannot find element button OK",
                15
        );
        waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );

        waitForElementByAndClick(
                By.xpath("//*[@text='JavaFX']"),
                "error: element article JavaFX - not found",
                15
        );
        waitForElementByAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "error: Cannot find element bookmark",
                15
        );
        waitForElementByAndClick(
                By.xpath("//*[@text='ДОБАВИТЬ В СПИСОК']"),
                "error: Cannot find element bookmark",
                15
        );
        waitForElementByAndClick(
                By.xpath("//*[@text='java tutorial']"),
                "error: Cannot find element - java tutorial",
                15
        );
        waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );
        waitForElementByAndClick(
                By.className("android.widget.ImageButton"),
                "error: element go to back not found",
                5
        );
        waitForElementByAndClick(
                By.xpath("//*[@content-desc='Сохранено']"),
                "error: Cannot find element - Сохранено",
                15
        );
        waitForElementByAndClick(
                By.xpath("//*[@text='java tutorial']"),
                "error: Cannot find element - java tutorial",
                15
        );
        waitForElementPresentBy(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: Cannot find element - JavaServer Pages in bookmark",
                15
        );
        //swipe
        swipeElementToLeft(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: NE SWAYPITSYA"
        );
        //
        waitForElementNotPresentBy(
                By.xpath("//*[@text='JavaServer Pages']"),
                "error: Script find element - JavaServer Pages in bookmark",
                15
        );
        waitForElementByAndClick(
                By.xpath("//*[@text='JavaFX']"),
                "error: Cannot find element - javaFX",
                15
        );
        waitForElementPresentBy(
                By.xpath("//*[@text='JavaFX']"),
                "error: Cannot find element - JavaFX Pages in bookmark",
                15
        );



    }

    //========================Methods=====================================

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresentBy(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int border = 10;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x -border,middle_y))
                .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                .moveTo(PointOption.point(left_x - border, middle_y))
                .release()
                .perform();

    }

    private boolean CheckHaveWordInSearchResult(List<WebElement> webElement) {
        boolean result;
        int count =0;
        ArrayList<String> list = new ArrayList<>();
        for (int x=0; x<webElement.size(); x++) {
            list.add(webElement.get(x).getText());
        }
        for (int x=0; x<webElement.size(); x++) {
            int indexJava = list.get(x).indexOf("Java");
            if(indexJava == - 1) {
            } else {
                count++;
            }
        }
        if (count == webElement.size())
            result = true;
        else result = false;
return result;
    }

    private WebElement waitForElementByAndClick(By by, String error_message, long timeoutInSecond) {
        WebElement webElement = waitForElementPresentBy(by, error_message, timeoutInSecond);
        webElement.click();
        return webElement;
    }

    private WebElement waitForElementPresentBy(By by, String error_message, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private List<WebElement> waitForElementsPresentBy(By by, String error_message, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private WebElement assertElementHasText(By by, String expected, String error_message, long timeoutInSecond) {
        WebElement input_search_title = waitForElementPresentBy(by, error_message, timeoutInSecond);
        String actual_input = input_search_title.getAttribute("text");
        Assert.assertEquals(
                "error :" + " " + expected + " " + "not correctly",
                expected,
                actual_input
        );
        return input_search_title;
    }

    private WebElement waitForElementByAndSendKeys(By by, String value, String error_message, long timeoutInSecond) {
        WebElement webElement = waitForElementPresentBy(by, error_message, timeoutInSecond);
        webElement.sendKeys(value);
        return webElement;
    }

    private Boolean waitForElementNotPresentBy(By by, String error_message, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }
}
