/** 
 * Expression.java
 * Class of classes that handles methods and hierchy of binary tree
 * Part of FINAL Homework 7 
 */
 
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/*
 *  Note: the indentation and placment of {'s and }'s is somewhat
 * of a mess in this file.  That is on purpose to give you (the student)
 * a chance to see what it's like working in code that has arbitrary
 * and inconsistent style.  I encourage you to clean it up for your 
 * submission (and sanity!).
 * 
 * /

/**
 * A class representing an abstract arithmetic expression
 */
public abstract class Expression
{
   /**
    * Creates a tree from an expression in postfix notation
    * @param postfix an array of Strings representing a postfix arithmetic expression
    * @return a new Expression that represents postfix
    */
   public static Expression expressionFromPostfix(String[] postfix)
   {
      if(postfix.length == 0){
         throw new IllegalArgumentException();
      }
      Stack<Expression> stack = new Stack<Expression>();
      for (String str : postfix){
         if(str.length() == 1 && Character.isDigit(str.charAt(0))){                                               
            stack.push(new IntegerOperand(Integer.parseInt(str)));
         }else if(str.length() > 1 && Character.isDigit(str.charAt(1))){
            stack.push(new IntegerOperand(Integer.parseInt(str)));
         }else if(Character.isLetter(str.charAt(0))){
            stack.push(new VariableOperand(str));
         }else if(str.equals("+")){
            Expression right = stack.pop();
            Expression left = stack.pop();
            stack.push(new SumExpression(left, right));
         }else if(str.equals("-")){
            Expression right = stack.pop();
            Expression left = stack.pop();
            stack.push(new DifferenceExpression(left, right));
         }else if(str.equals("*")){
            Expression right = stack.pop();
            Expression left = stack.pop();
            stack.push(new ProductExpression(left, right));
         }else if(str.equals("/")){
            Expression right = stack.pop();
            Expression left = stack.pop();
            stack.push(new QuotientExpression(left, right));
         }else if((postfix[0].length() == 1 && !Character.isDigit(postfix[0].charAt(0))) || postfix[0].length() > 1 && !Character.isDigit(postfix[0].charAt(1))){
            throw new IllegalArgumentException();
         }
      }
      return stack.pop();
   }

   /**
    * @return a String that represents this expression in prefix notation.
    */
   public abstract String toPrefix();

   /**
    * @return a String that represents this expression in infix notation.
    */  
   public abstract String toInfix();

   /**
    * @return a String that represents this expression in postfix notation.
    */  
   public abstract String toPostfix();

   /**
    * @return a String that represents the expression in infix notation
    */
   @Override
   public String toString()
   {
      return toInfix();
   }
   
   /**
    * @return a new Expression mathematically equivalent to this one, but simplified.
    */  
   public abstract Expression simplify();

   /**
    * Evaluates the expression given assignments of values to variables.
    * @param assignments a HashMap from Strings (variable names) to Integers (values).
    * @return the result of evaluating the expression with the given variable assignments
    */
   public abstract int evaluate(HashMap<String, Integer> assignments);

   /**
    * @return a Set of the variables contained in this expression
    */
   public abstract Set<String> getVariables();

   @Override
   public abstract boolean equals(Object obj);

   /**
    * Prints the expression as a tree in DOT format for visualization
    * @param filename the name of the output file
    */
   public void drawExpression(String filename) throws IOException
   {
      BufferedWriter bw = null;
      FileWriter fw = new FileWriter(filename);
      bw = new BufferedWriter(fw);
      
      bw.write("graph Expression {\n");
      
      drawExprHelper(bw);
      
      bw.write("}\n");
      
      bw.close();
      fw.close();     
   }

   /**
    * Recursively prints the vertices and edges of the expression tree for visualization
    * @param bw the BufferedWriter to write to
    */
   protected abstract void drawExprHelper(BufferedWriter bw) throws IOException;
}

/**
 * A class representing an abstract operand
 */
abstract class Operand extends Expression
{
}

/**
 * A class representing an expression containing only a single integer value
 */
class IntegerOperand extends Operand
{
   protected int operand;

   /**
    * Create the expression
    * @param operand the integer value this expression represents
    */
   public IntegerOperand(int operand)
   {
      this.operand = operand;
   }

   /**
    * @return a String that represents this expression in prefix notation.
    */   
   public String toPrefix()
   {
      return Integer.toString(this.operand);
   }

   /**
    * @return a String that represents this expression in postfix notation.
    */  
   public String toPostfix()
   {
      return Integer.toString(this.operand);
   }   

   /**
    * @return a String that represents the expression in infix notation
    */
   public String toInfix()
   {
      return Integer.toString(this.operand);      
   }

   /**
    * @return a new Expression mathematically equivalent to this one, but simplified.
    */  
   public Expression simplify()
   {
      return this;
   }   

   /**
    * Evaluates the expression given assignments of values to variables.
    * @param assignments a HashMap from Strings (variable names) to Integers (values).
    * @return the result of evaluating the expression with the given variable assignments
    */
   public int evaluate(HashMap<String, Integer> assignments)
   {
      return this.operand;
   }

   /**
    * @return a Set of the variables contained in this expression
    */
   public Set<String> getVariables()
   {
      return new TreeSet<>();
   }

   /**
    * @param obj and Object to compare to
    * @return true if obj is a logically equivalent Expression 
    */
   @Override
   public boolean equals(Object obj)
   {
      if  (!(obj instanceof IntegerOperand)){
			return false;
      }
      return ((IntegerOperand) obj).operand == this.operand;
   }   

   /**
    * Recursively prints the vertices and edges of the expression tree for visualization
    * @param bw the BufferedWriter to write to
    */
   protected void drawExprHelper(BufferedWriter bw) throws IOException
   {
      bw.write("\tnode"+hashCode()+"[label="+operand+"];\n");
   }
}

/**
 * A class representing an expression containing only a single variable
 */
class VariableOperand extends Operand
{
   protected String variable;

   /**
    * Create the expression
    * @param variable the variable name contained with this expression
    */
   public VariableOperand(String variable)
   {
      this.variable = variable;
   }

   /**
    * @return a String that represents this expression in prefix notation.
    */   
   public String toPrefix()
   {
      return this.variable;
   }

   /**
    * @return a String that represents this expression in postfix notation.
    */  
   public String toPostfix()
   {
      return this.variable;
   }   

   /**
    * @return a String that represents the expression in infix notation
    */
   public String toInfix()
   {
      return this.variable;      
   }

   /**
    * @return a new Expression mathematically equivalent to this one, but simplified.
    */  
   public Expression simplify()
   {
      return this;
   }   

   /**
    * Evaluates the expression given assignments of values to variables.
    * @param assignments a HashMap from Strings (variable names) to Integers (values).
    * @return the result of evaluating the expression with the given variable assignments
    */
   public int evaluate(HashMap<String, Integer> assignments)
   {
      if(assignments.isEmpty()){
         throw new IllegalArgumentException();
      } else {
      return assignments.get(this.variable);
      }
   }

   /**
    * @return a Set of the variables contained in this expression
    */
   public Set<String> getVariables()
   {
      TreeSet<String> vari = new TreeSet<String>();
      vari.add(this.variable);
      return vari;
   }

   /**
    * @param obj and Object to compare to
    * @return true if obj is a logically equivalent Expression 
    */
   @Override
   public boolean equals(Object obj)
   {
      if  (!(obj instanceof VariableOperand))
			return false;
      return this.variable.equals(((VariableOperand)obj).variable);
   }   

   /**
    * Recursively prints the vertices and edges of the expression tree for visualization
    * @param bw the BufferedWriter to write to
    */
   protected void drawExprHelper(BufferedWriter bw) throws IOException
   {
      bw.write("\tnode"+hashCode()+"[label="+variable+"];\n");
   }   
}

/**
 * A class representing an expression involving an operator
 */
abstract class OperatorExpression extends Expression
{
   protected Expression left;
   protected Expression right;

   /**
    * Create the expression
    * @param left the expression representing the left operand
    * @param right the expression representing the right operand
    */
   public OperatorExpression(Expression left, Expression right)
   {
      this.left = left;
      this.right = right;
   }

   /**
    * @return a string representing the operator
    */
   protected abstract String getOperator();     
   
   /**
    * Recursively prints the vertices and edges of the expression tree for visualization
    * @param bw the BufferedWriter to write to
    */
   protected void drawExprHelper(BufferedWriter bw) throws IOException
   {
      String rootID = "\tnode"+hashCode();
      bw.write(rootID+"[label=\""+getOperator()+"\"];\n");

      bw.write(rootID + " -- node" + left.hashCode() + ";\n");
      bw.write(rootID + " -- node" + right.hashCode() + ";\n");
      left.drawExprHelper(bw);
      right.drawExprHelper(bw);
   }   
   
   public TreeSet<String> variables(){
      TreeSet<String> variables = new TreeSet<String>();
      variables.addAll(this.left.getVariables());
      variables.addAll(this.right.getVariables());
      return variables;
   }

   public String opToPrefix(){
      return this.getOperator() + left.toPrefix() + " " + right.toPrefix();
   }

   public String opToInfix(){
      return "(" + left.toInfix() + " " + this.getOperator() + " " + right.toInfix() + ")";
   }

   public String opToPostfix(){
      return left.toPostfix() + " " + right.toPostfix() + this.getOperator();
   }
}

/**
 * A class representing an expression involving an sum
 */
class SumExpression extends OperatorExpression
{
   /**
    * Create the expression
    * @param left the expression representing the left operand
    * @param right the expression representing the right operand
    */
   public SumExpression(Expression left, Expression right)
   {
      super(left, right);
   }

   /**
    * @return a string representing the operand
    */
   protected String getOperator()
   {
      return "+";
   }
   
   /**
    * @return a String that represents this expression in prefix notation.
    */   
   public String toPrefix()
   {
      return opToPrefix();
   }

   /**
    * @return a String that represents this expression in postfix notation.
    */  
   public String toPostfix()
   {
      return opToPostfix();
   }   

   /**
    * @return a String that represents the expression in infix notation
    */
   public String toInfix()
   {
      return opToInfix();    
   }

   /**
    * @return a new Expression mathematically equivalent to this one, but simplified.
    */  
   public Expression simplify()
   {
      this.left = this.left.simplify();
      this.right = this.right.simplify();
      
      if (this.left instanceof IntegerOperand && ((IntegerOperand)this.left).operand == 0){
         return this.right;
      }
      if (this.right instanceof IntegerOperand && ((IntegerOperand)this.right).operand == 0){
         return this.left;
      }
      if (this.left instanceof IntegerOperand && this.right instanceof IntegerOperand){
         return new IntegerOperand(((IntegerOperand)this.left).operand + ((IntegerOperand)this.left).operand);
      }
      return this;
   }   

   /**
    * Evaluates the expression given assignments of values to variables.
    * @param assignments a HashMap from Strings (variable names) to Integers (values).
    * @return the result of evaluating the expression with the given variable assignments
    */
   public int evaluate(HashMap<String, Integer> assignments)
   {
      if((this.left instanceof VariableOperand || this.right instanceof VariableOperand) && assignments.isEmpty()){
         throw new IllegalArgumentException();
      } else {
         return this.left.evaluate(assignments) + this.right.evaluate(assignments);
      }
   }

   /**
    * @return a Set of the variables contained in this expression
    */
   public Set<String> getVariables()
   {
      return variables();
   }

   /**
    * @param obj and Object to compare to
    * @return true if obj is a logically equivalent Expression 
    */
   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof SumExpression))
         return false;
      boolean test1 = this.left.equals(((SumExpression)obj).left) && this.right.equals(((SumExpression)obj).right);
      Expression temp = null;
      this.left = temp;
      this.left = this.right;
      this.right = temp;
      boolean test2 = this.left.equals(((SumExpression)obj).left) && this.right.equals(((SumExpression)obj).right);
      temp = null;
      this.left = temp;
      this.left = this.right;             
      this.right = temp;
      return test1 || test2;
   }   
}

/**
 * A class representing an expression involving an differnce
 */
class DifferenceExpression extends OperatorExpression
{
   /**
    * Create the expression
    * @param left the expression representing the left operand
    * @param right the expression representing the right operand
    */
   public DifferenceExpression(Expression left, Expression right)
   {
      super(left, right);
   }

   /**
    * @return a string representing the operand
    */
   protected String getOperator()
   {
      return "-";
   }

   /**
    * @return a String that represents this expression in prefix notation.
    */   
   public String toPrefix()
   {
      return opToPrefix();
   }

   /**
    * @return a String that represents this expression in postfix notation.
    */  
   public String toPostfix()
   {
      return opToPostfix();
   }   

   /**
    * @return a String that represents the expression in infix notation
    */
   public String toInfix()
   {
      return opToInfix();   
   }

   /**
    * @return a new Expression mathematically equivalent to this one, but simplified.
    */  
   public Expression simplify()
   {
      this.left = this.left.simplify();
      this.right = this.right.simplify();

      if ((this.left instanceof VariableOperand && this.right instanceof IntegerOperand) && ((IntegerOperand)this.right).operand == 0){
         return this.left;
      }
      if ((this.left instanceof VariableOperand && this.right instanceof VariableOperand) && (((VariableOperand)this.left).variable.equals(((VariableOperand)this.right).variable))){
         return new IntegerOperand(0);
      }
      if (this.left instanceof IntegerOperand && this.right instanceof IntegerOperand){
         return new IntegerOperand(((IntegerOperand)this.left).operand - ((IntegerOperand)this.right).operand);
      }
      return this;
   }   

   /**
    * Evaluates the expression given assignments of values to variables.
    * @param assignments a HashMap from Strings (variable names) to Integers (values).
    * @return the result of evaluating the expression with the given variable assignments
    */
   public int evaluate(HashMap<String, Integer> assignments)
   {
      if((this.left instanceof VariableOperand || this.right instanceof VariableOperand) && assignments.isEmpty()){
         throw new IllegalArgumentException();
      } else {
         return this.left.evaluate(assignments) - this.right.evaluate(assignments);
      }
   }

   /**
    * @return a Set of the variables contained in this expression
    */
   public Set<String> getVariables()
   {
      return variables();
   }

   /**
    * @param obj and Object to compare to
    * @return true if obj is a logically equivalent Expression 
    */
   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof DifferenceExpression))
         return false;
       return this.left.equals(((DifferenceExpression)obj).left) && this.right.equals(((DifferenceExpression)obj).right);
   }      
}

/**
 * A class representing an expression involving a product
 */
class ProductExpression extends OperatorExpression
{
   /**
    * Create the expression
    * @param left the expression representing the left operand
    * @param right the expression representing the right operand
    */
   public ProductExpression(Expression left, Expression right)
   {
      super(left, right);
   }

   /**
    * @return a string representing the operand
    */
   protected String getOperator()
   {
      return "*";
   }

   /**
    * @return a String that represents this expression in prefix notation.
    */   
   public String toPrefix()
   {
      return opToPrefix();
   }

   /**
    * @return a String that represents this expression in postfix notation.
    */  
   public String toPostfix()
   {
      return opToPostfix();
   }   

   /**
    * @return a String that represents the expression in infix notation
    */
   public String toInfix()
   {
      return opToInfix();     
   }

   /**
    * @return a new Expression mathematically equivalent to this one, but simplified.
    */  
   public Expression simplify()
   {
      this.left = this.left.simplify();
      this.right = this.right.simplify();

      if (this.left instanceof VariableOperand && this.right instanceof IntegerOperand && ((IntegerOperand)this.right).operand == 1){
         return this.left;
      }
         if (this.right instanceof VariableOperand && this.left instanceof IntegerOperand && ((IntegerOperand)this.left).operand == 1){
         return this.right;
      } 
      if (this.right instanceof IntegerOperand && ((IntegerOperand)this.right).operand == 0){
         return new IntegerOperand(0);
      }
      if (this.left instanceof IntegerOperand && ((IntegerOperand)this.left).operand == 0){
         return new IntegerOperand(0);
      }
      if (this.left instanceof IntegerOperand && this.right instanceof IntegerOperand){
         return new IntegerOperand(((IntegerOperand)this.left).operand * ((IntegerOperand)this.right).operand);
      }
      return this;
   }   

   /**
    * Evaluates the expression given assignments of values to variables.
    * @param assignments a HashMap from Strings (variable names) to Integers (values).
    * @return the result of evaluating the expression with the given variable assignments
    */
   public int evaluate(HashMap<String, Integer> assignments){
      if((this.left instanceof VariableOperand || this.right instanceof VariableOperand) && assignments.isEmpty()){
         throw new IllegalArgumentException();
      } else {
      return this.left.evaluate(assignments) * this.right.evaluate(assignments);
      }
   }

   /**
    * @return a Set of the variables contained in this expression
    */
   public Set<String> getVariables()
   {
      return variables();
   }

   /**
    * @param obj and Object to compare to
    * @return true if obj is a logically equivalent Expression 
    */
   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof ProductExpression))
         return false;
      boolean test1 = this.left.equals(((ProductExpression)obj).left) && this.right.equals(((ProductExpression)obj).right);
      Expression temp = null;
      this.left = temp;
      this.left = this.right;
      this.right = temp;
      boolean test2 = this.left.equals(((ProductExpression)obj).left) && this.right.equals(((ProductExpression)obj).right);
      temp = null;
      this.left = temp;
      this.left = this.right;             
      this.right = temp;
      return test1 || test2;
   }      
}

/**
 * A class representing an expression involving a division
 */
class QuotientExpression extends OperatorExpression
{
   /**
    * Create the expression
    * @param left the expression representing the left operand
    * @param right the expression representing the right operand
    */
   public QuotientExpression(Expression left, Expression right)
   {
      super(left, right);
   }

   /**
    * @return a string representing the operand
    */
   protected String getOperator()
   {
      return "/";
   }

   /**
    * @return a String that represents this expression in prefix notation.
    */   
   public String toPrefix()
   {
      return opToPrefix();
   }

   /**
    * @return a String that represents this expression in postfix notation.
    */  
   public String toPostfix()
   {
      return opToPostfix();
   }   

   /**
    * @return a String that represents the expression in infix notation
    */
   public String toInfix()
   {
      return opToInfix();      
   }

   /**
    * @return a new Expression mathematically equivalent to this one, but simplified.
    */  
   public Expression simplify()
   {
      this.left = this.left.simplify();
      this.right = this.right.simplify();

      if ((this.right instanceof IntegerOperand && ((IntegerOperand)this.right).operand == 0)){
         throw new ArithmeticException();
      } 
      if ((this.left instanceof IntegerOperand && ((IntegerOperand)this.left).operand == 0)){
         return new IntegerOperand(0);
      } 
      if (this.left instanceof VariableOperand && (this.right instanceof IntegerOperand && ((IntegerOperand)this.right).operand == 1)){
         return this.left;
      }
      if(this.left instanceof VariableOperand && this.right instanceof VariableOperand){
         return new IntegerOperand(1);
      }
      if (this.left instanceof IntegerOperand && this.right instanceof IntegerOperand){
         return new IntegerOperand(((IntegerOperand)this.left).operand / ((IntegerOperand)this.right).operand);
      }
      return this;
   }   

   /**
    * Evaluates the expression given assignments of values to variables.
    * @param assignments a HashMap from Strings (variable names) to Integers (values).
    * @return the numerica result of evaluating the expression with the given variable assignments
    */
   public int evaluate(HashMap<String, Integer> assignments){
      if((this.left instanceof VariableOperand || this.right instanceof VariableOperand) && assignments.isEmpty()){
         throw new IllegalArgumentException();
      } else {
      return this.left.evaluate(assignments) / this.right.evaluate(assignments);
      }
   }

   /**
    * @return a Set of the variables contained in this expression
    */
   public Set<String> getVariables()
   {
      return variables();
   }

   /**
    * @param obj and Object to compare to
    * @return true if obj is a logically equivalent Expression 
    */
   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof QuotientExpression))
         return false;
       return this.left.equals(((QuotientExpression)obj).left) && this.right.equals(((QuotientExpression)obj).right);
   }  
}
