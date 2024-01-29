package testrail;

import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;

import static helpers.CustomColorOnConsole.*;

@Log4j2
public class TestRailAPI {

    /**
     * <h1>Configuration setup for TestRail integration.</h1>
     * <p>TestRail credentials were added for local use in mind if you want a more robust way to do it.</p>
     * <p>Consider adding dotenv or maybe using apache.</p>
     * <p>In CL/Bash type the following.</p>
     * <h2>For Windows systems:</h2>
     * <ul>
     *   <li>setx TESTRAIL_API_KEY "YOUR API KEY"</li>
     *   <li>setx TESTRAIL_URL "YOUR URL"</li>
     *   <li>setx TESTRAIL_USERNAME "YOUR USERNAME/EMAIL"</li>
     * </ul>
     *
     * <h2>For Unix systems:</h2>
     * <ul>
     *   <li>export TESTRAIL_API_KEY="YOUR API KEY"</li>
     *   <li>...</li>
     * </ul>
     */

    private static final String TESTRAIL_URL = System.getenv("TESTRAIL_URL");
    private static final String TESTRAIL_USERNAME = System.getenv("TESTRAIL_USERNAME");
    private static final String TESTRAIL_API_KEY = System.getenv("TESTRAIL_API_KEY");
    private static final String AUTH_STRING = TESTRAIL_USERNAME + ":" + TESTRAIL_API_KEY;

    private static final String TESTRAIL_API = "/index.php?/api/v2/";


    public static void updateTestResult(int testCaseId, TestRailResult testRailResult) {
        if (!TestRailListener.getTestRailEnabled()) {
            log.info("TestRail was disabled");
            return;
        }

        try {
            int testRunId = getTestRunId(TestRailListener.getTestRunName());
            String endPoint = TESTRAIL_URL + TESTRAIL_API + "add_result_for_case/" + testRunId + "/" + testCaseId;
            String jsonPayload = "{\"status_id\": " + testRailResult.resultValue + "}";

            HttpURLConnection connection = setUpHttpConnection(endPoint, TestRailRequest.POST);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                log.info(setColor("Test result updated successfully", Color.GREEN));
            } else {
                log.info(setColor("Failed to update test result. Response Code: " + responseCode, Color.RED));
            }
            connection.disconnect();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static int getTestRunId(String testRunName) {
        // Construct the API endpoint URL to get test runs for a project
        String endPoint = TESTRAIL_URL + TESTRAIL_API + "get_runs/"
                + getProjectIdByName(TestRailListener.getProjectName());
        HttpURLConnection connection = setUpHttpConnection(endPoint, TestRailRequest.GET);
        return getIdBasedOnNameFromArrayInJSON(connection, "runs", testRunName);

    }

    private static int getProjectIdByName(String projectName) {
        String endpoint = TESTRAIL_URL + TESTRAIL_API + "get_projects";
        HttpURLConnection connection = setUpHttpConnection(endpoint, TestRailRequest.GET);
        return getIdBasedOnNameFromArrayInJSON(connection, "projects", projectName);
    }


    /**
     * Set up a http connection from the provided endpoint.
     * @param endpoint the url necessary.
     * @param testRailRequest the request type we want to do.
     * @return the complete connection.
     */
    private static HttpURLConnection setUpHttpConnection(String endpoint, TestRailRequest testRailRequest) {
        HttpURLConnection connection;
        try {
            URL url = new URL(endpoint);
            connection = (HttpURLConnection) url.openConnection();
            // Set up basic authentication
            String encodedAuth = Base64.getEncoder().encodeToString(AUTH_STRING.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            connection.setRequestMethod(testRailRequest.request);
            connection.setRequestProperty("Content-Type", "application/json");

            // Make a request
            switch (testRailRequest) {
                case GET:
                    break;
                case POST:
                    connection.setDoOutput(true);
                    break;
                default :
                    log.error("Wrong request provided");
                    break;
            }
        } catch (IOException e) {
            throw new TestRailException(e);
        }
        return connection;
    }

    private static JSONObject getJSONObjectFromResponse(InputStream connectionInputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connectionInputStream, StandardCharsets.UTF_8))) {

            String jsonResponse = br.lines().collect(Collectors.joining());
            return new JSONObject(jsonResponse);
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }


    /**
     * return the {@code ID} of found name inside the connection JSON File.
     * @param connection the {@code HttpURLConnection} to be set.
     * @param arrayName the name of the array to be found.
     * @param nameToFind the name to find inside the array.
     * @return the {@code ID} of the name found.
     */
    private static int getIdBasedOnNameFromArrayInJSON(HttpURLConnection connection,String arrayName, String nameToFind) {
        try (InputStream inputStream = connection.getInputStream()) {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                JSONObject object = getJSONObjectFromResponse(inputStream);
                JSONArray array = new JSONObject(object.toString()).getJSONArray(arrayName);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject project = array.getJSONObject(i);
                    if (project.getString("name").equals(nameToFind)) {
                        return project.getInt("id");
                    }
                }
            } else {
                log.error("Error: Unable to get " + arrayName + " Status code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            throw new JSONException(e);
        }
        return -1;
    }
}
