package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.time.Duration.ofMillis;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }
    public String waitForElementAndGetAttribute (By by, String attribute, String error_message){
        String attributeElement = waitForElementPresentBy(by, error_message, 10).getAttribute(attribute);
        return attributeElement;
    }

    public void assertElementNotPresent(By by, String error_message) throws InterruptedException {
        int amount_of_elements = getAmountOfElements(by);
        if(amount_of_elements > 0){
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public int getAmountOfElements(By by) throws InterruptedException {
        Thread.sleep(2000);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void swipeElementToLeft(By by, String error_message){
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

    public void swipeUpToFindElement(By by, String error_message, int max_swipes){
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
                    .waitAction(WaitOptions.waitOptions(ofMillis(PRESS_TIME)))
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

    public void swipeScreen(com.sun.javafx.scene.traversal.Direction up) {
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
        action
                .tap(PointOption.point(x,start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(timeOfSwipe)))
                .moveTo(PointOption.point(x,end_y))
                .release()
                .perform();

    }

    public Boolean waitForElementNotPresentBy(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }
    public WebElement waitForElementPresentBy(By by, String error_message, long timeoutInSecond){
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

    public WebElement waitForElementByAndClick(By by, String error_message, long timeoutInSecond){
        WebElement webElement = waitForElementPresentBy(by,error_message,timeoutInSecond);
        webElement.click();
        return webElement;
    }
    public WebElement waitForElementByAndSendKeys(By by, String value, String error_message, long timeoutInSecond){
        WebElement webElement = waitForElementPresentBy(by,error_message,timeoutInSecond);
        webElement.sendKeys(value);
        return webElement;
    }
    public WebElement waitForElementByAndClear(By by, String error_message, long timeoutInSecond){
        WebElement webElement = waitForElementPresentBy(by,error_message,timeoutInSecond);
        webElement.clear();
        return webElement;
    }

}
