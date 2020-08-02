package unsw.dungeon;
import unsw.dungeon.menu.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DungeonApplication extends Application {

    private GameMenu gameMenu;
    private StartMenu startMenu;

    Stage window;
    Scene scene1, advancedScene, mazeScene, bouldersScene;
    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws IOException {
        music();
        window = primaryStage;
        Pane pane = new Pane();
        pane.setPrefSize(1024, 576);

        InputStream is = Files.newInputStream(Paths.get("images/GameMenu.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        // Resize the image
        imgView.setFitWidth(1024);
        imgView.setFitHeight(576);
    
        gameMenu = new GameMenu(window, mediaPlayer);
        gameMenu.setVisible(false);

        startMenu = new StartMenu(window, mediaPlayer);
        startMenu.setVisible(true);

        Label title = new Label("Dungeon Puzzles");
        title.setTranslateX(80);
        title.setTranslateY(60);
        title.setTextFill(Color.WHITE);
        title.setFont(title.getFont().font(80));
        
        Label reference = new Label("Welcome to the Dungeon Game implemented by George and Jeremy!");
        reference.setTranslateX(620);
        reference.setTranslateY(550);
        reference.setTextFill(Color.WHITE);

        pane.getChildren().addAll(imgView, startMenu, reference, title);

        // Initialise scene1
        scene1 = new Scene(pane);

        // Initialise the scenes
        advancedGame();
        mazeGame();
        //bouldersGame();

        // Pass the scenes to the menu
        startMenu.setAdvancedScene(advancedScene);
        startMenu.setMazeScene(mazeScene);
        //startMenu.setBouldersScene(bouldersScene);
        gameMenu.setScene1(scene1);

        // advancedScene.setOnKeyPressed(event -> {
        //     if (event.getCode() == KeyCode.ESCAPE) {
        //         if (!gameMenu.isVisible()) {
        //             FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
        //             ft.setFromValue(0);
        //             ft.setToValue(1);

        //             gameMenu.setVisible(true);
        //             ft.play();
        //         }
        //         else {
        //             FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
        //             ft.setFromValue(1);
        //             ft.setToValue(0);
        //             ft.setOnFinished(evt -> gameMenu.setVisible(false));
        //             ft.play();
        //         }
        //     }
        // });

        window.setScene(scene1);
        window.setTitle("Dungeon Puzzles");
        window.show();
    }
    public void music() {
        Media media = new Media(Paths.get("bgm/FateEXTELLA OST_Emiya.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    private void advancedGame() throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");

        DungeonController controller = dungeonLoader.loadController();
        controller.setDungeonApplication(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        //Scene scene = new Scene(root);
        Pane pane = new Pane();
        pane.getChildren().addAll(root, gameMenu);

        advancedScene = new Scene(pane);
        root.requestFocus();
    }

    private void mazeGame() throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

        DungeonController controller = dungeonLoader.loadController();
        controller.setDungeonApplication(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        //Scene scene = new Scene(root);
        Pane pane = new Pane();
        pane.getChildren().addAll(root, gameMenu);

        mazeScene = new Scene(pane);
        root.requestFocus();
    }

    private void bouldersGame() throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");

        DungeonController controller = dungeonLoader.loadController();
        controller.setDungeonApplication(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        //Scene scene = new Scene(root);
        Pane pane = new Pane();
        pane.getChildren().addAll(root, gameMenu);

        bouldersScene = new Scene(pane);
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args); 
    }
    public boolean isGameMenuVisible() {
        return gameMenu.isVisible();
    }

    public void setGameMenuVisible(Boolean result) {
        gameMenu.setVisible(result);
    }

    public void gameMenuAppear() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
        ft.setFromValue(0);
        ft.setToValue(1);

        gameMenu.setVisible(true);
        ft.play();
    }

    public void gameMenuDisappear() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(evt -> gameMenu.setVisible(false));
        ft.play();
    }
}
