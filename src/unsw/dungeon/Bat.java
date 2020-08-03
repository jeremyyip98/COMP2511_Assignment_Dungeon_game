package unsw.dungeon;

public class Bat extends Enemy{

    private Dungeon dungeon;

    public Bat(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public boolean moveUp() {
        setPosition(getX(), getY() - 1);
        return true;
    }

    @Override
    public boolean moveDown() {
        setPosition(getX(), getY() + 1);
        return true;
    }

    @Override
    public boolean moveLeft() {
        setPosition(getX() - 1, getY());
        return true;
    }

    @Override
    public boolean moveRight() {
        setPosition(getX() + 1, getY());
        return true;
    }

    @Override
    public void update(Player p) {
        if (p.invincible.getValue() == true){
            setScared();
        } else {
            setAggressive();
        }
        checkDeath(p);
        batMove(p, this);
    }
}
