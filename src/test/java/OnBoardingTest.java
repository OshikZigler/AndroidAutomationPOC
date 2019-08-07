import PageObject.OnBoardingScreen;
import PageObject.SignUpScreen;
import PageObject.YourHouzzScreen;
import PageObject.Base;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;

public class OnBoardingTest extends Base {

    private OnBoardingScreen onBoardingScreen;
    private SignUpScreen signUpScreen;
    private YourHouzzScreen yourHouzzScreen;


    @BeforeSuite
    public void setUp() throws MalformedURLException {
        getDriver();
        onBoardingScreen = new OnBoardingScreen(driver);
        signUpScreen = new SignUpScreen(driver);
        yourHouzzScreen = new YourHouzzScreen(driver);
    }

    @Test
    public void ValidationTest() throws IOException, SAXException, ParserConfigurationException {

        onBoardingScreen.validateScreen();

        //Check why this method return checkNotNull error
        //onBoardingScreen.clickOnSignIn();

        onBoardingScreen.clickOnGetStartedBtn();
        clickOnBackButton();
        onBoardingScreen.clickOnGetStartedBtn();
        signUpScreen.validateScreen();
        signUpScreen.signUpWithNoData();
        signUpScreen.signUpWithoutPassword();
        signUpScreen.signUpWithExistingEmail();
        signUpScreen.signUpWithRegisteredUser();
        yourHouzzScreen.verifySignedIn();
    }


    @AfterSuite
    public void tearDown() {
        driver.quit();
    }


}
