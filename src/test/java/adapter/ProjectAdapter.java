package adapter;

public class ProjectAdapter extends BaseAdapter {
    private static final String ENDPOINT = "project";

    public String getAllProjects(int statusCode) {
        return get(ENDPOINT, statusCode);
    }

    public String createProject(int statusCode, String requestBody) {
        return post(ENDPOINT, statusCode, requestBody);
    }

    //return gson.fromJson((post(ENDPOINT, statusCode, requestBody)), new TypeToken<PositiveResponse<Project>>() {
    // }.getType());PositiveResponse<Project>
    public String getProjectByCode(int statusCode, String projectCode) {
        return get(ENDPOINT + "/" + projectCode, statusCode);
    }

    public String deleteProjectByCode(int statusCode, String projectCode) {
        return delete(ENDPOINT + "/" + projectCode, statusCode);
    }


}
