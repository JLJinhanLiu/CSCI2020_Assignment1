import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calculator");
        stage.setWidth(320);
        stage.setHeight(280);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        //initialize labels and fields
        Label amountLabel = new Label("Investment Amount: ");
        amountLabel.setMinWidth(130);
        TextField amountField = new TextField();
        Label yearsLabel = new Label("Years: ");
        TextField yearsField = new TextField();
        Label rateLabel = new Label("Annual Interest Rate:");
        TextField rateField = new TextField();
        Label valueLabel = new Label("Future Value:");
        TextField valueField = new TextField();
        valueField.setEditable(false);
        valueField.setStyle("-fx-background-color: GAINSBORO;");
        //add all fields and labels to gridpane
        gridPane.add(amountLabel, 0, 0);
        gridPane.add(yearsLabel, 0, 1);
        gridPane.add(rateLabel, 0, 2);
        gridPane.add(valueLabel, 0, 3);
        gridPane.add(amountField, 1, 0);
        gridPane.add(yearsField, 1, 1);
        gridPane.add(rateField, 1, 2);
        gridPane.add(valueField, 1, 3);

        Button button = new Button("Calculate");
        gridPane.add(button, 1,4);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //make calculation and print results in uneditable textfield
                valueField.setText(Double.toString((Float.parseFloat(amountField.getText())
                        * Math.pow((1f + Float.parseFloat(rateField.getText()) * 0.01f),
                        Float.parseFloat(yearsField.getText())))));
            }
        };
        button.setOnAction(event);


        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }


}
