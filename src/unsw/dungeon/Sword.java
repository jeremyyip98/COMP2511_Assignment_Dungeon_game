package unsw.dungeon;

public class Sword extends PickUp {

    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public void pickup(Player p) {
        p.addSwordSwings();
        this.dungeon.removeEntity(this);
        this.x().set(0);
        this.y().set(0);
    }
    
}