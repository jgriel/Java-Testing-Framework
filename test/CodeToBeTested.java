/**
 * This class represents a sample of a piece of code being tested.  
 * The code here does not do anything interesting.  The methods are
 * only intended to demonstrate various testing scenarios.
 */
import java.lang.Math;

public class CodeToBeTested {

    /**
     * Member variable for checking side effects
     */
    private boolean variableToChange = false;

    /**
     * Accessor for member variable
     * @return value of variable
     */
    public boolean getVariableToChange() {
        return variableToChange;
    }

    /**
     * Method for checking return values
     * @param input
     * @return
     */
    public int addOne(int input) {
        return input + 1;
    }

    /**
     * Method for seeing challenges with precision
     * @param d
     * @return
     */
    public double returnsHalf(double d) {
        return d / 2.0;
    }

    /**
     * Method for checking values of returned objects
     * @return
     */
    public CodeToBeTested returnsObject() {
        return new CodeToBeTested();
    }

    /**
     * Method for checking returned objects equals
     * @return
     */
    public String returnsString() {
        return Boolean.toString(variableToChange);
    }

    /**
     * Method to create a side effect
     */
    public void modifyVariable() {
        variableToChange = true;
    }

    /**
     * Method that might throws an exception
     * @param arg1
     * @throws IllegalArgumentException if input is negative
     * @return
     */
    public int methodThrowsException(int arg1) {
        if (arg1 < 0) {
            throw new IllegalArgumentException();
        } else {
            return (int)(arg1 * Math.PI);
        }
    }
}