import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.swing.*;


public class CircleTriangle extends Application {

    private Circle CircleFunc(int X, int Y) {
        Circle circle = new Circle(X, Y, 7, Color.RED);
        circle.setCursor(Cursor.HAND);

        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                circle.setCursor(Cursor.CLOSED_HAND);
            }
        });

        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                double locX = mouseEvent.getSceneX() - 250;
                double locY = mouseEvent.getSceneY() - 250;
                //Find true degree through arccot - trigonometry
                double angle = Math.toDegrees(Math.PI/2 - Math.atan(locY/locX));
                //new coordinates calculated through trigonometry
                double newLocX = 150d * Math.sin(Math.toRadians(angle));
                double newLocY = 150d * Math.cos(Math.toRadians(angle));
                if (locX > 0) {//if X axis is positive, use current vaule
                    circle.setCenterX(250 + newLocX);
                    circle.setCenterY(250 + newLocY);
                }
                else{//if X axis is negative, invert current value
                    circle.setCenterX(250 - newLocX);
                    circle.setCenterY(250 - newLocY);
                }
            }
        });

        circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                circle.setCursor(Cursor.HAND);
            }
        });

        return circle;
    }

    private Line connection(Circle circle1, Circle circle2){

        Line line = new Line();

        line.startXProperty().bind(circle1.centerXProperty());
        line.startYProperty().bind(circle1.centerYProperty());

        line.endXProperty().bind(circle2.centerXProperty());
        line.endYProperty().bind(circle2.centerYProperty());

        return line;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Circle");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);

        HBox hBox = new HBox();
        Group group = new Group();

        hBox.setAlignment(Pos.CENTER);

        Canvas canvas = new Canvas(500,500);

        GraphicsContext bigCircle = canvas.getGraphicsContext2D();

        bigCircle.strokeOval(100,100,300,300);

        Circle circle1 = CircleFunc(100,250);
        Circle circle2 = CircleFunc(400,250);
        Circle circle3 = CircleFunc(250,100);

        Line line1 = connection(circle1, circle2);
        Line line2 = connection(circle2, circle3);
        Line line3 = connection(circle3, circle1);

        group.getChildren().add(canvas);

        group.getChildren().addAll(line1,line2,line3);

        group.getChildren().addAll(circle1,circle2,circle3);

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

