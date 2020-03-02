import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class CircleTriangle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        Canvas canvas = new Canvas(500,500);

        GraphicsContext circle = canvas.getGraphicsContext2D();

        circle.strokeOval(100,100,300,300);

        hBox.getChildren().add(canvas);

        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
