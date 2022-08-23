package pages.modals;

import elements.Input;
import models.Defect;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CreateDefectModal {
    public void fillForm(Defect inputDefect) {
        if (inputDefect.getDefectTitle() != null) {
            $("#title").setValue(inputDefect.getDefectTitle());
        }
        new Input("Actual result").setValue(inputDefect.getActualResult());
    }

    public void createDefectButtonClick() {
        $((By.xpath("//button[text()='Create defect']"))).scrollIntoView(true).click();
    }
}
