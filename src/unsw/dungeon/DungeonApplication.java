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
    Scene scene1, scene2, scene3;
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

        // Initialise scene2
        newGame();

        // Pass the scenes to the menu
        startMenu.setScene2(scene2);
        gameMenu.setScene1(scene1);

        scene2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (!gameMenu.isVisible()) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);

                    gameMenu.setVisible(true);
                    ft.play();
                }
                else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }
        });

        window.setScene(scene1);
        window.setTitle("Dungeon Puzzles");
        window.show();
    }
    public void music() {
        System.out.println("no problem");
        Media media = new Media(Paths.get("bgm/FateEXTELLA OST_Emiya.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    private void newGame() throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        //Scene scene = new Scene(root);
        Pane pane2 = new Pane();
        pane2.getChildren().addAll(root, gameMenu);

        scene2 = new Scene(pane2);
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
