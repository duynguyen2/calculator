package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class LeftParenthesisOperator extends Operator{

    @Override
    public int priority(){
        return -1;
    } //only need a value different from the other operators

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        return null;
    } //never executes
}
