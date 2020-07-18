package unsw.dungeon;

public class Enemy extends Entity {

    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        return false;
    }

}