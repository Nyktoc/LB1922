package com.finalsurge.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class OpenSiteTest {
    
    @Test
    public void openSite() {
        // Настройки
        System.setProperty("webdriver.chrome.driver", "C:/temp/selenium/chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        
        // Открываем сайт
        open("https://log.finalsurge.com");
        
        // Ждем 5 секунд
        System.out.println("Сайт открыт: " + title());
        sleep(5000);
    }
}