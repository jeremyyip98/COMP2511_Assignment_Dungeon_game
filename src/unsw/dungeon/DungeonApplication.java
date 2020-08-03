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
    private InventoryMenu inventoryMenu;

    Stage window;
    Scene startScene;
    String currentScene;
    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws IOException {
        music();
        window = primaryStage;
        Pane pane = new Pane();
        pane.setPrefSize(1024, 576);
        //window.setMaximized(true);

        InputStream is = Files.newInputStream(Paths.get("images/GameMenu.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        // Resize the image
        imgView.setFitWidth(1024);
        imgView.setFitHeight(576);
    
        gameMenu = new GameMenu(window, mediaPlayer, this);
        gameMenu.setVisible(false);

        startMenu = new StartMenu(window, mediaPlayer, this);
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

        // Initialise startScene
        startScene = new Scene(pane);

        gameMenu.setScene1(startScene);

        window.setScene(startScene);
        window.setTitle("Dungeon Puzzles");
        window.show();
    }
    public void music() {
        Media media = new Media(Paths.get("bgm/FateEXTELLA OST_Emiya.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    public Scene createGame(String map) throws IOException {
        DungeonControllerLoader dungeonLoader = null;
        switch (map){
            case "advanced":
                dungeonLoader = new DungeonControllerLoader("advanced.json");
                break;
            case "maze":
                dungeonLoader = new DungeonControllerLoader("maze.json");
                break;
            case "boulders":
                dungeonLoader = new DungeonControllerLoader("boulders.json");
                break;
        }
        
        DungeonController controller = dungeonLoader.loadController();
        controller.setDungeonApplication(this);

        inventoryMenu = new InventoryMenu(controller.getPlayer(), this);
        inventoryMenu.setVisible(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        Pane pane = new Pane();
        pane.getChildren().addAll(root, gameMenu, inventoryMenu);

        Scene scene = new Scene(pane);
        root.requestFocus();
        return scene;
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

    public boolean isInventoryMenuVisible() {
        return inventoryMenu.isVisible();
    }

    public void setInventoryhMenuVisible(Boolean result) {
        inventoryMenu.setVisible(result);
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

    public void inventoryMenuAppear() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), inventoryMenu);
        ft.setFromValue(0);
        ft.setToValue(1);

        inventoryMenu.setVisible(true);
        ft.play();
    }

    public void inventoryMenuDisappear() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), inventoryMenu);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(evt -> inventoryMenu.setVisible(false));
        ft.play();
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }

    public void restartGame() {
        Scene scene = null;
        try {
            if(currentScene != null) {
                scene = createGame(currentScene);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        window.setScene(scene);
        window.sizeToScene();
        switch (currentScene){
            case "advanced":
                window.setHeight(551.0);
                window.setWidth(592.0);
                break;
            case "maze":
                window.setHeight(615.0);
                window.setWidth(656.0);
                break;
            case "boulders":
                window.setHeight(327.0);
                window.setWidth(272.0);
                break;
        }
    }
    
}
 