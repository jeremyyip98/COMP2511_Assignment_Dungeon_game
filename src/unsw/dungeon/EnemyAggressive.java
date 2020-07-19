package unsw.dungeon;

public class EnemyAggressive extends Enemy{

    private Dungeon dungeon;

    public EnemyAggressive(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.dungeon = dungeon;
    }

    @Override
    public void update(Player p) {
        // this is where we can move the enemy
        if (p.getX() > this.getX()) {
            move("Right");
        } else if (p.getX() < this.getX()) {
            move("Left");
        } else if (p.getX() == this.getX()) {
            if (p.getY() > this.getY()) {
                move("Down");
            } else if (p.getY() < this.getY()) {
                move("Up");
            }
        }
    }
}