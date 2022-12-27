package data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        // LocalDate.now() = 12/26/2022 20:54:15.567
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCityFromArray() {
        String cites [] = {"Москва", "Санкт-Петербург", "Омск", "Великий Новгород", "Волгоград", "Рязань", "Псков", "Иркутск", "Владивосток", "Самара", "Вологда", "Смоленск"};
        // с помощью Faker, либо используя массив валидных городов и класс Random
        return cites[new Random().nextInt(cites.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        // использовать Faker
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        // использовать Faker
        // +7 (123) 123-45-67
        return faker.phoneNumber().phoneNumber().replace("(","").replace(")","").replace("-","");
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCityFromArray(), generateName(locale), generatePhone(locale));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}