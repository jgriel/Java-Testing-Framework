/**
 * ExpressionCodeTest.java
 * This test class includes how to test a piece of code.
 * It also provides the general flow of how the TestController is used:
 * instantiate the object, add tests, run all the tests, and generate the report.
 * Part of FINAL Homework 7
 */
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;

public class ExpressionCodeTest {
    
    public static void main(String[]args) throws FileNotFoundException{

        TestController tc =  new TestControllerImpl("Expression Results.html");

        //EXPRESSION
        //expressionFromPostfix
        //provide with an empty array
        Test testEmptyExpressionFromPostFix = new Test() {
            @Override
            public TestResult runTest(){
                String[] post = {};
                try {
                    Expression.expressionFromPostfix(post);
                } catch (IllegalArgumentException e) {
                    return TestResult.createPassedResult("testEmptyExpressionFromPostFix: successfully caught IllegalArgumentException");
                }
                return TestResult.createFailedResult("testEmptyExpressionFromPostFix: failed to catch IllegalArgumentException");
            }
        };
        tc.addTest(testEmptyExpressionFromPostFix, 1.0);

        //provide array with only numbers
        Test testNumberExpressionFromPostFix = new Test() {
            @Override
            public TestResult runTest(){
                String[] post = {"-12", "5", "7"};
                Expression test = Expression.expressionFromPostfix(post);
                if (test.toInfix().equals("7")){
                    return TestResult.createPassedResult("testNumberExpressionFromPostFix: 7 returned");
                } else {
                    return TestResult.createFailedResult("testNumberExpressionFromPostFix");
                }
            }
        };
        tc.addTest(testNumberExpressionFromPostFix, 2.0);

        //provide array with only symbols/operators
        Test testSymbolsExpressionFromPostFix = new Test() {
            @Override
            public TestResult runTest(){
                String[] post = {"&", "+", "$", "*"};
                try {
                    Expression.expressionFromPostfix(post);
                } catch (IllegalArgumentException e) {
                    return TestResult.createPassedResult("testSymbolsExpressionFromPostFix: successfully caught IllegalArgumentException");
                }
                return TestResult.createFailedResult("testSymbolsExpressionFromPostFix: failed to catch IllegalArgumentException");
            }
        };
        tc.addTest(testSymbolsExpressionFromPostFix, 3.0);

        //provide normal array
        Test testNormalExpressionFromPostFix = new Test() {
            @Override
            public TestResult runTest(){
                String[] post = {"-12", "x", "5", "y", "+", "+", "-"};
                Expression test = Expression.expressionFromPostfix(post);
                if (test.toPostfix().equals("-12 x 5 y++-")){
                    return TestResult.createPassedResult("testNormalExpressionFromPostFix: correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testNormalExpressionFromPostFix");
                }
            }
        };
        tc.addTest(testNormalExpressionFromPostFix, 4.0);

        //toString
        //esures toString returns the correct value
        Test testToString = new Test() {
            @Override
            public TestResult runTest(){
                String[] post = {"-12", "x", "5", "y", "+", "+", "-"};
                Expression test = Expression.expressionFromPostfix(post);
                if (test.toString().equals("(-12 - (x + (5 + y)))")){
                    return TestResult.createPassedResult("testToString: correctly returns corresponding String");
                } else {
                    return TestResult.createFailedResult("testToString");
                }
            }
        };
        tc.addTest(testToString, 37.0);


        //INTEGErOPERAND
        //toPrefix
        Test testIntToPrefix = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (test.toPrefix().equals("1")){
                    return TestResult.createPassedResult("testIntToPrefix: returns correct prefix notation");
                } else {
                    return TestResult.createFailedResult("testIntToPrefix");
                }
            }
        };
        tc.addTest(testIntToPrefix, 10.0);

        //toPostfix
        Test testIntToPostfix = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (test.toPostfix().equals("1")){
                    return TestResult.createPassedResult("testIntToPostfix: returns correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testIntToPostfix");
                }
            }
        };
        tc.addTest(testIntToPostfix, 11.0);
        
        //toInfix
        Test testIntToInfix = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (test.toInfix().equals("1")){
                    return TestResult.createPassedResult("testIntToInfix: returns correct infix notation");
                } else {
                    return TestResult.createFailedResult("testIntToInfix");
                }
            }
        };
        tc.addTest(testIntToInfix, 12.0);

        //simplify
        Test testIntSimplify = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (test.simplify().equals(test)){
                    return TestResult.createPassedResult("testIntSimplify: returns correct object");
                } else {
                    return TestResult.createFailedResult("testIntSimplify");
                }
            }
        };
        tc.addTest(testIntSimplify, 38.0);

        //getVariables
        Test testIntGetVariables = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                Set<String> empty = test.getVariables();
                if (empty.isEmpty()){
                    return TestResult.createPassedResult("testIntGetVariables: returns correct object");
                } else {
                    return TestResult.createFailedResult("testIntGetVariables");
                }
            }
        };
        tc.addTest(testIntGetVariables, 58.0);
        
        //evaluate
        //evaluate with empty hashset
        Test testIntEvaluateEmpty = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                if (test.evaluate(assignments) == 1){
                    return TestResult.createPassedResult("testIntEvaluateEmpty: returned correct value");
                } else {
                    return TestResult.createFailedResult("testIntEvaluateEmpty");
                }
            }
        };
        tc.addTest(testIntEvaluateEmpty, 69.0);

        //evaluate with non-empty hashset
        Test testIntEvaluateFull = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                assignments.put("x", -12);
                assignments.put("y", 5);
                if (test.evaluate(assignments) == 1){
                    return TestResult.createPassedResult("testIntEvaluateFull: returned correct value");
                } else {
                    return TestResult.createFailedResult("testIntEvaluateFull");
                }
            }
        };
        tc.addTest(testIntEvaluateFull, 70.0);

        //equals
        //equals w/null
        Test testIntEqualsNull = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (!test.equals(null)){
                    return TestResult.createPassedResult("testIntEqualsNull: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testIntEqualsNull");
                }
            }
        };
        tc.addTest(testIntEqualsNull, 81.0);
        
        //equals w/wrong obj type
        Test testIntEqualsNoType = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (!test.equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testIntEqualsNoType: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testIntEqualsNoType");
                }
            }
        };
        tc.addTest(testIntEqualsNoType, 82.0);

        //equals w/right obj type wrong value
        Test testIntEqualsNoValue = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (!test.equals(new IntegerOperand(2))){
                    return TestResult.createPassedResult("testIntEqualsNoValue: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testIntEqualsNoValue");
                }
            }
        };
        tc.addTest(testIntEqualsNoValue, 83.0);

        //equals w/right obj type right value
        Test testIntEqualsValue = new Test() {
            @Override
            public TestResult runTest(){
                IntegerOperand test = new IntegerOperand(1);
                if (test.equals(new IntegerOperand(1))){
                    return TestResult.createPassedResult("testIntEqualsValue: correctly returned true");
                } else {
                    return TestResult.createFailedResult("testIntEqualsValue");
                }
            }
        };
        tc.addTest(testIntEqualsValue, 84.0);


        //VARIABLeOPERAND
        //toPrefix
        Test testVariToPrefix = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (test.toPrefix().equals("x")){
                    return TestResult.createPassedResult("testVariToPrefix: returns correct prefix notation");
                } else {
                    return TestResult.createFailedResult("testVariToPrefix");
                }
            }
        };
        tc.addTest(testVariToPrefix, 13.0);

        //toPostfix
        Test testVariToPostfix = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (test.toPostfix().equals("x")){
                    return TestResult.createPassedResult("testVariToPostfix: returns correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testVariToPostfix");
                }
            }
        };
        tc.addTest(testVariToPostfix, 14.0);

        //toInfix
        Test testVariToInfix = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (test.toInfix().equals("x")){
                    return TestResult.createPassedResult("testVariToInfix: returns correct infix notation");
                } else {
                    return TestResult.createFailedResult("testVariToInfix");
                }
            }
        };
        tc.addTest(testVariToInfix, 15.0);

        //simplify
        Test testVariSimplify = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (test.simplify().equals(test)){
                    return TestResult.createPassedResult("testVariSimplify: returns correct object");
                } else {
                    return TestResult.createFailedResult("testVariSimplify");
                }
            }
        };
        tc.addTest(testVariSimplify, 39.0);

        //evaluate
        //evaluate with empty hashset
        Test testVariEvaluateEmpty = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                try {
                    test.evaluate(assignments);
                } catch (IllegalArgumentException e){
                    return TestResult.createPassedResult("testVariEvaluateEmpty: successfully caught IllegalArgumentException");
                }
                return TestResult.createFailedResult("testVariEvaluateEmpty");
            }
        };
        tc.addTest(testVariEvaluateEmpty, 71.0);

        //evaluate with non-empty hashset
        Test testVariEvaluateFull = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                assignments.put("x", -12);
                assignments.put("y", 5);
                if (test.evaluate(assignments) == -12){
                    return TestResult.createPassedResult("testVariEvaluateFull: returned correct value");
                } else {
                    return TestResult.createFailedResult("testVariEvaluateFull");
                }
            }
        };
        tc.addTest(testVariEvaluateFull, 72.0);

        //getVariables
        Test testVariGetVariables = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                Set<String> set = test.getVariables();
                if (set.contains("x")){
                    return TestResult.createPassedResult("testVariGetVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testVariGetVariables");
                }
            }
        };
        tc.addTest(testVariGetVariables, 59.0);

        //equals
        //equals w/null
        Test testVariEqualsNull = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (!test.equals(null)){
                    return TestResult.createPassedResult("testVariEqualsNull: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testVariEqualsNull");
                }
            }
        };
        tc.addTest(testVariEqualsNull, 85.0);
        
        //equals w/wrong obj type
        Test testVariEqualsNoType = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (!test.equals(new IntegerOperand(1))){
                    return TestResult.createPassedResult("testVariEqualsNoType: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testVariEqualsNoType");
                }
            }
        };
        tc.addTest(testVariEqualsNoType, 86.0);

        //equals w/right obj type wrong value
        Test testVariEqualsNoValue = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (!test.equals(new VariableOperand("y"))){
                    return TestResult.createPassedResult("testVariEqualsNoValue: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testVariEqualsNoValue");
                }
            }
        };
        tc.addTest(testVariEqualsNoValue, 87.0);

        //equals w/right obj type right value
        Test testVariEqualsValue = new Test() {
            @Override
            public TestResult runTest(){
                VariableOperand test = new VariableOperand("x");
                if (test.equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testVariEqualsValue: correctly returned true");
                } else {
                    return TestResult.createFailedResult("testVariEqualsValue");
                }
            }
        };
        tc.addTest(testVariEqualsValue, 88.0);

        //OPERATOrEXPRESSION
        //opToPrefix
        Test testOpToPrefix = new Test() {
            @Override
            public TestResult runTest(){
                OperatorExpression test = new SumExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPrefix().equals("+x 5")){
                    return TestResult.createPassedResult("testOpToPrefix: returns correct prefix notation");
                } else {
                    return TestResult.createFailedResult("testOpToPrefix");
                }
            }
        };
        tc.addTest(testOpToPrefix, 16.0);

        //opToPostfix
        Test testOpToPostfix = new Test() {
            @Override
            public TestResult runTest(){
                OperatorExpression test = new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPostfix().equals("x 5-")){
                    return TestResult.createPassedResult("testOpToPostfix: returns correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testOpToPostfix");
                }
            }
        };
        tc.addTest(testOpToPostfix, 17.0);

        //opToPostfix
        Test testOpToInfix = new Test() {
            @Override
            public TestResult runTest(){
                OperatorExpression test = new ProductExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toInfix().equals("(x * 5)")){
                    return TestResult.createPassedResult("testOpToInfix: returns correct infix notation");
                } else {
                    return TestResult.createFailedResult("testOpToInfix");
                }
            }
        };
        tc.addTest(testOpToInfix, 18.0);

        //variables
        //test Expression w/variables
        Test testOpVariables = new Test() {
            @Override
            public TestResult runTest(){
                OperatorExpression test = new QuotientExpression(new VariableOperand("x"), new VariableOperand("y"));
                Set<String> set = test.getVariables();
                if (set.contains("x") && set.contains("y")){
                    return TestResult.createPassedResult("testOpVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testOpVariables");
                }
            }
        };
        tc.addTest(testOpVariables, 60.0);

        //test Expression w/o variables
        Test testOpNoVariables = new Test() {
            @Override
            public TestResult runTest(){
                OperatorExpression test = new SumExpression(new IntegerOperand(1), new IntegerOperand(5));
                Set<String> set = test.getVariables();
                if (set.isEmpty()){
                    return TestResult.createPassedResult("testOpNoVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testOpNoVariables");
                }
            }
        };
        tc.addTest(testOpNoVariables, 61.0);

        //SUmEXPRESSION
        //getOperator
        Test testSumGetOperator = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.getOperator().equals("+")){
                    return TestResult.createPassedResult("testSumGetOperator: returns correct operator");
                } else {
                    return TestResult.createFailedResult("testSumGetOperator");
                }
            }
        };
        tc.addTest(testSumGetOperator, 6.0);

        //toPrefix
        Test testSumToPrefix = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPrefix().equals("+x 5")){
                    return TestResult.createPassedResult("testSumToPrefix: returns correct prefix notation");
                } else {
                    return TestResult.createFailedResult("testSumToPrefix");
                }
            }
        };
        tc.addTest(testSumToPrefix, 19.0);

        //toPostfix
        Test testSumToPostfix = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPostfix().equals("x 5+")){
                    return TestResult.createPassedResult("testSumToPostfix: returns correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testSumToPostfix");
                }
            }
        };
        tc.addTest(testSumToPostfix, 20.0);

        //toInfix
        Test testSumToInfix = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toInfix().equals("(x + 5)")){
                    return TestResult.createPassedResult("testSumToInfix: returns correct infix notation");
                } else {
                    return TestResult.createFailedResult("testSumToInfix");
                }
            }
        };
        tc.addTest(testSumToInfix, 21.0);

        //simplify
        //test with 0+x
        Test testSumSimp0X = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(0), new VariableOperand("x"));
                if (test.simplify().equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testSumSimp0X: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testSumSimp0X");
                }
            }
        };
        tc.addTest(testSumSimp0X, 40.0);

        //test with x+0
        Test testSumSimpX0 = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new VariableOperand("x"), new IntegerOperand(0));
                if (test.simplify().equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testSumSimpX0: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testSumSimpX0");
                }
            }
        };
        tc.addTest(testSumSimpX0, 41.0);

        //test with two numbers
        Test testSumSimpNum = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new IntegerOperand(0));
                if (test.simplify().equals(new IntegerOperand(5))){
                    return TestResult.createPassedResult("testSumSimpNum: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testSumSimpNum");
                }
            }
        };
        tc.addTest(testSumSimpNum, 42.0);

        //test with variable
        Test testSumSimpNorm = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (test.simplify().equals(new SumExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testSumSimpNorm: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testSumSimpNorm");
                }
            }
        };
        tc.addTest(testSumSimpNorm, 43.0);

        //evaluate
        //evaluate with empty hashset
        Test testSumEvalEmpty = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                try {
                    test.evaluate(assignments);
                } catch (IllegalArgumentException e){
                    return TestResult.createPassedResult("testSumEvalEmpty: successfully caught IllegalArgumentException");
                }
                return TestResult.createFailedResult("testSumEvalEmpty");
            }
        };
        tc.addTest(testSumEvalEmpty, 73.0);

        //evaluate with non-empty hashset
        Test testSumEvalFull = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                assignments.put("x", -12);
                assignments.put("y", 5);
                if (test.evaluate(assignments) == -7){
                    return TestResult.createPassedResult("testSumEvalFull: returned correct value");
                } else {
                    return TestResult.createFailedResult("testSumEvalFull");
                }
            }
        };
        tc.addTest(testSumEvalFull, 74.0);

        //getVariables
        //test Expression w/variables
        Test testSumGetVariables = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new VariableOperand("x"), new VariableOperand("y"));
                Set<String> set = test.getVariables();
                if (set.contains("x") && set.contains("y")){
                    return TestResult.createPassedResult("testSumGetVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testSumGetVariables");
                }
            }
        };
        tc.addTest(testSumGetVariables, 62.0);

        //test Expression w/o variables
        Test testSumGetNoVariables = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new IntegerOperand(0));
                Set<String> set = test.getVariables();
                if (set.isEmpty()){
                    return TestResult.createPassedResult("testSumGetNoVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testSumGetNoVariables");
                }
            }
        };
        tc.addTest(testSumGetNoVariables, 63.0);

        //equals
        //equals w/null obj
        Test testSumEqualsNull = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(null)){
                    return TestResult.createPassedResult("testSumEqualsNull: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testSumEqualsNull");
                }
            }
        };
        tc.addTest(testSumEqualsNull, 89.0);

        //equals w/wrong type
        Test testSumEqualsNoType = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new ProductExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testSumEqualsNoType: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testSumEqualsNoType");
                }
            }
        };
        tc.addTest(testSumEqualsNoType, 90.0);

        //equals w/right type wrong value
        Test testSumEqualsNoValue = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new SumExpression(new IntegerOperand(6), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testSumEqualsNoValue: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testSumEqualsNoValue");
                }
            }
        };
        tc.addTest(testSumEqualsNoValue, 91.0);

        //equals w/right type right value
        Test testSumEqualsValue = new Test() {
            @Override
            public TestResult runTest(){
                SumExpression test = new SumExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (test.equals(new SumExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testSumEqualsValue: correctly returned true");
                } else {
                    return TestResult.createFailedResult("testSumEqualsValue");
                }
            }
        };
        tc.addTest(testSumEqualsValue, 92.0);


        //DIFFERENCeEXPRESSION
        //getOperator
        Test testDifferenceGetOperator = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.getOperator().equals("-")){
                    return TestResult.createPassedResult("testDifferenceGetOperator: returns correct operator");
                } else {
                    return TestResult.createFailedResult("testDifferenceGetOperator");
                }
            }
        };
        tc.addTest(testDifferenceGetOperator, 7.0);

        //toPrefix
        Test testDifferenceToPrefix = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPrefix().equals("-x 5")){
                    return TestResult.createPassedResult("testDifferenceToPrefix: returns correct prefix notation");
                } else {
                    return TestResult.createFailedResult("testDifferenceToPrefix");
                }
            }
        };
        tc.addTest(testDifferenceToPrefix, 22.0);

        //toPostfix
        Test testDifferenceToPostfix = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPostfix().equals("x 5-")){
                    return TestResult.createPassedResult("testDifferenceToPostfix: returns correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testDifferenceToPostfix");
                }
            }
        };
        tc.addTest(testDifferenceToPostfix, 23.0);

        //toInfix
        Test testDifferenceToInfix = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toInfix().equals("(x - 5)")){
                    return TestResult.createPassedResult("testDifferenceToInfix: returns correct infix notation");
                } else {
                    return TestResult.createFailedResult("testDifferenceToInfix");
                }
            }
        };
        tc.addTest(testDifferenceToInfix, 24.0);

        //simplify
        //test with x-0
        Test testDifferenceSimpX0 = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(0));
                if (test.simplify().equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testDifferenceSimpX0: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testDifferenceSimpX0");
                }
            }
        };
        tc.addTest(testDifferenceSimpX0, 44.0);

        //test with x-x
        Test testDifferenceSimpXX = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new VariableOperand("x"));
                if (test.simplify().equals(new IntegerOperand(0))){
                    return TestResult.createPassedResult("testDifferenceSimpXX: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testDifferenceSimpXX");
                }
            }
        };
        tc.addTest(testDifferenceSimpXX, 45.0);

        //test with numbers
        Test testDifferenceSimpNum = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(15), new IntegerOperand(20));
                if (test.simplify().equals(new IntegerOperand(-5))){
                    return TestResult.createPassedResult("testDifferenceSimpNum: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testDifferenceSimpNum");
                }
            }
        };
        tc.addTest(testDifferenceSimpNum, 46.0);

        //test with variable
        Test testDifferenceSimpNorm = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.simplify().equals(new DifferenceExpression(new VariableOperand("x"), new IntegerOperand(5)))){
                    return TestResult.createPassedResult("testDifferenceSimpNorm: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testDifferenceSimpNorm");
                }
            }
        };
        tc.addTest(testDifferenceSimpNorm, 47.0);

        //evaluate
        //evaluate with empty hashset
        Test testDifferenceEvalEmpty = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                try {
                    test.evaluate(assignments);
                } catch (IllegalArgumentException e){
                    return TestResult.createPassedResult("testDifferenceEvalEmpty: successfully caught IllegalArgumentException");
                }
                return TestResult.createFailedResult("testDifferenceEvalEmpty");
            }
        };
        tc.addTest(testDifferenceEvalEmpty, 75.0);

        //evaluate with non-empty hashset
        Test testDifferenceEvalFull = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                assignments.put("x", -12);
                assignments.put("y", 5);
                if (test.evaluate(assignments) == 17){
                    return TestResult.createPassedResult("testDifferenceEvalFull: returned correct value");
                } else {
                    return TestResult.createFailedResult("testDifferenceEvalFull");
                }
            }
        };
        tc.addTest(testDifferenceEvalFull, 76.0);

        //getVariables
        //test Expression w/variables
        Test testDifferenceGetVariables = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new VariableOperand("x"), new VariableOperand("y"));
                Set<String> set = test.getVariables();
                if (set.contains("x") && set.contains("y")){
                    return TestResult.createPassedResult("testDifferenceGetVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testDifferenceGetVariables");
                }
            }
        };
        tc.addTest(testDifferenceGetVariables, 64.0);

        //test Expression w/o variables
        Test testDifferenceGetNoVariables = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(5), new IntegerOperand(0));
                Set<String> set = test.getVariables();
                if (set.isEmpty()){
                    return TestResult.createPassedResult("testDifferenceGetNoVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testDifferenceGetNoVariables");
                }
            }
        };
        tc.addTest(testDifferenceGetNoVariables, 65.0);

        //equals
        //equals w/null obj
        Test testDifferenceEqualsNull = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(null)){
                    return TestResult.createPassedResult("testDifferenceEqualsNull: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testDifferenceEqualsNull");
                }
            }
        };
        tc.addTest(testDifferenceEqualsNull, 93.0);

        //equals w/wrong type
        Test testDifferenceEqualsNoType = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new QuotientExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testDifferenceEqualsNoType: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testDifferenceEqualsNoType");
                }
            }
        };
        tc.addTest(testDifferenceEqualsNoType, 94.0);

        //equals w/right type wrong value
        Test testDifferenceEqualsNoValue = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new DifferenceExpression(new IntegerOperand(6), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testDifferenceEqualsNoValue: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testDifferenceEqualsNoValue");
                }
            }
        };
        tc.addTest(testDifferenceEqualsNoValue, 95.0);

        //equals w/right type right value
        Test testDifferenceEqualsValue = new Test() {
            @Override
            public TestResult runTest(){
                DifferenceExpression test = new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (test.equals(new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testDifferenceEqualsValue: correctly returned true");
                } else {
                    return TestResult.createFailedResult("testDifferenceEqualsValue");
                }
            }
        };
        tc.addTest(testDifferenceEqualsValue, 96.0);


        //PRODUCtEXPRESSION
        //getOperator
        Test testProductGetOperator = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.getOperator().equals("*")){
                    return TestResult.createPassedResult("testProductGetOperator: returns correct operator");
                } else {
                    return TestResult.createFailedResult("testProductGetOperator");
                }
            }
        };
        tc.addTest(testProductGetOperator, 8.0);

        //toPrefix
        Test testProductToPrefix = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPrefix().equals("*x 5")){
                    return TestResult.createPassedResult("testProductToPrefix: returns correct prefix notation");
                } else {
                    return TestResult.createFailedResult("testProductToPrefix");
                }
            }
        };
        tc.addTest(testProductToPrefix, 25.0);

        //toPostfix
        Test testProductToPostfix = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPostfix().equals("x 5*")){
                    return TestResult.createPassedResult("testProductToPostfix: returns correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testProductToPostfix");
                }
            }
        };
        tc.addTest(testProductToPostfix, 26.0);

        //toInfix
        Test testProductToInfix = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toInfix().equals("(x * 5)")){
                    return TestResult.createPassedResult("testProductToInfix: returns correct infix notation");
                } else {
                    return TestResult.createFailedResult("testProductToInfix");
                }
            }
        };
        tc.addTest(testProductToInfix, 27.0);

        //simplify
        //test with X*1
        Test testProductSimpX1 = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new VariableOperand("x"), new IntegerOperand(1));
                if (test.simplify().equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testProductSimpX1: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testProductSimpX1");
                }
            }
        };
        tc.addTest(testProductSimpX1, 48.0);

        //test with 1*X
        Test testProductSimp1X = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(1), new VariableOperand("x"));
                if (test.simplify().equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testProductSimp1X: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testProductSimp1X");
                }
            }
        };
        tc.addTest(testProductSimp1X, 49.0);

        //test with X*0
        Test testProductSimpX0 = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new VariableOperand("x"), new IntegerOperand(0));
                if (test.simplify().equals(new IntegerOperand(0))){
                    return TestResult.createPassedResult("testProductSimpX0: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testProductSimpX0");
                }
            }
        };
        tc.addTest(testProductSimpX0, 50.0);

        //test with 0*X
        Test testProductSimp0X = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(0), new VariableOperand("x")); 
                if (test.simplify().equals(new IntegerOperand(0))){
                    return TestResult.createPassedResult("testProductSimp0X: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testProductSimp0X");
                }
            }
        };
        tc.addTest(testProductSimp0X, 51.0);

        //test with all numbers
        Test testProductSimpNum = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(12), new IntegerOperand(12)); 
                if (test.simplify().equals(new IntegerOperand(144))){
                    return TestResult.createPassedResult("testProductSimpNum: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testProductSimpNum");
                }
            }
        };
        tc.addTest(testProductSimpNum, 52.0);

        //test with a variable
        Test testProductSimpNorm = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(12), new VariableOperand("x")); 
                if (test.simplify().equals(new ProductExpression(new IntegerOperand(12), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testProductSimpNorm: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testProductSimpNorm");
                }
            }
        };
        tc.addTest(testProductSimpNorm, 53.0);

        //evaluate
        //evaluate with empty hashset
        Test testProductEvalEmpty = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(5), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                try {
                    test.evaluate(assignments);
                } catch (IllegalArgumentException e){
                    return TestResult.createPassedResult("testProductEvalEmpty: successfully caught IllegalArgumentException");
                }
                return TestResult.createFailedResult("testProductEvalEmpty");
            }
        };
        tc.addTest(testProductEvalEmpty, 77.0);

        //evaluate with non-empty hashset
        Test testProductEvalFull = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(5), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                assignments.put("x", -12);
                assignments.put("y", 5);
                if (test.evaluate(assignments) == -60){
                    return TestResult.createPassedResult("testProductEvalFull: returned correct value");
                } else {
                    return TestResult.createFailedResult("testProductEvalFull");
                }
            }
        };
        tc.addTest(testProductEvalFull, 78.0);

        //getVariables
        //test Expression w/variables
        Test testProductGetVariables = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new VariableOperand("x"), new VariableOperand("y"));
                Set<String> set = test.getVariables();
                if (set.contains("x") && set.contains("y")){
                    return TestResult.createPassedResult("testProductGetVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testProductGetVariables");
                }
            }
        };
        tc.addTest(testProductGetVariables, 66.0);

        //test Expression w/o variables
        Test testProductGetNoVariables = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(5), new IntegerOperand(0));
                Set<String> set = test.getVariables();
                if (set.isEmpty()){
                    return TestResult.createPassedResult("testProductGetNoVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testProductGetNoVariables");
                }
            }
        };
        tc.addTest(testProductGetNoVariables, 67.0);

        //equals
        //equals w/null obj
        Test testProductEqualsNull = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(null)){
                    return TestResult.createPassedResult("testProductEqualsNull: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testProductEqualsNull");
                }
            }
        };
        tc.addTest(testProductEqualsNull, 97.0);

        //equals w/wrong type
        Test testProductEqualsNoType = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new SumExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testProductEqualsNoType: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testProductEqualsNoType");
                }
            }
        };
        tc.addTest(testProductEqualsNoType, 98.0);

        //equals w/right type wrong value
        Test testProductEqualsNoValue = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new ProductExpression(new IntegerOperand(6), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testProductEqualsNoValue: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testProductEqualsNoValue");
                }
            }
        };
        tc.addTest(testProductEqualsNoValue, 99.0);

        //equals w/right type right value
        Test testProductEqualsValue = new Test() {
            @Override
            public TestResult runTest(){
                ProductExpression test = new ProductExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (test.equals(new ProductExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testProductEqualsValue: correctly returned true");
                } else {
                    return TestResult.createFailedResult("testProductEqualsValue");
                }
            }
        };
        tc.addTest(testProductEqualsValue, 100.0);


        //QUOTIENtEXPRESSION
        //getOperator
        Test testQuotientGetOperator = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.getOperator().equals("/")){
                    return TestResult.createPassedResult("testQuotientGetOperator: returns correct operator");
                } else {
                    return TestResult.createFailedResult("testQuotientGetOperator");
                }
            }
        };
        tc.addTest(testQuotientGetOperator, 9.0);

        //toPrefix
        Test testQuotientToPrefix = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPrefix().equals("/x 5")){
                    return TestResult.createPassedResult("testQuotientToPrefix: returns correct prefix notation");
                } else {
                    return TestResult.createFailedResult("testQuotientToPrefix");
                }
            }
        };
        tc.addTest(testQuotientToPrefix, 28.0);

        //toPostfix
        Test testQuotientToPostfix = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toPostfix().equals("x 5/")){
                    return TestResult.createPassedResult("testQuotientToPostfix: returns correct postfix notation");
                } else {
                    return TestResult.createFailedResult("testQuotientToPostfix");
                }
            }
        };
        tc.addTest(testQuotientToPostfix, 29.0);

        //toInfix
        Test testQuotientToInfix = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new IntegerOperand(5));
                if (test.toInfix().equals("(x / 5)")){
                    return TestResult.createPassedResult("testQuotientToInfix: returns correct infix notation");
                } else {
                    return TestResult.createFailedResult("testQuotientToInfix");
                }
            }
        };
        tc.addTest(testQuotientToInfix, 30.0);

        //simplify
        //test with X/1
        Test testQuotientSimpX1 = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new IntegerOperand(1));
                if (test.simplify().equals(new VariableOperand("x"))){
                    return TestResult.createPassedResult("testQuotientSimpX1: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testQuotientSimpX1");
                }
            }
        };
        tc.addTest(testQuotientSimpX1, 54.0);

        //test with X/X
        Test testQuotientSimpXX = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new VariableOperand("x"));
                if (test.simplify().equals(new IntegerOperand(1))){
                    return TestResult.createPassedResult("testQuotientSimpXX: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testQuotientSimpXX");
                }
            }
        };
        tc.addTest(testQuotientSimpXX, 55.0);

        //test with 0/X
        Test testQuotientSimp0X = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(0), new VariableOperand("x"));
                if (test.simplify().equals(new IntegerOperand(0))){
                    return TestResult.createPassedResult("testQuotientSimp0X: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testQuotientSimp0X");
                }
            }
        };
        tc.addTest(testQuotientSimp0X, 56.0);

        //test with X/0
        Test testQuotientSimpX0 = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new IntegerOperand(0));
                try {
                    test.simplify();
                } catch (ArithmeticException e){
                    return TestResult.createPassedResult("testQuotientSimpX0: correctly caught ArithmeticException");
                }
                return TestResult.createFailedResult("testQuotientSimpX0");
            }
        };
        tc.addTest(testQuotientSimpX0, 57.0);

        //test with all numbers
        Test testQuotientSimpNum = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(144), new IntegerOperand(12));
                if (test.simplify().equals(new IntegerOperand(12))){
                    return TestResult.createPassedResult("testQuotientSimpNum: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testQuotientSimpNum");
                }
            }
        };
        tc.addTest(testQuotientSimpNum, 57.1);

        //test with a variable
        Test testQuotientSimpNorm = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new IntegerOperand(12));
                if (test.simplify().equals(new QuotientExpression(new VariableOperand("x"), new IntegerOperand(12)))){
                    return TestResult.createPassedResult("testQuotientSimpNorm: correctly returned simplified Expression");
                } else {
                    return TestResult.createFailedResult("testQuotientSimpNorm");
                }
            }
        };
        tc.addTest(testQuotientSimpNorm, 57.2);

        //evaluate
        //evaluate with empty hashset
        Test testQuotientEvalEmpty = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(144), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                try {
                    test.evaluate(assignments);
                } catch (IllegalArgumentException e){
                    return TestResult.createPassedResult("testQuotientEvalEmpty: successfully caught IllegalArgumentException");
                }
                return TestResult.createFailedResult("testQuotientEvalEmpty");
            }
        };
        tc.addTest(testQuotientEvalEmpty, 79.0);

        //evaluate with non-empty hashset
        Test testQuotientEvalFull = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(144), new VariableOperand("x"));
                HashMap<String, Integer> assignments = new HashMap<String, Integer>();
                assignments.put("x", -12);
                assignments.put("y", 5);
                if (test.evaluate(assignments) == -12){
                    return TestResult.createPassedResult("testQuotientEvalFull: returned correct value");
                } else {
                    return TestResult.createFailedResult("testQuotientEvalFull");
                }
            }
        };
        tc.addTest(testQuotientEvalFull, 80.0);

        //getVariables
        //test Expression w/variables
        Test testQuotientGetVariables = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new VariableOperand("x"), new VariableOperand("y"));
                Set<String> set = test.getVariables();
                if (set.contains("x") && set.contains("y")){
                    return TestResult.createPassedResult("testQuotientGetVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testQuotientGetVariables");
                }
            }
        };
        tc.addTest(testQuotientGetVariables, 68.0);

        //test Expression w/o variables
        Test testQuotientGetNoVariables = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(144), new IntegerOperand(12));
                Set<String> set = test.getVariables();
                if (set.isEmpty()){
                    return TestResult.createPassedResult("testQuotientGetNoVariables: returns correct object with correct values");
                } else {
                    return TestResult.createFailedResult("testQuotientGetNoVariables");
                }
            }
        };
        tc.addTest(testQuotientGetNoVariables, 68.1);

        //equals
        //equals w/null obj
        Test testQuotientEqualsNull = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(null)){
                    return TestResult.createPassedResult("testQuotientEqualsNull: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testQuotientEqualsNull");
                }
            }
        };
        tc.addTest(testQuotientEqualsNull, 101.0);

        //equals w/wrong type
        Test testQuotientEqualsNoType = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new DifferenceExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testQuotientEqualsNoType: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testQuotientEqualsNoType");
                }
            }
        };
        tc.addTest(testQuotientEqualsNoType, 102.0);

        //equals w/right type wrong value
        Test testQuotientEqualsNoValue = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (!test.equals(new QuotientExpression(new IntegerOperand(6), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testQuotientEqualsNoValue: correctly returned false");
                } else {
                    return TestResult.createFailedResult("testQuotientEqualsNoValue");
                }
            }
        };
        tc.addTest(testQuotientEqualsNoValue, 103.0);

        //equals w/right type right value
        Test testQuotientEqualsValue = new Test() {
            @Override
            public TestResult runTest(){
                QuotientExpression test = new QuotientExpression(new IntegerOperand(5), new VariableOperand("x"));
                if (test.equals(new QuotientExpression(new IntegerOperand(5), new VariableOperand("x")))){
                    return TestResult.createPassedResult("testQuotientEqualsValue: correctly returned true");
                } else {
                    return TestResult.createFailedResult("testQuotientEqualsValue");
                }
            }
        };
        tc.addTest(testQuotientEqualsValue, 104.0);

        //Run Tests
        tc.runTests();

        //Create Report
        tc.createReport();
    }
}
