package edu.school21.calc.model;

public interface CalcModel {
    void setExpression(String message);
    void setVariable(double x);
    double eval() throws RuntimeException;
    void clear();
}
