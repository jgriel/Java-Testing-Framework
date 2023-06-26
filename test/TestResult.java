/**
 * TestResult.java
 * Tracks the results of a single test.
 * Tests may optionally report some message describing the test or the result.
 * Each test is marked as having passed, failed, or caused an unexpected exception.
 * Part of FINAL
 */
public class TestResult {

    // Constants for printing each type of test result
    public static final String PASSED = "PASSED";
    public static final String FAILED = "FAILED";
    public static final String EXCEPTION = "EXCEPTION";

    public String result;
    public String note; 

    public TestResult(String condition){
        result = condition;
    }
    public TestResult(String condition, String message){
        result = condition;
        note = message;
    }

    /**
     * Create a TestResult for a passed test.  No message is provided.
     * @return TestResult for a passed test
     */
    public static TestResult createPassedResult() {
        return new TestResult(PASSED);
    }

    /**
     * Create a TestResult for a passed test. 
     * @param msg Message describing the test result
     * @return TestResult for a passed test
     */
    public static TestResult createPassedResult(String msg) {
        return new TestResult(PASSED, msg);
    }

    /**
     * Create a TestResult for a failed test.  No message is provided.
     * @return TestResult for a failed test
     */
    public static TestResult createFailedResult() {
        return new TestResult(FAILED);
    }

    /**
     * Create a TestResult for a failed test.
     * @param msg Message describing the test result
     * @return TestResult for a failed test
     */
    public static TestResult createFailedResult(String msg) {
        return new TestResult(FAILED, msg);
        
    }

    /**
     * Create a TestResult for a test with an unexpected exception.  No message is provided.
     * @return TestResult for an exception test
     */
    public static TestResult createExceptionResult() {
        return new TestResult(EXCEPTION);
    }

    /**
     * Create a TestResult for a test with an unexpected exception.  
     * @param msg  Message describing the test result
     * @return TestResult for an exception test
     */
    public static TestResult createExceptionResult(String msg) {
        return new TestResult(EXCEPTION, msg);
    }

    /**
     * Test for whether this is a passed test
     * @return true if this is a passed test
     */
    public boolean isPassed() {
        return result.equals(PASSED);
    }

    /**
     * Test for whether this is a failed test
     * @return true if this is a failed test
     */
    public boolean isFailed() {
        return result.equals(FAILED);
    }

    /**
     * Test for whether this is an exception test
     * @return true if this is an exception test
     */
    public boolean isException() {
        return result.equals(EXCEPTION);
    }
}
