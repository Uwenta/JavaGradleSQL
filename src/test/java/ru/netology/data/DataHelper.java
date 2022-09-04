package ru.netology.data;

import com.github.javafaker.Code;
import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getWrongAuthInfo() {
        return new AuthInfo(faker.internet().domainName(), getRandomPassword());
    }
    @Value
    public static class RandomPassword {
        private String password;
    }

    public static String getRandomPassword(){
        return faker.internet().password();
    }

    public static AuthInfo getAuthInfoWithWrongPassword(){
        return new AuthInfo(getAuthInfo().getLogin(), getRandomPassword());
    }


    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getWrongVerificationCode() {
        return new VerificationCode(Integer.toString(faker.number().numberBetween(100000, 999999)));
    }




}
