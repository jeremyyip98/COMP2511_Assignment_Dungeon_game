package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0) {
            // Check if there is an entity on the next block
            if (!dungeon.searchEntity(this, getX(), getY() - 1)) {
                // Check if the next block a boulder or not
                if (dungeon.isBoulder(getX(), getY() - 1)) {
                    if (dungeon.boulderMoveUp(getX(), getY() - 1)) {
                        y().set(getY() - 1);
                    }
                } else {
                    y().set(getY() - 1);
                }
            }
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            if (!dungeon.searchEntity(this, getX(), getY() + 1)) {
                // Check if the next block a boulder or not
                if (dungeon.isBoulder(getX(), getY() + 1)) {
                    if (dungeon.boulderMoveDown(getX(), getY() + 1)) {
                        y().set(getY() + 1);
                    }
                } else {
                    y().set(getY() + 1);
                }
            }
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            if (!dungeon.searchEntity(this, getX() - 1, getY())) {
                // Check if the next block a boulder or not
                if (dungeon.isBoulder(getX() - 1, getY())) {
                    if (dungeon.boulderMoveLeft(getX() - 1, getY())) {
                        x().set(getX() - 1);
                    }
                } else {
                    x().set(getX() - 1);
                }
            }
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            if (!dungeon.searchEntity(this, getX() + 1, getY())) {
                // Check if the next block a boulder or not
                if (dungeon.isBoulder(getX() + 1, getY())) {
                    if (dungeon.boulderMoveRight(getX() + 1, getY())) {
                        x().set(getX() + 1);
                    }
                } else {
                    x().set(getX() + 1);
                } 
            }
        }

    }
}
