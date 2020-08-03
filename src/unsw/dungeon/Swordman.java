package unsw.dungeon;

public class Swordman extends Enemy{

    private Dungeon dungeon;

    public Swordman(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public void update(Player p) {
        if (p.invincible.getValue() == true){
            setScared();
        } else {
            setAggressive();
        }
        checkDeath(p);
        move(p, this);
        // System.out.println("Player isInvincible: "+ !p.isInvincible());
        // System.out.println("Enemy x:" + this.getX());
        // System.out.println("Enemy y:" + this.getY());
        // System.out.println("Player x:" + p.getX());
        // System.out.println("Player y:" + p.getY());
    }
}
