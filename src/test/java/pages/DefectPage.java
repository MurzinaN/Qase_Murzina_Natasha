package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DefectPage {
    private final static String NAME_LOCATOR = "//a[@class='defect-title' and text()='%s']";

    public void addDefectButtonClick() {
        $((By.xpath("//a[text()='Create new defect']"))).shouldBe(Condition.visible).click();
    }

    public boolean defectIsCreated(String name) {
        return $((By.xpath(String.format(NAME_LOCATOR, name)))).shouldBe(Condition.visible).isDisplayed();
    }
}
