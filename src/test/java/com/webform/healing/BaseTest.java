package com.webform.healing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.epam.healenium.SelfHealingDriver;
import com.webform.healing.utils.DriverHolder;

import io.github.bonigarcia.wdm.WebDriverManager;

import static com.webform.healing.utils.DriverHolder.setDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Listeners;
import com.webform.healing.TestListener;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver delegate;
    public SelfHealingDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        delegate = new ChromeDriver();
        driver = SelfHealingDriver.create(delegate);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        DriverHolder.setDriver(driver);
        driver.navigate().to(getClass().getClassLoader().getResource("form-web/index.html"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();   
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) ((SelfHealingDriver) driver).getDelegate()).getScreenshotAs(OutputType.BYTES);
   }
}