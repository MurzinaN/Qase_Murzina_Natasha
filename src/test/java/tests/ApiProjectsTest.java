package tests;

import adapter.ProjectAdapter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.AllEntitiesResult;
import models.ErrorField;
import models.NegativeResponse;
import models.PositiveResponse;
import models.project.Counts;
import models.project.Project;
import models.project.Runs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;


public class ApiProjectsTest {
    private final static Gson gson = new Gson();
    private final String testCode = "CODE5";
    private final String getCode = "DEMO";
    ProjectAdapter projectAdapter = new ProjectAdapter();

    @Test
    public void getAllProjectsTest() {
        PositiveResponse<AllEntitiesResult<Project>> response = gson.fromJson((projectAdapter.getAllProjects(200)),
                new TypeToken<PositiveResponse<AllEntitiesResult<Project>>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(AllEntitiesResult.builder()
                        .total(3)
                        .filtered(3)
                        .count(3)
                        .entities(Arrays.asList(
                                Project.builder()
                                        .title("Demo Project")
                                        .code("DEMO")
                                        .counts(Counts.builder()
                                                .cases(10)
                                                .suites(3)
                                                .milestones(2)
                                                .runs(Runs.builder()
                                                        .build())
                                                .build())
                                        .build(),
                                Project.builder()
                                        .title("sharelane")
                                        .code("SHARELANE")
                                        .counts(Counts.builder()
                                                .cases(16)
                                                .suites(2)
                                                .milestones(0)
                                                .runs(Runs.builder()
                                                        .build())
                                                .build())
                                        .build(),
                                Project.builder()
                                        .title("QA19")
                                        .code("QA")
                                        .counts(Counts.builder()
                                                .runs(Runs.builder()
                                                        .build())
                                                .build())
                                        .build()

                        ))
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createProjectPositiveTest() {

        Project project = Project.builder()
                .title("QWE")
                .code(testCode)
                .description("QWE with CODE5")
                .build();
        PositiveResponse<Project> response = gson.fromJson(projectAdapter.createProject(200, gson.toJson(project)),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .code(testCode)
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createProjectNegativeTest() {
        Project project = Project.builder()
                .title("1")
                .code("2")
                .build();
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Data is invalid.")
                .errorFields(Arrays.asList(
                        ErrorField.builder()
                                .field("code")
                                .error("Project code must be at least 2 characters.")
                                .build()
                ))
                .build();
        NegativeResponse response = gson.fromJson(projectAdapter.createProject(400, gson.toJson(project)), NegativeResponse.class);
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getProjectByCodePositiveTest() {
        PositiveResponse<Project> response = gson.fromJson((projectAdapter.getProjectByCode(200, getCode)),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .title("Demo Project")
                        .code(getCode)
                        .counts(Counts.builder()
                                .cases(10)
                                .suites(3)
                                .milestones(2)
                                .runs(Runs.builder()
                                        .build())
                                .build())
                        .build())
                .build();


        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getProjectByCodeNegativeTest() {
        NegativeResponse response = gson.fromJson((projectAdapter.getProjectByCode(404, "ups")), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Project is not found.")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteProjectByCodePositiveTest() {
        PositiveResponse<Project> response = gson.fromJson((projectAdapter.deleteProjectByCode(200, testCode)),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder().build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteProjectByCodeNegativeTest() {
        NegativeResponse response = gson.fromJson((projectAdapter.deleteProjectByCode(404, "ups")), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Project is not found.")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }


}



