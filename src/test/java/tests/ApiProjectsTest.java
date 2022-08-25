package tests;

import adapter.ProjectAdapter;
import com.google.gson.Gson;
import models.Project;
import models.ProjectResponse;
import models.ProjectResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiProjectsTest {
    private final String expectedJsonAllProjects = "{\"status\":true,\"result\":{\"total\":3,\"filtered\":3,\"count\":3,\"entities\":[{\"title\":\"Demo Project\",\"code\":\"DEMO\",\"counts\":{\"cases\":10,\"suites\":3,\"milestones\":2,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"sharelane\",\"code\":\"SHARELANE\",\"counts\":{\"cases\":16,\"suites\":2,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":7,\"open\":7}}},{\"title\":\"QA19\",\"code\":\"QA\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":3,\"open\":3}}}]}}";
    private final String expectedJsonDeleteProject = "{\"status\":true}";
    private final String testCode = "CODE";
    private final String getCode = "DEMO";

    private final static Gson gson = new Gson();
    ProjectAdapter projectAdapter = new ProjectAdapter();

    @Test
    public void getAllProjectsTest() {
        String responseBody = projectAdapter.getAllProjects(200);
        Assert.assertEquals(responseBody, expectedJsonAllProjects);
    }

    @Test
    public void createProjectTest() {

        Project project = Project.builder()
                .title("QWE")
                .code(testCode)
                .description("QWE with CODE4")
                .build();

        ProjectResponse expectedProjectResponseBody = ProjectResponse
                .builder()
                .result(ProjectResult
                        .builder()
                        .code(testCode)
                        .build())
                .build();

        String actualResponseBody = projectAdapter.createProject(200, gson.toJson(project));
        Assert.assertEquals(gson.fromJson(actualResponseBody, ProjectResponse.class),
                expectedProjectResponseBody);
    }

    @Test
    public void getProjectByCodeTest() {
        String actualResponseBody = projectAdapter.getProjectByCode(200, getCode);
        ProjectResponse expectedProjectResponseBody = ProjectResponse
                .builder()
                .result(ProjectResult
                        .builder()
                        .code(getCode)
                        .build())
                .build();

        Assert.assertEquals(gson.fromJson(actualResponseBody, ProjectResponse.class),
                expectedProjectResponseBody);
    }

    @Test
    public void deleteProjectByCodeTest() {
        String responseBody = projectAdapter.deleteProjectByCode(200, testCode);
        Assert.assertEquals(responseBody, expectedJsonDeleteProject);
    }

}



