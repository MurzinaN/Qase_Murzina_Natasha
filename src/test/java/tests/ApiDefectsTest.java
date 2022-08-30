package tests;

import adapter.DefectAdapter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.AllEntitiesResult;
import models.ErrorField;
import models.NegativeResponse;
import models.PositiveResponse;
import models.defect.Defect;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ApiDefectsTest {
    private final static Gson gson = new Gson();
    private final String projectCode = "QA";
    private final int id = 10;
    private final int getId = 2;
    private final int updateId = 1;
    DefectAdapter defectAdapter = new DefectAdapter();

    @Test
    public void getAllDefectTest() {
        PositiveResponse<AllEntitiesResult<Defect>> response = gson.fromJson((defectAdapter.getAllDefects(200, projectCode)),
                new TypeToken<PositiveResponse<AllEntitiesResult<Defect>>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(AllEntitiesResult.builder()
                        .total(2)
                        .filtered(2)
                        .count(2)
                        .entities(Arrays.asList(
                                Defect.builder()
                                        .id(1)
                                        .title("Login")
                                        .actual_result("username")
                                        .status("open")
                                        .milestone_id(null)
                                        .project_id(236076)
                                        .severity("critical")
                                        .member_id(1)
                                        .attachments(Arrays.asList())
                                        .custom_fields(Arrays.asList())
                                        .external_data("{}")
                                        .resolved_at(null)
                                        .created("2022-08-25 15:00:46")
                                        .updated("2022-08-25 20:27:47")
                                        .created_at("2022-08-25T15:00:46+00:00")
                                        .updated_at("2022-08-25T20:27:47+00:00")
                                        .tags(Arrays.asList())
                                        .build(),
                                Defect.builder()
                                        .id(2)
                                        .title("Product")
                                        .actual_result("shopping cart")
                                        .status("open")
                                        .milestone_id(null)
                                        .project_id(236076)
                                        .severity("normal")
                                        .member_id(1)
                                        .attachments(Arrays.asList())
                                        .custom_fields(Arrays.asList())
                                        .external_data("{}")
                                        .resolved_at(null)
                                        .created("2022-08-25 15:01:25")
                                        .updated("2022-08-25 15:01:25")
                                        .created_at("2022-08-25T15:01:25+00:00")
                                        .updated_at("2022-08-25T15:01:25+00:00")
                                        .tags(Arrays.asList())
                                        .build())
                        )
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);

    }

    @Test
    public void createDefectPositiveTest() {

        Defect defect = Defect.builder()
                .title("Logout")
                .actual_result("logout button")
                .severity("3")
                .build();
        PositiveResponse<Defect> response = gson.fromJson((defectAdapter.createDefect(200, projectCode, gson.toJson(defect))),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(id)
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createDefectNegativeTest() {
        Defect defect = Defect.builder()
                .title("Logout")
                .actual_result("logout button")
                .severity("10")
                .build();
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Data is invalid.")
                .errorFields(Arrays.asList(
                        ErrorField.builder()
                                .field("severity")
                                .error("The selected severity is invalid.")
                                .build()
                ))
                .build();
        NegativeResponse response = gson.fromJson(defectAdapter.createDefect(400, projectCode, gson.toJson(defect)), NegativeResponse.class);
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getSpecificDefectPositiveTest() {

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(getId)
                        .title("Product")
                        .actual_result("shopping cart")
                        .status("open")
                        .milestone_id(null)
                        .project_id(236076)
                        .severity("normal")
                        .member_id(1)
                        .attachments(Arrays.asList())
                        .custom_fields(Arrays.asList())
                        .external_data("{}")
                        .resolved_at(null)
                        .created("2022-08-25 15:01:25")
                        .updated("2022-08-25 15:01:25")
                        .created_at("2022-08-25T15:01:25+00:00")
                        .updated_at("2022-08-25T15:01:25+00:00")
                        .tags(Arrays.asList())
                        .build())
                .build();
        PositiveResponse<Defect> response = gson.fromJson((defectAdapter.getSpecificDefect(200, projectCode, getId)),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getSpecificDefectNegativeTest() {
        NegativeResponse response = gson.fromJson((defectAdapter.getSpecificDefect(404, projectCode, 3)), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Defect not found")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteDefectPositiveTest() {
        PositiveResponse<Defect> response = gson.fromJson(((defectAdapter.deleteDefect(200, projectCode, id))),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(id)
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteDefectNegativeTest() {
        NegativeResponse response = gson.fromJson((defectAdapter.deleteDefect(404, projectCode, 3)), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Defect not found")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void updateDefectPositiveTest() {

        Defect defect = Defect.builder()
                .severity("2")
                .build();
        PositiveResponse<Defect> response = gson.fromJson((defectAdapter.updateDefect(200, projectCode, updateId, gson.toJson(defect))),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(updateId)
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void updateDefectNegativeTest() {
        Defect defect = Defect.builder()
                .severity("abc")
                .build();
        NegativeResponse response = gson.fromJson((defectAdapter.updateDefect(400, projectCode, updateId, gson.toJson(defect))), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Data is invalid.")
                .errorFields(Arrays.asList(
                        ErrorField.builder()
                                .field("severity")
                                .error("The severity must be an integer.")
                                .build(),
                        ErrorField.builder()
                                .field("severity")
                                .error("The selected severity is invalid.")
                                .build()
                ))
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

}
