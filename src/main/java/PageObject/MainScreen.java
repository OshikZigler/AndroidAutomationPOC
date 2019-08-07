package PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MainScreen extends Base {


    public MainScreen() {

    }

    //Constructor
    public MainScreen(AndroidDriver<AndroidElement> driver) {
        //this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Elements
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc=\"MainBottomVBarScreen\"]/android.widget.LinearLayout/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.ImageButton")
    public AndroidElement profileButton;

    @AndroidFindBy(id = "com.houzz.app:id/toolbar")
    public AndroidElement topBar;

    //Methods

    public void clickOnProfile(){
        profileButton.click();
    }



}