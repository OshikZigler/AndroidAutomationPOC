package PageObject;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class OnBoardingScreen extends Base {

    //Constructor
    public OnBoardingScreen(AndroidDriver<AndroidElement> driver) {
        //this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Elements
    @AndroidFindBy(id = "com.houzz.app:id/skip")
    public AndroidElement skipButton;
    @AndroidFindBy()

    @AndroidFindBy(id = "com.houzz.app:id/unlock_Houzz_Experience")
    public AndroidElement houzzExperienceMessage;

    @AndroidFindBy(id = "com.houzz.app:id/getStarted")
    public AndroidElement getStartedButton;

    @AndroidFindBy(id = "com.houzz.app:id/logIn")
    public AndroidElement loginButton;

    @AndroidFindBy(id = "com.houzz.app:id/termsOfUse")
    public AndroidElement TOS;


    //Methods
    public void skip() {
        skipButton.click();
    }

    public void clickOnGetStartedBtn() {
        getStartedButton.click();
    }


    public void validateScreen() throws ParserConfigurationException, SAXException, IOException {
        verifyElementsAreDisplayed();
        verifyElementsContent();
    }

    public void verifyElementsAreDisplayed() {
        verifyElementIsDisplayed(houzzExperienceMessage);
        verifyElementIsDisplayed(getStartedButton);
        verifyElementIsDisplayed(loginButton);
        verifyElementIsDisplayed(TOS);
    }


    public void verifyElementsContent() throws IOException, ParserConfigurationException, SAXException {
        verifyElementContent(houzzExperienceMessage, getData("OnBoardingWelcomeText"));
        verifyElementContent(getStartedButton, getData("GetStartedButtonText"));
        //verifyElementContent(loginButton , getData("SignInButtonText"));
        //verifyElementContent(TOS , getData("TearmsOfUseText"));
    }

    public void clickOnSignIn() {
        TouchAction touchAction = new TouchAction(driver);
        Point center = loginButton.getCenter();
        int width = center.getX();
        int height = center.getY();
        ;
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(loginButton, width + 100, height + 20))).perform();

    }


}





