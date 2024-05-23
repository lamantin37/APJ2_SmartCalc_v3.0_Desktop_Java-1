package edu.school21.calc.config;

import edu.school21.calc.exceptions.CalculatorException;
import edu.school21.calc.model.CalcModelImpl;
import edu.school21.calc.presenter.CalcPresenterImpl;
import edu.school21.calc.view.ViewJavaFXControllerImpl;
import edu.school21.calc.logging.LoggerController;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

    @Value("${vbox_background_color}")
    private String button_background_color;

    @Value("${vbox_background_color}")
    private String vbox_background_color;

    @Value("${textfield_background_color}")
    private String textfield_background_color;

    @Bean(name = "CalcModel")
    public CalcModelImpl calcModel() {
        return new CalcModelImpl();
    }

    @Bean(name = "CalcPresenter")
    public CalcPresenterImpl calcPresenter(@Qualifier("CalcModel") CalcModelImpl calcModel) {
        return new CalcPresenterImpl(calcModel);
    }

    @Bean(name = "CalcJavaFXView")
    public ViewJavaFXControllerImpl viewJavaFXController() {
        ViewJavaFXControllerImpl viewJavaFXController = new ViewJavaFXControllerImpl();
        System.out.println(button_background_color);
        System.out.println(vbox_background_color);
        System.out.println(textfield_background_color);
        viewJavaFXController.setAdditionalColors(button_background_color, vbox_background_color, textfield_background_color);
        return viewJavaFXController;
    }

    @Bean
    public CalculatorException calculatorException() {
        return new CalculatorException("Fatal error!");
    }

    @Bean
    public LoggerController loggerController() {
        return new LoggerController();
    }
}
