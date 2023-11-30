package ru.netology.qa;// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client


import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import org.junit.jupiter.api.*;

import org.openqa.selenium.remote.DesiredCapabilities;

public class UiautomatorTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");

        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void emptyInputTest() {
        var el0 = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys(" ");
        var el2 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        var el3 = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
        Assertions.assertEquals(el0, el3);

    }
    @Test
    public void textNewActivityTest() {

        var el4 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el4.sendKeys("netology");
        var el5 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el5.click();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        var el6 = driver.findElementById("ru.netology.testing.uiautomator:id/text").getText();
        Assertions.assertEquals("netology", el6);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
