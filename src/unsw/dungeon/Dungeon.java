/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

//import javafx.beans.property.IntegerProperty;

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

    private int activatedSwitches = 0;
    private int switches = 0;
    private int treasures = 0;

    private int width, height;
    private List<Entity> entities;

    ArrayList<FloorSwitch> switchList = new ArrayList<>();


    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        //this.boulders = new ArrayList<>();
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
        /**
        if (entity instanceof Boulder) {
            boulders.add((Boulder) entity);
        }*/
    }

    /**
     * checks if position is a legal move
     * - must be on non walkable tile
     * - must be bound to game dimensions
     * @param m
     * @param x
     * @param y
     * @return
     */
    public boolean checkIsWalkAllowed(Moveable m, int x, int y){
        if (x < 0 || x >= this.width || y < 0 || y >= height) return false;
        for (Entity e: entities) {
            if ((e.getX() == x) && (e.getY() == y)) {
                if (!e.isWalkAllowed(m)) return false;
            }
        }
        return true;
    }

    public void connectEntities(){
        for (Entity e : entities){
            if (e instanceof PlayerObserver){
                player.attach((PlayerObserver) e);
            }
            if (e instanceof FloorSwitch){
                if (!switchList.contains(e))
                    switchList.add((FloorSwitch) e);
                this.switches++;
            }
        }
    }

    public ArrayList<FloorSwitch> getSwitchList() {
        return this.switchList;
    }

    public void triggerSwitch(){
        activatedSwitches++;
    }
    public void untriggerSwitch(){
        activatedSwitches--;
    } 
    public boolean switchComplete(){
        System.out.println("A " + activatedSwitches + "B " + switches);
        return (activatedSwitches == switches);

    }

}
