package unsw.dungeon;

public class EnemyScared extends Enemy{

    private Dungeon dungeon;

    public EnemyScared(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.dungeon = dungeon;
    }

    @Override
    public void update(Player p) {
        // this is where we can move the enemy
        if (p.getX() > this.getX()) {
            move("Left");
        } else if (p.getX() < this.getX()) {
            move("Right");
        } else if (p.getX() == this.getX()) {
            if (p.getY() > this.getY()) {
                move("Up");
            } else if (p.getY() < this.getY()) {
                move("Down");
            }
        }
    }
}