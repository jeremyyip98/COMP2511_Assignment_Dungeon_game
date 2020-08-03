package unsw.dungeon;

import unsw.dungeon.menu.*;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private DungeonApplication dungeonApplication;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
            
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            if (player.isMovement().getValue()) {
                if (dungeon.checkIsWalkAllowed(player, player.getX(), player.getY() - 1)) 
                player.moveUp();
                player.movementPotionTick();
            }
            if (dungeon.checkIsWalkAllowed(player, player.getX(), player.getY() - 1)) 
                player.moveUp();
            break;
        case DOWN:
            if (player.isMovement().getValue()) {
                if (dungeon.checkIsWalkAllowed(player, player.getX(), player.getY() + 1))
                player.moveDown();
                player.movementPotionTick();
            }
            if (dungeon.checkIsWalkAllowed(player, player.getX(), player.getY() + 1))
                player.moveDown();
            break;
        case LEFT:
            if (player.isMovement().getValue()) {
                if (dungeon.checkIsWalkAllowed(player, player.getX() - 1, player.getY()))
                player.moveLeft();
                player.movementPotionTick();
            }
            if (dungeon.checkIsWalkAllowed(player, player.getX() - 1, player.getY()))
                player.moveLeft();
            break;
        case RIGHT:
            if (player.isMovement().getValue()) {
                if (dungeon.checkIsWalkAllowed(player, player.getX() + 1, player.getY()))
                player.moveRight();
                player.movementPotionTick();
            }
            if (dungeon.checkIsWalkAllowed(player, player.getX() + 1, player.getY()))
                player.moveRight();
            break;
        case SPACE:
            player.playerAttack();
            break;
        case P:
            dungeonApplication.restartGame();
            break;
        case E:
            if (!dungeonApplication.isInventoryMenuVisible()) {
                dungeonApplication.inventoryMenuAppear();
            }
            else {
                dungeonApplication.inventoryMenuDisappear();
            }
            break;
        case ESCAPE:
            if (!dungeonApplication.isGameMenuVisible()) {
                dungeonApplication.gameMenuAppear();
            }
            else {
                dungeonApplication.gameMenuDisappear();
            }
            break;
        default:
            break;
        }
        checkGoal(player);
        checkDeath();
    }

    // For Testing purpose
    public void handleMovement(String move) {
        switch (move) {
        case "Up":
            if (dungeon.checkIsWalkAllowed(player, player.getX(), player.getY() - 1)) 
                player.moveUp();
            break;
        case "Down":
            if (dungeon.checkIsWalkAllowed(player, player.getX(), player.getY() + 1)) 
                player.moveDown();
            break;
        case "Left":
            if (dungeon.checkIsWalkAllowed(player, player.getX() - 1, player.getY())) 
                player.moveLeft();
            break;
        case "Right":
            if (dungeon.checkIsWalkAllowed(player, player.getX() + 1, player.getY())) 
                player.moveRight();
            break;
        case "Space":
            player.playerAttack();
            break;
        default:
            break;
        }
    }

    public void checkGoal(Player p){
        if (dungeon.goalComplete()){
            // we want to congrat the player !!!!
            dungeonApplication.completeAppear();
        }
    }

    public void checkDeath(){
        if (dungeon.getPlayer() == null){
            dungeonApplication.deadAppear();
        }
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Player getPlayer() {
        return player;
    }

    public void setDungeonApplication(DungeonApplication dungeonApplication) {
        this.dungeonApplication = dungeonApplication;
    }

}

