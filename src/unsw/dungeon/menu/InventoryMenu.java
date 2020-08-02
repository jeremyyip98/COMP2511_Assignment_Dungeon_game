package unsw.dungeon.menu;

import java.io.File;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import unsw.dungeon.*;

public class InventoryMenu extends Parent{

    private ImageView keyImageView;
    private ImageView potionImageView;
    private ImageView swordImageView;
    private ImageView treasureImageView;
    
    public InventoryMenu(Player player, DungeonApplication dungeonApplication) {
        keyImageView = new ImageView(new Image((new File("images/key.png")).toURI().toString()));
        potionImageView = new ImageView(new Image((new File("images/brilliant_blue_new.png")).toURI().toString()));
        swordImageView = new ImageView(new Image((new File("images/greatsword_1_new.png")).toURI().toString()));
        treasureImageView = new ImageView(new Image((new File("images/gold_pile.png")).toURI().toString()));
        
        // Let the distances between the elements be 10
        HBox menu0 = new HBox(10);

        menu0.setTranslateX(100);
        menu0.setTranslateY(200);

        menu0.getChildren().addAll(keyImageView, potionImageView, swordImageView, treasureImageView);

        Rectangle background = new Rectangle(600, 600);
        background.setFill(Color.GREY);
        background.setOpacity(0.4);

        getChildren().addAll(menu0);
    }
}