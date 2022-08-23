package pages.modals;

import elements.Input;
import models.TestCase;

import static com.codeborne.selenide.Selenide.$;

public class CreateTestCaseModal {
    public void fillForm(TestCase inputTestCase) {
        if (inputTestCase.getTitle() != null) {
            $("#title").setValue(inputTestCase.getTitle());
        }
        new Input("Description").setValue(inputTestCase.getDescription());
        new Input("Pre-conditions").setValue(inputTestCase.getPreConditions());
        new Input("Post-conditions").setValue(inputTestCase.getPostConditions());

    }

    public void saveButtonClick() {
        $(("#save-case")).click();
    }
}
