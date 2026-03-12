package com.finalsurge.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

public class NegativeTests {
    
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/temp/selenium/chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 15000; // Увеличиваем таймаут
        
        // Открываем страницу регистрации
        open("https://log.finalsurge.com/register.cshtml?page_redirect=%2f");
        
        // Ждем загрузки страницы - проверяем что поле FirstName появилось
        $("#FirstName").shouldBe(visible, enabled);
        System.out.println("✓ Страница регистрации загружена");
    }
    
    @Test
    public void testEmptyForm() {
        // Нажимаем Register ничего не заполняя
        $("input[type='submit']").shouldBe(enabled).click();
        
        // Ждем появления ошибок
        $("#FirstName-error").shouldBe(visible);
        $("#LastName-error").shouldBe(visible);
        $("#Email-error").shouldBe(visible);
        $("#Password-error").shouldBe(visible);
        
        // Проверяем, что ошибки есть
        assertTrue($("#FirstName-error").exists(), "Ошибка для First Name не появилась");
        assertTrue($("#LastName-error").exists(), "Ошибка для Last Name не появилась");
        assertTrue($("#Email-error").exists(), "Ошибка для Email не появилась");
        assertTrue($("#Password-error").exists(), "Ошибка для Password не появилась");
        
        System.out.println("✓ Тест пустой формы пройден");
        sleep(2000);
    }
    
    @Test
    public void testInvalidEmail() {
        // Заполняем форму с невалидным email
        $("#FirstName").shouldBe(visible).setValue("Test");
        $("#LastName").setValue("User");
        $("#Email").setValue("invalid-email");
        $("#Password").setValue("Test123");
        $("#ConfirmPassword").setValue("Test123");
        $("#Terms").click();
        
        // Отправляем форму
        $("input[type='submit']").shouldBe(enabled).click();
        
        // Проверяем ошибку email
        $("#Email-error").shouldBe(visible);
        assertTrue($("#Email-error").getText().contains("valid"), 
                  "Текст ошибки не содержит 'valid': " + $("#Email-error").getText());
        
        System.out.println("✓ Тест невалидного email пройден");
        sleep(2000);
    }
    
    @Test
    public void testShortPassword() {
        // Заполняем форму с коротким паролем
        $("#FirstName").shouldBe(visible).setValue("Test");
        $("#LastName").setValue("User");
        $("#Email").setValue("test@test.com");
        $("#Password").setValue("123");
        $("#ConfirmPassword").setValue("123");
        $("#Terms").click();
        
        // Отправляем форму
        $("input[type='submit']").shouldBe(enabled).click();
        
        // Проверяем ошибку пароля
        $("#Password-error").shouldBe(visible);
        assertTrue($("#Password-error").getText().contains("at least") || 
                  $("#Password-error").getText().contains("minimum"),
                  "Текст ошибки не соответствует: " + $("#Password-error").getText());
        
        System.out.println("✓ Тест короткого пароля пройден");
        sleep(2000);
    }
    
    @Test
    public void testPasswordMismatch() {
        // Заполняем форму с разными паролями
        $("#FirstName").shouldBe(visible).setValue("Test");
        $("#LastName").setValue("User");
        $("#Email").setValue("test@test.com");
        $("#Password").setValue("Test123");
        $("#ConfirmPassword").setValue("Test456");
        $("#Terms").click();
        
        // Отправляем форму
        $("input[type='submit']").shouldBe(enabled).click();
        
        // Проверяем ошибку подтверждения пароля
        $("#ConfirmPassword-error").shouldBe(visible);
        assertTrue($("#ConfirmPassword-error").getText().contains("match"), 
                  "Текст ошибки не содержит 'match': " + $("#ConfirmPassword-error").getText());
        
        System.out.println("✓ Тест несовпадения паролей пройден");
        sleep(2000);
    }
    
    @Test
    public void testTermsNotChecked() {
        // Заполняем форму без согласия с правилами
        $("#FirstName").shouldBe(visible).setValue("Test");
        $("#LastName").setValue("User");
        $("#Email").setValue("test@test.com");
        $("#Password").setValue("Test123");
        $("#ConfirmPassword").setValue("Test123");
        // Не кликаем на Terms
        
        // Отправляем форму
        $("input[type='submit']").shouldBe(enabled).click();
        
        // Проверяем ошибку Terms
        $("#Terms-error").shouldBe(visible);
        assertTrue($("#Terms-error").getText().contains("Terms"), 
                  "Текст ошибки не содержит 'Terms': " + $("#Terms-error").getText());
        
        System.out.println("✓ Тест без согласия с правилами пройден");
        sleep(2000);
    }
    
    @Test
    public void testLongName() {
        // Генерируем длинное имя (300 символов)
        String longName = "A".repeat(300);
        
        // Заполняем форму с длинным именем
        $("#FirstName").shouldBe(visible).setValue(longName);
        $("#LastName").setValue("User");
        $("#Email").setValue("test@test.com");
        $("#Password").setValue("Test123");
        $("#ConfirmPassword").setValue("Test123");
        $("#Terms").click();
        
        // Отправляем форму
        $("input[type='submit']").shouldBe(enabled).click();
        
        // Проверяем общую ошибку
        $(".validation-summary-errors").shouldBe(visible);
        
        System.out.println("✓ Тест длинного имени пройден");
        sleep(2000);
    }
}