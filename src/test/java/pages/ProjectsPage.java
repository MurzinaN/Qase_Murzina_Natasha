package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsPage {
    public boolean ProjectsPageIsOpen() {
        return $("#createButton").isDisplayed();
    }

    public void openProject() {
        $(By.xpath("//a[text()='sharelane']")).shouldBe(Condition.visible).click();
    }
}
