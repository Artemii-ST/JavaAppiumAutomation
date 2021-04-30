import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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

//==========================================================================

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
