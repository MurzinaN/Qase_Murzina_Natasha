package pages.modals;

import elements.Input;
import models.defect.Defect;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CreateDefectModal {
    public void fillForm(Defect inputDefect) {
        if (inputDefect.getTitle() != null) {
            $("#title").setValue(inputDefect.getTitle());
        }
        new Input("Actual result").setValue(inputDefect.getActual_result());
    }

    public void createDefectButtonClick() {
        $((By.xpath("//button[text()='Create defect']"))).scrollIntoView(true).click();
    }
}
