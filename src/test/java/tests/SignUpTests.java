package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.CSVReader;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class SignUpTests {


    protected WebDriver driver;

    protected static ExtentReports reporter;
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentTest logger;



    @BeforeSuite (alwaysRun = true)
    public void setupReport(){

        reporter =  new ExtentReports();
        String path =  System.getProperty("user.dir") + "/test-output/extentReports/index.html";
        htmlReporter = new ExtentSparkReporter(path);
        htmlReporter.config().setReportName("DUOTIFY AUTOMATION TESTS");

        reporter.attachReporter(htmlReporter);

        // Configuration settings
        reporter.setSystemInfo("Tester", "John Doe");
        reporter.setSystemInfo("Environment", "TEST_ENV");
        reporter.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
    }





    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setupMethod(@Optional String browser, Method method){

        driver = Driver.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));

        logger = reporter.createTest("TEST CASE: " + method.getName());


    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result){

        if(result.getStatus()==ITestResult.SUCCESS){
            logger.pass("PASSED: "  + result.getName());
        }else if(result.getStatus()==ITestResult.SKIP){
            logger.skip("SKIPPED: "  +result.getName());
        }else if(result.getStatus()==ITestResult.FAILURE){
            logger.fail("FAILED: "  +result.getName());
            logger.fail(result.getThrowable());

            String path = SeleniumUtils.getScreenshot("failureScreenshot");
            logger.addScreenCaptureFromPath(path);



        }




        Driver.quitDriver();
    }


    @AfterSuite(alwaysRun = true)
    public void tearDownReport(){
        reporter.flush();
    }





    @Test (groups = {"smoke"})
    public void signUp(){

        new LoginPage().signUpLink.click();

        SignUpPage signUpPage = new SignUpPage();
        Faker fake = new Faker();
        logger.info("Entering the username");
        signUpPage.usernameField.sendKeys(fake.name().username());
        logger.info("Entering the first name");
        signUpPage.firstName.sendKeys(fake.name().firstName());
        logger.info("Entering the last name");
        signUpPage.lastName.sendKeys(fake.name().lastName());
        logger.info("Entering the email");
        String email = fake.internet().emailAddress();

        signUpPage.email.sendKeys(email);

        logger.info("Re-Entering the email");
        signUpPage.email2.sendKeys(email);

        String pass = fake.internet().password();
        logger.info("Entering the password");

        signUpPage.password.sendKeys(pass);
        logger.info("Re-Entering the password");

        signUpPage.password2.sendKeys(pass);
        signUpPage.registerButton.click();
        logger.info("Waiting till url becomes the expected");

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

        logger.info("Verifying the url is as expected");

        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));




    }

    @Test (dataProvider = "getData")
    public void signUpUsingCSV(String username,String firstName, String lastName, String email, String password ){

        new LoginPage().signUpLink.click();

        SignUpPage signUpPage = new SignUpPage();



        signUpPage.usernameField.sendKeys(username);
        logger.info("Entering the first name");
        signUpPage.firstName.sendKeys(firstName);
        logger.info("Entering the last name");
        signUpPage.lastName.sendKeys(lastName);
        logger.info("Entering the email");


        signUpPage.email.sendKeys(email);

        logger.info("Re-Entering the email");
        signUpPage.email2.sendKeys(email);


        logger.info("Entering the password");

        signUpPage.password.sendKeys(password);
        logger.info("Re-Entering the password");

        signUpPage.password2.sendKeys(password);
        signUpPage.registerButton.click();
        logger.info("Waiting till url becomes the expected");

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlToBe("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));

        logger.info("Verifying the url is as expected");

        Assert.assertTrue(driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?"));




    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return CSVReader.readData("MOCK_DATA.csv");
    }





}
