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
        this.x().set(x);
        this.y().set(y);
    }

    @Override
    public void update(Player p) {

        // this is where we can move the enemy
        if (p.getX() > this.getX()) {
            if (right) {

            }
            this.moveRight();
        } else if (p.getX() < this.getX()) {
            this.moveLeft();
        } else if (p.getX() == this.getX()) {
            if (p.getY() > this.getY()) {
                this.moveDown();
            } else if (p.getY() < this.getY()) {
                this.moveUp();
            }
        }
    }
    public void move(String direction) {
        boolean up = dungeon.checkIsWalkAllowed(this, getX(), getY() - 1);
        boolean down = dungeon.checkIsWalkAllowed(this, getX(), getY() + 1);
        boolean left = dungeon.checkIsWalkAllowed(this, getX() - 1, getY());
        boolean right = dungeon.checkIsWalkAllowed(this, getX() + 1, getY());
        /*
        while(true) {
            if ()
            String possibleDirection = 
        }
        switch (direction) {
            case "Up":
                if (up) {
                    this.moveUp();
                } else 
                break;
            case "Down":
                break;
            case "Left":
                break;
            case "Right":
                break;
        }
        while(true){
            if (direction.equals("right")) {
                if (right) {
                    this.moveRight();
                }
            }
        }
        */
    }
}