package com.finalsurge.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllTests {
    
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/temp/selenium/chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 15000;
        
        // Открываем страницу регистрации
        open("https://log.finalsurge.com/register.cshtml?page_redirect=%2f");
        
        // Ждем загрузки страницы
        $("#create_first").shouldBe(visible);
        System.out.println("✓ Страница регистрации загружена");
    }
    
    @Test
    public void testPageLoaded() {
        assertTrue(title().contains("Create New Account"), "Заголовок не соответствует");
        System.out.println("✓ Тест загрузки страницы пройден");
    }
    
    @Test
    public void testEmptyForm() {
        // Нажимаем кнопку по тексту
        $(byText("Create New Account")).shouldBe(enabled).click();
        
        // Ждем появления ошибок
        sleep(2000);
        
        // Проверяем текст ошибок
        String firstNameError = $("label.error[for='create_first']").getText();
        assertEquals("This field is required.", firstNameError, "Неверный текст ошибки для First Name");
        
        String lastNameError = $("label.error[for='create_last']").getText();
        assertEquals("This field is required.", lastNameError, "Неверный текст ошибки для Last Name");
        
        System.out.println("✓ Тест пустой формы пройден");
    }
    
    @Test
    public void testInvalidEmail() {
        // Заполняем форму
        $("#create_first").setValue("Test");
        $("#create_last").setValue("User");
        $("#create_email").setValue("invalid-email");
        $("#password_meter").setValue("Test123");
        $("#create_passwordmatch").setValue("Test123");
        
        // Нажимаем кнопку
        $(byText("Create New Account")).click();
        
        // Ждем появления ошибок
        sleep(2000);
        
        // Проверяем текст ошибки
        String emailError = $("label.error[for='create_email']").getText();
        assertEquals("Please enter a valid email address.", emailError, "Неверный текст ошибки для Email");
        
        System.out.println("✓ Тест невалидного email пройден");
    }
}