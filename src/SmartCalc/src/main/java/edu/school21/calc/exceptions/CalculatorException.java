package edu.school21.calc.exceptions;

import org.springframework.stereotype.Component;

@Component
public class CalculatorException extends RuntimeException {
    public CalculatorException(String message) {
        super(message);
    }
}
