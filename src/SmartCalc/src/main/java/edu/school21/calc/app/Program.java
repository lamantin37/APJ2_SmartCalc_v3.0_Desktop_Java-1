package edu.school21.calc.app;

import edu.school21.calc.presenter.CalcPresenterImpl;
import edu.school21.calc.view.ViewJavaFXControllerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.calc");
        CalcPresenterImpl calcPresenter = context.getBean("CalcPresenter", CalcPresenterImpl.class);
        ViewJavaFXControllerImpl javaFXController = new ViewJavaFXControllerImpl();
        javaFXController.startJavaFX(args);
    }

}