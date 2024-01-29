package testrail;

public class TestRailException extends RuntimeException{

    public TestRailException() {
        super();
    }

    public TestRailException(String message) {
        super(message);
    }

    public TestRailException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
