package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class SubtractOperator extends Operator{
    private int priorityValue = 1;

    @Override
    public int priority(){
        return priorityValue;
    }

    @Override
    public Operand execute(Operand operator1, Operand operator2){
        int value1 = operator1.getValue(), value2 = operator2.getValue();
        Operand operand = new Operand(value1 - value2);
        return operand;
    }
}
