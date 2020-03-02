import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Histogram extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Histogram");
        stage.setWidth(600);
        stage.setHeight(500);

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setSpacing(-85);

        Canvas canvas = new Canvas(600, 500);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        //rectangle for "filepath" line
        graphicsContext.strokeRect(1, 400, 598, 50);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setFill(Color.BLACK);
        //baseline for histograph and index
        graphicsContext.strokeLine(20,350, 540, 350);
        for (int x = 0; x < 26; x++)//print index
            graphicsContext.fillText(Character.toString((char)(65 + x)), 20 * (x + 1) + 3 , 365);

        TextField directory = new TextField();
        directory.setMinWidth(400);

        Label label = new Label("Filename:");

        Button button = new Button("View");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    FileInputStream fileIO = new FileInputStream(directory.getText());
                    int inChar;
                    //create an int array that corresponds to all alphabet characters, then convert all found
                    //characters into upper case.
                    int[] alphabetArray = new int[26];
                    while ((inChar = fileIO.read()) != -1){
                        int converted = Character.toUpperCase((char) inChar) - 65;
                        if(converted >= 0 && converted <= 25)
                            alphabetArray[converted]++;
                    }
                    //chear all drawn graph
                    graphicsContext.clearRect(0,0, 540, 349);
                    drawGraph(graphicsContext, alphabetArray, findMax(alphabetArray));
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found.");
                    return;
                } catch (IOException ex) {
                    System.out.println("File not readable.");
                    return;
                }
            }
        };
        button.setOnAction(event);

        hBox.getChildren().addAll(label, directory, button);
        vBox.getChildren().addAll(canvas, hBox);
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    public void drawGraph(GraphicsContext graphicsContext, int[] array, int largest){
        //draws the baseline for the graph
        graphicsContext.beginPath();
        graphicsContext.moveTo(20, 350);

        for (int x = 0; x < 26; x++){
            //calculate the ratio between most frequent char and the current char in array
            //then draw the height
            graphicsContext.lineTo(20 * (x + 1), 350d - ((double)array[x] / (double)largest) * 300d );
            graphicsContext.lineTo(20 * (x + 1) + 15, 350d - ((double)array[x] / (double)largest) * 300d );
            graphicsContext.lineTo(20 * (x + 1) + 15, 350 );
            graphicsContext.lineTo(20 * (x + 1) + 20, 350 );
        }
        graphicsContext.setLineWidth(1);
        graphicsContext.stroke();
    }

    //Method for finding max number of recurrence for scaling purposes
    public int findMax(int[] array){
        int largest = 0;
        for(int x : array){
            if (x > largest)
                largest = x;
        }
        return largest;
    }

}
