package com.finalsurge.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class FindTextTest {
    
    @Test
    public void findAllText() {
        System.setProperty("webdriver.chrome.driver", "C:/temp/selenium/chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        
        // Открываем сайт
        open("https://log.finalsurge.com");
        System.out.println("Сайт открыт: " + title());
        
        // Находим все ссылки и их текст
        System.out.println("\n=== ВСЕ ССЫЛКИ ===");
        $$("a").forEach(link -> {
            String text = link.getText();
            if (text != null && !text.isEmpty()) {
                System.out.println("Текст: '" + text + "'");
            }
        });
        
        // Ждем 10 секунд
        sleep(10000);
    }
}