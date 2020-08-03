package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Moveable {

    List<PlayerObserver> observers = new ArrayList<>();

    private int oldX;
    private int oldY;

    // Inventory
    private IntegerProperty invTreasure; // amount of treasure player is holding
    private IntegerProperty keyID; // if -1 then not holding a key
    private IntegerProperty swordSwings; // remaining swings on sword

    public boolean attacking; // boolean true if player is attacking
    public boolean invincible; // boolean true if potion is active
    public IntegerProperty potionTicks;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(int x, int y) {
        super(x, y);
        this.oldX = x;
        this.oldY = y;
        this.invTreasure = new SimpleIntegerProperty(0);
        this.attacking = false;
        this.invincible = false; // no potion active
        this.potionTicks = new SimpleIntegerProperty(0);
        this.keyID = new SimpleIntegerProperty(-1);
        this.swordSwings = new SimpleIntegerProperty(0);
    }

    @Override
    public boolean moveUp() {
        setPosition(getX(), getY() - 1);
        return true;
    }
    @Override
    public boolean moveDown() {
        setPosition(getX(), getY() + 1);
        return true;
    }
    @Override
    public boolean moveLeft() {
        setPosition(getX() - 1, getY());
        return true;
    }
    @Override
    public boolean moveRight() {
        setPosition(getX() + 1, getY());
        return true;
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
        this.checkPotionStatus(); // see if player is still invincible
        for (PlayerObserver observe : this.observers){
            observe.update(this);
        }
    }

    @Override
    public boolean ableUnlockDoor(Door door) {
        // Players can unlock doors
        return (door.getId() == this.keyID.get());
    }


    public void addTreasure(){
        this.invTreasure.set(this.invTreasure.get() + 1);;
    }

    public int getKeyID() {
        return this.keyID.get();
    }

    public void setKeyID(int keyID) {
        this.keyID.set(keyID);;
    }

    public boolean playerAttack(){
        if (this.getSwordSwings() > 0){
            this.attacking = true;
            useSwordSwing();
            notifyObsevers();
            this.attacking = false;
            return true;
        }
        return false;
    }


    public int getSwordSwings() {
        return this.swordSwings.get();
    }

    public void addSwordSwings() {
        // set swings to 5
        this.swordSwings.set(5);
    }

    public void useSwordSwing() {
        // consume a sword swing
        this.swordSwings.set(this.swordSwings.get() - 1);
    }


    public boolean isInvincible() {
        return this.invincible;
    }
    /**
     * player has consumed potion and is invincible for 10 ticks
     */
    public void setInvincible() {
        this.invincible = true;
        this.potionTicks.set(10);
    }

    public int getPotionTicks() {
        return this.potionTicks.get();
    }
    /**
     * 1 tick has elapsed
     */
    public void potionTick() {
        this.potionTicks.set(this.potionTicks.get() - 1);;
    }

    public void checkPotionStatus(){
        if (this.potionTicks.get() > 0){
            this.potionTick();
            this.invincible = true;
        } else {    
            this.invincible = false;
        }
        
    }

    public int getTreasure() {
        return this.invTreasure.get();
    }

	public IntegerProperty getInvTreasureProperty() {
		return invTreasure;
	}

	public IntegerProperty getSwordSwingsProperty() {
        return swordSwings;
    }

    public IntegerProperty getPotionTicksProperty() {
        return potionTicks;
    }

    public IntegerProperty getKeyIDProperty() {
        return keyID;
    }

}
