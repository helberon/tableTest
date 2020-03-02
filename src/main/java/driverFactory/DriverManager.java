package driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;
    private static String driverName = System.getProperty("driver")==null ? "chrome" : System.getProperty("driver");

    public static WebDriver getDriver(){
        if(driver==null){
            switch (driverName){
                case ("chrome"):
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case ("firefox"):
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return  driver;
    }

    public static void closeDriver(){
        driver.close();
        if (driver != null)
        {
            driver = null;
        }
    }
}