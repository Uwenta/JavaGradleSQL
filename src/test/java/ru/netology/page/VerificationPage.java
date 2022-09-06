package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement error = $("[data-test-id='error-notification']");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void enterCode(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
    }

    public ListCardsPage validVerify(DataHelper.VerificationCode verificationCode) {
        enterCode(verificationCode);
        return new ListCardsPage();
    }

    public SelenideElement invalidVerify(DataHelper.VerificationCode verificationCode) {
        enterCode(verificationCode);
        return error;
    }

}


