import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckEnv {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\WebDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_00_Logo() {
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed());

    }

    @Test
    public void TC_01_Url() {
        AssertJUnit.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
    }

    @Test
    public void TC_02_Logo() {
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

























