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
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
                double angle = Math.PI/2 - Math.atan(locY/locX);
                //new coordinates calculated through trigonometry
                double newLocX = 150d * Math.sin(angle);
                double newLocY = 150d * Math.cos(angle);
                if (locX > 0) {//if X axis is positive, use current value
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

    private Group angleShow(Circle circle1, Circle circle2, Circle circle3){
        Group groupMama = new Group();

        Text angleNum1 = new Text();
        Text angleNum2 = new Text();
        Text angleNum3 = new Text();

        double lengthA = lengthCalculation(circle2,circle3);
        double lengthB = lengthCalculation(circle1,circle3);
        double lengthC = lengthCalculation(circle1,circle2);

        double calculation1 = angleCalculation(lengthA, lengthB, lengthC);
        double calculation2 = angleCalculation(lengthB, lengthA, lengthC);
        double calculation3 = angleCalculation(lengthC, lengthB, lengthA);

        angleNum1.setText(Double.toString(calculation1));
        angleNum2.setText(Double.toString(calculation2));
        angleNum3.setText(Double.toString(calculation3));

        angleNum1.xProperty().bind(circle1.centerXProperty());
        angleNum1.yProperty().bind(circle1.centerYProperty());

        angleNum2.xProperty().bind(circle2.centerXProperty());
        angleNum2.yProperty().bind(circle2.centerYProperty());

        angleNum3.xProperty().bind(circle3.centerXProperty());
        angleNum3.yProperty().bind(circle3.centerYProperty());

        groupMama.getChildren().addAll(angleNum1,angleNum2,angleNum3);

        return groupMama;
    }

    private double lengthCalculation(Circle point1, Circle point2){
        double value = Math.sqrt(
                Math.pow((point2.getCenterX() - point1.getCenterX()),2)
                +
                Math.pow((point2.getCenterY() - point1.getCenterY()),2)
                );
        System.out.println(value);
        return value;
    }

    private double angleCalculation(double lengthA, double lengthB, double lengthC){
        double calculation = Math.toDegrees(Math.acos((lengthA*lengthA - lengthB*lengthB - lengthC*lengthC)/(-2 * lengthB * lengthC)));
        return calculation;
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
        Circle circle2 = CircleFunc(250,100);
        Circle circle3 = CircleFunc(400,250);


        Line line1 = connection(circle1, circle2);
        Line line2 = connection(circle2, circle3);
        Line line3 = connection(circle3, circle1);

        Group angle = angleShow(circle1, circle2, circle3);

        group.getChildren().add(canvas);

        group.getChildren().addAll(line1,line2,line3);

        group.getChildren().addAll(circle1,circle2,circle3);

        group.getChildren().add(angle);

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

