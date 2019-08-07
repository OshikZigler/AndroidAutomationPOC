package PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SignUpScreen extends Base {

    //Constructor
    public SignUpScreen(AndroidDriver<AndroidElement> driver) {
        //this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Elements
    @AndroidFindBy(id = "com.houzz.app:id/createAccount")
    public AndroidElement createAnAccountTitle;

    @AndroidFindBy(id = "com.houzz.app:id/sign_in_title")
    public AndroidElement signInTitle;

    @AndroidFindBy(id = "com.houzz.app:id/signinWithFacebookButton")
    public AndroidElement signinWithFacebookButton;

    @AndroidFindBy(id = "com.houzz.app:id/signinWithGoogleButton")
    public AndroidElement signinWithGoogleButton;

    @AndroidFindBy(id = "com.houzz.app:id/userEmail")
    public AndroidElement userEmail;

    @AndroidFindBy(id = "com.houzz.app:id/userPassword")
    public AndroidElement userPassword;

    @AndroidFindBy(id = "com.houzz.app:id/text_input_password_toggle")
    public AndroidElement showPassword;

    @AndroidFindBy(id = "com.houzz.app:id/passwordRequirementMessage")
    public AndroidElement passwordRequirementMessage;

    @AndroidFindBy(id = "com.houzz.app:id/forgotPasswordButton")
    public AndroidElement forgotPasswordButton;

    @AndroidFindBy(id = "com.houzz.app:id/signUp")
    public AndroidElement signUpButton;

    @AndroidFindBy(id = "com.houzz.app:id/signIn")
    public AndroidElement signInButton;

    @AndroidFindBy(id = "com.houzz.app:id/signInButton")
    public AndroidElement haveAnAccount;

    @AndroidFindBy(id = "com.google.android.gms:id/credential_picker_layout")
    public AndroidElement googleCredentialPicker;

    @AndroidFindBy(id = "com.google.android.gms:id/cancel")
    public AndroidElement dismissGoogleCredentialPicker;

    @AndroidFindBy(id = "com.google.android.gms:id/credential_save_confirmation")
    public AndroidElement googleSmartLock;

    @AndroidFindBy(id = "com.google.android.gms:id/credential_save_reject")
    public AndroidElement dismissGoogleSmartLock;

    @AndroidFindBy(id = "com.houzz.app:id/header")
    public AndroidElement emailTakenDialogHeader;

    @AndroidFindBy(id = "com.houzz.app:id/content")
    public AndroidElement emailTakenDialogContent;

    @AndroidFindBy(id = "android:id/button1")
    public AndroidElement dialogOKButton;

    @AndroidFindBy(id = "com.houzz.app:id/textinput_error")
    public AndroidElement emailErrorMessage;


    //Methods

    public void validateScreen() throws ParserConfigurationException, SAXException, IOException {
        verifyElementsAreDisplayed();
        verifyElementsContent();
    }

    public void verifyElementsAreDisplayed() {
        verifyElementIsDisplayed(createAnAccountTitle);
        verifyElementIsDisplayed(signinWithFacebookButton);
        verifyElementIsDisplayed(signinWithGoogleButton);
        verifyElementIsDisplayed(userEmail);
        verifyElementIsDisplayed(userPassword);
        verifyElementIsDisplayed(showPassword);
        verifyElementIsDisplayed(passwordRequirementMessage);
        verifyElementIsDisplayed(signUpButton);
        verifyElementIsDisplayed(haveAnAccount);
    }

    public void verifyElementsContent() throws IOException, ParserConfigurationException, SAXException {
        verifyElementContent(createAnAccountTitle, getData("CreateAnAccountTitle"));
        verifyElementContent(userEmail, getData("Email"));
        verifyElementContent(userPassword, getData("Password"));
        verifyElementContent(passwordRequirementMessage, getData("PasswordRequirementMessage"));
        verifyElementContent(signUpButton, getData("SignUp"));
        //verifyElementContent(haveAnAccount, getData("HaveAccountSignInText"));
    }

    public void signUpWithNoData() throws IOException, SAXException, ParserConfigurationException {
        signUpButton.click();
        verifyElementIsDisplayed(emailErrorMessage);
        verifyElementContent(emailErrorMessage, getData("NoEmail"));
    }

    public void signUpWithoutPassword() throws IOException, ParserConfigurationException, SAXException {
        userEmail.click();
        dismissGoogleCredentialPicker();
        userEmail.clear();
        userEmail.sendKeys(getData("falseTestEmailProd"));
        signUpButton.click();
        verifyElementIsDisplayed(passwordRequirementMessage);

    }


    public void signUpWithExistingEmail() throws IOException, ParserConfigurationException, SAXException {
        userEmail.click();
        userEmail.clear();
        userEmail.sendKeys(getData("trueTestEmailProd"));
        userPassword.click();
        userPassword.clear();
        userPassword.sendKeys(getData("falseTestPasswordProd"));
        signUpButton.click();
        verifyElementIsDisplayed(emailTakenDialogHeader);
        verifyElementIsDisplayed(emailTakenDialogContent);
        verifyElementContent(emailTakenDialogHeader, getData("EmailAlreadyTakenHeader"));
        verifyElementContent(emailTakenDialogContent, getData("EmailAlreadyTakenContent"));
        dialogOKButton.click();
    }

    public void signUpWithRegisteredUser() throws IOException, ParserConfigurationException, SAXException {
        userEmail.click();
        userEmail.clear();
        userEmail.sendKeys(getData("trueTestEmailProd"));
        userPassword.click();
        userPassword.clear();
        userPassword.sendKeys(getData("trueTestPasswordProd"));
        signUpButton.click();
        dismissSmartLock();
    }

    public void dismissGoogleCredentialPicker() {

        try {
            if (googleCredentialPicker.isDisplayed()) {
                dismissGoogleCredentialPicker.click();
            }
        } catch (Exception e) {
        }
    }

    public void dismissSmartLock() {
        try {
            if (googleSmartLock.isDisplayed()) {
                dismissGoogleSmartLock.click();
            }
        } catch (Exception e) {
        }

    }

}
