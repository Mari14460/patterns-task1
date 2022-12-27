package test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import data.DataGenerator;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DataGenerator.Registration.generateUser;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        String locale = "ru";
        DataGenerator.UserInfo validUser = generateUser(locale);
        $("[data-test-id='city'] input").setValue(validUser.getCity());

        int daysToAddForFirstMeeting = 4;
        String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        int daysToAddForSecondMeeting = 7;
        String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        // добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.

        enterDate("[data-test-id='date'] input", firstMeetingDate);

        $("[data-test-id='name'] input").setValue(validUser.getName());

        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

        // перепланирование встречи.
        enterDate("[data-test-id='date'] input", secondMeetingDate);

        $(byText("Запланировать")).click();

        // Перепланировать
        $(".button__content").click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    public static void enterDate(String selector, String date){
        $(selector).click();
        $(selector).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $(selector).sendKeys(date);
    }
}
