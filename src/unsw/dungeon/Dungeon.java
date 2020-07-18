/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<Boulder> boulders;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.boulders = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Boulder) {
            boulders.add((Boulder) entity);
        }
    }

    // searchEntity for Player
    // Search if there is an entity on the given position
    // Return true if there is an entity exists, and that entity is a wall.
    // Otherwise return false.
    // Also print in terminal when the player reached the exit
    public boolean searchEntity(Player player, int x, int y) {
        boolean result = false;

        for (Entity e: entities) {
            if ((e.getX() == x) && (e.getY() == y)) {
                if (e instanceof Wall) {
                    result = true;
                    break;       
                } else if (e instanceof Exit) {
                    System.out.println("You have reached the exit!!!");
                }
            }
        }
        return result;
    }

    // searchEntity for Boulder
    // Search if there is an entity on the given position
    // Return true if there is an entity exists, and that entity is a wall or a boulder.
    // Otherwise return false.
    public boolean searchEntity(Boulder boulder, int x, int y) {
        boolean result = false;

        for (Entity e: entities) {
            if ((e.getX() == x) && (e.getY() == y)) {
                if ((e instanceof Wall) || (e instanceof Boulder) || (e instanceof Exit)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean checkIsWalkAllowed(Moveable m, int x, int y){
        if (x < 0 || x >= this.width || y < 0 || y >= height) return false;
        for (Entity e: entities) {
            if ((e.getX() == x) && (e.getY() == y)) {
                if (!e.isWalkAllowed(m)) return false;
            }
        }
        return true;
    }

}
