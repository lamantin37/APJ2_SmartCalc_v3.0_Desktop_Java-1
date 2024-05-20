package edu.school21.calc.model;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import edu.school21.calc.exceptions.CalculatorException;

public interface CalcLibImpl extends Library {
    CalcLibImpl INSTANCE = Native.load("smartcalcv3_core", CalcLibImpl.class);

    Pointer Calculator_new();
    void Calculator_setExpression(Pointer calculator, String str);
    void Calculator_setVariable(Pointer calculator, double var);
    void Calculator_clear(Pointer calculator);
    double Calculator_eval(Pointer calculator) throws CalculatorException;
}
