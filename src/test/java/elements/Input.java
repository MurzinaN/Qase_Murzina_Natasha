package elements;


import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;


public class Input extends BaseElement {
    private final static String INPUT_LOCATOR = "//label[text()='%s']/parent::div/descendant::p[@data-placeholder]";

    public Input(String label) {
        super(label);
    }

    public void setValue(String value) {
        if (Objects.nonNull(value)) {
            $(By.xpath(String.format(INPUT_LOCATOR, label)))
                    .scrollIntoView(true)
                    .setValue(value);
        }
    }
}
