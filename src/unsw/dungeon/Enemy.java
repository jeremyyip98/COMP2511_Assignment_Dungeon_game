package unsw.dungeon;

public class Enemy extends Entity implements Moveable, PlayerObserver{

    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        return true; // need to walk on to die
    }

    @Override
    public boolean ableBoulder() {
        // can't push boulder
        return false;
    }

    @Override
    public void moveUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveDown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPosition(int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Player p) {
        // this is where we can move the enemy
    }

}