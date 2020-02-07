package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class AddOperator {
    private int priorityValue = 1;

    public int priority(){
        return priorityValue;
    }

    public Operand execute(Operand operator1, Operand operator2){
        int value1 = operator1.getValue(), value2 = operator2.getValue();
        return (new Operand(value1 + value2));
    }
}
