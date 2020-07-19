package unsw.dungeon;

public class EnemyScared implements EnemyStrategy{

    @Override
    public void move(Player p, Enemy e) {
        // this is where we can move the enemy
        if (p.getX() > e.getX()) {
            e.moveLeft();
        } else if (p.getX() < e.getX()) {
            e.moveRight();
        } else if (p.getX() == e.getX()) {
            if (p.getY() > e.getY()) {
                e.moveUp();
            } else if (p.getY() < e.getY()) {
                e.moveDown();
            }
        }
    }



}