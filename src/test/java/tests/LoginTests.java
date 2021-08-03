package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class LoginTests {


    WebDriver driver;


    @BeforeMethod
    public void setupMethod(){
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
    }


    @AfterMethod
    public void tearDownMethod(){
       Driver.quit();
    }


    @Test
    public void positiveLogin1(){

        LoginPage loginPage = new LoginPage();

        loginPage.usernameField.sendKeys(ConfigReader.getProperty("username1"));
        loginPage.passwordField.sendKeys(ConfigReader.getProperty("password1"));
        loginPage.loginButton.click();

    }


    @Test
    public void positiveLogin2(){

        LoginPage loginPage = new LoginPage();

        loginPage.login(ConfigReader.getProperty("username2"), ConfigReader.getProperty("password2"));




    }



}
