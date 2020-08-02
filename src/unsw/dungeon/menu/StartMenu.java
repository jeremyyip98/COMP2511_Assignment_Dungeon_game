package unsw.dungeon.menu;
import unsw.dungeon.DungeonApplication;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartMenu extends Parent {

    public StartMenu(Stage window, MediaPlayer mediaPlayer, DungeonApplication dungeonApplication) {
        // Let the distances between the elements be 10
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10);
        VBox menu2 = new VBox(10);
        VBox menu3 = new VBox(10);

        menu0.setTranslateX(100);
        menu0.setTranslateY(200);

        menu1.setTranslateX(100);
        menu1.setTranslateY(200);

        menu2.setTranslateX(100);
        menu2.setTranslateY(200);

        menu3.setTranslateX(100);
        menu3.setTranslateY(200);

        final int offset = 400;

        menu1.setTranslateX(offset);
        menu2.setTranslateX(offset);
        menu3.setTranslateX(offset);

        MenuButton btnStart = new MenuButton("NEW GAME");
        btnStart.setOnMouseClicked(event -> {
            getChildren().add(menu3);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu3);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        });

        MenuButton btnBack3 = new MenuButton("BACK");
        btnBack3.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
            tt.setToX(menu3.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu3.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu3);
            });
        });

        MenuButton btnMazeGame = new MenuButton("MAZE GAME");
        btnMazeGame.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
            tt.setToX(menu3.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu3.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu3);
            });
            
            Scene scene = null;

            try {
                scene = dungeonApplication.createGame("maze");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            window.setScene(scene);
            window.sizeToScene();
            dungeonApplication.setCurrentScene("maze");
        });

        MenuButton btnAdvancedGame = new MenuButton("ADVANCED GAME");
        btnAdvancedGame.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
            tt.setToX(menu3.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu3.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu3);
            });

            Scene scene = null;

            try {
                scene = dungeonApplication.createGame("advanced");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            window.setScene(scene);
            window.sizeToScene();
            dungeonApplication.setCurrentScene("advanced");
        });

        MenuButton btnBouldersGame = new MenuButton("BOULDERS GAME");
        btnBouldersGame.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
            tt.setToX(menu3.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu3.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu3);
            });
            
            Scene scene = null;

            try {
                scene = dungeonApplication.createGame("boulders");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            window.setScene(scene);
            window.sizeToScene();
            dungeonApplication.setCurrentScene("boulders");
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
                    
        MenuButton btnSound = new MenuButton("SOUND");
        btnSound.setOnMouseClicked(event -> {
            getChildren().add(menu2);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() - offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu1);
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

        MenuButton btnBack2 = new MenuButton("BACK");
        btnBack2.setOnMouseClicked(event -> {
            getChildren().add(menu1);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu2.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
        });
        MenuButton btnTurnOff = new MenuButton("TUFF OFF BGM");
        btnTurnOff.setOnMouseClicked(event -> {
            mediaPlayer.pause();
        });

        MenuButton btnTurnOn = new MenuButton("TUFF ON BGM");
        btnTurnOn.setOnMouseClicked(event -> {
            mediaPlayer.play();
        });

        MenuButton btnVideo = new MenuButton("VIDEO");

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu0.getChildren().addAll(btnStart, btnOptions, btnExit);
        menu1.getChildren().addAll(btnBack, btnSound, btnVideo);
        menu2.getChildren().addAll(btnBack2, btnTurnOn, btnTurnOff);
        menu3.getChildren().addAll(btnBack3, btnAdvancedGame, btnMazeGame, btnBouldersGame);

        getChildren().addAll(menu0);
    }
}

