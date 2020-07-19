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

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() - 1)) {
            setPosition(getX(), getY() - 1);
        }
    }

    public void moveDown() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() + 1)) {
            setPosition(getX(), getY() + 1);
        }
    }

    public void moveLeft() {
        if (dungeon.checkIsWalkAllowed(this, getX() - 1, getY())) {
            setPosition(getX() - 1, getY());
        }
    }

    public void moveRight() {
        if (dungeon.checkIsWalkAllowed(this, getX() + 1, getY())) {
            setPosition(getX() + 1, getY());
        }
    }

    @Override
    public void setPosition(int x, int y) {
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
        observers.add(o);
    }
    
    /**
     * for all observers observing player call their update function
     */
    public void notifyObsevers(){
        for (PlayerObserver observe : this.observers){
            observe.update(dungeon.getPlayer());
        }
    }


}
