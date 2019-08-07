package PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class YourHouzzScreen extends Base {

    //Constructor
    public YourHouzzScreen(AndroidDriver<AndroidElement> driver) {
        //this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc=\"MainBottomVBarScreen\"]/android.widget.LinearLayout/android.widget.FrameLayout[4]")
    public AndroidElement profileButton;

    @AndroidFindBy(id = "com.houzz.app:id/userName")
    AndroidElement userName;


    public void verifySignedIn() {
        profileButton.click();
        verifyElementIsDisplayed(userName);
    }
}
