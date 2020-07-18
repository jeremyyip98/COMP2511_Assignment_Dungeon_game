package unsw.dungeon;

public class Boulder extends Entity implements Moveable{

    private Dungeon dungeon;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public boolean moveUp() {
        boolean result = false;
        if (getY() > 0) {
            if (!dungeon.searchEntity(this, getX(), getY() - 1)) {
                y().set(getY() - 1);
                result = true;
            }
        }
        return result;
    }

    public boolean moveDown() {
        boolean result = false;
        if (getY() < dungeon.getHeight() - 1) {
            if (!dungeon.searchEntity(this, getX(), getY() + 1)) {
                y().set(getY() + 1);
                result = true;
            }
        }
        return result;
    }

    public boolean moveLeft() {
        boolean result = false;
        if (getX() > 0) {
            if (!dungeon.searchEntity(this, getX() - 1, getY())) {
                x().set(getX() - 1);
                result = true;
            }
        }
        return result;
    }

    public boolean moveRight() {
        boolean result = false;
        if (getX() < dungeon.getWidth() - 1) {
            if (!dungeon.searchEntity(this, getX() + 1, getY())) {
                x().set(getX() + 1);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isWalkAllowed(Moveable m) {
        int newX = getX() - m.getX();
        int newY = getY() - m.getY();
        
        return dungeon.checkIsWalkAllowed(this, getX()+newX, getY()+newY) && m.ableBoulder();
    }

    @Override
    public void setPosition(int x, int y) {
        this.x().set(x);
        this.y().set(y);
    }

    @Override
    public boolean ableBoulder() {
        // boudlers can't push other boulders
        return false;
    }
}