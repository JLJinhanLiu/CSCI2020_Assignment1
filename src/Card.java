import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Card extends Application{

    @Override
    public void start(Stage stage) {
        stage.setTitle("Assignment 1");
        stage.setWidth(300);
        stage.setHeight(150);

        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        //initialize randomizers
        int card2, card3, card1 = (int)(Math.random() * 54 + 1);

        //to make sure cards don't replicate, will keep on randomizing until they don't match
        do {
            card2 = (int)(Math.random() * 54 + 1);
        }while (card2 == card1);

        do {
            card3 = (int)(Math.random() * 54 + 1);
        }while (card3 == card2 || card3 == card1);

        //imageView objects are initialized when they're added to hbox
        hbox.getChildren().addAll(
                new ImageView(new Image("/Cards/" + card1 + ".png" )),
                new ImageView(new Image("/Cards/" + card2 + ".png" )),
                new ImageView(new Image("/Cards/" + card3 + ".png" ))
        );

        Scene scene = new Scene(hbox);
        stage.setScene(scene);
        stage.show();
    }



}