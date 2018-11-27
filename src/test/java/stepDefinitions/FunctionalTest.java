package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FunctionalTest {
    protected static WebDriver driver;

    public WebDriver getDriver(){
        if (driver==null){
            System.out.println("--------------------------------SET UP -------------------------");
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\joeye\\Documents\\Automation Test\\geckodriver.exe");
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
        }
        return driver;
    }

/*
    protected static WebDriver driver;

    @Before
    public static void setUp() {
        System.out.println("--------------------------------SET UP -------------------------");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\joeye\\Documents\\Automation Test\\geckodriver.exe");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

*/
}