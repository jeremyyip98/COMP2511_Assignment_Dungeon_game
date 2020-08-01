package unsw.dungeon;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DungeonApplication extends Application {

    private GameMenu gameMenu;
    private StartMenu startMenu;
    Stage window;
    Scene scene1, scene2;


    @Override
    public void start(Stage primaryStage) throws IOException {
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

        startMenu = new StartMenu();
        startMenu.setVisible(true);
        gameMenu = new GameMenu();
        gameMenu.setVisible(false);

        Label title = new Label("Dungeon Puzzles");
        title.setTranslateX(80);
        title.setTranslateY(60);
        title.setTextFill(Color.WHITE);
        title.setFont(title.getFont().font(80));
        
        Label reference = new Label("Welcome to the Dungeon Game implemented by George and Jeremy!");
        reference.setTranslateX(620);
        reference.setTranslateY(550);
        reference.setTextFill(Color.WHITE);

        pane.getChildren().addAll(imgView, gameMenu, startMenu, reference, title);

        Scene scene1 = new Scene(pane);
        
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        //Scene scene = new Scene(root);
        scene2 = new Scene(root);
        root.requestFocus();

        scene1.setOnKeyPressed(event -> {
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
        window.show();
    }

    // https://www.youtube.com/watch?v=aOcow70vqb4&t=715s
    private class GameMenu extends Parent {
        public GameMenu() {
            // Let the distances between the elements be 10
            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);

            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            final int offset = 400;

            menu1.setTranslateX(offset);

            MenuButton btnResume = new MenuButton("RESUME");
            btnResume.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(evt -> setVisible(false));
                ft.play();
            });


            MenuButton btnOptions = new MenuButton("OPTIONS");
            btnOptions.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnSound = new MenuButton("SOUND");
            MenuButton btnVideo = new MenuButton("VIDEO");

            menu0.getChildren().addAll(btnResume, btnOptions, btnExit);
            menu1.getChildren().addAll(btnBack, btnSound, btnVideo);

            Rectangle background = new Rectangle(600, 600);
            background.setFill(Color.GREY);
            background.setOpacity(0.4);

            getChildren().addAll(background, menu0);
        }
    }

    private class StartMenu extends Parent{
        public StartMenu() {
            // Let the distances between the elements be 10
            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);

            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            final int offset = 400;

            menu1.setTranslateX(offset);

            MenuButton btnStart = new MenuButton("PLAY");
            btnStart.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setFromValue(1);
                ft.setToValue(0);
                //ft.setOnFinished(evt -> setVisible(false));
                
                ft.play();
            });
            btnStart.setOnMouseClicked(event -> window.setScene(scene2));

            MenuButton btnOptions = new MenuButton("OPTIONS");
            btnOptions.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });
            
            MenuButton btnSound = new MenuButton("SOUND");
            MenuButton btnVideo = new MenuButton("VIDEO");

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            menu0.getChildren().addAll(btnStart, btnOptions, btnExit);
            menu1.getChildren().addAll(btnBack, btnSound, btnVideo);

            getChildren().addAll(menu0);
        }
    }

    private static class StartButton extends StackPane {
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

    // https://www.youtube.com/watch?v=aOcow70vqb4&t=715s
    private static class MenuButton extends StackPane {
        private Text text;

        public MenuButton(String name) {
            text = new Text(name);
                
            text.setFont(text.getFont().font(20));
            // Background text originally in white colour
            text.setFill(Color.WHITE);

            Rectangle background = new Rectangle(250, 30);
            background.setOpacity(0.6);
            // Background text change to black colour when we move our mouse to it
            background.setFill(Color.BLACK);
            background.setEffect(new GaussianBlur(3.5));

            // Align the buttons to position center-left
            setAlignment(Pos.CENTER_LEFT);
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

    public static void main(String[] args) {
        launch(args); 
    }

    public Boolean isGameMenuVisbile() {
        System.out.println(gameMenu.isVisible());
        return gameMenu.isVisible();
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public void setGameMenuVisible(Boolean result) {
        gameMenu.setVisible(result);
    }
}
