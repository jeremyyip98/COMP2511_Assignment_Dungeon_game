package unsw.dungeon.menu;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import unsw.dungeon.*;

public class InventoryMenu extends Parent{

    private ImageView keyImageView;
    private ImageView potionImageView;
    private ImageView movementPotionImageView;
    private ImageView swordImageView;
    private ImageView treasureImageView;
    
    public InventoryMenu(Player player, DungeonApplication dungeonApplication) {
        keyImageView = new ImageView(new Image((new File("images/key.png")).toURI().toString()));
        potionImageView = new ImageView(new Image((new File("images/brilliant_blue_new.png")).toURI().toString()));
        movementPotionImageView = new ImageView(new Image((new File("images/bubbly.png")).toURI().toString()));
        swordImageView = new ImageView(new Image((new File("images/greatsword_1_new.png")).toURI().toString()));
        treasureImageView = new ImageView(new Image((new File("images/gold_pile.png")).toURI().toString()));

        // Let the distances between the elements be 10
        HBox menu0 = new HBox(35);
        HBox menu1 = new HBox(10);

        menu0.setTranslateX(0);
        menu0.setTranslateY(35);
        menu0.setStyle("-fx-background-color: white;");
        menu1.setStyle("-fx-background-color: white;");

        // menu1.setTranslateX(200);
        // menu1.setTranslateY(0);

        Label keyID = new Label("");
        Label swordSwings = new Label("");
        Label potionTicks = new Label("");
        Label movementPotionTicks = new Label("");
        Label numTreasures = new Label("");

        keyID.setTextFill(Color.BLACK);
        swordSwings.setTextFill(Color.BLACK);
        potionTicks.setTextFill(Color.BLACK);
        movementPotionTicks.setTextFill(Color.BLACK);
        numTreasures.setTextFill(Color.BLACK);

        keyID.textProperty().bind(player.getKeyIDProperty().asString());
        swordSwings.textProperty().bind(player.getSwordSwingsProperty().asString());
        potionTicks.textProperty().bind(player.getPotionTicksProperty().asString());
        movementPotionTicks.textProperty().bind(player.getMovementPotionTicksProperty().asString());
        numTreasures.textProperty().bind(player.getInvTreasureProperty().asString());

        menu0.getChildren().addAll(keyID, potionTicks, movementPotionTicks, swordSwings, numTreasures);
        menu1.getChildren().addAll(keyImageView, potionImageView, movementPotionImageView, swordImageView, treasureImageView);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(menu1, menu0); // hbox with button and text on top of image view

        //menu0.getChildren().addAll(keyImageView, potionImageView, swordImageView, treasureImageView);

        Rectangle background = new Rectangle(700, 700);
        background.setFill(Color.GREY);
        background.setOpacity(0.4);

        getChildren().addAll(background, stackPane);

    }
}