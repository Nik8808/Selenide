package ru.netology.service;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {
    private String getFutureDate(int addDays, String pattern) {

        String formattedDate = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
        return formattedDate;
    }

    String planningDate = getFutureDate(4, "dd.MM.yyyy");
    String planningDateDay = getFutureDate(4, "d");

    @Test
    void cardTest() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Брэт Пит");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    @Test
    void cardTestChoice() throws InterruptedException{
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Кр");
        $(byText("Краснодар")).click();
        $("[data-test-id='date']").click();
        $(byText(planningDateDay)).click();
        Thread.sleep(2000);
        $("[data-test-id=name] input").setValue("Брэт Пит");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

}
