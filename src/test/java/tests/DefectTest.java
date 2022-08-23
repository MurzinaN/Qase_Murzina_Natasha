package tests;

import models.Defect;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DefectPage;
import pages.ProjectsPage;
import pages.RepositoryPage;
import pages.modals.CreateDefectModal;

public class DefectTest extends BaseTest {
    private pages.ProjectsPage ProjectsPage;
    private RepositoryPage RepositoryPage;
    private DefectPage DefectPage;
    private CreateDefectModal CreateDefectModal;


    @BeforeClass
    public void initialise() {
        ProjectsPage = new ProjectsPage();
        RepositoryPage = new RepositoryPage();
        DefectPage = new DefectPage();
        CreateDefectModal = new CreateDefectModal();
    }

    @Test
    public void newTestCaseTest() {
        LoginPage.login(USERNAME, PASSWORD);
        ProjectsPage.openProject(PROJECT_NAME);
        RepositoryPage.defectsButtonClick();
        DefectPage.addDefectButtonClick();
        Defect newDefect = Defect.builder()
                .defectTitle(faker.name().username())
                .actualResult(faker.name().username())
                .build();
        CreateDefectModal.fillForm(newDefect);
        CreateDefectModal.createDefectButtonClick();
        Assert.assertTrue(DefectPage.defectIsCreated(newDefect.getDefectTitle()), String.format("On Defect page should be defect with name %s", newDefect.getDefectTitle()));
    }
}
