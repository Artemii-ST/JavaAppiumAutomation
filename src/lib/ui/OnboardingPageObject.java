package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class OnboardingPageObject extends MainPageObject {
    private static final String
    SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
    START_BUTTON = "org.wikipedia:id/fragment_onboarding_done_button";
    public OnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }
    public void justSkip(){
        this.waitForElementByAndClick(By.id(SKIP_BUTTON), "error: element skip_button - not found", 5);
    }
    public void swipeAndStart(){
        this.swipeScreen(lib.ui.MainPageObject.Direction.LEFT);
        this.swipeScreen(lib.ui.MainPageObject.Direction.LEFT);
        this.swipeScreen(lib.ui.MainPageObject.Direction.LEFT);

        this.waitForElementByAndClick(
                By.id(START_BUTTON),
                "error: element done_button - not found",
                5
        );
    }
    public void clickStartButton(){
        this.waitForElementByAndClick(
                By.id(START_BUTTON),
                "error: element done_button - not found",
                5
        );
    }
}
