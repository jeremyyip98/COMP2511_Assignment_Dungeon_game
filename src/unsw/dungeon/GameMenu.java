package unsw.dungeon;

import java.io.IOException;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenu extends Application {

    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        primaryStage.setTitle("Dungeon");

        Label label1 = new Label("Welcome to the Dungeon Game implemented by George and Jeremy!");
        Button button1 = new Button("Start Game");
        button1.setOnAction(e -> window.setScene(scene2));

        // First Layout
        VBox layout1  = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 600, 600);

        // Gaming Layout

        //JSONObject json = null;
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(json);

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        //Scene scene = new Scene(root);
        scene2 = new Scene(root);
        root.requestFocus();
        //primaryStage.setScene(scene2);
        //primaryStage.show();
        window.setScene(scene1);
        window.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }

}
