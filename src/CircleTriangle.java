import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CircleTriangle extends Application {

    private Circle CircleFunc(int X, int Y) {
        Circle circle = new Circle(X, Y, 7, Color.RED);
        circle.setCursor(Cursor.HAND);

        circle.setOnMousePressed(mouseEvent -> circle.setCursor(Cursor.CLOSED_HAND));

        circle.setOnMouseReleased(mouseEvent -> circle.setCursor(Cursor.HAND));

        return circle;
    }

    private void CircleFunc(Circle circle1, Circle circle2, Circle circle3, Text angleNum1, Text angleNum2, Text angleNum3){

        EventHandler<MouseEvent> event = mouseEvent -> {

            double locX = mouseEvent.getSceneX() - 250;
            double locY = mouseEvent.getSceneY() - 250;
            //Find true degree through arccot - trigonometry
            double angle = Math.PI / 2 - Math.atan(locY / locX);
            double angle2 = Math.PI / 2 - Math.atan((circle2.getCenterY() - 250) / (circle2.getCenterX() - 250));
            double angle3 = Math.PI / 2 - Math.atan((circle3.getCenterY() - 250) / (circle3.getCenterX() - 250));
            //new coordinates calculated through trigonometry
            double newLocX = 150d * Math.sin(angle);
            double newLocY = 150d * Math.cos(angle);
            if (locX >= 0) {//if X axis is positive, use current value
                circle1.setCenterX(250 + newLocX);
                circle1.setCenterY(250 + newLocY);
            } else {//if X axis is negative, invert current value
                circle1.setCenterX(250 - newLocX);
                circle1.setCenterY(250 - newLocY);
            }
            double lengthA = lengthCalculation(circle2, circle3);
            double lengthB = lengthCalculation(circle1, circle3);
            double lengthC = lengthCalculation(circle1, circle2);

            double calculation1 = angleCalculation(lengthA, lengthB, lengthC);
            double calculation2 = angleCalculation(lengthB, lengthA, lengthC);
            double calculation3 = angleCalculation(lengthC, lengthB, lengthA);

            angleNum1.setText(Integer.toString((int)Math.round(calculation1)));
            translateText(angleNum1, angle, circle1.getCenterX() - 250 >= 0);
            angleNum2.setText(Integer.toString((int)Math.round(calculation2)));
            translateText(angleNum2, angle2, circle2.getCenterX() - 250 >= 0);
            angleNum3.setText(Integer.toString((int)Math.round(calculation3)));
            translateText(angleNum3, angle3, circle3.getCenterX() - 250 >= 0);

        };

        circle1.setOnMouseDragged(event);
    }

    private Line connection(Circle circle1, Circle circle2){

        Line line = new Line();

        line.startXProperty().bind(circle1.centerXProperty());
        line.startYProperty().bind(circle1.centerYProperty());

        line.endXProperty().bind(circle2.centerXProperty());
        line.endYProperty().bind(circle2.centerYProperty());

        return line;
    }

    private Text initText(Circle circle1, Circle circle2, Circle circle3){

        Text newText = new Text();

        newText.setTextAlignment(TextAlignment.JUSTIFY);

        double lengthA = lengthCalculation(circle2,circle3);
        double lengthB = lengthCalculation(circle1,circle3);
        double lengthC = lengthCalculation(circle1,circle2);

        double calculation1 = angleCalculation(lengthA, lengthB, lengthC);

        newText.setText(Integer.toString((int) calculation1));

        newText.xProperty().bind(circle1.centerXProperty());
        newText.yProperty().bind(circle1.centerYProperty());

        return newText;
    }

    private void translateText(Text text, double angle, boolean positive){
        double translateX = 20d * Math.sin(angle);
        double translateY = 20d * Math.cos(angle);
        if (positive) {
            text.setTranslateX(translateX - 10);
            text.setTranslateY(translateY + 5);
        } else {
            text.setTranslateX(-translateX - 10);
            text.setTranslateY(-translateY + 5);
        }
    }

    private double lengthCalculation(Circle point1, Circle point2){
        return Math.sqrt(
                Math.pow((point2.getCenterX() - point1.getCenterX()),2)
                +
                Math.pow((point2.getCenterY() - point1.getCenterY()),2)
                );
    }

    private double angleCalculation(double lengthA, double lengthB, double lengthC){
        return Math.toDegrees(Math.acos((lengthA*lengthA - lengthB*lengthB - lengthC*lengthC)/(-2 * lengthB * lengthC)));
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Circle");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);

        Canvas canvas = new Canvas(500,500);
        GraphicsContext bigCircle = canvas.getGraphicsContext2D();

        bigCircle.strokeOval(100,100,300,300);

        Circle circle1 = CircleFunc(100,250);
        Circle circle2 = CircleFunc(250,100);
        Circle circle3 = CircleFunc(400,250);

        Text angleNum1 = initText(circle1, circle2, circle3);
        Text angleNum2 = initText(circle2, circle1, circle3);
        Text angleNum3 = initText(circle3, circle1, circle2);
        angleNum1.setTranslateX(-30);
        angleNum1.setTranslateY(5);
        angleNum2.setTranslateX(-10);
        angleNum2.setTranslateY(-15);
        angleNum3.setTranslateX(10);
        angleNum3.setTranslateY(5);

        Line line1 = connection(circle1, circle2);
        Line line2 = connection(circle2, circle3);
        Line line3 = connection(circle3, circle1);

        CircleFunc(circle1, circle2, circle3, angleNum1, angleNum2, angleNum3);
        CircleFunc(circle2, circle1, circle3, angleNum2, angleNum1, angleNum3);
        CircleFunc(circle3, circle1, circle2, angleNum3, angleNum1, angleNum2);

        Group group = new Group();
        group.getChildren().add(canvas);
        group.getChildren().addAll(line1,line2,line3);
        group.getChildren().addAll(circle1,circle2,circle3);
        group.getChildren().addAll(angleNum1, angleNum2, angleNum3);

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

