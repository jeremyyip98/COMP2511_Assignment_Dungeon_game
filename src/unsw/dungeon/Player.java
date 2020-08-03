package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Moveable {

    CopyOnWriteArrayList<PlayerObserver> observers = new CopyOnWriteArrayList<>();

    private int oldX;
    private int oldY;

    // Inventory
    private IntegerProperty invTreasure; // amount of treasure player is holding
    private IntegerProperty keyID; // if -1 then not holding a key
    private IntegerProperty swordSwings; // remaining swings on sword

    private Key key;

    public BooleanProperty attacking; // boolean true if player is attacking
    public BooleanProperty invincible; // boolean true if potion is active
    public BooleanProperty movement; // boolean true if movement potion is active
    public IntegerProperty potionTicks;
    public IntegerProperty movementPotionTicks;

    public boolean playerDeath;

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
        this.attacking = new SimpleBooleanProperty(false); 
        this.invincible = new SimpleBooleanProperty(false); // no potion active
        this.movement = new SimpleBooleanProperty(false); // no potion active
        this.potionTicks = new SimpleIntegerProperty(0);
        this.movementPotionTicks = new SimpleIntegerProperty(0);
        this.keyID = new SimpleIntegerProperty(-1);
        this.swordSwings = new SimpleIntegerProperty(0);
        this.playerDeath = false;
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
        this.checkPotionStatus(); // see if player is still have the potoin effects
        for (PlayerObserver observe : this.observers){
            observe.update(this);
        }
    }

    @Override
    public boolean ableUnlockDoor(Door door) {
        // Players can unlock doors
        if (this.key == null) return false;
        return (door.getId() == this.key.getId());
    }

    public void placeKey(){
        this.key.x().set(this.x().get());
        this.key.y().set(this.y().get()-1);
        key.setAble(false);
    }

    public Key getKey() {
        return this.key;
    }

    public void setKey(Key key) {
        this.key = key;
        this.keyID.set(key.getId());
    }

    public void addTreasure(){
        this.invTreasure.set(this.invTreasure.get() + 1);;
    }

    public int getKeyID() {
        return this.keyID.get();
    }

    public void setKeyID(int keyID) {
        this.keyID.set(keyID);
    }

    public boolean playerAttack(){
        if (this.getSwordSwings() > 0){
            this.attacking.set(true);
            //useSwordSwing(); only use a swing if player dies
            notifyObsevers();
            this.attacking.set(false);
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

    public BooleanProperty isInvincible() {
        return invincible;
    }

    public BooleanProperty isMovement() {
        return movement;
    }
    /**
     * player has consumed potion and is invincible for 10 ticks
     */
    public void setInvincible() {
        this.invincible.set(true);
        this.potionTicks.set(10);
    }

    /**
     * player has consumed potion and increase movement speed for 10 ticks
     */
    public void setMovement() {
        this.movement.set(true);
        this.movementPotionTicks.set(10);
    }

    public int getPotionTicks() {
        return this.potionTicks.get();
    }

    public int getMovementPotionTicks() {
        return this.movementPotionTicks.get();
    }
    /**
     * 1 tick has elapsed
     */
    public void potionTick() {
        this.potionTicks.set(this.potionTicks.get() - 1);;
    }

    public void movementPotionTick() {
        this.movementPotionTicks.set(this.movementPotionTicks.get() - 1);;
    }

    public void checkPotionStatus(){
        // Check the status of invincible potion
        if (this.potionTicks.get() > 0){
            this.potionTick();
            this.invincible.set(true);
        } else {
            this.invincible.set(false);
        }

        // Check the status of movement potion
        if (this.movementPotionTicks.get() > 0){
            //this.movementPotionTick();
            this.movement.set(true);
        } else {    
            this.movement.set(false);
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

    public IntegerProperty getMovementPotionTicksProperty() {
        return movementPotionTicks;
    }

    public IntegerProperty getKeyIDProperty() {
        return keyID;
    }

    public void playerIsDead() {
        this.playerDeath = true;
    }

    public boolean isPlayerDeath() {
        return playerDeath;
    }

}
