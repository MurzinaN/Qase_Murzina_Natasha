package tests;

import models.defect.Defect;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DefectPage;
import pages.ProjectsPage;
import pages.RepositoryPage;
import pages.modals.CreateDefectModal;

public class DefectTest extends BaseTest {
    private ProjectsPage projectsPage;
    private RepositoryPage repositoryPage;
    private DefectPage defectPage;
    private CreateDefectModal createDefectModal;


    @BeforeClass
    public void initialise() {
        projectsPage = new ProjectsPage();
        repositoryPage = new RepositoryPage();
        defectPage = new DefectPage();
        createDefectModal = new CreateDefectModal();
    }

    @Test
    public void newTestCaseTest() {
        loginPage.login(USERNAME, PASSWORD);
        projectsPage.openProject(PROJECT_NAME);
        repositoryPage.defectsButtonClick();
        defectPage.addDefectButtonClick();
        Defect newDefect = Defect.builder()
                .title(faker.name().username())
                .actual_result(faker.name().username())
                .build();
        createDefectModal.fillForm(newDefect);
        createDefectModal.createDefectButtonClick();
        Assert.assertTrue(defectPage.defectIsCreated(newDefect.getTitle()), String.format("On Defect page should be defect with name %s", newDefect.getTitle()));
    }
}
