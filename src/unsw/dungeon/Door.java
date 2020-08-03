package unsw.dungeon;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity implements PlayerObserver{

    private int id;

    private BooleanProperty locked; // true if door is locked

    SimpleBooleanProperty t = new SimpleBooleanProperty(true);
    SimpleBooleanProperty f = new SimpleBooleanProperty(false);


    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.locked = t;
    }

    @Override
    public void update(Player p) {
        // if player does move onto door then it has opened
        if (p.getX() == this.getX() && p.getY() == this.getY()){
            this.locked.set(false);
            this.locked = f;
        }
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        if (this.locked == f) return true;
        return (m.ableUnlockDoor(this));
    }
 
    public int getId() {
        return this.id;
    }

	public BooleanProperty locked() {
		return locked;
	}
}
