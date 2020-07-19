package unsw.dungeon;

public class Door extends Entity implements PlayerObserver{

    private int id;
    private Dungeon dungeon;
    private boolean locked; // true if door is locked

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.dungeon = dungeon;
        this.locked = true;
    }

    @Override
    public void update(Player p) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        if (this.locked == false) return true;
        return dungeon.getPlayer().getKeyID() == this.id;
    }


    
}