package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class DivideOperator extends Operator{

    @Override
    public int priority(){
        return 2;
    }

    @Override
    public Operand execute(Operand operand1, Operand operand2){
        int value1 = operand1.getValue(), value2 = operand2.getValue();
        Operand operand = new Operand(value1 / value2);
        return operand;
    }
}
