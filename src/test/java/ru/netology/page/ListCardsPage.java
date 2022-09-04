package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ListCardsPage {

    private SelenideElement h2 = $("[data-test-id='dashboard']");

    public ListCardsPage() {
        h2.shouldBe(visible);
    }
}
