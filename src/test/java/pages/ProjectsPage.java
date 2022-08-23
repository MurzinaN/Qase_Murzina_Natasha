package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsPage {
    private final static String PROJECT_NAME_LOCATOR = "//a[text()='%s']";
    public boolean ProjectsPageIsOpen() {
        return $("#createButton").isDisplayed();
    }

    public void openProject(String projectName) {
        $(By.xpath(String.format(PROJECT_NAME_LOCATOR, projectName))).shouldBe(Condition.visible).click();
    }
}
