package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class SignUpTests extends TestBase {



    @Test (groups = {"smoke"})
    public void signUp(){

        new LoginPage().signUpLink.click();

        SignUpPage signUpPage = new SignUpPage();
        Faker fake = new Faker();

        signUpPage.usernameField.sendKeys(fake.name().username());
        signUpPage.firstName.sendKeys(fake.name().firstName());
        signUpPage.lastName.sendKeys(fake.name().lastName());
        String email = fake.internet().emailAddress();
        signUpPage.email.sendKeys(email);
        signUpPage.email2.sendKeys(email);

        String pass = fake.internet().password();
        signUpPage.password.sendKeys(pass);
        signUpPage.password2.sendKeys(pass);
        signUpPage.registerButton.click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));




    }


}
