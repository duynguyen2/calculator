package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class RightParanthesisOperator extends Operator{
    private int priorityValue = 0;

    @Override
    public int priority(){
        return priorityValue;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        return null;
    }
}
