package unsw.dungeon;

/**
 * All entities that the player can pickup
 */
public abstract class PickUp extends Entity implements PlayerObserver{
    protected Dungeon dungeon;

    public PickUp(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public abstract void pickup(Player p);
 
    public void update (Player p){
        if (p.getX() == this.getX() && p.getY() == this.getY()){
            pickup(p);
        }
    }

    @Override
    public boolean isWalkAllowed(Moveable m){
        return true;
    }

}