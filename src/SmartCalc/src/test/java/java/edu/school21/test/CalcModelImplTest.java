package java.edu.school21.test;


import edu.school21.calc.presenter.CalcPresenterImpl;
import edu.school21.calc.view.ViewJavaFXControllerImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalcModelImplTest {

    private static CalcPresenterImpl calcPresenter;

    @BeforeAll
    static void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.calc");
        calcPresenter = context.getBean("CalcPresenter", CalcPresenterImpl.class);
    }

    @Test
    void CheckCalcWorksCorrectlyWithIntegerNumbers() {
        String expr = "20 + x";
        Assertions.assertEquals(50, CalcPresenterImpl.evalWithExprAndValue(expr, 30));
    }
}
