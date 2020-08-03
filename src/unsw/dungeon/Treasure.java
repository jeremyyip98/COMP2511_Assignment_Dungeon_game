package unsw.dungeon;

public class Treasure extends PickUp{

    public Treasure(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public void pickup(Player p) {
        p.addTreasure();
        p.detach(this);
        this.dungeon.removeEntity(this);
        this.x().set(0);
        this.y().set(0);
    }
    
}