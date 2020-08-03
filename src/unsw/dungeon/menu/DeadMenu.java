package unsw.dungeon.menu;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DeadMenu extends Parent {

    public DeadMenu(Stage window, DungeonApplication dungeonApplication, Dungeon dungeon) throws IOException{
        ImageView backgroundGoal = new ImageView(new Image(Files.newInputStream(Paths.get("images/background.jpg"))));
        backgroundGoal.setFitWidth(700);
        backgroundGoal.setFitHeight(700);
        
        // Let the distances between the elements be 10
        VBox menu0 = new VBox(10);

        menu0.setTranslateX(100);
        menu0.setTranslateY(100);

        Label title = new Label("YOU ARE DEAD");
        // title.setTranslateX(80);
        // title.setTranslateY(60);
        title.setTextFill(Color.WHITE);
        title.setFont(title.getFont().font(30));

        MenuButton btnBack = new MenuButton("BACK TO MENU");
        btnBack.setOnMouseClicked(event -> {
            dungeonApplication.deadAppear();
            dungeonApplication.returnToStartScene();
            window.setHeight(576.0 + 35);
            window.setWidth(1024.0 + 15);
            dungeonApplication.deadDisappear();
        });

        MenuButton btnRestart = new MenuButton("RESTART THE GAME");
        // when the mouse clicked, reset the scene
        btnRestart.setOnMouseClicked(event -> {
            dungeonApplication.deadDisappear();
            dungeonApplication.restartGame();
        });

        menu0.getChildren().addAll(title, btnBack, btnRestart);

        Rectangle background = new Rectangle(700, 700);
        background.setFill(Color.GREY);
        background.setOpacity(0.4);

        getChildren().addAll(backgroundGoal, background, menu0);
    }
}

