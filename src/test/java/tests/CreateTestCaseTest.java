package tests;

import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProjectsPage;
import pages.RepositoryPage;
import pages.modals.CreateTestCaseModal;

public class CreateTestCaseTest extends BaseTest {
    private ProjectsPage ProjectsPage;
    private RepositoryPage RepositoryPage;
    private CreateTestCaseModal CreateTestCaseModal;

    @BeforeClass
    public void initialise() {
        ProjectsPage = new ProjectsPage();
        RepositoryPage = new RepositoryPage();
        CreateTestCaseModal = new CreateTestCaseModal();

    }

    @Test
    public void newTestCaseTest() {
        LoginPage.login(USERNAME, PASSWORD);
        ProjectsPage.openProject(PROJECT_NAME);
        RepositoryPage.addCaseButtonClick();
        TestCase newTestCase = TestCase.builder()
                .title(faker.name().username())
                .description(faker.name().username())
                .preConditions(faker.name().username())
                .postConditions(faker.name().username())
                .build();
        CreateTestCaseModal.fillForm(newTestCase);
        CreateTestCaseModal.saveButtonClick();
        Assert.assertTrue(RepositoryPage.caseIsCreated(newTestCase.getTitle()), String.format("On Repository page should be case with name %s", newTestCase.getTitle()));

    }

}
