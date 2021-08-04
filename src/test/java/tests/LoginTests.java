package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class LoginTests extends TestBase{

    @Test
    public void positiveLogin1(){

        LoginPage loginPage = new LoginPage();

        loginPage.usernameField.sendKeys(ConfigReader.getProperty("username1"));
        loginPage.passwordField.sendKeys(ConfigReader.getProperty("password1"));
        loginPage.loginButton.click();

        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

    }


    @Test
    public void positiveLogin2(){

        LoginPage loginPage = new LoginPage();

        loginPage.login(ConfigReader.getProperty("username2"), ConfigReader.getProperty("password2"));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));




    }



}
