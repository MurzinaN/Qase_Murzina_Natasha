package tests;

import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProjectsPage;
import pages.RepositoryPage;
import pages.modals.CreateTestCaseModal;

public class CreateTestCaseTest extends BaseTest {
    private ProjectsPage projectsPage;
    private RepositoryPage repositoryPage;
    private CreateTestCaseModal createTestCaseModal;

    @BeforeClass
    public void initialise() {
        projectsPage = new ProjectsPage();
        repositoryPage = new RepositoryPage();
        createTestCaseModal = new CreateTestCaseModal();

    }

    @Test
    public void newTestCaseTest() {
        loginPage.login(USERNAME, PASSWORD);
        projectsPage.openProject(PROJECT_NAME);
        repositoryPage.addCaseButtonClick();
        TestCase newTestCase = TestCase.builder()
                .title(faker.name().username())
                .description(faker.name().username())
                .preConditions(faker.name().username())
                .postConditions(faker.name().username())
                .build();
        createTestCaseModal.fillForm(newTestCase);
        createTestCaseModal.saveButtonClick();
        Assert.assertTrue(repositoryPage.caseIsCreated(newTestCase.getTitle()), String.format("On Repository page should be case with name %s", newTestCase.getTitle()));

    }

}
