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
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
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
    }

    // Search if there is an entity on the given position
    // Return true if there is an entity exists, and that entity is a wall.
    // Otherwise return false.
    // Also print in terminal when the player reached the exit
    public boolean searchEntity(int x, int y) {

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
}
