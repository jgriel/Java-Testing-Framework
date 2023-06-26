/**
 * TestControllerImpl.java
 * The TestController is the interface describing the essential flow for managing a test suite. This class is the concrete 
 * implementation of TestController
 * Part of FINAL
 */

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class TestControllerImpl implements TestController {

    private String fileName;
    private PriorityQueue<Double> order = new PriorityQueue<Double>();    
    private HashMap<Double, Test> lookUp = new HashMap<Double, Test>();
    private LinkedList<TestResult> passed = new LinkedList<TestResult>();
    private LinkedList<TestResult> failed = new LinkedList<TestResult>();
    private LinkedList<TestResult> exception = new LinkedList<TestResult>();

    public TestControllerImpl(String txt){
        fileName = txt;
    }

    /**
     * Add the given test to the test suite.  
     * @param test Test to be added
     * @param rank Ordering for when to run the test
     */
    @Override
    public void addTest(Test test, double rank) {
        order.add(rank);
        lookUp.put(rank, test);
    }

    /**
     * Run all tests in the test suite and collect the results.
     * The results are not returned but stored internally.
     */
    @Override
    public void runTests() {
        for(int i = 0; i < lookUp.size(); i++){
            try{
                TestResult result = lookUp.get(order.poll()).runTest();
                if (result.isPassed()){
                    passed.add(result);
                } else if (result.isFailed()){
                    failed.add(result);
                }
            } catch (IllegalArgumentException e){
                exception.add(TestResult.createExceptionResult());
            }
        }
    }
    /**
     * Create a report of all the results of the tests that have been run.
     * @throws FileNotFoundException
     */
    @Override
    public void createReport() throws FileNotFoundException {
        TestReporterImpl html = new TestReporterImpl(fileName);
        html.generateHtml(passed, failed, exception);
        
    }

}
