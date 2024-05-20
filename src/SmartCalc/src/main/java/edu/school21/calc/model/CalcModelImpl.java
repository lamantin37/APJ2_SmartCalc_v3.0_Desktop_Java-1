package edu.school21.calc.model;

import com.sun.jna.Pointer;
import edu.school21.calc.exceptions.CalculatorException;
import org.springframework.stereotype.Component;

@Component
public class CalcModelImpl implements CalcModel{

    private static Pointer calculator;
    public CalcModelImpl() {
        calculator = CalcLibImpl.INSTANCE.Calculator_new();
    }

    @Override
    public void setExpression(String message) { CalcLibImpl.INSTANCE.Calculator_setExpression(calculator, message); }
    @Override
    public void setVariable(double x) { CalcLibImpl.INSTANCE.Calculator_setVariable(calculator, x); }
    @Override
    public double eval() throws CalculatorException { return CalcLibImpl.INSTANCE.Calculator_eval(calculator); }
    @Override
    public void clear() { CalcLibImpl.INSTANCE.Calculator_clear(calculator); }
}
