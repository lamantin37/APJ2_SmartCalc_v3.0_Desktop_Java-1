package edu.school21.calc.config;

import edu.school21.calc.exceptions.CalculatorException;
import edu.school21.calc.model.CalcModelImpl;
import edu.school21.calc.presenter.CalcPresenterImpl;
import edu.school21.calc.view.ViewJavaFXControllerImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfig {
    @Bean
    @Qualifier("CalcModel")
    CalcModelImpl calcModel() {
        return new CalcModelImpl();
    }

    @Bean(name = "CalcPresenter")
    CalcPresenterImpl calcPresenter(@Qualifier("CalcModel") CalcModelImpl calcModel) {
        return new CalcPresenterImpl(calcModel);
    }

    @Bean
    CalculatorException calculatorException() {
        return new CalculatorException("Fatal error!");
    }
}

