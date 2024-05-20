package edu.school21.calc.view;

import edu.school21.calc.presenter.CalcPresenterImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

@Component
public class ViewJavaFXControllerImpl extends Application {

    // Graph

    @FXML
    private LineChart<Number, Number> graph;
    @FXML
    public TextField y_base;
    @FXML
    public TextField x_base;

    // Calc methods and numbers

    @FXML
    public TextFlow output;
    @FXML
    public TextField x_value;
    @FXML
    public Button division;
    @FXML
    public Button multiply;
    @FXML
    public Button minus;
    @FXML
    public Button plus;
    @FXML
    public MenuItem left_parenthesis;
    @FXML
    public MenuItem right_parenthesis;
    @FXML
    public Button clear;
    @FXML
    public Button eval;
    @FXML
    public Button zero;
    @FXML
    public Button one;
    @FXML
    public Button two;
    @FXML
    public Button three;
    @FXML
    public Button four;
    @FXML
    public Button five;
    @FXML
    public Button six;
    @FXML
    public Button seven;
    @FXML
    public Button eight;
    @FXML
    public Button nine;

    // Calc functions

    @FXML
    public Button ln;
    @FXML
    public Button log;
    @FXML
    public Button sqrt;
    @FXML
    public Button power;
    @FXML
    public Button mod;
    @FXML
    public MenuButton trigonometry;
    @FXML
    public MenuItem sin;
    @FXML
    public MenuItem cos;
    @FXML
    public MenuItem tan;
    @FXML
    public MenuItem asin;
    @FXML
    public MenuItem acos;
    @FXML
    public MenuItem atan;

    public NumberAxis xAxis;
    public NumberAxis yAxis;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/layout/calc_java.fxml"));
        Parent root = loader.load();
        ViewJavaFXControllerImpl controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void startJavaFX(String[] args) {
        launch(args);
    }

    @FXML
    private void initialize() {
        graph.setCreateSymbols(true);
        graph.setHorizontalGridLinesVisible(true);
        graph.setAnimated(false);
        graph.setLegendVisible(false);
        graph.getXAxis().setTickLabelsVisible(false);
        graph.getYAxis().setTickLabelsVisible(false);
        graph.setCreateSymbols(false);

        xAxis = (NumberAxis) graph.getXAxis();
        yAxis = (NumberAxis) graph.getYAxis();

        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
    }

    // Calc methods and numbers

    @FXML
    public void add_divison(ActionEvent actionEvent) {
        appendTextInTextFlow("/");
    }

    @FXML
    public void add_multiply(ActionEvent actionEvent) {
        appendTextInTextFlow("*");
    }

    @FXML
    public void add_minus(ActionEvent actionEvent) {
        appendTextInTextFlow("-");
    }

    @FXML
    public void add_plus(ActionEvent actionEvent) {
        appendTextInTextFlow("+");
    }

    @FXML
    public void add_nine(ActionEvent actionEvent) {
        appendTextInTextFlow("9");
    }

    @FXML
    public void add_three(ActionEvent actionEvent) {
        appendTextInTextFlow("3");
    }

    @FXML
    public void add_six(ActionEvent actionEvent) {
        appendTextInTextFlow("6");
    }

    @FXML
    public void add_point(ActionEvent actionEvent) { appendTextInTextFlow("."); }

    @FXML
    public void add_eight(ActionEvent actionEvent) {
        appendTextInTextFlow("8");
    }

    @FXML
    public void add_five(ActionEvent actionEvent) {
        appendTextInTextFlow("5");
    }

    @FXML
    public void add_two(ActionEvent actionEvent) {
        appendTextInTextFlow("2");
    }

    @FXML
    public void add_seven(ActionEvent actionEvent) {
        appendTextInTextFlow("7");
    }

    @FXML
    public void add_four(ActionEvent actionEvent) {
        appendTextInTextFlow("4");
    }

    @FXML
    public void add_one(ActionEvent actionEvent) {
        appendTextInTextFlow("1");
    }

    @FXML
    public void add_ln(ActionEvent actionEvent) {
        appendTextInTextFlow("ln");
    }

    @FXML
    public void add_log(ActionEvent actionEvent) {
        appendTextInTextFlow("log");
    }

    @FXML
    public void add_sqrt(ActionEvent actionEvent) {
        appendTextInTextFlow("sqrt");
    }

    @FXML
    public void add_pow(ActionEvent actionEvent) {
        appendTextInTextFlow("^");
    }

    @FXML
    public void add_left_parenthesis(ActionEvent actionEvent) { appendTextInTextFlow("("); }

    @FXML
    public void add_right_parenthesis(ActionEvent actionEvent) {
        appendTextInTextFlow(")");
    }

    @FXML
    public void add_mod(ActionEvent actionEvent) {
        appendTextInTextFlow("mod");
    }

    @FXML
    public void add_sin(ActionEvent actionEvent) { appendTextInTextFlow("sin"); }

    @FXML
    public void add_cos(ActionEvent actionEvent) { appendTextInTextFlow("cos"); }

    @FXML
    public void add_tan(ActionEvent actionEvent) { appendTextInTextFlow("tan"); }

    @FXML
    public void add_asin(ActionEvent actionEvent) { appendTextInTextFlow("asin"); }

    @FXML
    public void add_acos(ActionEvent actionEvent) { appendTextInTextFlow("acos"); }

    @FXML
    public void add_atan(ActionEvent actionEvent) { appendTextInTextFlow("atan"); }

    @FXML
    public void add_zero(ActionEvent actionEvent) { appendTextInTextFlow("0"); }

    @FXML
    public void add_x(ActionEvent actionEvent) { appendTextInTextFlow("x"); }

    @FXML
    public void eval(ActionEvent actionEvent) {
        double x = getXValueAsDouble();
        double result = CalcPresenterImpl.evalWithExprAndValue(
                getTextInTextFlow().toString(),
                x);
        draw();
        setTextInTextFlow(new StringBuilder(Double.toString(result)));
    }

    public StringBuilder getTextInTextFlow() {
        StringBuilder allText = new StringBuilder();

        for (Node node : output.getChildren()) {
            if (node instanceof Text) {
                Text textNode = (Text) node;
                allText.append(textNode.getText());
            }
        }
        return allText;
    }

    public void setTextInTextFlow(StringBuilder text) {
        Text newText = new Text(text.toString());
        output.getChildren().clear();
        output.getChildren().add(newText);
    }

    public void appendTextInTextFlow(String element) {
        setTextInTextFlow(getTextInTextFlow().append(element));
    }

    public void clean_field(ActionEvent actionEvent) {
        output.getChildren().clear();
    }

    public double getXValueAsDouble() {
        try {
            return Double.parseDouble(x_value.getText());
        } catch (NumberFormatException e) {
            return 0.f;
        }
    }

    public void draw() {
        graph.getData().clear();
        graph.getData().add(getDataForGraph());
    }

    public XYChart.Series<Number, Number> getDataForGraph() {

        double[] xBounds = parseBounds(x_base);
        double xLowerBound = xBounds[0];
        double xUpperBound = xBounds[1];

        double[] yBounds = parseBounds(y_base);
        double yLowerBound = yBounds[0];
        double yUpperBound = yBounds[1];

        double step = 0.5;

        xAxis.setLowerBound(xLowerBound);
        xAxis.setUpperBound(xUpperBound);
        yAxis.setLowerBound(yLowerBound);
        yAxis.setUpperBound(yUpperBound);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        String expression = getTextInTextFlow().toString();
        System.out.println(expression);

        DoubleStream.iterate(xLowerBound, x -> x + step)
                .filter(x -> x <= xUpperBound)
                .limit((long) ((xUpperBound - xLowerBound) / step) + 1)
                .forEach(x -> {
                    System.out.print(x + " : ");
                    double result = CalcPresenterImpl.evalWithExprAndValue(expression, x);
                    System.out.println(result);
                    series.getData().add(new XYChart.Data<>(x, result));
                });

        return series;
    }

    public double[] parseBounds(TextField base) {
        String input = base.getText();
        double[] bounds = new double[2];

        Pattern pattern = Pattern.compile("\\[(-?[0-9.]+):(-?[0-9.]+)]");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            bounds[0] = Double.parseDouble(matcher.group(1));
            bounds[1] = Double.parseDouble(matcher.group(2));
        } else {
            bounds[0] = -10;
            bounds[1] = 10;
        }

        return bounds;
    }
}
