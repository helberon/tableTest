import driverFactory.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import table.KyivTrainsTable;

import java.util.List;

public class TestCase {
    private WebDriver driver;

    @Before
    public void setupPage() {
        driver = DriverManager.getDriver();
        driver.get("http://poezdato.net/raspisanie-po-stancyi/kiev-pass/");
    }

    @Test
    public void badLoginWithAppropriateEmailFormat() {
       List<KyivTrainsTable> list= new KyivTrainsTable().getAllItems(driver);
        System.out.println(list);
    }

    @After
    public void clearEnv() {
        DriverManager.closeDriver();
    }
}
