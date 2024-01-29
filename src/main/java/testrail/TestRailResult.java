package testrail;

public enum TestRailResult {

    PASS("PASSED", 1),      //Value of 1 equals PASSED state on TestRail.
    BLOCKED("BLOCKED", 2),  //Value of 2 equals BLOCKED state on TestRail.
    RETEST("RETEST", 4),    //Value of 4 equals RETEST state on TestRail.
    FAIL("FAILED", 5);      //Value of 5 equals FAILED state on TestRail.
    // you can add more based on your needs...


    public final String result;
    public final int resultValue;

    private TestRailResult(String result, int resultValue) {
        this.result = result;
        this.resultValue = resultValue;
    }

}
