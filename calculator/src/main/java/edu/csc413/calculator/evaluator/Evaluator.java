package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.exceptions.InvalidTokenException;
import edu.csc413.calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer expressionTokenizer;
  private final String delimiters = " +/*-^()"; //included the parenthesis

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  // note that when we eval the expression 1 - 2 we will
  // push the 1 then the 2 and then do the subtraction operation
  // This means that the first number to be popped is the
  // second operand, not the first operand - see the following code
  private void process() { //method to execute, instead of repeating these lines
    Operator operatorFromStack = operatorStack.pop();
    Operand operandTwo = operandStack.pop(), operandOne = operandStack.pop();
    Operand result = operatorFromStack.execute(operandOne, operandTwo);
    operandStack.push(result);
  }

  public int evaluateExpression(String expression ) throws InvalidTokenException {
    String expressionToken;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.expressionTokenizer = new StringTokenizer( expression, this.delimiters, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators

    while (this.expressionTokenizer.hasMoreTokens()) {
      // filter out spaces
      if (!( expressionToken = this.expressionTokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if (Operand.check( expressionToken )) {//operand check
          operandStack.push( new Operand( expressionToken ));
        } else {
          if (!Operator.check( expressionToken)) {
            throw new InvalidTokenException(expressionToken);
          }

          else if (")".equals(expressionToken)) { //right parenthesis check
            while (operatorStack.peek().priority() != 0)  //processing until corresponding left parenthesis is found
              process();

            operatorStack.pop(); //discarding the left parenthesis
          }

          else if("(".equals(expressionToken)) //left parenthesis check
            operatorStack.push(new LeftParenthesisOperator()); //push a new left parenthesis operator to stack

          else { //new operator has to be a mathematical operator that's not parenthesis
            Operator newOperator = Operator.getOperator(expressionToken);

            if(!operatorStack.isEmpty()) { //checking if operator stack is empty
              while ((operatorStack.peek().priority() >= newOperator.priority()) && (!operatorStack.isEmpty()) && (operandStack.size() >= 2)) { //if the operatorStack item has higher or equal priority to the new operator or the operator stack is empty
                process();
                if(operatorStack.isEmpty())
                  break;

              } //end while process
            } //end not empty operator stack if
            operatorStack.push(newOperator);//pushing new operator
          } //end mathematical operator else
        } //end operand check else
      } //end space filter if
    } //end token while

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks,
    // that is, we should keep evaluating the operator stack until it is empty;

    while(!operatorStack.isEmpty()) { //runs until it is empty
      process(); //processes until the stack is empty
    }

    return operandStack.pop().getValue(); //return the value of the top of the operand stack
  }
}
