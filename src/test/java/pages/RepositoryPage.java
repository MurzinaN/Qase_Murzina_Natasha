package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RepositoryPage {
    private final static String NAME_LOCATOR = "//div[@data-handler-id]/descendant::div[text()='%s']";

    public void addCaseButtonClick() {
        $(("#create-case-button")).shouldBe(Condition.visible).click();
    }

    public boolean caseIsCreated(String name) {
        return $((By.xpath(String.format(NAME_LOCATOR, name)))).shouldBe(Condition.visible).isDisplayed();
    }

    public void defectsButtonClick() {
        $((By.xpath("//span[text()='Defects']"))).scrollIntoView(true).click();
    }

}
