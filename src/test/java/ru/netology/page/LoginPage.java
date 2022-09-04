package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");

    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public void enterData(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public VerificationPage enterValidAuthInfo(DataHelper.AuthInfo info) {
        enterData(info);
        return new VerificationPage();
    }

    public LoginPage enterInvalidAuthInfo(DataHelper.AuthInfo info) {
        enterData(info);
        return new LoginPage();
    }

}