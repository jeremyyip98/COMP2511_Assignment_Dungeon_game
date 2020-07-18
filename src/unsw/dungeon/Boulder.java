package unsw.dungeon;

public class Boulder extends Entity {

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
}