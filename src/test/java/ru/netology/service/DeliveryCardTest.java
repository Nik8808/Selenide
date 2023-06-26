package ru.netology.service;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCardTest {
    LocalDate date = new LocalDate();
    @Test
    void cardTest() throws InterruptedException {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id=date] input").doubleClick().setValue(planningDate);
        $("[data-test-id=name] input").setValue("Брэт Пит");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
        Thread.sleep(2000);
    }

    @Test
    void cardTestChoice() throws InterruptedException {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Кр");
        $(byText("Краснодар")).click();
        $("[data-test-id='date']").click();
        $("[data-step='1']").click();
        $("[data-day='1690657200000']").click();
        Thread.sleep(2000);
        $("[data-test-id=name] input").setValue("Брэт Пит");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }

}
