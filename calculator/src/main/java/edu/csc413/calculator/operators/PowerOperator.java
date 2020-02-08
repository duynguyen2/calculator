package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator{
    private int priorityValue = 3;

    @Override
    public int priority(){
        return priorityValue;
    }

    @Override
    public Operand execute(Operand operator1, Operand operator2){
        int value1 = operator1.getValue(), value2 = operator2.getValue();
        int exponent = 1;
        for (int i = 0; i < value2; i++)
            exponent *= value1;

        Operand operand = new Operand(exponent);
        return operand;
    }
}
