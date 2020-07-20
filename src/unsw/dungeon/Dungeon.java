/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private int enemies = 0;

    private boolean exitComplete; // true if reached exit


    private int width, height;
    private List<Entity> entities;

    ArrayList<FloorSwitch> switchList = new ArrayList<>();


    private Player player;
    private Goal goal;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        //this.boulders = new ArrayList<>();
        this.player = null;
        this.goal = null;
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

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
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
            if (e == null) continue;
            if ((e.getX() == x) && (e.getY() == y)) {
                if (!e.isWalkAllowed(m)) return false;
            }
        }
        return true;
    }

    public void connectEntities(){
        CopyOnWriteArrayList<Portal> portalList = new CopyOnWriteArrayList<>(); // comodification prevention
        ArrayList<Door> doorList = new ArrayList<>();
        ArrayList<Key> keyList = new ArrayList<>();
 
        for (Entity e : entities){
            if (e instanceof PlayerObserver){
                player.attach((PlayerObserver) e);
            }
            if (e instanceof FloorSwitch){
                if (!switchList.contains(e))
                    switchList.add((FloorSwitch) e);
                this.switches++;
            }
            if (e instanceof Portal){
                portalList.add((Portal) e);
            }
            if (e instanceof Key){
                keyList.add((Key)e);
            }
            if (e instanceof Door){
                doorList.add((Door)e);
            }
            if (e instanceof Treasure){
                this.treasures++;
            }
            if (e instanceof Enemy){
                this.enemies++;
            }
        }

        for (Door d : doorList)
            for (Key k : keyList)
                if (d.getId() == k.getId()){
                    k.setPartner(d);
                    break;
                }

        //Iterator<Portal> iter = portalList.iterator();
        for (Portal p : portalList){
            for (Portal q : portalList){
                if(q == null) continue;
                if (!p.equals(q) && p.getId() == q.getId()){
                    p.setPartner(q);
                    q.setPartner(p);
                    portalList.remove(p); 
                    portalList.remove(q);                    
                    break;
                }
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
        return (activatedSwitches == switches);
    }

    public boolean isExitComplete() {
        return this.exitComplete;
    }

    public void setExitComplete() {
        this.exitComplete = true;
    }

    public int getTreasures() {
        return this.treasures;
    }
}
