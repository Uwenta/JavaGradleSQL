package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class DbInteractionDbUtilsTest {
    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @AfterAll
    static void setDown(){
        SQLHelper.cleanDB();
    }

    @Test //зарегистрированный пользователь и валидный код
    void validVerificationTest() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.enterValidAuthInfo(authInfo);
        var verificationCode = SQLHelper.getCode();
        var listCardPage = verificationPage.validVerify(verificationCode);
    }

    @Test //незарегистрированный пользователь
    void invalidAuthInfoWithNotRegisteredUser() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getWrongAuthInfo();
        loginPage.enterInvalidAuthInfo(authInfo);
        loginPage.searchErrorWithText("Неверно указан логин или пароль");
    }

    @Test //зарегистрированный пользователь и неверный пароль
    void invalidAuthInfoWithRegisteredUserAndWrongPassword() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoWithWrongPassword();
        loginPage.enterInvalidAuthInfo(authInfo);
        loginPage.searchErrorWithText("Неверно указан логин или пароль");
    }

    @Test //зарегистрированный пользователь и неверный код верификации
    void invalidVerificationTest(){
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.enterValidAuthInfo(authInfo);
        var verificationCode = DataHelper.getWrongVerificationCode();
        verificationPage.invalidVerify(verificationCode);
        verificationPage.searchErrorWithText("Неверно указан код");
    }


}