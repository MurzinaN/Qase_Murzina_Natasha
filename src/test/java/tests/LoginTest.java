package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProjectsPage;

public class LoginTest extends BaseTest {
    protected final static String EXPECTED_MESSAGE = "These credentials do not match our records.";
    private ProjectsPage ProjectsPage;

    @BeforeClass
    public void initialise() {
        ProjectsPage = new ProjectsPage();
    }

    @Test
    public void positiveLoginTest() {
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(ProjectsPage.ProjectsPageIsOpen(), "Projects page should be open");
    }

    @Test
    public void negativeLoginTest() {
        loginPage.login(USERNAME, "123");
        Assert.assertEquals(loginPage.getMessageErrorText(), EXPECTED_MESSAGE, "Error message should be on display");
    }
}
