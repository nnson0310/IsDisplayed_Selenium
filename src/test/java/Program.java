import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Program {

    WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://ericsson.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        Thread.sleep(5000);
    }

    @Test
    public void TC_00_IsDisplayedWorks() throws InterruptedException {

        driver.findElement(By.cssSelector("a#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

        driver.findElement(By.cssSelector("button.sticky-share-fn__toggle")).click();

        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.sticky-share-fn__items")).isDisplayed());

        driver.findElement(By.cssSelector("button.sticky-share-fn__toggle")).click();

        Thread.sleep(1000);

        Assert.assertFalse(driver.findElement(By.cssSelector("div.sticky-share-fn__items")).isDisplayed());
    }

    @Test
    public void TC_01_IsDisplayedNotWork() throws InterruptedException {

        driver.findElement(By.cssSelector("button.search__toggle-btn")).click();

        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#SearchPageInputField")).isDisplayed());

        driver.findElement(By.cssSelector("button.close")).click();

        Thread.sleep(1000);

        Assert.assertFalse(driver.findElement(By.cssSelector("div#search-input")).isDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }
}
