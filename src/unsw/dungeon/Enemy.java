package unsw.dungeon;

public class Enemy extends Entity implements Moveable, PlayerObserver{

    private Dungeon dungeon;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
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
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() - 1)) {
            setPosition(getX(), getY() - 1);
        }
    }
    @Override
    public void moveDown() {
        if (dungeon.checkIsWalkAllowed(this, getX(), getY() + 1)) {
            setPosition(getX(), getY() + 1);
        }
    }
    @Override
    public void moveLeft() {
        if (dungeon.checkIsWalkAllowed(this, getX() - 1, getY())) {
            setPosition(getX() - 1, getY());
        }
    }
    @Override
    public void moveRight() {
        if (dungeon.checkIsWalkAllowed(this, getX() + 1, getY())) {
            setPosition(getX() + 1, getY());
        }
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