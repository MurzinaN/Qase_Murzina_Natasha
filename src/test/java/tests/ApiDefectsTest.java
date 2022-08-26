package tests;

import adapter.DefectAdapter;
import com.google.gson.Gson;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiDefectsTest {
    private final String expectedJsonAllDefects = "{\"status\":true,\"result\":{\"total\":3,\"filtered\":3,\"count\":3,\"entities\":[{\"id\":1,\"title\":\"Login\",\"actual_result\":\"username\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":236076,\"severity\":\"normal\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-25 15:00:46\",\"updated\":\"2022-08-25 15:00:46\",\"created_at\":\"2022-08-25T15:00:46+00:00\",\"updated_at\":\"2022-08-25T15:00:46+00:00\",\"tags\":[]},{\"id\":2,\"title\":\"Product\",\"actual_result\":\"shopping cart\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":236076,\"severity\":\"normal\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-25 15:01:25\",\"updated\":\"2022-08-25 15:01:25\",\"created_at\":\"2022-08-25T15:01:25+00:00\",\"updated_at\":\"2022-08-25T15:01:25+00:00\",\"tags\":[]},{\"id\":5,\"title\":\"Pay\",\"actual_result\":\"pay card\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":236076,\"severity\":\"normal\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-25 17:41:27\",\"updated\":\"2022-08-25 17:41:27\",\"created_at\":\"2022-08-25T17:41:27+00:00\",\"updated_at\":\"2022-08-25T17:41:27+00:00\",\"tags\":[]}]}}";
    private final String expectedJsonDeleteDefect = "{\"status\":true,\"result\":{\"id\":8}}";
    private final String projectCode = "QA";
    private final int id = 8;
    private final int getId = 2;
    private final int updateId = 1;
    private final static Gson gson = new Gson();
    DefectAdapter defectAdapter = new DefectAdapter();
    @Test
    public void getAllDefectTest() {
        String responseBody = defectAdapter.getAllDefects(200, projectCode);
        Assert.assertEquals(responseBody, expectedJsonAllDefects);
    }
    @Test
    public void createDefectTest() {

        Defect defect = Defect.builder()
                .title("Logout")
                .actual_result("logout button")
                .severity(3)
                .build();

        DefectResponse expectedDefectResponseBody = DefectResponse
                .builder()
                .result(DefectResult
                        .builder()
                        .id(id)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.createDefect(200, projectCode, gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class),
                expectedDefectResponseBody);
    }
    @Test
    public void getSpecificDefectTest() {

        DefectResponse expectedDefectResponseBody = DefectResponse
                .builder()
                .result(DefectResult
                        .builder()
                        .id(getId)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.getSpecificDefect(200, projectCode, getId);
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class),
                expectedDefectResponseBody);
    }
    @Test
    public void deleteDefectTest() {
        String responseBody = defectAdapter.deleteDefect(200, projectCode, id);
        Assert.assertEquals(responseBody, expectedJsonDeleteDefect);
    }
    @Test
    public void updateDefectTest() {

        Defect defect = Defect.builder()
                .severity(2)
                .build();

        DefectResponse expectedDefectResponseBody = DefectResponse
                .builder()
                .result(DefectResult
                        .builder()
                        .id(updateId)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.updateDefect(200, projectCode, updateId, gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class),
                expectedDefectResponseBody);
    }

}
