package testrail;

public enum TestRailRequest {

    GET("GET"),
    POST("POST");

    public final String request;
    private TestRailRequest(String request) {
        this.request = request;
    }
}
