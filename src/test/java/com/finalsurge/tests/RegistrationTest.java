package com.finalsurge.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    
    @Test
    public void testRegistration() {
        // Настройки
        System.setProperty("webdriver.chrome.driver", "C:/temp/selenium/chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 15000;
        
        // Открываем страницу регистрации напрямую
        open("https://log.finalsurge.com/register.cshtml?page_redirect=%2f");
        System.out.println("Страница регистрации открыта: " + title());
        
        // Ждем 10 секунд
        sleep(10000);
    }
}