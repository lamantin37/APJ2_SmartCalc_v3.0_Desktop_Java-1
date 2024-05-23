package edu.school21.calc.view;

import edu.school21.calc.config.ApplicationConfig;
import edu.school21.calc.logging.LoggerController;
import edu.school21.calc.presenter.CalcPresenterImpl;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

@Component
public class ViewJavaFXControllerImpl extends Application {

    @FXML private LineChart<Number, Number> graph;
    @FXML private TextField y_base, x_base, x_value;
    @FXML private TextFlow output;
    @FXML private Button division, multiply, minus, plus, clear, eval, zero, one, two, three, four, five, six, seven, eight, nine, ln, log, sqrt, power, mod, x;
    @FXML private MenuButton trigonometry;
    @FXML private MenuItem left_parenthesis, right_parenthesis, sin, cos, tan, asin, acos, atan;

    private static String button_background_color;
    private static String vbox_background_color;
    private static String textfield_background_color;

    private NumberAxis xAxis, yAxis;

    private final Map<Button, String> buttonMappings = new HashMap<>();
    private final Map<MenuItem, String> menuItemMappings = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {
        LoggerController.info("Initialising JavaFX...");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layout/calc_java.fxml")));

        Scene scene = new Scene(root);
        LoggerController.info("Connecting styles...");
        try {
            LoggerController.info("Connecting css...");
            scene.getStylesheets().add(getClass().getResource("/layout/css/button_style.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/layout/css/vbox_style.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/layout/css/textfield_style.css").toExternalForm());
            LoggerController.info("Connected!");

            LoggerController.info("Connecting additional styles...");
            ObservableList<Node> allNodes = root.getChildrenUnmodifiable();
            LoggerController.info("Color: " + button_background_color);
            applyStyleToNodes(allNodes, Button.class, "-fx-background-color: " + button_background_color + ";");
            LoggerController.info("Additional color: " + button_background_color + " appended!");
            LoggerController.info("Color: " + vbox_background_color);
            applyStyleToNodes(allNodes, VBox.class, "-fx-background-color: " + vbox_background_color + ";");
            LoggerController.info("Additional color: " + vbox_background_color + " appended!");
            LoggerController.info("Color: " + textfield_background_color);
            applyStyleToNodes(allNodes, TextField.class, "-fx-background-color: " + textfield_background_color + ";");
            LoggerController.info("Additional color: " + textfield_background_color + " appended!");
            LoggerController.info("Connected!");

        } catch (Exception e) {
            LoggerController.error("Failed to connect!");
        }

        stage.setScene(scene);
        stage.setResizable(false);
        LoggerController.info("Initialised!");
        stage.show();
    }

    public void setAdditionalColors(String button, String vBox, String textField) {
        button_background_color = button;
        vbox_background_color = vBox;
        textfield_background_color = textField;
    }

    private void applyStyleToNodes(ObservableList<Node> nodes, Class<? extends Node> type, String style) {
        if (!style.isEmpty()) {
            for (Node node : nodes) {
                if (type.isInstance(node)) {
                    node.setStyle(node.getStyle() + style);
                }
            }
        }
    }

    public void startJavaFX(String[] args) {
        LoggerController.info("Starting application...");
        launch(args);
    }

    @FXML
    private void initialize() {
        setupGraph();
        initializeMappings();
        buttonMappings.forEach((button, symbol) -> button.setOnAction(this::handleButtonPress));
        menuItemMappings.forEach((item, symbol) -> item.setOnAction(this::handleMenuItemPress));
    }

    private void initializeMappings() {
        buttonMappings.put(division, "/");
        buttonMappings.put(multiply, "*");
        buttonMappings.put(minus, "-");
        buttonMappings.put(plus, "+");
        buttonMappings.put(zero, "0");
        buttonMappings.put(one, "1");
        buttonMappings.put(two, "2");
        buttonMappings.put(three, "3");
        buttonMappings.put(four, "4");
        buttonMappings.put(five, "5");
        buttonMappings.put(six, "6");
        buttonMappings.put(seven, "7");
        buttonMappings.put(eight, "8");
        buttonMappings.put(nine, "9");
        buttonMappings.put(ln, "ln");
        buttonMappings.put(log, "log");
        buttonMappings.put(sqrt, "sqrt");
        buttonMappings.put(power, "^");
        buttonMappings.put(mod, "mod");
        buttonMappings.put(clear, "");
        buttonMappings.put(eval, "eval");
        buttonMappings.put(x, "x");

        menuItemMappings.put(left_parenthesis, "(");
        menuItemMappings.put(right_parenthesis, ")");
        menuItemMappings.put(sin, "sin");
        menuItemMappings.put(cos, "cos");
        menuItemMappings.put(tan, "tan");
        menuItemMappings.put(asin, "asin");
        menuItemMappings.put(acos, "acos");
        menuItemMappings.put(atan, "atan");
    }

    private void setupGraph() {
        graph.setCreateSymbols(false);
        graph.setHorizontalGridLinesVisible(true);
        graph.setAnimated(false);
        graph.setLegendVisible(false);
        graph.getXAxis().setTickLabelsVisible(false);
        graph.getYAxis().setTickLabelsVisible(false);

        xAxis = (NumberAxis) graph.getXAxis();
        yAxis = (NumberAxis) graph.getYAxis();
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
    }

    private void handleButtonPress(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (buttonMappings.get(button).equals("eval")) {
            evaluateExpression();
        } else if (buttonMappings.get(button).isEmpty()) {
            output.getChildren().clear();
        } else {
            appendText(buttonMappings.get(button));
        }
    }

    private void handleMenuItemPress(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        appendText(menuItemMappings.get(item));
    }

    private void appendText(String text) {
        Text newText = new Text(getText() + text);
        output.getChildren().setAll(newText);
    }

    private String getText() {
        return output.getChildren().stream()
                .filter(node -> node instanceof Text)
                .map(node -> ((Text) node).getText())
                .reduce("", String::concat);
    }

    private void evaluateExpression() {
        double x = getXValue();
        double result = CalcPresenterImpl.evalWithExprAndValue(getText(), x);
        LoggerController.info("expression: " + getText());
        LoggerController.info("x: " + x);
        drawGraph();
        setText(Double.toString(result));
    }

    private double getXValue() {
        try {
            LoggerController.info("Parsing x value...");
            return Double.parseDouble(x_value.getText());
        } catch (NumberFormatException e) {
            LoggerController.warn("No x value found!");
            return 0.0;
        }
    }

    private void setText(String text) {
        output.getChildren().setAll(new Text(text));
    }

    private void drawGraph() {
        LoggerController.info("Clearing drawing field...");
        graph.getData().clear();
        LoggerController.info("Updating drawing field...");
        graph.getData().add(getGraphData());
        LoggerController.info("Updated!");
    }

    private XYChart.Series<Number, Number> getGraphData() {
        double[] xBounds = parseBounds(x_base);
        double[] yBounds = parseBounds(y_base);

        xAxis.setLowerBound(xBounds[0]);
        xAxis.setUpperBound(xBounds[1]);
        yAxis.setLowerBound(yBounds[0]);
        yAxis.setUpperBound(yBounds[1]);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        String expression = getText();
        LoggerController.info("Calculating values for graph...");
        DoubleStream.iterate(xBounds[0], x -> x + 0.07)
                .limit((long) ((xBounds[1] - xBounds[0]) / 0.07) + 1)
                .forEach(x -> {
                    double result = CalcPresenterImpl.evalWithExprAndValue(expression, x);
                    series.getData().add(new XYChart.Data<>(x, (result < yBounds[0] || result > yBounds[1]) ? null : result));
                });
        LoggerController.info("Calculated!");

        return series;
    }

    private double[] parseBounds(TextField base) {
        Matcher matcher = Pattern.compile("\\[(-?[0-9.]+):(-?[0-9.]+)]").matcher(base.getText());
        if (matcher.matches()) {
            LoggerController.info("Bounds found!");
            return new double[]{Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2))};
        } else {
            LoggerController.warn("No bounds found, using default.");
            return new double[]{-10, 10};
        }
    }

}
