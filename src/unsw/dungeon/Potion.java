package unsw.dungeon;

public class Potion extends PickUp {

    public Potion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void pickup(Player p) {
        p.setInvincible();
        dungeon.removeEntity(this);
    }
    
}