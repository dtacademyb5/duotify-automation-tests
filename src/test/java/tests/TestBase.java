package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;


    @BeforeMethod (alwaysRun = true)
    public void setupMethod(){
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));


    }


    @AfterMethod  (alwaysRun = true)
    public void tearDownMethod(){

        Driver.quitDriver();
    }

}
