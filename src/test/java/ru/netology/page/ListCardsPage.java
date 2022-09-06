package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ListCardsPage {

    private SelenideElement h2 = $("[data-test-id='dashboard']");

    public ListCardsPage() {
        h2.shouldBe(visible);
    }

    public SelenideElement getH2(){
        return h2;
    }


}
