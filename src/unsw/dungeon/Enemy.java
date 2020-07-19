package unsw.dungeon;

public abstract class Enemy extends Entity implements Moveable, PlayerObserver{

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
        this.x().set(x);
        this.y().set(y);
    }

    @Override
    public abstract void update(Player p);

    public void move(String direction) {
        boolean up = dungeon.checkIsWalkAllowed(this, getX(), getY() - 1);
        boolean down = dungeon.checkIsWalkAllowed(this, getX(), getY() + 1);
        boolean left = dungeon.checkIsWalkAllowed(this, getX() - 1, getY());
        boolean right = dungeon.checkIsWalkAllowed(this, getX() + 1, getY());

        String possibleDirection = "";
        if (up) {
            possibleDirection = "Up";
        } else if (down) {
            possibleDirection = "Down";
        } else if (left) {
            possibleDirection = "Left";
        } else if (right) {
            possibleDirection = "Right";
        }
        switch (direction) {
            case "Up":
                if (up) {
                    this.moveUp();
                } else {
                    possibleDirection(possibleDirection);
                }
                break;
            case "Down":
                if (down) {
                    this.moveDown();
                } else {
                    possibleDirection(possibleDirection);
                }
                break;
            case "Left":
                if (left) {
                    this.moveLeft();
                } else {
                    possibleDirection(possibleDirection);
                }
                break;
            case "Right":
                if (right) {
                    this.moveRight();
                } else {
                    possibleDirection(possibleDirection);
                }
                break;
        }
    }

    public void possibleDirection(String possibleDirection) {
        switch (possibleDirection) {
            case "Up":
                this.moveUp();
                break;
            case "Down":
                this.moveDown();
                break;
            case "Left":
                this.moveLeft();
                break;
            case "Right":
                this.moveRight();
                break;
        }
    }
}