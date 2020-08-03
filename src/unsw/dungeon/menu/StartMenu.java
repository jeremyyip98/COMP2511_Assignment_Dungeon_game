package unsw.dungeon.menu;

import unsw.dungeon.DungeonApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
            // System.out.println("Height is: " + window.heightProperty());
            // System.out.println("Width is: " + window.widthProperty());
            window.setHeight(615.0);
            window.setWidth(656.0);
            dungeonApplication.setCurrentScene("maze");
        });

        MenuButton btnSwordmanGame = new MenuButton("SWORDMAN GAME");
        btnSwordmanGame.setOnMouseClicked(event -> {
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
                scene = dungeonApplication.createGame("swordman");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            window.setScene(scene);
            // System.out.println("Height is: " + window.heightProperty());
            // System.out.println("Width is: " + window.widthProperty());
            window.setHeight(551.0);
            window.setWidth(592.0);
            dungeonApplication.setCurrentScene("swordman");
        });

        MenuButton btnHoundGame = new MenuButton("HOUND GAME");
        btnHoundGame.setOnMouseClicked(event -> {
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
                scene = dungeonApplication.createGame("hound");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            window.setScene(scene);
            // System.out.println("Height is: " + window.heightProperty());
            // System.out.println("Width is: " + window.widthProperty());
            window.setHeight(551.0);
            window.setWidth(592.0);
            dungeonApplication.setCurrentScene("hound");
        });

        MenuButton btnBatGame = new MenuButton("BAT GAME");
        btnBatGame.setOnMouseClicked(event -> {
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
                scene = dungeonApplication.createGame("bat");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            window.setScene(scene);
            // System.out.println("Height is: " + window.heightProperty());
            // System.out.println("Width is: " + window.widthProperty());
            window.setHeight(551.0);
            window.setWidth(592.0);
            dungeonApplication.setCurrentScene("bat");
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
            // System.out.println("Height is: " + window.heightProperty());
            // System.out.println("Width is: " + window.widthProperty());
            // window.setHeight(327.0);
            // window.setWidth(272.0);
            window.setHeight(551.0);
            window.setWidth(700.0);
            dungeonApplication.setCurrentScene("boulders");
        });

        

        MenuButton btnHowToPlay = new MenuButton("HOW TO PLAY");
        btnHowToPlay.setOnMouseClicked(event -> {
            dungeonApplication.ruleAppear();
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

        menu0.getChildren().addAll(btnStart, btnHowToPlay, btnOptions, btnExit);
        menu1.getChildren().addAll(btnBack, btnSound, btnVideo);
        menu2.getChildren().addAll(btnBack2, btnTurnOn, btnTurnOff);
        menu3.getChildren().addAll(btnBack3, btnSwordmanGame, btnHoundGame, btnBatGame, btnMazeGame, btnBouldersGame);

        getChildren().addAll(menu0);
    }
}

