package unsw.dungeon;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StartButton extends StackPane {
    private Text text;

    public StartButton(String name) {
        text = new Text(name);
        text.setFont(text.getFont().font(50));
        // Background text originally in white colour
        text.setFill(Color.WHITE);

        Rectangle background = new Rectangle(250, 60);
        background.setOpacity(0.6);
        // Background text change to black colour when we move our mouse to it
        background.setFill(Color.BLACK);
        background.setEffect(new GaussianBlur(3.5));

        // Align the play button to position center
        setAlignment(Pos.CENTER);
        setRotate(-0.5);
        getChildren().addAll(background, text);

        // If we move the mouse to the stack pane, it will shift the background and the text by 10 to the right
        // Also set the colour of it
        setOnMouseEntered(event -> {
            background.setTranslateX(10);
            text.setTranslateX(10);
            background.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
        });

        // If we move the mouse away from the stack pane
        setOnMouseExited(event -> {
            background.setTranslateX(0);
            text.setTranslateX(0);
            background.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
        });

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
}