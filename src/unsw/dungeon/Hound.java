package unsw.dungeon;

public class Hound extends Enemy{

    private Dungeon dungeon;

    public Hound(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public void update(Player p) {
        if (p.invincible.getValue() == true){
            setScared();
        } else {
            setAggressive();
        }
        move(p, this);
        move(p, this);
        checkDeath(p);
    }
}
