package edu.school21.calc.presenter;

import edu.school21.calc.exceptions.CalculatorException;
import edu.school21.calc.model.CalcLibImpl;
import edu.school21.calc.model.CalcModelImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CalcPresenterImpl{

    private static CalcModelImpl calcModel;

    @Autowired
    public CalcPresenterImpl(@Qualifier("CalcModel") CalcModelImpl calcModel) {
        CalcPresenterImpl.calcModel = calcModel;
    }

    public static double evalWithExprAndValue(String message, double value) throws CalculatorException {
        calcModel.clear();
        calcModel.setExpression(message);
        calcModel.setVariable(value);
        return calcModel.eval();
    }
}
