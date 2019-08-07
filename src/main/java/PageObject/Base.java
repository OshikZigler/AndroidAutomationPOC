package PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Base {

    public AndroidDriver<AndroidElement> driver;


    //Setting configurations file
    public static String readProperty(String property) {

        Properties properties;
        String value = null;

        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File("/Users/oshikzigler/OshikWorkspace/Houzz/config")));

            value = properties.getProperty(property);

            if (value == null) {
                throw new Exception(("value not set or empty"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    //Setting up Desired Capabilities and Android Driver
    public void getDriver() throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, readProperty("emulator.android.version"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, readProperty("emulator.android.name"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(MobileCapabilityType.APP, new File("/Users/oshikzigler/Documents/Automation/Test_APK/HouzzApp_27102.apk"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, readProperty("houzz.app.package"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, readProperty("houzz.app.activity"));

        // Initialize driver
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    //Setting data xml file
    public static String getData(String nodeName) throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File("/Users/oshikzigler/OshikWorkspace/Houzz/src/main/java/Utils/Strings.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    //Commonly used method - Verify Element Is Displayed
    public void verifyElementIsDisplayed(AndroidElement element) {

        try {
            Assert.assertTrue(element.isDisplayed(), "Element is not displayed");
        } catch (Exception e) {
            fail("Element doesn't exist," + e.getMessage());
        }
    }

    //Commonly used method - Verify Element Content
     public void verifyElementContent(AndroidElement element, String expectedValue) {

        // This test fails only for Strings with spannable text like with "Sign in" or "Terms of use"
        // https://stackoverflow.com/questions/36324452/assertequalsstring-string-comparisonfailure-when-contents-are-identical

        try {
            String actualValue = element.getText();
            actualValue = actualValue.replaceAll("[\r\n]+", " ");

            Assert.assertEquals(actualValue, expectedValue, "Element content is wrong");

        } catch (Exception e) {
            fail("Element doesn't exist");
        }

    }


    //Commonly used method - Click on device back button
    public void clickOnBackButton() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }


}
