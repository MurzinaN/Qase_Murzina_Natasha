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
                .defectTitle(faker.name().username())
                .actualResult(faker.name().username())
                .build();
        createDefectModal.fillForm(newDefect);
        createDefectModal.createDefectButtonClick();
        Assert.assertTrue(defectPage.defectIsCreated(newDefect.getDefectTitle()), String.format("On Defect page should be defect with name %s", newDefect.getDefectTitle()));
    }
}
