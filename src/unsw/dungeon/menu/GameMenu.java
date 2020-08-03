package unsw.dungeon.menu;
import unsw.dungeon.DungeonApplication;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

    // https://www.youtube.com/watch?v=aOcow70vqb4&t=715s
    public class GameMenu extends Parent {

        Scene startScene;

        public GameMenu(Stage window, MediaPlayer mediaPlayer, DungeonApplication dungeonApplication) {
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

            MenuButton btnRestart = new MenuButton("RESTART THE GAME");
            // when the mouse clicked, reset the scene
            btnRestart.setOnMouseClicked(event -> {
                dungeonApplication.gameMenuDisappear();
                dungeonApplication.restartGame();
            });

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                getChildren().add(menu2);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
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

            MenuButton btnBack2 = new MenuButton("BACK");
            btnBack2.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
                tt.setToX(menu2.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu2.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu2);
                });
            });

            MenuButton btnBackToMenu = new MenuButton("BACK TO MENU");
            btnBackToMenu.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
                tt.setToX(menu2.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu2.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu2);
                });
                window.setScene(startScene);
                window.setHeight(576.0 + 35);
                window.setWidth(1024.0 + 15);
                dungeonApplication.gameMenuDisappear();
            });

            MenuButton btnExitGame = new MenuButton("EXIT GAME");
            btnExitGame.setOnMouseClicked(event -> {
                System.exit(0);
            });


            MenuButton btnBack3 = new MenuButton("BACK");
            btnBack3.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
                tt.setToX(menu3.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu3.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu3);
                });
            });

            MenuButton btnSound = new MenuButton("SOUND");
            btnSound.setOnMouseClicked(event -> {
                getChildren().add(menu3);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu3);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });
            MenuButton btnVideo = new MenuButton("VIDEO");

            // MenuButton btnTurnOff = new MenuButton("TURN OFF BGM");
            // btnTurnOff.setOnMouseClicked(event -> {
            //     mediaPlayer.pause();
            // });
            // if (btnTurnOff.toString().equals("TURN OFF BGM")) {
            //     btnTurnOff.setOnMouseClicked(event -> {
            //         mediaPlayer.pause();
            //     });
            //     btnTurnOff.setText("TURN ON BGM");
            // } else {
            //     btnTurnOff.setOnMouseClicked(event -> {
            //         mediaPlayer.play();
            //     });
            //     btnTurnOff.setText("TURN OFF BGM");
            // }

            MenuButton btnTurnOff = new MenuButton("TUFF OFF BGM");
            btnTurnOff.setOnMouseClicked(event -> {
                mediaPlayer.pause();
            });

            MenuButton btnTurnOn = new MenuButton("TUFF ON BGM");
            btnTurnOn.setOnMouseClicked(event -> {
                mediaPlayer.play();
            });

            menu0.getChildren().addAll(btnResume, btnOptions, btnRestart, btnExit);
            menu1.getChildren().addAll(btnBack, btnSound, btnVideo);
            menu2.getChildren().addAll(btnBack2, btnBackToMenu, btnExitGame);
            menu3.getChildren().addAll(btnBack3, btnTurnOn, btnTurnOff);

            Rectangle background = new Rectangle(700, 700);
            background.setFill(Color.GREY);
            background.setOpacity(0.4);

            getChildren().addAll(background, menu0);
        }

        public void setScene1(Scene startScene) {
            this.startScene = startScene;
        }

    }

 