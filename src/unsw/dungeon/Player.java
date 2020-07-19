package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Moveable {

    private Dungeon dungeon;
    List<PlayerObserver> observers = new ArrayList<>();

    private int oldX;
    private int oldY;


    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.oldX = x;
        this.oldY = y;
    }
    @Override
    public boolean moveUp() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() - 1)) {
            setPosition(getX(), getY() - 1);
            return true;
        }
        return false;
    }
    @Override
    public boolean moveDown() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() + 1)) {
            setPosition(getX(), getY() + 1);
            return true;
        }
        return false;
    }
    @Override
    public boolean moveLeft() {
        if (dungeon.checkIsWalkAllowed(this, getX() - 1, getY())) {
            setPosition(getX() - 1, getY());
            return true;
        }
        return false;
    }
    @Override
    public boolean moveRight() {
        if (dungeon.checkIsWalkAllowed(this, getX() + 1, getY())) {
            setPosition(getX() + 1, getY());
            return true;
        }
        return false;
    }

    /**
     * store the players previous move if position is changed
     */
    public void storeOldPosition(){
        if (oldX != getX() || oldY != getY()){
            oldX = getX();
            oldY = getY();
        }
    }

    @Override
    public void setPosition(int x, int y) {
        storeOldPosition();
        this.x().set(x);
        this.y().set(y);
        notifyObsevers();
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        // enemies can walk so they can kill
        return true;
    }

    @Override
    public boolean ableBoulder() {
        // can push boulders
        return true;
    }


    public int getOldX() {
        return this.oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return this.oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }


    /**
     * Player Subject methods in observer pattern
     * @param o
     */

    // https://www.tutorialspoint.com/design_pattern/observer_pattern.html
    /**
     * subscribe/attach observer to list of observers
     * @param o
     */
    public void attach(PlayerObserver o){
        if (!observers.contains(o)){
            observers.add(o);
        }
    }
    /**
     * remove an observer
     * @param o
     */
    public void detach(PlayerObserver o){
        observers.remove(o);
    }
    
    /**
     * for all observers observing player call their update function
     */
    public void notifyObsevers(){
        for (PlayerObserver observe : this.observers){
            observe.update(dungeon.getPlayer());
        }
    }

    @Override
    public boolean ableUnlockDoor() {
        // Players can unlock doors
        return true;
    }


}
