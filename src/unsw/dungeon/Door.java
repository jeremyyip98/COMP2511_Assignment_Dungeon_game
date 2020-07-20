package unsw.dungeon;

public class Door extends Entity implements PlayerObserver{

    private int id;

    private boolean locked; // true if door is locked

    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.locked = true;
    }

    @Override
    public void update(Player p) {
        // if player does move onto door then it has opened
        if (p.getX() == this.getX() && p.getY() == this.getY()){
            this.locked = false;
        }
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        if (this.locked == false) return true;
        return ( m.ableUnlockDoor(this));
    }

    public int getId() {
        return this.id;
    }
}