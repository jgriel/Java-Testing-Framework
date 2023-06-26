/**
 * This test class includes many examples of how to test a piece of code.
 * It also provides the general flow of how the TestController is used:
 * instantiate the object, add tests, run all the tests, and generate the report.
 */

import java.io.FileNotFoundException;

public class SampleCodeTest {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        TestController tc = new TestControllerImpl("results.html");

        // Check int
        Test testInt = new Test() {                                         //ANONYMOUS CLASS
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                int returnedVal = code.addOne(0);
                if (returnedVal == 1) {
                    return TestResult.createPassedResult();
                } else {
                    return TestResult.createFailedResult("addOne expected to return 1");
                }
            }
        };
        tc.addTest(testInt, 1.0);

        // Check double that fails due to precision
        Test testDoubleFail = new Test() {
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                double returnedVal = code.returnsHalf(Math.PI);
                if (returnedVal == 1.570796326794897) {
                    return TestResult.createPassedResult();
                } else {
                    return TestResult.createFailedResult("returnsHalf expected to return half of pi");
                }
            }
        };
        tc.addTest(testDoubleFail, 2.0);

        // Check double that works
        Test testDouble = new Test() {
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                double returnedVal = code.returnsHalf(Math.PI);
                if (returnedVal > 1.570796326794896 && returnedVal < 1.570796326794898) {
                    return TestResult.createPassedResult("returnsHalf works");
                } else {
                    return TestResult.createFailedResult("returnsHalf expected to return half of pi");
                }
            }
        };
        tc.addTest(testDouble, 3.0);
        
        // Check returned object value
        Test testObjectValue = new Test() {
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                CodeToBeTested returnedVal = code.returnsObject();
                if (returnedVal.getVariableToChange() == false) {
                    return TestResult.createPassedResult("testObjectValue");
                } else {
                    return TestResult.createFailedResult("testObjectValue expected to find object's value to be false");
                }
            }
        };
        tc.addTest(testObjectValue, 4.0);
        
        // Check returned object equals
        Test testObject = new Test() {
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                String returnedVal = code.returnsString();
                if (returnedVal.equals("false")) {
                    return TestResult.createPassedResult("testObject");
                } else {
                    return TestResult.createFailedResult("testObject expected the String 'false'");
                }
            }
        };
        tc.addTest(testObject, 5.0);

        // Checking a modified instance variable
        Test testSideEffect = new Test() {
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                code.modifyVariable();
                if (code.getVariableToChange()) { // variable successfully changed
                    return TestResult.createPassedResult("Value was true");
                } else {
                    return TestResult.createFailedResult("Value was false");
                }
            }
        };
        tc.addTest(testSideEffect, 10.0);

        // Check for an anticipated exception
        Test testAntiException = new Test() {
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                try {
                    code.methodThrowsException(-1);
                } catch (IllegalArgumentException e) {
                    return TestResult.createPassedResult("Correctly caught exception");
                }
                return TestResult.createFailedResult("testAntiException: Failed to catch exception");
            }
        };
        tc.addTest(testAntiException, 15.0);

        // Failed to check for an exception
        Test testException = new Test() {
            @Override
            public TestResult runTest() {
                CodeToBeTested code = new CodeToBeTested();
                code.methodThrowsException(-1);
                // Should never get to this part of the code
                return TestResult.createFailedResult("testException should have thrown an exception");  
            }
        };
        tc.addTest(testException, 16.0);


        // run tests
        tc.runTests();

        // get report
        tc.createReport();
    }
}
