package com.finalsurge.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public abstract class BaseTest {

    @BeforeAll
    public static void globalSetup() {
        // Для локального запуска
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "C:/temp/selenium/chromedriver.exe");
        }
        
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.timeout = 10000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.reportsFolder = "target/allure-results";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    public void addAllureListener() {
        SelenideLogger.addListener("AllureSelenide", 
            new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));
    }

    @AfterEach
    public void removeAllureListener() {
        SelenideLogger.removeListener("AllureSelenide");
    }
}